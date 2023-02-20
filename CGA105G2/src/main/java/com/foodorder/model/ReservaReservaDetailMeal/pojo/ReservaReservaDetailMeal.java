package com.foodorder.model.ReservaReservaDetailMeal.pojo;

import javax.persistence.*;

@Entity
public class ReservaReservaDetailMeal {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "REN_ID")
	private Integer renId;
	  @Column(name = "REN_NAME")
	private String renName;
	  @Column(name = "REN_PHONE")
	private String renPhone;
	  @Column(name = "REN_TIME")
	private String renTime;
	  @Column(name = "REN_STATUS")
	private Integer renStatus;
	  @Column(name = "REN_DATE")
	private java.sql.Date renDate;
	  @Column(name = "REN_HEADCOUNT")
	private Integer renHeadcount;
	  @Column(name = "MEAL_NAME_LIST")
	private String mealNameList;
	  @Column(name = "STORE_NAME")
	private String storeName;
	  @Column(name = "ART_SCORE")
	private Integer artScore;
	
	
	
	
	public Integer getRenId() {
		return renId;
	}
	public void setRenId(Integer renId) {
		this.renId = renId;
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
	public java.sql.Date getRenDate() {
		return renDate;
	}
	public void setRenDate(java.sql.Date renDate) {
		this.renDate = renDate;
	}
	public Integer getRenHeadcount() {
		return renHeadcount;
	}
	public void setRenHeadcount(Integer renHeadcount) {
		this.renHeadcount = renHeadcount;
	}
	public String getMealNameList() {
		return mealNameList;
	}
	public void setMealNameList(String mealNameList) {
		this.mealNameList = mealNameList;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getArtScore() {
		return artScore;
	}
	public void setArtScore(Integer artScore) {
		this.artScore = artScore;
	}
	
	

}
