package it.corso.dao;

import org.springframework.data.repository.CrudRepository;
import it.corso.model.Order;

public interface OrderDao extends CrudRepository<Order, Integer>{

	
}
