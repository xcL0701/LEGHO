package main;

import java.util.Vector;

public class Users {

	private String email;
	private String password;
	private Vector<Lego> cart;
	private Vector<Integer> quantity;
	private boolean active;
	
	
	public Users(String email, String password) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
		cart = new Vector<Lego>();
		quantity = new Vector<Integer>();
		quantity.add(0);
		quantity.add(0);
		quantity.add(0);
		quantity.add(0);
		active = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Vector<Lego> getCart() {
		return cart;
	}

	public void setCart(Vector<Lego> cart) {
		this.cart = cart;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Vector<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(Vector<Integer> quantity) {
		this.quantity = quantity;
	}
	

}
