package com.point.model.PointOrder.pojo;

import javax.persistence.*;

@Entity
@Table(catalog = "cga105g2",name = "point_order")
public class PointOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PO_ID")
  private Integer poId;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "PD_ID")
  private Integer pdId;
  @Column(name = "PO_PRICE")
  private Integer poPrice;
  @Column(name = "PO_TEXT")
  private String poText;
  @Column(name = "PO_STATUS")
  private Integer poStatus;
  @Column(name = "PO_TIME")
  private java.sql.Timestamp poTime;
  @Column(name = "PO_UTIME")
  private java.sql.Timestamp poUtime;
  @Column(name = "EMP_ID")
  private Integer empId;


  public Integer getPoId() {
    return poId;
  }

  public void setPoId(Integer poId) {
    this.poId = poId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }


  public Integer getPdId() {
    return pdId;
  }

  public void setPdId(Integer pdId) {
    this.pdId = pdId;
  }


  public Integer getPoPrice() {
    return poPrice;
  }

  public void setPoPrice(Integer poPrice) {
    this.poPrice = poPrice;
  }


  public String getPoText() {
    return poText;
  }

  public void setPoText(String poText) {
    this.poText = poText;
  }


  public Integer getPoStatus() {
    return poStatus;
  }

  public void setPoStatus(Integer poStatus) {
    this.poStatus = poStatus;
  }


  public java.sql.Timestamp getPoTime() {
    return poTime;
  }

  public void setPoTime(java.sql.Timestamp poTime) {
    this.poTime = poTime;
  }


  public java.sql.Timestamp getPoUtime() {
    return poUtime;
  }

  public void setPoUtime(java.sql.Timestamp poUtime) {
    this.poUtime = poUtime;
  }


  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }

  public com.point.model.PointGoods.pojo.PointGoods getPointGoods() {
	    com.point.model.service.PointGoodsService pointgoodsSvc = new com.point.model.service.PointGoodsService();
	    com.point.model.PointGoods.pojo.PointGoods pointgoods = pointgoodsSvc.getPointGood(pdId);
	    return pointgoods;
  }

}
