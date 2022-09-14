package it.corso.service;

import java.util.List;

import it.corso.model.Order;

public interface OrderService {

	void registerOrder(Order o, Object... orderData);
	Order getOrderById(int id);
	List<Order> getOrders();
	void deleteOrder(Order o);
}
