package rpc1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	private String userName;
	
	private String password;
	
	@ElementCollection
	private List<String> roles = new ArrayList<>();
	
	@JsonView(value= {EntityJsonViews.Summary.class, EntityJsonViews.Details.class})
	private String email;
	
	public User(){
		
	}
	public int getId() {
		return userId;
	}
	public void setId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		if(userName == null) {
			throw new IllegalArgumentException("cannot be null");
		}
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public List getCart() {
//		return cart;
//	}
//	public void setCart(List cart) {
//		this.cart = cart;
//	}
//	public List getHistory() {
//		return history;
//	}
//	public void setHistory(List history) {
//		this.history = history;
//	}
	public void copy(User u) {
		this.userId = u.getId();
		this.userName = u.userName;
		this.password = u.password;
	}
}
