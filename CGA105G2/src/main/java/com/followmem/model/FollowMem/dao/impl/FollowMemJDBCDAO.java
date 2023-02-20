package com.followmem.model.FollowMem.dao.impl;

import com.core.common.Common;
import com.followmem.model.FollowMem.dao.FollowMem_interface;
import com.followmem.model.FollowMem.pojo.FollowMem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FollowMemJDBCDAO implements FollowMem_interface {
	
	static {
		  try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 }

	@Override
	public void insert(FollowMem FollowMem) {
		String sql =
				"INSERT INTO cga105g2.FOLLOW_MEM (MEM_ID1, MEM_ID2) VALUES (?, ?)";

		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {


			pstmt.setInt(1, FollowMem.getMemId1());
			pstmt.setInt(2, FollowMem.getMemId2());

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} 

	}

	@Override
	public List<FollowMem> getAll() {
		String sql =
				"SELECT * FROM cga105g2.FOLLOW_MEM";
		List<FollowMem> list = new ArrayList<FollowMem>();
		FollowMem FollowMem = null;


		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				FollowMem = new FollowMem();
				FollowMem.setFollowId(rs.getInt("FOLLOW_ID"));
				FollowMem.setMemId1(rs.getInt("MEM_ID1"));
				FollowMem.setMemId2(rs.getInt("MEM_ID2"));

				list.add(FollowMem);
			}

			
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 
		return list;
	}

	@Override
	public FollowMem getByMemId1(Integer memId1) {
		String sql =
				"SELECT * FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ?";
		FollowMem FollowMem = null;

		ResultSet rs = null;

		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
;

			pstmt.setInt(1, memId1);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				FollowMem = new FollowMem();
				FollowMem.setFollowId(rs.getInt("FOLLOW_ID"));
				FollowMem.setMemId1(rs.getInt("MEM_ID1"));
				FollowMem.setMemId2(rs.getInt("MEM_ID2"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 
		return FollowMem;
	}
	@Override
	public List<FollowMem> getAllByMemId1(Integer memId) {
		String sql =
				"SELECT * FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ?";
		List<FollowMem> list = new ArrayList<FollowMem>();
		FollowMem FollowMem = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			pstmt.setInt(1, memId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				FollowMem = new FollowMem();
				FollowMem.setMemId1(rs.getInt("MEM_ID1"));
				FollowMem.setMemId2(rs.getInt("MEM_ID2"));

				list.add(FollowMem);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return list;
	}
	@Override
	public List<FollowMem> getAllByMemId1MemId2(Integer memId1, Integer memId2) {
		String sql =
				"SELECT * FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ? and MEM_ID2 = ? ";
		List<FollowMem> list = new ArrayList<FollowMem>();
		FollowMem FollowMem = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			pstmt.setInt(1, memId1);
			pstmt.setInt(2, memId2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				FollowMem = new FollowMem();
				FollowMem.setMemId1(rs.getInt("MEM_ID1"));
				FollowMem.setMemId2(rs.getInt("MEM_ID2"));

				list.add(FollowMem);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
		return list;
	}

	@Override
	public void delete(Integer memId1, Integer memId2) {
		String sql = 
				"DELETE FROM cga105g2.FOLLOW_MEM where MEM_ID1 = ? and MEM_ID2 = ?";

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, memId1);
			pstmt.setInt(2, memId2);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		
		} 
	}
	public static void main(String[] args) {
		FollowMemJDBCDAO followMemJDBCDAO = new FollowMemJDBCDAO();
		FollowMem FollowMem = new FollowMem();
//		FollowMem.setMemId1(4);
//		FollowMem.setMemId2(2);
//		followMemJDBCDAO.insert(FollowMem); // insert成功
		
		//單一查詢
		FollowMem = followMemJDBCDAO.getByMemId1(4);
		System.out.println("Follow_id:" + FollowMem.getFollowId());
		System.out.println("Follow_id:" + FollowMem.getMemId1());
		System.out.println("Follow_id:" + FollowMem.getMemId2());
		
		//依照會員id查詢全部
		List<FollowMem> list = followMemJDBCDAO.getAllByMemId1(1);
		for (FollowMem a : list) {
		System.out.println("Follow_id:" + a.getMemId1());
		System.out.println("Follow_id:" + a.getMemId2());
		System.out.println("=================");
		}
		
//		followMemJDBCDAO.delete(4, 2);
		//查詢全部
		List<FollowMem> list2 = followMemJDBCDAO.getAll();
		for (FollowMem FollowMem2 : list2) {
			System.out.println("Follow_id:" + FollowMem2.getFollowId());
			System.out.println("MEM_id1:" + FollowMem2.getMemId1());
			System.out.println("MEM_id2:" + FollowMem2.getMemId2());
			System.out.println("=================");
		}
		
	}
}


