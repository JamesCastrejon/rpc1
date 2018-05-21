package rpc1;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rpc1.repos.ItemJpaRepository;
import rpc1.repos.UserJpaRepository;

@RestController // Now class is a controller for the restful rest service
@RequestMapping("/items") // Basically a url
public class MyRestController {

	@Autowired
	private UserJpaRepository userRepo;
	
	@Autowired
	private ItemJpaRepository itemRepo;
	
	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional
	public void addItem(
			@RequestBody Item newI){
		itemRepo.saveAndFlush(newI);
	}

	@RequestMapping(method=RequestMethod.PUT)
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional
	public void updateItem(
			@RequestBody Item i,
			Principal principal) {
		
		Item existing = itemRepo.findById(i.getId()).orElse(null);
		existing.copy(i);
		itemRepo.saveAndFlush(existing);
	}

	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional
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
	@Transactional
	public Item retrieveItem(
			@PathVariable int id){
		return itemRepo.findById(id).orElse(null);
	}

	@RequestMapping(method=RequestMethod.GET)
	public List<Item> retrieveItems(
			@RequestParam(required=false) Boolean sort){
		
		return itemRepo.findAll();
	}
}
