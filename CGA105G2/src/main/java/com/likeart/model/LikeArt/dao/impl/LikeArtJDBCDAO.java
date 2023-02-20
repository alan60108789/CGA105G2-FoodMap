package com.likeart.model.LikeArt.dao.impl;

import com.core.common.Common;
import com.likeart.model.LikeArt.dao.LikeArt_interface;
import com.likeart.model.LikeArt.pojo.LikeArt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LikeArtJDBCDAO implements LikeArt_interface {
	
	@Override
	public void insert(LikeArt LikeArt) {
		String sql =
				"INSERT INTO cga105g2.LIKE_ART (ART_ID, MEM_ID) VALUES (?, ?)";

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){


			pstmt.setInt(1, LikeArt.getArtId());
			pstmt.setInt(2, LikeArt.getMemId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 

	}

	@Override
	public List<LikeArt> getAll() {
		String sql =
				"SELECT * FROM cga105g2.LIKE_ART";
		List<LikeArt> list = new ArrayList<LikeArt>();
		LikeArt LikeArt = null;

		ResultSet rs = null;

		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				LikeArt = new LikeArt();
				LikeArt.setLikeId(rs.getInt("LIKE_ID"));
				LikeArt.setArtId(rs.getInt("ART_ID"));
				LikeArt.setMemId(rs.getInt("MEM_ID"));

				list.add(LikeArt); // Store the row in the list
			}

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		
		} 
		return list;
	}

	@Override
	public LikeArt getById(Integer ArtId){
		
		String sql =
				"SELECT * FROM cga105g2.LIKE_ART where ART_ID = ?";
		LikeArt LikeArt = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			pstmt.setInt(1, ArtId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				LikeArt = new LikeArt();
				LikeArt.setLikeId(rs.getInt("LIKE_ID"));
				LikeArt.setArtId(rs.getInt("ART_ID"));
				LikeArt.setMemId(rs.getInt("MEM_ID"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		
		} 
		return LikeArt;
	}

	@Override
	public void delete(Integer likeId) {
		String sql = 
				"DELETE FROM cga105g2.LIKE_ART where LIKE_ID = ?";


		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){


			pstmt.setInt(1, likeId);

			pstmt.executeUpdate();

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 
	}
	public static void main(String[] args) {
		LikeArtJDBCDAO likeArtJDBCDAO = new LikeArtJDBCDAO();
		LikeArt LikeArt = new LikeArt();
		//新增
//		LikeArt.setArtId(5);
//		LikeArt.setMemId(6);
//		likeArtJDBCDAO.insert(LikeArt);
		//查詢by文章ID
//		LikeArt = likeArtJDBCDAO.getById(5);
//		System.out.println("ART_ID:" + LikeArt.getArtId());
//		System.out.println("MEM_ID:" + LikeArt.getMemId());
//		System.out.println("=====================");
//		
//		likeArtJDBCDAO.delete(4);
//		
//		List<LikeArt> list = likeArtJDBCDAO.getAll();
//		for (LikeArt a : list) {
//			System.out.println("ART_ID:" + a.getArtId());
//			System.out.println("MEM_ID:" + a.getMemId());
//			System.out.println("=====================");
//		}
	}
}
