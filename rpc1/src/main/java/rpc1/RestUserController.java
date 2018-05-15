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
	public void addUser(
			@RequestBody User newP) {
		Users.add(newP);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void updateUser(
			@RequestBody User p) {
		
		User existing = retrieveUser(p.getId());
		if(existing == null) {
			throw new IllegalArgumentException("No existing User");
		}
		existing.setId(p.getId());
		existing.setUserName(p.getUserName());
		existing.setPassword(p.getPassword());
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public void deleteUser(
			@PathVariable int id) {
		Users.remove(id);
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
