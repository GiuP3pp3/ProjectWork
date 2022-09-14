package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Customer;

public interface CustomerDao extends CrudRepository<Customer, Integer> {

	Customer findByFiscalCode(String fc);
}
