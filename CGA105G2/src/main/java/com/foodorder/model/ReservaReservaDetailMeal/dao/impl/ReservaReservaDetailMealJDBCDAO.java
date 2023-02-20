package com.foodorder.model.ReservaReservaDetailMeal.dao.impl;

import com.core.common.Common;
import com.foodorder.model.ReservaReservaDetailMeal.dao.ReservaReservaDetailMealDAO_interface;
import com.foodorder.model.ReservaReservaDetailMeal.pojo.ReservaReservaDetailMeal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaReservaDetailMealJDBCDAO implements ReservaReservaDetailMealDAO_interface {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ReservaReservaDetailMeal> getById(Integer id, String chooseId) {
		String where = " r." + chooseId + " = ?";
		String sql = "SELECT r.REN_ID, r.REN_NAME, r.REN_PHONE, r.REN_TIME, r.REN_STATUS, r.REN_DATE, r.REN_HEADCOUNT, GROUP_CONCAT(concat(rd.RD_QUANTITY,\"*\",m.MEAL_NAME)) MEAL_NAME_LIST, s.STORE_NAME, a.ART_SCORE FROM cga105g2.reserva r\r\n"
				+ "join cga105g2.reserva_detail rd on r.REN_ID = rd.REN_ID\r\n"
				+ "join cga105g2.meal m on rd.MEAL_ID = m.MEAL_ID\r\n"
				+ "join cga105g2.store s on s.STORE_ID = r.STORE_ID\r\n"
				+ "right join cga105g2.article a on a.STORE_ID = r.STORE_ID and a.MEM_ID = r.MEM_ID\r\n" + "where"
				+ where + "\r\n" + "GROUP BY r.REN_ID;";
		List<ReservaReservaDetailMeal> list = new ArrayList<ReservaReservaDetailMeal>();
		try (Connection con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			 PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ReservaReservaDetailMeal reservaReservaDetailMeal = new ReservaReservaDetailMeal();
				reservaReservaDetailMeal.setRenId(rs.getInt("REN_ID"));
				reservaReservaDetailMeal.setRenName(rs.getString("REN_NAME"));
				reservaReservaDetailMeal.setRenPhone(rs.getString("REN_PHONE"));
				reservaReservaDetailMeal.setRenTime(rs.getString("REN_TIME"));

				reservaReservaDetailMeal.setRenStatus(rs.getInt("REN_STATUS"));
				reservaReservaDetailMeal.setRenDate(rs.getDate("REN_DATE"));
				reservaReservaDetailMeal.setRenHeadcount(rs.getInt("REN_HEADCOUNT"));
				reservaReservaDetailMeal.setMealNameList(rs.getString("MEAL_NAME_LIST"));
				reservaReservaDetailMeal.setStoreName(rs.getString("STORE_NAME"));
				reservaReservaDetailMeal.setArtScore(rs.getInt("ART_SCORE"));
				list.add(reservaReservaDetailMeal);

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;

	}



}
