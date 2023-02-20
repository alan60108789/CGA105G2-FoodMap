package com.point.model.Point.dao.impl;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.core.common.Common;
import com.member.model.Member.pojo.Member;
import com.point.model.Point.dao.PointDAO_interface;
import com.point.model.Point.pojo.Point;
import com.point.model.PointGoods.pojo.PointGoods;

public class PointDAO implements PointDAO_interface {
	@Override
	public void insert(Point point) {
		String sql = "INSERT INTO cga105g2.point (MEM_ID, POINT_CHANGE, POINT_NUMBER) VALUES (?, ?, ?)";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, point.getMemId());
			pstmt.setString(2, point.getPointChange());
			pstmt.setInt(3, point.getPointNumber());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Point point) {
		String sql = "UPDATE cga105g2.point set POINT_CHANGE=?, POINT_NUMBER=? where POINT_ID = ?";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setString(1, point.getPointChange());
			pstmt.setInt(2, point.getPointNumber());
			pstmt.setInt(3, point.getPointId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer pointid) {
		String sql = "DELETE FROM cga105g2.point where POINT_ID = ?";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, pointid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Point getByPK(Integer pointid) {
		Point point = null;
		String sql = "SELECT POINT_ID, MEM_ID, POINT_CHANGE, POINT_NUMBER FROM cga105g2.point WHERE POINT_ID = ? ";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, pointid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
            	point = new Point();
            	point.setPointId(rs.getInt("POINT_ID"));
            	point.setMemId(rs.getInt("MEM_ID"));
            	point.setPointChange(rs.getString("POINT_CHANGE"));
            	point.setPointNumber(rs.getInt("POINT_NUMBER"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return point;
	}

	@Override
	public List<Point> getAll() {
		List<Point> list = new ArrayList<Point>();
		String sql = "SELECT POINT_ID, MEM_ID, POINT_CHANGE, POINT_NUMBER FROM cga105g2.point order by POINT_ID ";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()){
            	Point point = new Point();
            	point.setPointId(rs.getInt("POINT_ID"));
            	point.setMemId(rs.getInt("MEM_ID"));
            	point.setPointChange(rs.getString("POINT_CHANGE"));
            	point.setPointNumber(rs.getInt("POINT_NUMBER"));
				list.add(point);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Point> getAllByMemId(Integer memId) {
		List<Point> list = new ArrayList<Point>();
		String sql = "SELECT POINT_ID, MEM_ID, POINT_CHANGE, POINT_NUMBER FROM cga105g2.point WHERE MEM_ID = ? ";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {
			pstmt.setInt(1, memId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Point point = new Point();
            	point.setPointId(rs.getInt("POINT_ID"));
            	point.setMemId(rs.getInt("MEM_ID"));
            	point.setPointChange(rs.getString("POINT_CHANGE"));
            	point.setPointNumber(rs.getInt("POINT_NUMBER"));
            	list.add(point);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {

		PointDAO_interface dao = new PointDAO();

		// 新增
//		Point point = new Point();
//		point.setMemId(1);
//		point.setPointChange("insert");
//		point.setPointNumber(2);
//		dao.insert(point);

		// 修改
//		Point point2 = new Point();
//		point2.setPointId(2);
//		point2.setPointChange("update");
//		point2.setPointNumber(3);
//		dao.update(point2);

		// 刪除
//		dao.delete(1);

		
		// 查詢
//		Point point3 = dao.getByPK(4);
//		System.out.print(point3.getPointId() + ",");
//		System.out.print(point3.getMemId() + ",");
//		System.out.print(point3.getPointChange() + ",");
//		System.out.println(point3.getPointNumber());

		// 查詢
		List<Point> list = dao.getAll();
		for (Point aPoint : list) {
			System.out.print(aPoint.getPointId() + ",");
			System.out.print(aPoint.getMemId() + ",");
			System.out.print(aPoint.getPointChange() + ",");
			System.out.print(aPoint.getPointNumber());
			System.out.println();
		}
		
		
	}
}
