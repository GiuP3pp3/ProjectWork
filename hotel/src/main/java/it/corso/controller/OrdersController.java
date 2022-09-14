package it.corso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.dao.CustomerDao;
import it.corso.model.Article;
import it.corso.model.Customer;
import it.corso.model.Order;
import it.corso.service.ArticleService;
import it.corso.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderService orderService;
	
	private Order order;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name= "id", required= false) Integer id) {
		
		order= id == null ? new Order() : orderService.getOrderById(id);
		List<Customer> customers= (List<Customer>) customerDao.findAll();
		List<Article> articles= articleService.getArticles();
		
		if(order.getId() != 0) {
			
			articles.forEach(a ->
			{
				order.getArticles().forEach(oa ->
				{
					if(a.getId() == oa.getId())
						a.setIncluded(true);
				});
			});
			
			for (Article a : articles) {
				for (Article oa : order.getArticles()) {
					if(a.getId() == oa.getId())
						a.setIncluded(true);
				}
			}
		}
		
		model.addAttribute("title", "Orders Management");
		model.addAttribute("orders", orderService.getOrders());
		model.addAttribute("order", order);
		model.addAttribute("customers", customers);
		model.addAttribute("articles", articles);
		return "orders";
	}
	
	@PostMapping("/saveorder")
	public String registerOrder(
			@RequestParam("date") String date,
			@RequestParam("selCustomer") int customerId,
			@RequestParam(name= "orderArticles", required= false) int[] orderArticles) {
		
		if (orderArticles == null)
			return "redirect:/orders";
		orderService.registerOrder(order, date, customerId, orderArticles);
		return "redirect:/orders";
	}
	
	@GetMapping("/deleteOrder")
	public String deleteOrder(@RequestParam("id") int id) {
		orderService.deleteOrder(orderService.getOrderById(id));
		return "redirect:/orders";
	}
}
