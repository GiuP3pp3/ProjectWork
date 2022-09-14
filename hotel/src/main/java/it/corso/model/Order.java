package it.corso.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name= "orders")
public class Order implements Serializable{

	private static final long serialVersionUID = -3340434629259458660L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name= "date", nullable= false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name= "amount", nullable= false)
	private double amount;
	
	@ManyToOne(cascade= CascadeType.REFRESH)
	@JoinColumn(name= "customer_id", referencedColumnName= "id")
	private Customer customer;
	
	@ManyToMany(cascade= CascadeType.REFRESH, fetch= FetchType.EAGER)
	@JoinTable(
		name= "orders_articles",
		joinColumns= @JoinColumn(name= "order_id", referencedColumnName= "id"),
		inverseJoinColumns= @JoinColumn(name= "article_id", referencedColumnName= "id")
	)
	@Fetch(FetchMode.SUBSELECT)
	private List<Article> articles= new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
