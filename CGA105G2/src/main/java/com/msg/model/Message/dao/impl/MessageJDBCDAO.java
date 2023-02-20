package com.msg.model.Message.dao.impl;

import com.core.common.Common;
import com.msg.model.Message.dao.Message_interface;
import com.msg.model.Message.pojo.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageJDBCDAO implements Message_interface {

	@Override
	public void insert(Message Message) {
		
		String sql = "INSERT INTO cga105g2.MESSAGE (FOLLOW_ID, MES_TEXT) VALUES (?, ?)";

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, Message.getFollowId());
			pstmt.setString(2, Message.getMesText());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
	}

	@Override
	public List<Message> getAll() {
		
		String sql = "SELECT * FROM cga105g2.MESSAGE";
		List<Message> list = new ArrayList<Message>();
		Message Message = null;

		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				Message = new Message();
				Message.setMesId(rs.getInt("MES_ID"));
				Message.setFollowId(rs.getInt("FOLLOW_ID"));
				Message.setMesText(rs.getString("MES_TEXT"));
				Message.setMesTime(rs.getTimestamp("MES_TIME"));

				list.add(Message);
			}

		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 
		return list;
	}

	@Override
	public Message getByFollowId(Integer followId) {
		String sql = "SELECT * FROM cga105g2.MESSAGE where FOLLOW_ID = ?";
		Message Message = null;
		ResultSet rs = null;

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
			

			pstmt.setInt(1, followId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Message = new Message();
				Message.setMesId(rs.getInt("MES_ID"));
				Message.setFollowId(rs.getInt("FOLLOW_ID"));
				Message.setMesText(rs.getString("MES_TEXT"));
				Message.setMesTime(rs.getTimestamp("MES_TIME"));

			}

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			
		} 
		return Message;
	}
	
	public List<Message> getAllByFollowId(Integer followId) {
		
		String sql = "SELECT * FROM cga105g2.MESSAGE where FOLLOW_ID = ?";
		List<Message> list = new ArrayList<Message>();
		Message Message = null;

		ResultSet rs = null;

		try(Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

			
			pstmt.setInt(1, followId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Message = new Message();
				Message.setMesText(rs.getString("MES_TEXT"));
				Message.setMesTime(rs.getTimestamp("MES_TIME"));

				list.add(Message);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}

	@Override
	public void delete(Integer MesId) {
		
		String sql = "DELETE FROM cga105g2.MESSAGE where MES_ID = ?";

		try (Connection con= DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
	         PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

			pstmt.setInt(1, MesId);

			pstmt.executeUpdate();

		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} 
	}
	public static void main(String[] args) {
		MessageJDBCDAO messageJDBCDAO = new MessageJDBCDAO();
		Message Message = new Message();
		//新增
//		Message.setFollowId(1);
//		Message.setMesText("早安早安早安早安早安早安");
//		messageJDBCDAO.insert(Message);
		//依照追蹤編號單一查詢
//		Message = messageJDBCDAO.getByFollowId(1);
//		System.out.println("MES_ID:" + Message.getMesId() + ",");
//		System.out.println("FOLLOW_ID:" + Message.getFollowId() + ",");
//		System.out.println("MES_TEXT:" + Message.getMesText() + ",");
//		System.out.println("MES_TIME:" + Message.getMesTime() + ",");
		
		//依照FollowId查詢全部訊息
//		List<Message> list = messageJDBCDAO.getAllByFollowId(1);
//		for (Message a : list) {
//		System.out.println("MES_TEXT:" + a.getMesText());
//		System.out.println("MES_TIME:" + a.getMesTime());
//		System.out.println("=====================");
//		}
		//刪除
//		messageJDBCDAO.delete(5);
		//查詢全部
//		List<Message> list = messageJDBCDAO.getAll();
//		for (Message a : list) {
//			System.out.println("MES_ID:" + a.getMesId() + ",");
//			System.out.println("FOLLOW_ID:" + a.getFollowId() + ",");
//			System.out.println("MES_TEXT:" + a.getMesText() + ",");
//			System.out.println("MES_TIME:" + a.getMesTime() + ",");
//			System.out.println("=====================");
//		}
	}
}
