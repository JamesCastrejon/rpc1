package rpc1;

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

import com.fasterxml.jackson.annotation.JsonView;

import rpc1.repos.UserJpaRepository;

@RestController
@RequestMapping("/users")
public class RestUserController {

	@Autowired
	private UserJpaRepository userRepo;

	@RequestMapping(method=RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional
	public User addUser(
			@RequestBody User newU) {
		userRepo.saveAndFlush(newU);
		return retrieveUser(newU.getId());
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@PreAuthorize("hasAuthority('ADMIN')")
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
	@PreAuthorize("hasAuthority('ADMIN')")
	@Transactional
	public User deleteUser(
			@PathVariable int id) {
		userRepo.deleteById(id);
		return retrieveUser(id);
	}
	
	@RequestMapping(path="/{username}", method=RequestMethod.GET)
	@Transactional
	public User getUser(
			@PathVariable String username) {
		return userRepo.findByUserName(username).orElse(null);
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
