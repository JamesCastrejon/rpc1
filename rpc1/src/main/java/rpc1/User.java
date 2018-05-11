package rpc1;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userName;
	private String password;
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
}
