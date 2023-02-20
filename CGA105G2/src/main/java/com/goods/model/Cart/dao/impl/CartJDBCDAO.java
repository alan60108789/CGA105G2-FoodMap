package com.goods.model.Cart.dao.impl;

import com.core.common.Common;
import com.goods.model.Cart.dao.CartDAO_interface;
import com.goods.model.Cart.pojo.Cart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;


public class CartJDBCDAO implements CartDAO_interface {

		public void insert(Cart cart) {
			final String sql =
					"INSERT INTO cga105g2.cart (mem_id,goods_id,cart_num) VALUES (?, ?, ?)";
			try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				pstmt.setInt(1, cart.getMemId());
				pstmt.setInt(2, cart.getGoodsId());
				pstmt.setInt(3, cart.getCartNum());
				pstmt.executeUpdate();

				// Handle any driver errors
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			}
		}

		@Override
		public void update(Cart cart) {
			final String sql =
					"UPDATE cga105g2.cart set mem_id=?,goods_id=?,cart_num=? where mem_id= ? AND goods_id = ?";
			try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				pstmt.setInt(1, cart.getMemId());
				pstmt.setInt(2, cart.getGoodsId());
				pstmt.setInt(3, cart.getCartNum());
				pstmt.executeUpdate();
				// Handle any driver errors
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			}
		}

		@Override
		public void delete(Integer cartno) {
			final String sql =
					"DELETE FROM cga105g2.cart where mem_id= ? AND goods_id = ?";
			try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				pstmt.setInt(1, cartno);
				pstmt.executeUpdate();
				// Handle any driver errors
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			}
		}

		@Override
		public Cart getById(Integer cartno) {
			final String sql =
					"SELECT mem_id,goods_id,cart_num FROM cga105g2.cart where mem_id= ? AND goods_id = ?";
			Cart cart = null;
			try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				pstmt.setInt(1, cartno);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					cart = new Cart();
					cart.setMemId(rs.getInt("mem_id"));
					cart.setGoodsId(rs.getInt("goods_id"));
					cart.setCartNum(rs.getInt("cart_num"));
				}
				// Handle any driver errors
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			}
			return cart;
		}

		@Override
		public List<Cart> getAll() {
			final String sql =
					"SELECT mem_id,goods_id,cart_num FROM cga105g2.cart";
			List<Cart> list = new ArrayList<Cart>();
			try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Cart cart=new Cart();
					 // Store the row in the list
					cart.setMemId(rs.getInt("mem_id"));
					cart.setGoodsId(rs.getInt("goods_id"));
					cart.setCartNum(rs.getInt("cart_num"));
					list.add(cart);
				}
				// Handle any driver errors
			}catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
			}
			return list;
		}
		
		public static void main(String[] args) {

			CartJDBCDAO dao = new CartJDBCDAO();

			// �s�W
			Cart cart = new Cart();
			
			cart.setMemId(12);
			cart.setGoodsId(null);
			cart.setCartNum(12);

			// �ק�
			Cart cart2 = new Cart();
			cart2.setMemId(12);
			cart2.setGoodsId(null);
			cart2.setCartNum(12);
			dao.insert(cart2);

//			// �R��
			dao.delete(1);

			// �d��
			Cart cart3 = dao.getById(2);
			System.out.print(cart3.getMemId() + ",");
			System.out.print(cart3.getGoodsId() + ",");
			System.out.print(cart3.getCartNum() + ",");

//			 �d��
			List<Cart> list = dao.getAll();
			for (Cart goods : list) {
				System.out.print(cart3.getMemId() + ",");
				System.out.print(cart3.getGoodsId() + ",");
				System.out.print(cart3.getCartNum() + ",");
				System.out.println();
			}
		}
}