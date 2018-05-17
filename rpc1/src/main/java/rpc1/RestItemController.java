package rpc1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rpc1.repos.ItemJpaRepository;

@RestController
@RequestMapping("/items")
public class RestItemController {

	@Autowired
	private ItemJpaRepository itemRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public Item addItem(
			@RequestBody Item newI) {
		itemRepo.saveAndFlush(newI);
		return retrieveItem(newI.getId());
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@Transactional
	public Item updateItem(
			@RequestBody Item i) {
		
		Item existing = itemRepo.findById(i.getId()).orElse(null);
		if(existing == null) {
			throw new IllegalArgumentException("No existing Category");
		}
		existing.copy(i);
		itemRepo.saveAndFlush(existing);
		return retrieveItem(i.getId());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@Transactional
	public Item deleteItem(
			@PathVariable(value="id") int id) {
		itemRepo.deleteById(id);
		return retrieveItem(id);
	}
	
	@RequestMapping(path="/searchByName/{name}", method=RequestMethod.GET)
	public List<Item> findItemByName(
			@PathVariable(value="name") String name) {
		List<Item> results = itemRepo.findByName(name);
		return results;
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	@Transactional
	public Item retrieveItem(
			@PathVariable(value="id") int id) {
		return itemRepo.findById(id).orElse(null);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Item> retrieveItems(
			@RequestParam(required=false) Boolean sort) {
		return itemRepo.findAll();
	}
}
