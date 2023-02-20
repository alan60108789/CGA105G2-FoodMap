package com.order.model.OrderDetail.dao.impl;

import com.core.common.Common;
import com.order.model.OrderDetail.dao.OrderDetailDAO_interface;
import com.order.model.OrderDetail.pojo.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface {

	@Override
	public void insert(OrderDetail orderDetail){
	final String sql =
			"INSERT INTO cga105g2.order_detail (order_id,goods_id,detail_quantity,detailprice) VALUES (?, ?, ?, ?)";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, orderDetail.getOrderId());
			pstmt.setInt(2, orderDetail.getGoodsId());
			pstmt.setInt(3, orderDetail.getDetailQuantity());
			pstmt.setInt(4, orderDetail.getDetailPrice());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void update(OrderDetail orderDetail) {
		final String sql =
				"UPDATE cga105g2.order_detail set order_id=?, goods_id=?, detail_quantity=?, detailprice=? where order_id = ? AND goods_id=?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, orderDetail.getOrderId());
			pstmt.setInt(2, orderDetail.getGoodsId());
			pstmt.setInt(3, orderDetail.getDetailQuantity());
			pstmt.setInt(4, orderDetail.getDetailPrice());
			pstmt.setInt(5, orderDetail.getOrderId());
			pstmt.setInt(6, orderDetail.getGoodsId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void delete(Integer orderDetailVO,Integer orderDetailVO2) {
		final String sql =
				"DELETE FROM cga105g2.order_detail where order_id = ? AND goods_id=?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, orderDetailVO);
			pstmt.setInt(2, orderDetailVO2);
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public OrderDetail getById(Integer orderDetailno, Integer orderDetailno2) {
		final String sql =
				"SELECT order_id,goods_id,detail_quantity,detailprice FROM cga105g2.order_detail where order_id = ? AND goods_id=? order by order_id";
		OrderDetail orderDetail=null;
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1,orderDetailno);
			pstmt.setInt(2,orderDetailno2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				orderDetail = new OrderDetail();
				orderDetail.setOrderId(rs.getInt("order_id"));
				orderDetail.setGoodsId(rs.getInt("goods_id"));
				orderDetail.setDetailQuantity(rs.getInt("detail_quantity"));
				orderDetail.setDetailPrice(rs.getInt("detailprice"));
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return orderDetail;
	}

	@Override
	public List<OrderDetail> getAll() {
		final String sql =
				"SELECT order_id,goods_id,detail_quantity,detailprice FROM cga105g2.order_detail order by order_id";
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		OrderDetail orderDetail = null;
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				orderDetail = new OrderDetail();
				orderDetail.setOrderId(rs.getInt("order_id"));
				orderDetail.setGoodsId(rs.getInt("goods_id"));
				orderDetail.setDetailQuantity(rs.getInt("detail_quantity"));
				orderDetail.setDetailPrice(rs.getInt("detailprice"));
				list.add(orderDetail); // Store the row in the list
			}
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}

	public static void main(String[] args) {

		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();

//		// �s�W
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setOrderId(2);
//		orderDetailVO1.setGoodsId(5);
//		orderDetailVO1.setDetailQuantity(8);
//		orderDetailVO1.setDetailPrice(800);
//		
//		dao.insert(orderDetailVO1);
//
//		// �ק�
//		OrderDetailVO orderDetailVO2 = new OrderDetailVO();
//		orderDetailVO2.setOrderId(2);
//		orderDetailVO2.setGoodsId(4);
//		orderDetailVO2.setDetailQuantity(6);
//		orderDetailVO2.setDetailPrice(888);
//		orderDetailVO2.setOrderId(2);
//		orderDetailVO2.setGoodsId(5);
//		
//		dao.update(orderDetailVO2);
//
//		// �R��
		dao.delete(2,5);

		// �d��
//		OrderDetailVO orderDetailVO3 = dao.getById(1,3);
//		System.out.print(orderDetailVO3.getOrderId() + ",");
//		System.out.print(orderDetailVO3.getGoodsId() + ",");
//		System.out.print(orderDetailVO3.getDetailQuantity() + ",");
//		System.out.print(orderDetailVO3.getDetailPrice() );
//		System.out.println();
//		System.out.println("---------------------");
//
//		// �d��
//		List<OrderDetailVO> list = dao.getAll();
//		for (OrderDetailVO Od : list) {
//			System.out.print(Od.getOrderId() + ",");
//			System.out.print(Od.getGoodsId() + ",");
//			System.out.print(Od.getDetailQuantity() + ",");
//			System.out.print(Od.getDetailPrice() + ",");
//			System.out.println();
//		}
	}
}
