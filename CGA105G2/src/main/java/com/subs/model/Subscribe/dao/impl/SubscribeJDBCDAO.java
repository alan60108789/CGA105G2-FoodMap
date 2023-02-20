package com.subs.model.Subscribe.dao.impl;

import com.core.common.Common;
import com.subs.model.Subscribe.dao.Subscribe_interface;
import com.subs.model.Subscribe.pojo.Subscribe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SubscribeJDBCDAO implements Subscribe_interface {
	
	static {
		  try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }

	@Override
	public void insert(Subscribe Subscribe) {
		String sql =
				"INSERT INTO cga105g2.SUBSCRIBE (STORE_ID, MEM_ID) VALUES (?, ?)";
		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			pstmt.setInt(1, Subscribe.getStoreId());
			pstmt.setInt(2, Subscribe.getMemId());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public List<Subscribe> getAll() {
		String sql =
				"SELECT * FROM cga105g2.SUBSCRIBE";
		List<Subscribe> list = new ArrayList<Subscribe>();
		Subscribe Subscribe = null;
		ResultSet rs = null;
		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Subscribe = new Subscribe();
				Subscribe.setSubId(rs.getInt("SUB_ID"));
				Subscribe.setStoreId(rs.getInt("STORE_ID"));
				Subscribe.setMemId(rs.getInt("MEM_ID"));
				list.add(Subscribe);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return list;
	}

	@Override
	public Subscribe getByMemId(Integer memId) {
		String sql =
				"SELECT * FROM cga105g2.SUBSCRIBE where MEM_ID = ?";
		Subscribe Subscribe = null;
		ResultSet rs = null;
		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Subscribe = new Subscribe();
				Subscribe.setSubId(rs.getInt("SUB_ID"));
				Subscribe.setStoreId(rs.getInt("STORE_ID"));
				Subscribe.setMemId(rs.getInt("MEM_ID"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return Subscribe;
	}
	
	public Subscribe getByMemIdStoreId(Integer storeId, Integer memId ) {
		String sql =
				"SELECT * FROM cga105g2.SUBSCRIBE where MEM_ID = ? and STORE_ID = ?";
		Subscribe Subscribe = null;
		ResultSet rs = null;
		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			pstmt.setInt(1, storeId);
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Subscribe = new Subscribe();
				Subscribe.setSubId(rs.getInt("SUB_ID"));
				Subscribe.setStoreId(rs.getInt("STORE_ID"));
				Subscribe.setMemId(rs.getInt("MEM_ID"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return Subscribe;
	}

	public List<Subscribe> getAllByMemId(Integer memId) {
		String sql =
				"SELECT * FROM cga105g2.SUBSCRIBE where MEM_ID = ?";
		List<Subscribe> list = new ArrayList<Subscribe>();
		Subscribe Subscribe = null;
		ResultSet rs = null;
		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Subscribe = new Subscribe();
				Subscribe.setSubId(rs.getInt("SUB_ID"));
				Subscribe.setStoreId(rs.getInt("STORE_ID"));
				Subscribe.setMemId(rs.getInt("MEM_ID"));
				list.add(Subscribe);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return list;
	}
	
	public List<Subscribe> getAllByMemIdStoreId(Integer storeId, Integer memId) {
		String sql =
				"SELECT * FROM cga105g2.SUBSCRIBE where STORE_ID = ? and MEM_ID = ?";
		List<Subscribe> list = new ArrayList<Subscribe>();
		Subscribe Subscribe = null;
		ResultSet rs = null;
		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, storeId);
			pstmt.setInt(2, memId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Subscribe = new Subscribe();
				Subscribe.setStoreId(rs.getInt("STORE_ID"));
				Subscribe.setMemId(rs.getInt("MEM_ID"));
				list.add(Subscribe);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return list;
	}
	
	public List<Subscribe> getAllBystoreId(Integer storeId) {
		String sql =
				"SELECT * FROM cga105g2.SUBSCRIBE where STORE_ID = ?";
		List<Subscribe> list = new ArrayList<Subscribe>();
		Subscribe Subscribe = null;
		ResultSet rs = null;
		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, storeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Subscribe = new Subscribe();
				Subscribe.setSubId(rs.getInt("SUB_ID"));
				Subscribe.setStoreId(rs.getInt("STORE_ID"));
				Subscribe.setMemId(rs.getInt("MEM_ID"));
				list.add(Subscribe);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}


	
	@Override
	public void delete(Integer storeId, Integer memId) {
		String sql = 
				"DELETE FROM cga105g2.SUBSCRIBE where STORE_ID = ? AND MEM_ID = ?";
		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			pstmt.setInt(1, storeId);
			pstmt.setInt(2, memId);
			pstmt.executeUpdate();
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}
}
