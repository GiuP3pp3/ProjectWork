package it.corso.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

@Embeddable
public class Address {
	
	@Pattern(regexp= "[a-zA-Z\\s'.]{1,255}", message= "error.charnotallowed")
	@Column(table= "addresses", name= "street", length= 255, nullable= false)
	private String street;
	
	@Pattern(regexp= "[a-zA-Z\\s'.]{1,255}", message= "error.charnotallowed")
	@Column(table= "addresses", name= "town", length= 255, nullable= false)
	private String town;
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}
}
