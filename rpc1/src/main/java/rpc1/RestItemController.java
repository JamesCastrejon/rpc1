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
	public Item addItem(
			@RequestBody Item newI) {
		Items.add(newI);
		return retrieveItem(newI.getId());
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Item updateItem(
			@RequestBody Item i) {
		
		Item existing = retrieveItem(i.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing Item");
		}
		existing.setId(i.getId());
		existing.setName(i.getName());
		existing.setCost(i.getCost());
		existing.setDetails(i.getDetails());
		//existing.setCategory(p.getCategory());
		return retrieveItem(i.getId());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public Item deleteItem(
			@PathVariable int id) {
		Items.remove(id);
		return retrieveItem(id);
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






