package com.saveArt.model.SaveArt.dao.impl;

import com.core.common.Common;
import com.saveArt.model.SaveArt.dao.SaveArt_interface;
import com.saveArt.model.SaveArt.pojo.SaveArt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SaveArtJDBCDAO implements SaveArt_interface {

	@Override
	public void insert(SaveArt SaveArt) {
		String sql =
				"INSERT INTO cga105g2.SAVE_ART (ART_ID, MEM_ID) VALUES (?, ?)";
		

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, SaveArt.getArtId());
			pstmt.setInt(2, SaveArt.getMemId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 

	}

	@Override
	public List<SaveArt> getAll() {
		
		String sql =
				"SELECT * FROM cga105g2.SAVE_ART";
		List<SaveArt> list = new ArrayList<SaveArt>();
		SaveArt SaveArt = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				SaveArt = new SaveArt();
				SaveArt.setArtId(rs.getInt("ART_ID"));
				SaveArt.setMemId(rs.getInt("MEM_ID"));

				list.add(SaveArt);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
		return list;
	}

	@Override
	public SaveArt getByMemId(Integer memId) {
		String sql =
				"SELECT * FROM cga105g2.SAVE_ART where MEM_ID = ?";
		SaveArt SaveArt = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				SaveArt = new SaveArt();
				SaveArt.setArtId(rs.getInt("ART_ID"));
				SaveArt.setMemId(rs.getInt("MEM_ID"));

			}

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		}
		return SaveArt;
	}

	public List<SaveArt> getAllById(Integer memId) {
		
		String sql =
				"SELECT * FROM cga105g2.SAVE_ART where MEM_ID = ?";
		List<SaveArt> list = new ArrayList<SaveArt>();
		SaveArt SaveArt = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				SaveArt = new SaveArt();
				SaveArt.setArtId(rs.getInt("ART_ID"));
				SaveArt.setMemId(rs.getInt("MEM_ID"));

				list.add(SaveArt);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 
		return list;
	}
	
	@Override
	public void delete(Integer artId, Integer memId) {
		String sql = 
				"DELETE FROM cga105g2.save_art where ART_ID = ? and MEM_ID = ?";
		
		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, artId);
			pstmt.setInt(2, memId);

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 
	}
	public static void main(String[] args) {
		SaveArtJDBCDAO saveArtJDBCDAO = new SaveArtJDBCDAO();
		SaveArt SaveArt = new SaveArt();
//		SaveArt.setArtId(4);
//		SaveArt.setMemId(2);
//		saveArtJDBCDAO.insert(SaveArt); // insert成功
		//依照會員id單一查詢
//		SaveArt = saveArtJDBCDAO.getByMemId(1);
//		System.out.println("ART_ID:" + SaveArt.getArtId());
//		System.out.println("MEM_ID:" + SaveArt.getMemId());
//		System.out.println("=====================");
	
		//依照會員id查詢全部
//		List<SaveArt> list = saveArtJDBCDAO.getAllById(1);
//		for (SaveArt a : list) {
//		System.out.println("ART_ID:" + a.getArtId());
//		System.out.println("MEM_ID:" + a.getMemId());
//		System.out.println("=====================");
//		}
		//刪除某會員收藏特定文章
//		saveArtJDBCDAO.delete(56, 4);
//		//查詢全部
//		List<SaveArt> list = saveArtJDBCDAO.getAll();
//		for (SaveArt a : list) {
//			System.out.print("ART_ID:" + a.getArtId());
//			System.out.print("MEM_ID:" + a.getMemId());
//			System.out.println("=====================");
//		}
	}
}
