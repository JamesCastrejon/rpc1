package rpc1;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rpc1.repos.UserJpaRepository;

@RestController
@RequestMapping("/users")
public class RestUserController {

	@Autowired
	private UserJpaRepository userRepo;

	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public User addUser(
			@RequestBody User newU) {
		userRepo.saveAndFlush(newU);
		return retrieveUser(newU.getId());
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@Transactional
	public User updateUser(
			@RequestBody User u) {
		
		User existing = retrieveUser(u.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing User");
		}
		existing.copy(u);
		userRepo.saveAndFlush(existing);
		return retrieveUser(u.getId());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	@Transactional
	public User deleteUser(
			@PathVariable int id) {
		userRepo.deleteById(id);
		return retrieveUser(id);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	@Transactional
	public User retrieveUser(
			@PathVariable int id) {
		return userRepo.findById(id).orElse(null);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> retrieveUsers(
			@RequestParam(required=false) Boolean sort) {
		return userRepo.findAll();
	}
}
