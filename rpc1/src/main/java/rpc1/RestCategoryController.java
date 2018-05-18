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

import rpc1.repos.CategoryJpaRepository;
import rpc1.repos.ItemJpaRepository;

@RestController
@RequestMapping("/categories")
public class RestCategoryController {
	
	@Autowired
	private ItemJpaRepository itemRepo;

	@Autowired
	private CategoryJpaRepository catRepo;

	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public Category addCategory(
			@RequestBody Category newC) {
		catRepo.saveAndFlush(newC);
		return retrieveCategory(newC.getId());
	}

	@RequestMapping(path="/{categoryId}/items",method=RequestMethod.POST)
	@Transactional
	public Category addItem(
			@PathVariable(value="categoryId") Integer categoryId,
			@RequestBody Item newI) {
		
		if(newI == null || newI.getDetails() == null) {
			throw new IllegalArgumentException("cannot be null");
		}
		Category c = catRepo.findById(categoryId).orElse(null);
		c.getItems().add(newI);
		newI.setCategory(c);
		itemRepo.saveAndFlush(newI);
		catRepo.saveAndFlush(c);
		return c;
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.PUT)
	@Transactional
	public Category updateCategory(
			@RequestBody Category c) {
		
		Category existing = retrieveCategory(c.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing Category");
		}
		existing.copy(c);
		catRepo.saveAndFlush(existing);
		return retrieveCategory(c.getId());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@Transactional
	public Category deleteCategory(
			@PathVariable int id) {
		catRepo.deleteById(id);
		return retrieveCategory(id);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	@Transactional
	public Category retrieveCategory(
			@PathVariable int id) {
		return catRepo.findById(id).orElse(null);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Category> retrieveCategorys(
			@RequestParam(required=false) Boolean sort) {
		return catRepo.findAll();
	}
}
