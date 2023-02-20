package com.goods.model.Goods.dao.impl;

import com.core.common.Common;
import com.goods.model.Goods.dao.GoodsDAO_interface;
import com.goods.model.Goods.pojo.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class GoodsJDBCDAO implements GoodsDAO_interface {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Goods Goods) {

		final String sql = "INSERT INTO cga105g2.goods (store_id,goods_img,goods_name,goods_status,goods_price,goods_text) VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
		PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, Goods.getStoreId());
			pstmt.setBytes(2, Goods.getGoodsImg());
			pstmt.setString(3, Goods.getGoodsName());
			pstmt.setInt(4, Goods.getGoodsStatus());
			pstmt.setInt(5, Goods.getGoodsPrice());
			pstmt.setString(6, Goods.getGoodsText());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void update(Goods Goods) {
		final String sql = "UPDATE cga105g2.goods set store_id=?, goods_img=?, goods_name=?, goods_status=?, goods_price=?, goods_text=? where goods_id = ?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, Goods.getStoreId());
			pstmt.setBytes(2, Goods.getGoodsImg());
			pstmt.setString(3, Goods.getGoodsName());
			pstmt.setInt(4, Goods.getGoodsStatus());
			pstmt.setInt(5, Goods.getGoodsPrice());
			pstmt.setString(6, Goods.getGoodsText());
			pstmt.setInt(7, Goods.getGoodsId());
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public void delete(Integer goodsno) {
		final String sql = "DELETE FROM cga105g2.goods where goods_id = ?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, goodsno);
			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}

	@Override
	public Goods getById(Integer goodsno) {
		final String sql = "SELECT goods_id, store_id, goods_img, goods_name, goods_status, goods_price, goods_text, goods_time, goods_rtime FROM cga105g2.goods where goods_id = ?";
		Goods Goods = null;
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, goodsno);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods = new Goods();
				Goods.setGoodsId(rs.getInt("goods_id"));
				Goods.setStoreId(rs.getInt("store_id"));
				Goods.setGoodsImg(rs.getBytes("goods_img"));
				Goods.setGoodsName(rs.getString("goods_name"));
				Goods.setGoodsStatus(rs.getInt("goods_status"));
				Goods.setGoodsPrice(rs.getInt("goods_price"));
				Goods.setGoodsText(rs.getString("goods_text"));
				Goods.setGoodsTime(rs.getTimestamp("goods_time"));
				Goods.setGoodsRtime(rs.getTimestamp("goods_rtime"));
			}
			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return Goods;
	}

	@Override
	public List<Goods> getAll() {
		final String sql = "SELECT goods_id, store_id, goods_img, goods_name, goods_status, goods_price, goods_text, goods_time, goods_rtime FROM cga105g2.goods ";
		List<Goods> list = new ArrayList<Goods>();
		Goods Goods = null;
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Goods = new Goods();
				Goods.setGoodsId(rs.getInt("goods_id"));
				Goods.setStoreId(rs.getInt("store_id"));
				Goods.setGoodsImg(rs.getBytes("goods_img"));
				Goods.setGoodsName(rs.getString("goods_name"));
				Goods.setGoodsStatus(rs.getInt("goods_status"));
				Goods.setGoodsPrice(rs.getInt("goods_price"));
				Goods.setGoodsText(rs.getString("goods_text"));
				Goods.setGoodsTime(rs.getTimestamp("goods_time"));
				Goods.setGoodsRtime(rs.getTimestamp("goods_rtime"));
				list.add(Goods);
			}

			// Handle any driver errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	public static void main(String[] args) {

		GoodsJDBCDAO dao = new GoodsJDBCDAO();

//		// �s�W
//		Goods Goods = new Goods();
//
//		Goods.setStoreId(12);
//		Goods.setGoodsName("xxx");
//		Goods.setGoodsStatus(0);
//		Goods.setGoodsPrice(500);
//		Goods.setGoodsText("yyyy");
//		
//		File file = new File("image/tomcat.png");
//		byte[] b = new byte[(int) file.length()];
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream(file);
//			fis.read(b);
//			Goods.setGoodsImg(b);
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		dao.insert(Goods);
//
		// �ק�
//		Goods Goods2 = new Goods();
//		Goods2.setGoodsId(7);
//		Goods2.setStoreId(12);
//		Goods2.setGoodsName("aaa");
//		Goods2.setGoodsStatus(0);
//		Goods2.setGoodsPrice(0);
//		Goods2.setGoodsText("aaa");
//
//		File file = new File("image/back1.png");
//		byte[] b2 = new byte[(int) file.length()];
//		FileInputStream fis2;
//		try {
//			fis2 = new FileInputStream(file);
//			fis2.read(b2);
//			Goods2.setGoodsImg(b2);
//			fis2.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		dao.update(Goods2);
//
//			// �R��
//			dao.delete(10);

//			// �d��
			Goods Goods3 = dao.getById(2);
			System.out.print(Goods3.getStoreId() + ",");
			System.out.print(Goods3.getGoodsImg() + ",");
			System.out.print(Goods3.getGoodsName() + ",");
			System.out.print(Goods3.getGoodsStatus() + ",");
			System.out.print(Goods3.getGoodsPrice() + ",");
			System.out.print(Goods3.getGoodsText() + ",");
			System.out.println(Goods3.getGoodsTime());
			System.out.println(Goods3.getGoodsRtime());
			System.out.println();
			System.out.println("---------------------");
//
////			 �d��
//			List<Goods> list = dao.getAll();
//			for (Goods goods : list) {
//				System.out.print(goods.getGoodsId() + ",");
//				System.out.print(goods.getStoreId() + ",");
//				System.out.print(goods.getGoodsImg() + ",");
//				System.out.print(goods.getGoodsName() + ",");
//				System.out.print(goods.getGoodsStatus() + ",");
//				System.out.print(goods.getGoodsPrice()+ ",");
//				System.out.print(goods.getGoodsText() + ",");
//				System.out.print(goods.getGoodsTime()+ ",");
//				System.out.print(goods.getGoodsRtime());
//				System.out.println();
//			}
	}
}
