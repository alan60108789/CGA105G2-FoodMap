package com.waiting.model.pojo;

import javax.persistence.*;

@Entity
public class Standby {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "STA_ID")
  private Integer staId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "STA_NAME")
  private String staName;
  @Column(name = "STA_PHONE")
  private String staPhone;
  @Column(name = "STA_NUMBER")
  private Integer staNumber;
  @Column(name = "STA_TIME")
  private java.sql.Timestamp staTime;
  @Column(name = "STA_STATUS")
  private Integer staStatus;


  public Integer getStaId() {
    return staId;
  }

  public void setStaId(Integer staId) {
    this.staId = staId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public String getStaName() {
    return staName;
  }

  public void setStaName(String staName) {
    this.staName = staName;
  }


  public String getStaPhone() {
    return staPhone;
  }

  public void setStaPhone(String staPhone) {
    this.staPhone = staPhone;
  }


  public Integer getStaNumber() {
    return staNumber;
  }

  public void setStaNumber(Integer staNumber) {
    this.staNumber = staNumber;
  }


  public java.sql.Timestamp getStaTime() {
    return staTime;
  }

  public void setStaTime(java.sql.Timestamp staTime) {
    this.staTime = staTime;
  }


  public Integer getStaStatus() {
    return staStatus;
  }

  public void setStaStatus(Integer staStatus) {
    this.staStatus = staStatus;
  }

}
