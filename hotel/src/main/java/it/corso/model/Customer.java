package it.corso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Entity
@Table(name= "customers")
@SecondaryTable(name= "addresses", pkJoinColumns= {@PrimaryKeyJoinColumn(name= "id")})
public class Customer implements Serializable {

	private static final long serialVersionUID = -4147178304067085504L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp= "[a-zA-Z\\s']{1,255}", message= "error.charnotallowed")
	@Column(name= "surname", length= 255, nullable= false)
	private String surname;
	
	@Pattern(regexp= "[a-zA-Z0-9\\s]{1,45}", message= "error.charnotallowed")
	@Column(name= "fiscal_code", length= 45, nullable= false)
	private String fiscalCode;

	@Valid
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name= "profile_id", referencedColumnName= "id")
	private Profile profile;
	
	@Valid
	@Embedded
	private Address address;
	
	@OneToMany(
		mappedBy= "customer",
		cascade= CascadeType.ALL,
		fetch= FetchType.EAGER,
		orphanRemoval= true
	)
	private List<Order> orders= new ArrayList<>();
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getFiscalCode() {
		return fiscalCode;
	}
	
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
