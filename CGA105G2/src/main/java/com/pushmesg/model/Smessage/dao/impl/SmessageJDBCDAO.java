package com.pushmesg.model.Smessage.dao.impl;

import com.core.common.Common;
import com.pushmesg.model.Smessage.dao.SmessageDAO_interface;
import com.pushmesg.model.Smessage.pojo.Smessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class SmessageJDBCDAO implements SmessageDAO_interface {

	@Override
	public void insert(Smessage smessageVO) {
		String sql = "INSERT INTO cga105g2.smessage (SUB_ID, SMESSAGE_TXET) VALUES (?,?);";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, smessageVO.getSubId());
			pstmt.setString(2, smessageVO.getSmessageTxet());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}
	@Override
	public List<Smessage> getById(Integer id) {
		String sql = "SELECT SMESSAGE_ID, SUB_ID, SMESSAGE_TXET, SMESSAGE_TIME FROM cga105g2.smessage where SUB_ID=? order by SMESSAGE_TIME";
		Smessage smessage = null;
		List<Smessage> list = new ArrayList<Smessage>();
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				smessage = new Smessage();
				smessage.setSmessageId(rs.getInt("SMESSAGE_ID"));
				smessage.setSubId(rs.getInt("SUB_ID"));
				smessage.setSmessageTxet(rs.getString("SMESSAGE_TXET"));
				smessage.setSmessageTime(rs.getTimestamp("SMESSAGE_TIME"));
				list.add(smessage);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}
	public void deleteById(Integer id) {
		String sql="DELETE from cga105g2.smessage where SMESSAGE_ID=?";
		try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
			PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
	}

}
