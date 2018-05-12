package rpc1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rpc1.repos.ItemJpaRepository;

@RestController // Now class is a controller for the restful rest service
@RequestMapping("/items") // Basically a url
public class MyRestController {

	@Autowired
	private ItemJpaRepository itemRepo;

	@RequestMapping(method=RequestMethod.POST)
	public void addItem(
			@RequestBody Item newI){
		itemRepo.saveAndFlush(newI);
	}

	@RequestMapping(method=RequestMethod.PUT)
	@Transactional
	public void updateItem(
			@RequestBody Item i) {
		
		Item existing = itemRepo.findById(i.getId()).orElse(null);
		existing.copy(i);
		itemRepo.saveAndFlush(existing);
	}

	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public void deleteItem(
			@PathVariable int id) {
		itemRepo.deleteById(id);
	}
	
	@RequestMapping(path="/searchByName/{name}", method=RequestMethod.GET)
	public List<Item> findItemByName(
			@PathVariable String name) {
		List<Item> results = itemRepo.findByNameLike(name);
		return results;
	}

	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Item retrieveItem(
			@PathVariable int id){
		Item results = itemRepo.findById(id).orElse(null);
		return results;
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Item> retrieveItems(
			@RequestParam(required=false) Boolean sort){
		
		return itemRepo.findAll();
	}
}
