package com.emp.model.EmployeeRoot.dao;

import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;

import java.util.List;



public interface EmployeeRootDAO_interface {
	public void insert(EmployeeRoot EmployeeRootVO);
	public void delete(Integer EMP_ID,Integer ROOT_ID);
	public List<EmployeeRoot> findByEMP_ID(Integer EMP_ID);
	public List<EmployeeRoot> findByROOT_ID(Integer EMP_ID);
	public List<EmployeeRoot> getAll();



}
