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
@RequestMapping("/users")
public class RestUserController {
	
	private List<User> Users = new ArrayList<>();

	@RequestMapping(method=RequestMethod.POST)
	public User addUser(
			@RequestBody User newU) {
		Users.add(newU);
		return retrieveUser(newU.getId());
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public User updateUser(
			@RequestBody User u) {
		
		User existing = retrieveUser(u.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing User");
		}
		existing.setId(u.getId());
		existing.setUserName(u.getUserName());
		existing.setPassword(u.getPassword());
		return retrieveUser(u.getId());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public User deleteUser(
			@PathVariable int id) {
		Users.remove(id);
		return retrieveUser(id);
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public User retrieveUser(
			@PathVariable int id) {
		User existing = Users.stream()
				.filter(i -> i.getId() == id).findFirst().orElse(null);
		return existing;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> retrieveUsers(
			@RequestParam(required=false) Boolean sort) {
		
		if(sort == null) {
			return Users;
		}
		
		return Users.stream()
			.sorted((l,r) -> {
						return (sort ?
								Long.compare(l.getId(), r.getId()) :
								Long.compare(r.getId(), l.getId()));
					})
			.collect(Collectors.toList());
	}
}
