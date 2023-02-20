package com.emp.model.EmployeeRoot.pojo;

import javax.persistence.*;

import com.emp.model.Employee.pojo.Employee;
import com.emp.model.Root.pojo.Root;
import com.emp.model.service.EmployeeService;

@Entity
@IdClass(EmployeeRoot_PK.class)
@Table(catalog = "cga105g2",name = "employee_root")
public class EmployeeRoot {


  @Id
  @Column(name = "EMP_ID")
  private Integer empId;
  @Id
  @Column(name = "ROOT_ID")
  private Integer rootId;


  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }


  public Integer getRootId() {
    return rootId;
  }

  public void setRootId(Integer rootId) {
    this.rootId = rootId;
  }
  public Root getRoot()
  {
      EmployeeService empSvc = new EmployeeService();
      Root root = empSvc.findByRootId(rootId);
      return root;
  }

  public Employee getEmp()
  {
      EmployeeService empSvc = new EmployeeService();
      Employee emp = empSvc.getOneEmp(empId);
      return emp;
  }

}
