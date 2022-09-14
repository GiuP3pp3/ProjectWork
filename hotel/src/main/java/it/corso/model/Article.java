package it.corso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "articles")
public class Article implements Serializable{
	
	private static final long serialVersionUID = 5487533939965844137L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp= "[a-zA-Z0-9\\s]{1,255}", message= "{error.charnotallowed}")
	@Column(name= "description", length= 45, nullable= false)
	private String description;
	
	@Digits(integer= 4, fraction= 2, message= "{error.invalidamount}")
	@Column(name= "price", nullable= false)
	private double price;
	
	@ManyToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinTable(
		name= "orders_articles",
		joinColumns= @JoinColumn(name= "article_id", referencedColumnName= "id"),
		inverseJoinColumns= @JoinColumn(name= "order_id", referencedColumnName= "id")
	)
	private List<Order> orders= new ArrayList<>();
	
	@Transient
	private boolean included;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isIncluded() {
		return included;
	}
	public void setIncluded(boolean included) {
		this.included = included;
	}
}
