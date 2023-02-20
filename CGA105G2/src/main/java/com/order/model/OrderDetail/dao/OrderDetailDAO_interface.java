package com.order.model.OrderDetail.dao;

import com.order.model.OrderDetail.pojo.OrderDetail;

import java.util.List;


public interface OrderDetailDAO_interface {
	public void insert(OrderDetail orderDetail);
    public void update(OrderDetail orderDetail);
    public void delete(Integer orderDetailno,Integer orderDetailno2);

    OrderDetail getById(Integer orderDetailno, Integer orderDetailno2);

    public List <OrderDetail> getAll();
}
