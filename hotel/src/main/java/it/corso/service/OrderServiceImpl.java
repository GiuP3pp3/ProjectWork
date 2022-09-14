package it.corso.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.CustomerDao;
import it.corso.dao.OrderDao;
import it.corso.model.Article;
import it.corso.model.Order;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ArticleService articleService;
	
	@Override
	public void registerOrder(Order o, Object... orderData) {
		
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		try {
			o.setDate(format.parse((String) orderData[0]));
		} 
		
		catch (ParseException e) {
			o.setDate(new Date());
		}
		
		o.setCustomer(customerDao.findById((int)orderData[1]).get());
		
		o.getArticles().clear();
		for(int i : (int[])orderData[2])
			o.getArticles().add(articleService.getArticleById(i));
		
		/*
			double amount= 0;
			for(Article a : o.getArticles())
			amount += a.getPrice();
		*/
		
		double amount= o.getArticles()
				.stream()
				.map(a -> a.getPrice())
				.reduce(0.0, (a1, a2) -> a1 + a2);
		
		o.setAmount(amount);
		
		orderDao.save(o);
	}

	@Override
	public Order getOrderById(int id) {
		return orderDao.findById(id).get();
	}

	@Override
	public List<Order> getOrders() {
		return (List<Order>) orderDao.findAll();
	}

	@Override
	public void deleteOrder(Order o) {
		
		o.setCustomer(null);
		o.setArticles(null);
		orderDao.save(o);
		orderDao.delete(o);
	}
}
