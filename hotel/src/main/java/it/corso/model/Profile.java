package it.corso.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name= "profiles")
public class Profile implements Serializable {

	private static final long serialVersionUID = 7121183977250705644L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp= "[a-zA-Z0-9.]{1,255}", message= "error.charnotallowed")
	@Column(name= "username", length= 255, nullable= false)
	private String username;
	
	@Pattern(regexp= "[a-zA-Z0-9.]{1,255}", message= "error.charnotallowed")
	@Column(name= "password", length= 255, nullable= false)
	private String password;
	
	@OneToOne(mappedBy= "profile", cascade= CascadeType.REFRESH)
	private Customer customer;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
