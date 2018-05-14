package rpc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rpc1.repos.CategoryJpaRepository;

public class CategoryRestController {
	
	@Autowired
	private CategoryJpaRepository categoryRepo;
	
	@RequestMapping(path="", method=RequestMethod.POST)
	public void addCategory(){
		
	}
	
}
