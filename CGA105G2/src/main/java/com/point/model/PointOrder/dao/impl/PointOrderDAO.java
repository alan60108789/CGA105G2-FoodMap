package com.point.model.PointOrder.dao.impl;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.standard.DateTimeAtCompleted;

import com.core.common.Common;
import com.point.model.PointGoods.pojo.PointGoods;
import com.point.model.PointOrder.dao.PointOrderDAO_interface;
import com.point.model.PointOrder.pojo.PointOrder;

public class PointOrderDAO implements PointOrderDAO_interface {

	@Override
	public void insert(PointOrder pointorder) {
		String sql = "INSERT INTO cga105g2.point_order (MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, pointorder.getMemId());
			pstmt.setInt(2, pointorder.getPdId());
			pstmt.setInt(3, pointorder.getPoPrice());
			pstmt.setString(4, pointorder.getPoText());
			pstmt.setInt(5, pointorder.getPoStatus());
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
			pstmt.setTimestamp(6, sqlTimestamp);
//			pstmt.setInt(7, pointorder.getEmpId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PointOrder pointorder) {
		String sql = "UPDATE cga105g2.point_order set MEM_ID=?, PD_ID=?, PO_PRICE=?, PO_TEXT=?, PO_STATUS=?, PO_UTIME=? where PO_ID = ?";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, pointorder.getMemId());
			pstmt.setInt(2, pointorder.getPdId());
			pstmt.setInt(3, pointorder.getPoPrice());
			pstmt.setString(4, pointorder.getPoText());
			pstmt.setInt(5, pointorder.getPoStatus());
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
			pstmt.setTimestamp(6, sqlTimestamp);
			pstmt.setInt(7, pointorder.getPoId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer po_id) {
		String sql = "DELETE FROM cga105g2.point_order where PO_ID = ?";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, po_id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PointOrder getByPK(Integer po_id) {
		PointOrder pointorder = null;
		String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order WHERE PO_ID = ? ";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, po_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pointorder = new PointOrder();
				pointorder.setPoId(rs.getInt("PO_ID"));
				pointorder.setMemId(rs.getInt("MEM_ID"));
				pointorder.setPdId(rs.getInt("PD_ID"));
				pointorder.setPoPrice(rs.getInt("PO_PRICE"));
				pointorder.setPoText(rs.getString("PO_TEXT"));
				pointorder.setPoStatus(rs.getInt("PO_STATUS"));
				pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
				pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
				pointorder.setEmpId(rs.getInt("EMP_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pointorder;
	}

	@Override
	public List<PointOrder> getAll() {
		List<PointOrder> list = new ArrayList<PointOrder>();
		String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order order by PO_ID";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PointOrder pointorder = new PointOrder();
				pointorder.setPoId(rs.getInt("PO_ID"));
				pointorder.setMemId(rs.getInt("MEM_ID"));
				pointorder.setPdId(rs.getInt("PD_ID"));
				pointorder.setPoPrice(rs.getInt("PO_PRICE"));
				pointorder.setPoText(rs.getString("PO_TEXT"));
				pointorder.setPoStatus(rs.getInt("PO_STATUS"));
				pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
				pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
				pointorder.setEmpId(rs.getInt("EMP_ID"));
				list.add(pointorder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<PointOrder> getBackOrder() {
		List<PointOrder> list = new ArrayList<PointOrder>();
		String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order WHERE PO_STATUS = 0";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PointOrder pointorder = new PointOrder();
				pointorder.setPoId(rs.getInt("PO_ID"));
				pointorder.setMemId(rs.getInt("MEM_ID"));
				pointorder.setPdId(rs.getInt("PD_ID"));
				pointorder.setPoPrice(rs.getInt("PO_PRICE"));
				pointorder.setPoText(rs.getString("PO_TEXT"));
				pointorder.setPoStatus(rs.getInt("PO_STATUS"));
				pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
				pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
				pointorder.setEmpId(rs.getInt("EMP_ID"));
				list.add(pointorder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public void updateStatus(PointOrder pointorder) {
		String sql = "UPDATE cga105g2.point_order set PO_STATUS=?, PO_UTIME=? where PO_ID = ?";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, pointorder.getPoStatus());
			java.util.Date utilDate = new java.util.Date();
			java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
			pstmt.setTimestamp(2, sqlTimestamp);
			pstmt.setInt(3, pointorder.getPoId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<PointOrder> getMemOrder(Integer memId) {
		List<PointOrder> list = new ArrayList<PointOrder>();
		String sql = "SELECT PO_ID, MEM_ID, PD_ID, PO_PRICE, PO_TEXT, PO_STATUS, PO_TIME, PO_UTIME, EMP_ID FROM cga105g2.point_order WHERE MEM_ID= ?";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, memId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PointOrder pointorder = new PointOrder();
				pointorder.setPoId(rs.getInt("PO_ID"));
				pointorder.setMemId(rs.getInt("MEM_ID"));
				pointorder.setPdId(rs.getInt("PD_ID"));
				pointorder.setPoPrice(rs.getInt("PO_PRICE"));
				pointorder.setPoText(rs.getString("PO_TEXT"));
				pointorder.setPoStatus(rs.getInt("PO_STATUS"));
				pointorder.setPoTime(rs.getTimestamp("PO_TIME"));
				pointorder.setPoUtime(rs.getTimestamp("PO_UTIME"));
				pointorder.setEmpId(rs.getInt("EMP_ID"));
				list.add(pointorder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

		PointOrderDAO_interface dao = new PointOrderDAO();

		// 新增
//		PointOrder pointorder = new PointOrder();
//		pointorder.setMemId(1);
//		pointorder.setPdId(1);
//		pointorder.setPoPrice(200);
//		pointorder.setPoText("TEST");
//		pointorder.setPoStatus(0);
//		pointorder.setPoTime(null);
//		pointorder.setEmpId(3);
//		dao.insert(pointorder);

		// 修改
//		PointOrder pointorder2 = new PointOrder();
//		pointorder2.setMemId(1);
//		pointorder2.setPdId(1);
//		pointorder2.setPoPrice(400);
//		pointorder2.setPoText("TEST2");
//		pointorder2.setPoStatus(1);
//		pointorder2.setPoUtime(null);
//		pointorder2.setPoId(3);
//		dao.update(pointorder2);

		// 刪除
//		dao.delete(3);
		
		// 查詢
//		PointOrder pointorder3 = dao.getByPK(1);
//		System.out.print(pointorder3.getPoId() + ",");
//		System.out.print(pointorder3.getMemId() + ",");
//		System.out.print(pointorder3.getPdId() + ",");
//		System.out.print(pointorder3.getPoPrice() + ",");
//		System.out.print(pointorder3.getPoText() + ",");
//		System.out.print(pointorder3.getPoStatus() + ",");
//		System.out.print(pointorder3.getPoTime() + ",");
//		System.out.print(pointorder3.getPoUtime() + ",");
//		System.out.println(pointorder3.getEmpId());

		// 查詢
//		List<PointOrder> list = dao.getAll();
//		for (PointOrder PO : list) {
//		System.out.print(PO.getPoId() + ",");
//		System.out.print(PO.getMemId() + ",");
//		System.out.print(PO.getPdId() + ",");
//		System.out.print(PO.getPoPrice() + ",");
//		System.out.print(PO.getPoText() + ",");
//		System.out.print(PO.getPoStatus() + ",");
//		System.out.print(PO.getPoTime() + ",");
//		System.out.print(PO.getPoUtime() + ",");
//		System.out.println(PO.getEmpId());
//		}

	}
}
