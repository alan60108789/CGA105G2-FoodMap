package com.emp.model.Root.dao.impl;

import com.core.common.Common;
import com.emp.model.Root.dao.RootDAO_interface;
import com.emp.model.Root.pojo.Root;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class RootJDBCDAO implements RootDAO_interface {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Root> findByROOT_TEXT(String ROOT_TEXT) {
		String GET_ONE_STMT = "SELECT * FROM cga105g2.root where ROOT_TEXT LIKE  ? ";
		List<Root> list = new ArrayList<Root>();
		Root rootvo = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setString(1, ROOT_TEXT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				rootvo = new Root();
				rootvo.setRootId(rs.getInt("ROOT_ID"));
				rootvo.setRootText(rs.getString("ROOT_TEXT"));
				list.add(rootvo);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;

	}

	@Override
	public List<Root> getAll() {
		String GET_ALL_STMT = "SELECT * FROM cga105g2.root order by ROOT_ID";
		List<Root> list = new ArrayList<Root>();

		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Root rootVO = new Root();
				rootVO.setRootId(rs.getInt("ROOT_ID"));
				rootVO.setRootText(rs.getString("ROOT_TEXT"));
				list.add(rootVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}


	public Root findByRootId(Integer integer) {
		String find = "SELECT * FROM cga105g2.root where ROOT_ID LIKE  ? ";
		Root rootvo = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(find, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, integer);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rootvo = new Root();
				rootvo.setRootId(rs.getInt("ROOT_ID"));
				rootvo.setRootText(rs.getString("ROOT_TEXT"));
			}

		}
		catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		}
		return rootvo;
	}
	public static void main(String args[ ]) {
		RootJDBCDAO aa = new RootJDBCDAO();
		aa.findByRootId(1);
		System.out.println(aa);
	}
}
