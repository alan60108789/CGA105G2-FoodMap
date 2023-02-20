package com.foodorder.model.Reserva.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "REN_ID")
  private Integer renId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "REN_NAME")
  private String renName;
  @Column(name = "REN_PHONE")
  private String renPhone;
  @Column(name = "REN_TIME")
  private String renTime;
  @Column(name = "REN_STATUS")
  private Integer renStatus;
  @Column(name = "REN_TABLE")
  private Integer renTable;
  @Column(name = "REN_DATE")
  private java.sql.Date renDate;
  @Column(name = "REN_CURDATE")
  private java.sql.Timestamp renCurdate;
  @Column(name = "REN_HEADCOUNT")
  private Integer renHeadcount;
  private Integer codeId;
  private Integer renPrice;
  private Integer renFprice;

	public Reserva() {
		super();
	}



	public Reserva(Integer renId, Integer renStatus, Integer renTable) {
		super();
		this.renId = renId;
		this.renStatus = renStatus;
		this.renTable = renTable;
	}

	public Reserva(Integer storeId, Integer memId, String renName, String renPhone, String renTime, Date renDate,
			Integer renHeadcount, Integer renPrice, Integer renFprice) {
		super();
		this.storeId = storeId;
		this.memId = memId;
		this.renName = renName;
		this.renPhone = renPhone;
		this.renTime = renTime;
		this.renDate = renDate;
		this.renHeadcount = renHeadcount;
		this.renPrice = renPrice;
		this.renFprice = renFprice;
	}

	public Reserva(Integer storeId, Integer memId, String renName, String renPhone, String renTime, Date renDate,
			Integer renHeadcount, Integer codeId, Integer renPrice, Integer renFprice) {
		super();
		this.storeId = storeId;
		this.memId = memId;
		this.renName = renName;
		this.renPhone = renPhone;
		this.renTime = renTime;
		this.renDate = renDate;
		this.renHeadcount = renHeadcount;
		this.codeId = codeId;
		this.renPrice = renPrice;
		this.renFprice = renFprice;
	}

  public Integer getRenId() {
    return renId;
  }

  public void setRenId(Integer renId) {
    this.renId = renId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }


  public String getRenName() {
    return renName;
  }

  public void setRenName(String renName) {
    this.renName = renName;
  }


  public String getRenPhone() {
    return renPhone;
  }

  public void setRenPhone(String renPhone) {
    this.renPhone = renPhone;
  }


  public String getRenTime() {
    return renTime;
  }

  public void setRenTime(String renTime) {
    this.renTime = renTime;
  }


  public Integer getRenStatus() {
    return renStatus;
  }

  public void setRenStatus(Integer renStatus) {
    this.renStatus = renStatus;
  }


  public Integer getRenTable() {
    return renTable;
  }

  public void setRenTable(Integer renTable) {
    this.renTable = renTable;
  }


  public java.sql.Date getRenDate() {
    return renDate;
  }

  public void setRenDate(java.sql.Date renDate) {
    this.renDate = renDate;
  }


  public java.sql.Timestamp getRenCurdate() {
    return renCurdate;
  }

  public void setRenCurdate(java.sql.Timestamp renCurdate) {
    this.renCurdate = renCurdate;
  }


  public Integer getRenHeadcount() {
    return renHeadcount;
  }

  public void setRenHeadcount(Integer renHeadcount) {
    this.renHeadcount = renHeadcount;
  }


  public Integer getCodeId() {
    return codeId;
  }

  public void setCodeId(Integer codeId) {
    this.codeId = codeId;
  }


  public Integer getRenPrice() {
    return renPrice;
  }

  public void setRenPrice(Integer renPrice) {
    this.renPrice = renPrice;
  }


  public Integer getRenFprice() {
    return renFprice;
  }

  public void setRenFprice(Integer renFprice) {
    this.renFprice = renFprice;
  }

}
