package com.order.model.Order.dao.impl;

import com.core.common.Common;
import com.order.model.Order.dao.OrderDAO_interface;
import com.order.model.Order.pojo.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;


public class OrderJDBCDAO implements OrderDAO_interface {

	@Override
	public void insert(Order order) {
		final String sql = "INSERT INTO cga105g2.order (mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text,order_otime) VALUES (?, ? ,?, ?, ?, ?, ?, ? )";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, order.getMemId());
			pstmt.setInt(2, order.getStoreId());
			pstmt.setInt(3, order.getOrderPrice());
			pstmt.setInt(4, order.getCodeId());
			pstmt.setInt(5, order.getOrderFre());
			pstmt.setInt(6, order.getOrderFprice());
			pstmt.setString(7, order.getOrderText());
			pstmt.setDate(8, order.getOrderOtime());
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void update(Order order) {
		final String sql = "UPDATE cga105g2.order SET mem_id=?,store_id=?,order_price=?,code_id=?,order_fre=?,order_fprice=?,order_text=?,order_status=?,order_otime=? WHERE order_id = ?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, order.getMemId());
			pstmt.setInt(2, order.getStoreId());
			pstmt.setInt(3, order.getOrderPrice());
			pstmt.setInt(4, order.getCodeId());
			pstmt.setInt(5, order.getOrderFre());
			pstmt.setInt(6, order.getOrderFprice());
			pstmt.setString(7, order.getOrderText());
			pstmt.setInt(8, order.getOrderStatus());
			pstmt.setDate(9, order.getOrderOtime());
			pstmt.setInt(10, order.getOrderId());
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void delete(Integer orderno) {
		final String sql = "DELETE FROM cga105g2.order where order_id = ?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, orderno);
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public Order getById(Integer orderno) {
		final String sql = "SELECT order_id,mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text,order_status,order_time,order_otime,order_rtime FROM cga105g2.order where order_id = ?";
		Order order = null;
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, orderno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				order=new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setMemId(rs.getInt("mem_id"));
				order.setStoreId(rs.getInt("store_id"));
				order.setOrderPrice(rs.getInt("order_price"));
				order.setCodeId(rs.getInt("code_id"));
				order.setOrderFre(rs.getInt("order_fre"));
				order.setOrderFprice(rs.getInt("order_fprice"));
				order.setOrderText(rs.getString("order_text"));
				order.setOrderStatus(rs.getInt("order_status"));
				order.setOrderTime(rs.getTimestamp("order_time"));
				order.setOrderOtime(rs.getDate("order_otime"));
				order.setOrderRtime(rs.getTimestamp("order_rtime"));
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return order;
	}

	@Override
	public List<Order> getAll() {
		final String sql = "SELECT order_id,mem_id,store_id,order_price,code_id,order_fre,order_fprice,order_text,order_status,order_time,order_otime,order_rtime FROM cga105g2.order order by  order_id";
		List<Order> list = new ArrayList<Order>();
		Order order = null;
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				order = new Order();
				order.setOrderId(rs.getInt("order_id"));
				order.setMemId(rs.getInt("mem_id"));
				order.setStoreId(rs.getInt("store_id"));
				order.setOrderPrice(rs.getInt("order_price"));
				order.setCodeId(rs.getInt("code_id"));
				order.setOrderFre(rs.getInt("order_fre"));
				order.setOrderFprice(rs.getInt("order_fprice"));
				order.setOrderText(rs.getString("order_text"));
				order.setOrderStatus(rs.getInt("order_status"));
				order.setOrderTime(rs.getTimestamp("order_time"));
				order.setOrderOtime(rs.getDate("order_otime"));
				order.setOrderRtime(rs.getTimestamp("order_rtime"));
				list.add(order); // Store the row in the list
			}
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}

	public static void main(String[] args) {

		OrderJDBCDAO dao = new OrderJDBCDAO();

		// �s�W
//		Order order1 = new Order();
//		order1.setMemId(2);
//		order1.setStoreId(2);
//		order1.setOrderPrice(2000);
//		order1.setCodeId(2);
//		order1.setOrderFre(80);
//		order1.setOrderFprice(2080);
//		order1.setOrderText("xxx");
//		order1.setOrderOtime(java.sql.Date.valueOf("2002-01-01"));
//		dao.insert(order1);
//		System.out.println("�s�W���\");

		// �ק�
//		Order order2 = new Order();
//		order2.setMemId(2);
//		order2.setStoreId(2);
//		order2.setOrderPrice(100);
//		order2.setCodeId(2);
//		order2.setOrderFre(120);
//		order2.setOrderFprice(0000);
//		order2.setOrderText("ccc");
//		order2.setOrderStatus(1);
//		order2.setOrderOtime(java.sql.Date.valueOf("2022-01-01"));
//		order2.setOrderId(7);
//		dao.update(order2);

//		// �R��
		dao.delete(7);

		// �d��
//		Order order3 = dao.getById(5);
//		System.out.print(order3.getOrderId() + ",");
//		System.out.print(order3.getMemId() + ",");
//		System.out.print(order3.getStoreId() + ",");
//		System.out.print(order3.getOrderPrice() + ",");
//		System.out.print(order3.getCodeId() + ",");
//		System.out.print(order3.getOrderFre() + ",");
//		System.out.print(order3.getOrderFprice() + ",");
//		System.out.print(order3.getOrderText() + ",");
//		System.out.print(order3.getOrderStatus() + ",");
//		System.out.print(order3.getOrderTime() + ",");
//		System.out.print(order3.getOrderOtime() + ",");
//		System.out.print(order3.getOrderOtime());
//		System.out.println();
//		System.out.println("----------------------------------");
//
//		// �d��
//		List<Order> list = dao.getAll();
//		for (Order Od : list) {
//			System.out.print(Od.getOrderId() + ",");
//			System.out.print(Od.getMemId() + ",");
//			System.out.print(Od.getStoreId() + ",");
//			System.out.print(Od.getOrderPrice() + ",");
//			System.out.print(Od.getCodeId() + ",");
//			System.out.print(Od.getOrderFre() + ",");
//			System.out.print(Od.getOrderFprice() + ",");
//			System.out.print(Od.getOrderText() + ",");
//			System.out.print(Od.getOrderStatus() + ",");
//			System.out.print(Od.getOrderTime() + ",");
//			System.out.print(Od.getOrderOtime() + ",");
//			System.out.print(Od.getOrderOtime());
//			System.out.println();
//		}
	}
}
