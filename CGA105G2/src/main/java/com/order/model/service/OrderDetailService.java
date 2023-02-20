package com.order.model.service;

import java.util.List;

import com.order.model.OrderDetail.dao.OrderDetailDAO_interface;
import com.order.model.OrderDetail.dao.impl.OrderDetailJDBCDAO;
import com.order.model.OrderDetail.pojo.OrderDetail;


public class OrderDetailService {
	private OrderDetailDAO_interface dao;

	public  OrderDetailService() {
		dao = new OrderDetailJDBCDAO();
	}

	public OrderDetail addOrderDetail(Integer orderId, Integer goodsId, Integer detailQuantity,
			Integer detailPrice) {

		OrderDetail orderDetail = new OrderDetail();

		orderDetail.setOrderId(orderId);
		orderDetail.setGoodsId(goodsId);
		orderDetail.setDetailQuantity(detailQuantity);
		orderDetail.setDetailPrice(detailPrice);
		
		dao.insert(orderDetail);
		
		return orderDetail;
	}

	public OrderDetail updateOrderDetail(Integer orderId, Integer goodsId, Integer detailQuantity,
			Integer detailPrice) {

		OrderDetail orderDetail = new OrderDetail();

		orderDetail.setOrderId(orderId);
		orderDetail.setGoodsId(goodsId);
		orderDetail.setDetailQuantity(detailQuantity);
		orderDetail.setDetailPrice(detailPrice);
		dao.update(orderDetail);

		return orderDetail;
	}

	public void deleteOrderDetail(Integer orderDetailno,Integer orderDetailno2) {
		dao.delete(orderDetailno,orderDetailno2);
	}

	public OrderDetail getOneOrderDetail(Integer orderDetailno,Integer orderDetailno2) {
		return dao.getById(orderDetailno,orderDetailno2);
	}

	public List<OrderDetail> getAll() {
		return dao.getAll();
	}
}

