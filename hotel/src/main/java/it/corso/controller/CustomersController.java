package it.corso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.dao.CustomerDao;
import it.corso.dao.ProfileDao;
import it.corso.model.Customer;
import it.corso.model.Profile;

@Controller
@RequestMapping("/customers")
public class CustomersController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@GetMapping
	public String getPage(
			Model model,
			@RequestParam(name= "id", required= false) Integer id) {
		
		Customer customer= id == null ? new Customer(): customerDao.findById(id).get();
		model.addAttribute("title", "Customer Management");
		model.addAttribute("customers", customerDao.findAll());
		model.addAttribute("customer", customer);
		return "customers";
	}
	
	@PostMapping
	public String registerCustomer(@Valid @ModelAttribute("customer") Customer customer,
			BindingResult result) {
		
		if(result.hasErrors())
			return "customers";
		
		if(customer.getId() != 0) {
			Profile profile= profileDao.findById(customer.getId()).get();
			profile.setUsername(customer.getProfile().getUsername());
			profile.setPassword(customer.getProfile().getPassword());
			customer.setProfile(profile);
		}
		customerDao.save(customer);
		return "redirect:/customers";
	}
	
	@GetMapping("/deletecustomer")
	public String deleteCustomer(@RequestParam("id") int id) {
		
		Customer customer= customerDao.findById(id).get();
		customerDao.delete(customer);
		return "redirect:/customers";
	}
}
