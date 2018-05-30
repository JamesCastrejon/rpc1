package rpc1.mongo.core;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import rpc1.repos.MediaMongoRepository;

@RestController
@RequestMapping("/media")
public class RestMongoController {

	@Autowired
	MediaMongoRepository mediaRepo;

	@RequestMapping(method=RequestMethod.POST)
	public Media storeFile(
			@RequestBody Media m) {
        
		String file = new String(Base64.getEncoder().encode(
                m.getFilePath().getBytes(StandardCharsets.UTF_8)));
		m.setFilePath(file);
		
		Document doc = new Document().append("id", m.getId())
											.append("itemId", m.getItem_Id())
											.append("fileName", m.getFileName())
											.append("filePath", m.getFilePath());
		
        MongoClient mongo = new MongoClient();
        MongoDatabase db = mongo.getDatabase("my_db");
        MongoCollection<Document> collection = db.getCollection("test");

    	collection.insertOne(doc);
		
		mediaRepo.save(m);
		return retrieveMedia(m.getId()); // TODO: replace this with a findMedia by id or something
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Media updateFile(
			@RequestBody Media m) {
		
        Media existing = retrieveMedia(m.getId());
        if(existing == null) {
			throw new IllegalArgumentException("No existing Category");
		}
        
        String file = new String(Base64.getEncoder().encode(
                m.getFilePath().getBytes(StandardCharsets.UTF_8)));
		m.setFilePath(file);
        
        existing.copy(m);

		mediaRepo.save(existing);
		return mediaRepo.findById(existing.getId()).orElse(null);
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.DELETE)
	public Media deleteFile(
			@PathVariable(value="id") int id) {
		mediaRepo.deleteById(id);
		return mediaRepo.findById(id).orElse(null);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Media retrieveMedia(@PathVariable(value = "id") int id) {
		
        MongoClient mongo = new MongoClient();
        MongoDatabase db = mongo.getDatabase("my_db");
        MongoCollection<Document> collection = db.getCollection("test");
        
		Document query = new Document("id", id);
		Document cursor = collection.find(query).first();
		
		return mediaRepo.findById((int)cursor.getInteger("id")).orElse(null);
	}
	
	@RequestMapping(path = "/single/{id}", method = RequestMethod.GET)
	public Document retrieve(@PathVariable(value = "id") int id) {
        MongoClient mongo = new MongoClient();
        MongoDatabase db = mongo.getDatabase("my_db");
        MongoCollection<Document> collection = db.getCollection("test");
        
		Document query = new Document("id", id);
		Document cursor = collection.find(query).first();
		
		Media m = mediaRepo.findById((int)cursor.getInteger("id")).orElse(null);
		
		String file = new String(Base64.getDecoder().decode(m.getFilePath()), StandardCharsets.UTF_8);

		Document json = new Document("filePath", file);
		
		return json;
	}

}
