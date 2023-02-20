package com.advertise.model.Advertise.pojo;

import javax.persistence.*;

import com.emp.model.service.EmployeeService;
import com.store.model.Store.pojo.Store;
import com.store.model.service.StoreService;

import java.sql.Date;


@Entity
public class Advertise implements java.io.Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ADV_ID")
  private Integer advId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "EMP_ID")
  private Integer empId;
  @Column(name = "ADV_STATUS")
  private Integer advStatus;
  @Column(name = "ADV_IMG")
  private byte[] advImg;
  @Column(name = "ADV_TEXT")
  private String advText;
  @Column(name = "ADV_TIME")
  private java.sql.Timestamp advTime;
  @Column(name = "ADV_STIME")
  private java.sql.Date advStime;
  @Column(name = "ADV_NTIME")
  private java.sql.Date advNtime;

  public Advertise() {}

  public Advertise(Integer empId, Integer advStatus) {
    super();

    this.empId = empId;
    this.advStatus = advStatus;

  }

  public Advertise(Integer advId, Integer empId, Integer advStatus) {
    super();

    this.advId = advId;
    this.empId = empId;
    this.advStatus = advStatus;

  }

  public Advertise(Integer storeId, Integer advStatus, byte[] advImg, String advText,
                     Date advStime) {
    super();

    this.storeId = storeId;
    this.advStatus = advStatus;
    this.advImg = advImg;
    this.advText = advText;
    this.advStime = advStime;
  }

  public Advertise(Integer storeId, Integer empId, Integer advStatus, byte[] advImg, String advText,
                     Date advStime, Date advNtime) {
    super();

    this.storeId = storeId;
    this.empId = empId;
    this.advStatus = advStatus;
    this.advImg = advImg;
    this.advText = advText;
    this.advStime = advStime;
    this.advNtime = advNtime;
  }

  public Advertise(Integer advId, Integer storeId, Integer empId, Integer advStatus, byte[] advImg, String advText,
                     Date advStime, Date advNtime) {
    super();

    this.advId = advId;
    this.storeId = storeId;
    this.empId = empId;
    this.advStatus = advStatus;
    this.advImg = advImg;
    this.advText = advText;
    this.advStime = advStime;
    this.advNtime = advNtime;
  }




  public Integer getAdvId() {
    return advId;
  }

  public void setAdvId(Integer advId) {
    this.advId = advId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }


  public Integer getAdvStatus() {
    return advStatus;
  }

  public void setAdvStatus(Integer advStatus) {
    this.advStatus = advStatus;
  }


  public byte[] getAdvImg() {
    return advImg;
  }

  public void setAdvImg(byte[] advImg) {
    this.advImg = advImg;
  }


  public String getAdvText() {
    return advText;
  }

  public void setAdvText(String advText) {
    this.advText = advText;
  }


  public java.sql.Timestamp getAdvTime() {
    return advTime;
  }

  public void setAdvTime(java.sql.Timestamp advTime) {
    this.advTime = advTime;
  }


  public java.sql.Date getAdvStime() {
    return advStime;
  }

  public void setAdvStime(java.sql.Date advStime) {
    this.advStime = advStime;
  }


  public java.sql.Date getAdvNtime() {
    return advNtime;
  }

  public void setAdvNtime(java.sql.Date advNtime) {
    this.advNtime = advNtime;
  }

  public Store getStore() {
	  StoreService storeSvc = new StoreService();
	  return storeSvc.getById(storeId);

  }


}
