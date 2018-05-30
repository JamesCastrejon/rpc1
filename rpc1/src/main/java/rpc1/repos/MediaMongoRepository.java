package rpc1.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import rpc1.mongo.core.Media;

public interface MediaMongoRepository extends MongoRepository<Media, String> {

	public Optional<Media> findById(int id);
	public void deleteById(int id);
	
}
