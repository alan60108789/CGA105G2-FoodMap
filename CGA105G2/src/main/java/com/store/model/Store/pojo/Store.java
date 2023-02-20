package com.store.model.Store.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
//@Table(catalog = "cga105g2",name = "store")
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "EMP_ID")
  private Integer empId;
  @Column(name = "STORE_STATUS")
  private Integer storeStatus;
  @Column(name = "STORE_NAME")
  private String storeName;
  @Column(name = "STORE_PHONE1")
  private String storePhone1;
  @Column(name = "STORE_HOURS")
  private String storeHours;
  @Column(name = "STORE_MAP")
  private String storeMap;
  @Column(name = "STORE_CITY")
  private String storeCity;
  @Column(name = "STORE_DISTRICT")
  private String storeDistrict;
  @Column(name = "STORE_ADDRESS")
  private String storeAddress;
  @Column(name = "STORE_URL")
  private String storeUrl;
  @Column(name = "STORE_WEB")
  private String storeWeb;
  @Column(name = "STORE_ACC")
  private String storeAcc;
  @Column(name = "STORE_PWD")
  private String storePwd;
  @Column(name = "STORE_MAIL")
  private String storeMail;
  @Column(name = "STORE_COM_ID")
  private String storeComId;
  @Column(name = "STORE_COM_ADDRESS")
  private String storeComAddress;
  @Column(name = "STORE_TW_ID")
  private String storeTwId;
  @Column(name = "STORE_PHONE2")
  private String storePhone2;
  @Column(name = "STORE_TEXT")
  private String storeText;
  @Column(name = "STORE_PLAN")
  private Integer storePlan;
  @Column(name = "STORE_NPLAN")
  private Integer storeNplan;
  @Column(name = "STORE_TIME")
  private java.sql.Timestamp storeTime;
  @Column(name = "STORE_ONTIME")
  private java.sql.Date storeOntime;
  @Column(name = "STORE_RETIME")
  private java.sql.Timestamp storeRetime;
  @Column(name = "STORE_ETIME")
  private String storeEtime;
  @Column(name = "STORE_TABLE")
  private Integer storeTable;
  @Column(name = "STORE_ETABLE")
  private Integer storeEtable;

  public Store() {
  };

  public Store(String storeName, String storePhone1, String storeHours, String storeMap, String storeCity, String storeDistrict, String storeAddress, String storeUrl, String storeWeb){
    this.storeName=storeName;
    this.storePhone1=storePhone1;
    this.storeHours=storeHours;
    this.storeMap=storeMap;
    this.storeCity=storeCity;
    this.storeDistrict=storeDistrict;
    this.storeAddress=storeAddress;
    this.storeUrl=storeUrl;
    this.storeWeb=storeWeb;
  };

  public Store(Integer empId, String storeName, String storePhone1, String storeHours, String storeCity,
               String storeDistrict, String storeAddress, String storeUrl, String storeWeb, String storeAcc,
               String storePwd, String storeMail, String storeComId, String storeComAddress, String storeTwId,
               String storePhone2) {
    super();
    this.empId = empId;
    this.storeName = storeName;
    this.storePhone1 = storePhone1;
    this.storeHours = storeHours;
    this.storeCity = storeCity;
    this.storeDistrict = storeDistrict;
    this.storeAddress = storeAddress;
    this.storeUrl = storeUrl;
    this.storeWeb = storeWeb;
    this.storeAcc = storeAcc;
    this.storePwd = storePwd;
    this.storeMail = storeMail;
    this.storeComId = storeComId;
    this.storeComAddress = storeComAddress;
    this.storeTwId = storeTwId;
    this.storePhone2 = storePhone2;
  }

  public Store(Integer storeId, Integer empId, String storeName, String storePhone1, String storeHours,
               String storeMap, String storeCity, String storeDistrict, String storeAddress, String storeUrl,
               String storeWeb, String storeAcc, String storePwd, String storeMail, String storeComId,
               String storeComAddress, String storeTwId, String storePhone2, String storeText, Integer 			storePlan, Integer storeNplan, Timestamp storeTime, Date storeOntime, Timestamp storeRetime, String storeEtime, Integer storeTable, Integer storeEtable) {
    super();
    this.storeId = storeId;
    this.empId = empId;
    this.storeName = storeName;
    this.storePhone1 = storePhone1;
    this.storeHours = storeHours;
    this.storeMap = storeMap;
    this.storeCity = storeCity;
    this.storeDistrict = storeDistrict;
    this.storeAddress = storeAddress;
    this.storeUrl = storeUrl;
    this.storeWeb = storeWeb;
    this.storeAcc = storeAcc;
    this.storePwd = storePwd;
    this.storeMail = storeMail;
    this.storeComId = storeComId;
    this.storeComAddress = storeComAddress;
    this.storeTwId = storeTwId;
    this.storePhone2 = storePhone2;
    this.storeText = storeText;
    this.storePlan = storePlan;
    this.storeNplan = storeNplan;
    this.storeTime = storeTime;
    this.storeOntime = storeOntime;
    this.storeRetime = storeRetime;
    this.storeEtime = storeEtime;
    this.storeTable = storeTable;
    this.storeEtable = storeEtable;
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


  public Integer getStoreStatus() {
    return storeStatus;
  }

  public void setStoreStatus(Integer storeStatus) {
    this.storeStatus = storeStatus;
  }


  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }


  public String getStorePhone1() {
    return storePhone1;
  }

  public void setStorePhone1(String storePhone1) {
    this.storePhone1 = storePhone1;
  }


  public String getStoreHours() {
    return storeHours;
  }

  public void setStoreHours(String storeHours) {
    this.storeHours = storeHours;
  }


  public String getStoreMap() {
    return storeMap;
  }

  public void setStoreMap(String storeMap) {
    this.storeMap = storeMap;
  }


  public String getStoreCity() {
    return storeCity;
  }

  public void setStoreCity(String storeCity) {
    this.storeCity = storeCity;
  }


  public String getStoreDistrict() {
    return storeDistrict;
  }

  public void setStoreDistrict(String storeDistrict) {
    this.storeDistrict = storeDistrict;
  }


  public String getStoreAddress() {
    return storeAddress;
  }

  public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
  }


  public String getStoreUrl() {
    return storeUrl;
  }

  public void setStoreUrl(String storeUrl) {
    this.storeUrl = storeUrl;
  }


  public String getStoreWeb() {
    return storeWeb;
  }

  public void setStoreWeb(String storeWeb) {
    this.storeWeb = storeWeb;
  }


  public String getStoreAcc() {
    return storeAcc;
  }

  public void setStoreAcc(String storeAcc) {
    this.storeAcc = storeAcc;
  }


  public String getStorePwd() {
    return storePwd;
  }

  public void setStorePwd(String storePwd) {
    this.storePwd = storePwd;
  }


  public String getStoreMail() {
    return storeMail;
  }

  public void setStoreMail(String storeMail) {
    this.storeMail = storeMail;
  }


  public String getStoreComId() {
    return storeComId;
  }

  public void setStoreComId(String storeComId) {
    this.storeComId = storeComId;
  }


  public String getStoreComAddress() {
    return storeComAddress;
  }

  public void setStoreComAddress(String storeComAddress) {
    this.storeComAddress = storeComAddress;
  }


  public String getStoreTwId() {
    return storeTwId;
  }

  public void setStoreTwId(String storeTwId) {
    this.storeTwId = storeTwId;
  }


  public String getStorePhone2() {
    return storePhone2;
  }

  public void setStorePhone2(String storePhone2) {
    this.storePhone2 = storePhone2;
  }


  public String getStoreText() {
    return storeText;
  }

  public void setStoreText(String storeText) {
    this.storeText = storeText;
  }


  public Integer getStorePlan() {
    return storePlan;
  }

  public void setStorePlan(Integer storePlan) {
    this.storePlan = storePlan;
  }


  public Integer getStoreNplan() {
    return storeNplan;
  }

  public void setStoreNplan(Integer storeNplan) {
    this.storeNplan = storeNplan;
  }


  public java.sql.Timestamp getStoreTime() {
    return storeTime;
  }

  public void setStoreTime(java.sql.Timestamp storeTime) {
    this.storeTime = storeTime;
  }


  public java.sql.Date getStoreOntime() {
    return storeOntime;
  }

  public void setStoreOntime(java.sql.Date storeOntime) {
    this.storeOntime = storeOntime;
  }


  public java.sql.Timestamp getStoreRetime() {
    return storeRetime;
  }

  public void setStoreRetime(java.sql.Timestamp storeRetime) {
    this.storeRetime = storeRetime;
  }


  public String getStoreEtime() {
    return storeEtime;
  }

  public void setStoreEtime(String storeEtime) {
    this.storeEtime = storeEtime;
  }


  public Integer getStoreTable() {
    return storeTable;
  }

  public void setStoreTable(Integer storeTable) {
    this.storeTable = storeTable;
  }


  public Integer getStoreEtable() {
    return storeEtable;
  }

  public void setStoreEtable(Integer storeEtable) {
    this.storeEtable = storeEtable;
  }

}
