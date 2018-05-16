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
@RequestMapping("/categories")
public class RestCategoryController {

	private List<Category> categories = new ArrayList<>();

	@RequestMapping(method=RequestMethod.POST)
	public Category addCategory(
			@RequestBody Category newC) {
		categories.add(newC);
		return retrieveCategory(newC.getId());
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public Category updateCategory(
			@RequestBody Category c) {
		
		Category existing = retrieveCategory(c.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing Category");
		}
		existing.setId(c.getId());
		existing.setName(c.getName());
		return retrieveCategory(c.getId());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public Category deleteCategory(
			@PathVariable int id) {
		categories.remove(id);
		return retrieveCategory(id);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public Category retrieveCategory(
			@PathVariable int id) {
		Category existing = categories.stream()
				.filter(c -> c.getId() == id).findFirst().orElse(null);
		return existing;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> retrieveCategorys(
			@RequestParam(required=false) Boolean sort) {
		
		if(sort == null) {
			return categories;
		}
		
		return categories.stream()
			.sorted((l,r) -> {
						return (sort ?
								Long.compare(l.getId(), r.getId()) :
								Long.compare(r.getId(), l.getId()));
					})
			.collect(Collectors.toList());
	}
}
