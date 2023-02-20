package com.foodorder.model.ReservaDetail.dao.impl;

import com.core.common.Common;
import com.foodorder.model.ReservaDetail.dao.ReservaDetailDAO_interface;
import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReservaDetailJDBCDAO implements ReservaDetailDAO_interface {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(ReservaDetail reservaDetailVO) {
		String sql = "INSERT INTO cga105g2.reserva_detail (REN_ID, MEAL_ID, RD_QUANTITY, PD_PRICE) VALUES (?,?,?,?);";
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, reservaDetailVO.getRenId());
			pstmt.setInt(2, reservaDetailVO.getMealId());
			pstmt.setInt(3, reservaDetailVO.getRdQuantity());
			pstmt.setInt(4, reservaDetailVO.getPdPrice());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

	}

	@Override
	public List<ReservaDetail> getByPK(Integer id, String pk) {
		List<ReservaDetail> list = new ArrayList<ReservaDetail>();
		String sql = "select * from cga105g2.reserva_detail where";
		String where = null;
		if (pk.equals("renId")) {
			where = " REN_ID=?;";
		} else {
			where = " MEAL_ID=?;";
		}
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql + where, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservaDetail reservaDetail = new ReservaDetail();
				reservaDetail.setRenId(rs.getInt("REN_ID"));
				reservaDetail.setMealId(rs.getInt("MEAL_ID"));
				reservaDetail.setRdQuantity(rs.getInt("RD_QUANTITY"));
				reservaDetail.setPdPrice(rs.getInt("PD_PRICE"));
				list.add(reservaDetail);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}

	@Override
	public List<ReservaDetail> getAll() {
		String sql = "select * from cga105g2.reserva_detail";
		List<ReservaDetail> list = new ArrayList<ReservaDetail>();
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservaDetail reservaDetail = new ReservaDetail();
				reservaDetail.setRenId(rs.getInt("REN_ID"));
				reservaDetail.setMealId(rs.getInt("MEAL_ID"));
				reservaDetail.setRdQuantity(rs.getInt("RD_QUANTITY"));
				reservaDetail.setPdPrice(rs.getInt("PD_PRICE"));
				list.add(reservaDetail);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}


}
