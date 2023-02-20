package com.emp.model.Employee.dao.impl;

import com.core.common.Common;
import com.emp.model.Employee.dao.EmployeeDAO_interface;
import com.emp.model.Employee.pojo.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(Employee EmployeeVO) {
		String INSERT_STMT = "INSERT INTO cga105g2.employee (EMP_ACC,EMP_PWD) VALUES (?, ?)";
		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setString(1, EmployeeVO.getEmpAcc());
			pstmt.setString(2, EmployeeVO.getEmpPwd());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public Employee update(Employee EmployeeVO) {
		String UPDATE = "UPDATE cga105g2.employee set EMP__STATUS=?,  EMP_ACC=?, EMP_PWD=?, EMP_PER=?, EMP_TIME=?, EMP_RTIME=? where EMP_ID = ?";

		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setInt(1, EmployeeVO.getEmpStatus());
			pstmt.setString(2, EmployeeVO.getEmpAcc());
			pstmt.setString(3, EmployeeVO.getEmpPwd());
			pstmt.setInt(4, EmployeeVO.getEmpPer());
			pstmt.setDate(5, EmployeeVO.getEmpTime());
			pstmt.setDate(6, EmployeeVO.getEmpRtime());
			pstmt.setInt(7, EmployeeVO.getEmpId());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}

        return EmployeeVO;
    }

	@Override
	public Employee findByEMP_ID(Integer EMP_ID) {
		String GET_ONE_STMT = "SELECT EMP__STATUS,EMP_ID,EMP_ACC,EMP_PWD,EMP_PER,EMP_TIME,EMP_RTIME FROM cga105g2.employee where EMP_ID = ?";
		Employee employeeVO = null;

		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setInt(1, EMP_ID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new Employee();
				employeeVO.setEmpStatus(rs.getInt("EMP__STATUS"));
				employeeVO.setEmpId(rs.getInt("EMP_ID"));
				employeeVO.setEmpAcc(rs.getString("EMP_ACC"));
				employeeVO.setEmpPwd(rs.getString("EMP_PWD"));
				employeeVO.setEmpPer(rs.getInt("EMP_PER"));
				employeeVO.setEmpTime(rs.getDate("EMP_TIME"));
				employeeVO.setEmpRtime(rs.getDate("EMP_RTIME"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return employeeVO;
	}
	@Override
	public Employee findByEmpAcc(String empAcc) {
		String GET_ONE_STMT = "SELECT * FROM cga105g2.employee where EMP_ACC = ?";
		Employee employee = null;

		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			pstmt.setString(1, empAcc);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				employee = new Employee();
				employee.setEmpStatus(rs.getInt("EMP__STATUS"));
				employee.setEmpId(rs.getInt("EMP_ID"));
				employee.setEmpAcc(rs.getString("EMP_ACC"));
				employee.setEmpPwd(rs.getString("EMP_PWD"));
				employee.setEmpPer(rs.getInt("EMP_PER"));
				employee.setEmpTime(rs.getDate("EMP_TIME"));
				employee.setEmpRtime(rs.getDate("EMP_RTIME"));

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return employee;
	}

	@Override
	public List<Employee> getAll() {
		List<Employee> list = new ArrayList<Employee>();
		Employee employeeVO = null;
		String GET_ALL_STMT = "SELECT * FROM cga105g2.employee order by EMP_ID";
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(Common.URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY)) {

			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new Employee();
				employeeVO.setEmpStatus(rs.getInt("EMP__STATUS"));
				employeeVO.setEmpId(rs.getInt("EMP_ID"));
				employeeVO.setEmpAcc(rs.getString("EMP_ACC"));
				employeeVO.setEmpPwd(rs.getString("EMP_PWD"));
				employeeVO.setEmpPer(rs.getInt("EMP_PER"));
				employeeVO.setEmpTime(rs.getDate("EMP_TIME"));
				employeeVO.setEmpRtime(rs.getDate("EMP_RTIME"));
				list.add(employeeVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
		return list;
	}


	public static void main(String[] args) {

		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

		// 新增
//		EmployeeVO add = new EmployeeVO();
//		add.setEmpAcc("TEST1");
//		add.setEmpPwd("SUCCESS");
//		dao.insert(add);

		// 查詢byId
		Employee searchId = dao.findByEMP_ID(4);
		System.out.print(searchId.getEmpStatus() + ",");
		System.out.print(searchId.getEmpId() + ",");
		System.out.print(searchId.getEmpAcc() + ",");
		System.out.print(searchId.getEmpPwd() + ",");
		System.out.print(searchId.getEmpPer() + ",");
		System.out.print(searchId.getEmpTime() + ",");
		System.out.println(searchId.getEmpRtime());
		System.out.println("---------------------");

		// 查詢全部
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO searchAll : list) {
//			System.out.print(searchAll.getEmpStatus() + ",");
//			System.out.print(searchAll.getEmpId() + ",");
//			System.out.print(searchAll.getEmpAcc() + ",");
//			System.out.print(searchAll.getEmpPwd() + ",");
//			System.out.print(searchAll.getEmpPer() + ",");
//			System.out.print(searchAll.getEmpTime() + ",");
//			System.out.println(searchAll.getEmpRtime());
//		}

		// 修改
//		EmployeeVO empVO2 = new EmployeeVO();
//		empVO2.setEmpId(empVO3.getEmpId());
//		empVO2.setEmpStatus(1);
//		empVO2.setEmpAcc(empVO3.getEmpAcc());
//		empVO2.setEmpPwd(empVO3.getEmpPwd());
//		empVO2.setEmpPer(1);
//		empVO2.setEmpTime(empVO3.getEmpTime());
//		empVO2.setEmpRtime(empVO3.getEmpRtime());
//		dao.update(empVO2);

//				empVO2.EmployeeVO(1,1){
//
//				}

	}

}
