package rpc1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rpc1.repos.CategoryJpaRepository;
import rpc1.repos.ItemJpaRepository;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {
	
	@Autowired
	private ItemJpaRepository itemRepo;
	
	@Autowired
	private CategoryJpaRepository categoryRepo;
	
	@RequestMapping(path="",method=RequestMethod.POST)
	public void addCategory(
			@RequestBody Category newCategory) {
		categoryRepo.saveAndFlush(newCategory);
	}

	@RequestMapping(path="/{categoryId}/products",method=RequestMethod.POST)
	public void addProduct(
			@PathVariable(value="categoryId") Integer categoryId,
			@RequestBody Item newI) {
		
		if(newI == null || newI.getDetails() == null) {
			throw new IllegalArgumentException("cannot be null");
		}
		Category c = categoryRepo.findById(categoryId).orElse(null);
		c.getItems().add(newI);
		itemRepo.saveAndFlush(newI);
	}
	
	@RequestMapping(path="", method=RequestMethod.GET)
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}
	
//	@RequestMapping(path="/{categoryId}/products",method=RequestMethod.GET)
//	public List<Product> getProductsInCategory() {
//		
//	}
}
