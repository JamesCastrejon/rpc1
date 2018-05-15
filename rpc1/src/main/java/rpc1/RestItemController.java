package rpc1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class RestItemController {
	
	private List<Item> Items = new ArrayList<>();

	@RequestMapping(method=RequestMethod.POST)
	public void addItem(
			@RequestBody Item newP) {
		Items.add(newP);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void updateItem(
			@RequestBody Item p) {
		
		Item existing = retrieveItem(p.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing Item");
		}
		existing.setId(p.getId());
		existing.setName(p.getName());
		existing.setCost(p.getCost());
		existing.setDetails(p.getDetails());
		//existing.setCategory(p.getCategory());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public void deleteItem(
			@PathVariable int id) {
		Items.remove(id);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Item retrieveItem(
			@PathVariable int id) {
		Item existing = Items.stream()
				.filter(i -> i.getId() == id).findFirst().orElse(null);
		return existing;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Item> retrieveItems(
			@RequestParam(required=false) Boolean sort) {
		
		if(sort == null) {
			return Items;
		}
		
		return Items.stream()
			.sorted((l,r) -> {
						return (sort ?
								Long.compare(l.getId(), r.getId()) :
								Long.compare(r.getId(), l.getId()));
					})
			.collect(Collectors.toList());
	}
}






