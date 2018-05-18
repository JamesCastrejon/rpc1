package rpc1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Id;

import org.apache.logging.log4j.util.Strings;

import com.fasterxml.jackson.annotation.JsonView;

public class User {
	@Id
	private int id;
	
	@JsonView(EntityJsonViews.Summary.class)
	private String userName;
	
	private String password;
	
	@JsonView(value= {EntityJsonViews.Summary.class, EntityJsonViews.Details.class})
	private String email;
	
	@JsonView(EntityJsonViews.Details.class)
	private boolean admin;
	
	@ElementCollection
	private List<String> roles = new ArrayList<>();
	
	private List cart = new ArrayList<Item>();
	private List history = new ArrayList<Item>();
	public User(){
		
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List getCart() {
		return cart;
	}
	public void setCart(List cart) {
		this.cart = cart;
	}
	public List getHistory() {
		return history;
	}
	public void setHistory(List history) {
		this.history = history;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
