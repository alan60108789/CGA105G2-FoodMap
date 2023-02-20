package com.order.model.Order.dao;

import com.order.model.Order.pojo.Order;

import java.util.List;


public interface OrderDAO_interface {
	public void insert(Order order);
    public void update(Order order);
    public void delete(Integer orderno);

    Order getById(Integer orderno);

    public List <Order> getAll();
}
