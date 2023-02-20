package com.order.model.service;
import java.sql.Date;
import java.util.List;

import com.order.model.Order.dao.OrderDAO_interface;
import com.order.model.Order.dao.impl.OrderJDBCDAO;
import com.order.model.Order.pojo.Order;

public class OrderService {
	private OrderDAO_interface dao;

	public OrderService() {
		dao = new OrderJDBCDAO();
	}

	public Order addOrder(Integer memId, Integer storeId, Integer orderPrice,
			Integer codeId, Integer orderFre, Integer orderFprice,String orderText,Date orderOtime) {

		Order order = new Order();

		order.setMemId(memId);
		order.setStoreId(storeId);
		order.setOrderPrice(orderPrice);
		order.setCodeId(codeId);
		order.setOrderFre(orderFre);
		order.setOrderFprice(orderFprice);
		order.setOrderText(orderText);
		order.setOrderOtime(orderOtime);
		dao.insert(order);
		
		return order;
	}

	public Order updateOrder(Integer orderId,Integer memId, Integer storeId, Integer orderPrice,
			Integer codeId, Integer orderFre, Integer orderFprice,String orderText,Date orderOtime) {

		Order order = new Order();

		order.setOrderId(orderId);
		order.setMemId(memId);
		order.setStoreId(storeId);
		order.setOrderPrice(orderPrice);
		order.setCodeId(codeId);
		order.setOrderFre(orderFre);
		order.setOrderFprice(orderFprice);
		order.setOrderText(orderText);
		order.setOrderOtime(orderOtime);
		dao.update(order);

		return order;
	}

	public void deleteOrder(Integer orderno) {
		dao.delete(orderno);
	}

	public Order getOneOrder(Integer orderno) {
		return dao.getById(orderno);
	}

	public List<Order> getAll() {
		return dao.getAll();
	}
}

