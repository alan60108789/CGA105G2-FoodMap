package com.waiting.model.dao.impl;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.URL;
import static com.core.common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;
import com.waiting.model.dao.StandbyDAO_interface;
import com.waiting.model.pojo.Standby;

public class StandbyDAO implements StandbyDAO_interface {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer insert(Standby standbyVo) {
		String INSERT_STMT = " INSERT INTO cga105g2.standby(`STORE_ID`, `STA_NAME`, `STA_PHONE`, `STA_NUMBER`) VALUES  (?, ?, ?, ?)";
		int id = 0;
		// java.util.Date utilDate = new java.util.Date();
		// java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(INSERT_STMT, PreparedStatement.RETURN_GENERATED_KEYS);) {
			ResultSet rs = null;

			pstm.setInt(1, standbyVo.getStoreId());
			pstm.setString(2, standbyVo.getStaName());
			pstm.setString(3, standbyVo.getStaPhone());
			pstm.setInt(4, standbyVo.getStaNumber());

			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return id;

	}

	@Override
	public void update(Standby standbyVo) {
		String UPDATE = "update cga105g2.standby set sta_status =? where sta_id =?;";
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(UPDATE)) {
			pstm.setInt(1, standbyVo.getStaStatus());
			pstm.setInt(2, standbyVo.getStaId());
			pstm.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	};
	//打烊案off清空後位表
	@Override
	public void resetStandBy() {
		String sql = "TRUNCATE TABLE cga105g2.standby;";
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			 java.sql.Statement stm = con.createStatement();) {
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	};
	@Override
	public void delete(Integer staId) {
		String DELETE = "DELETE FROM cga105g2.`standby` where sta_id = ?";
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(DELETE);) {
			pstm.setInt(1, staId);
			pstm.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
	}
	@Override
	public Standby findByPrimaryKey(Integer staId) {
		String GET_ONE_STMT = "select  sta_id ,store_id,sta_name,sta_phone,sta_number,sta_number,sta_time,sta_status from cga105g2.`standby`where STA_ID = ?;";
		Standby standbyVo = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(GET_ONE_STMT);) {
			pstm.setInt(1, staId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				standbyVo = new Standby();
				standbyVo.setStaId(rs.getInt("sta_Id"));
				standbyVo.setStoreId(rs.getInt("store_Id"));
				standbyVo.setStaName(rs.getString("sta_Name"));
				standbyVo.setStaPhone(rs.getString("sta_Phone"));
				standbyVo.setStaNumber(rs.getInt("sta_Number"));
				standbyVo.setStaTime(rs.getTimestamp("sta_Time"));
				standbyVo.setStaStatus(rs.getInt("sta_Status"));

			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return standbyVo;
	}

	@Override
	public List<Standby> getAll() {
		String GET_ALL_STMT = "SELECT `STA_ID`, `STORE_ID`, `STA_NAME`, `STA_PHONE`, `STA_NUMBER`, `STA_TIME`, `STA_STATUS`\r\n"
				+ "FROM cga105g2.`standby` where STA_STATUS = 0    order by sta_id";
		List<Standby> list = new ArrayList<Standby>();
		Standby standbyVo = null;

		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(GET_ALL_STMT);) {

			rs = pstm.executeQuery();
			while (rs.next()) {
				standbyVo = new Standby();
				standbyVo.setStaId(rs.getInt("sta_Id"));
				standbyVo.setStoreId(rs.getInt("store_Id"));
				standbyVo.setStaName(rs.getString("Sta_Name"));
				standbyVo.setStaPhone(rs.getString("sta_Phone"));
				standbyVo.setStaNumber(rs.getInt("sta_Number"));
				standbyVo.setStaTime(rs.getTimestamp("sta_Time"));
				standbyVo.setStaStatus(rs.getInt("sta_Status"));

				list.add(standbyVo);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}

	@Override
	public Integer standByCount() {
		String sql = "SELECT COUNT(1) FROM cga105g2.STANDBY where sta_status = 0;";
		int staCount = 0;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(sql)) {

			rs = pstm.executeQuery();
			while (rs.next()) {

				staCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staCount;
	}

	@Override
	public Integer getStandbyId() {
		String sql = "SELECT LAST_INSERT_ID();";
		int lastInsertId = 0;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstm = con.prepareStatement(sql)) {
			rs = pstm.executeQuery();
			while (rs.next()) {

				lastInsertId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lastInsertId;
	}

}
