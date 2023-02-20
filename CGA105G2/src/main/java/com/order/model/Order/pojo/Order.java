package com.order.model.Order.pojo;

import javax.persistence.*;

@Entity
public class Order implements java.io.Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ID")
  private Integer orderId;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "ORDER_PRICE")
  private Integer orderPrice;
  @Column(name = "CODE_ID")
  private Integer codeId;
  @Column(name = "ORDER_FRE")
  private Integer orderFre;
  @Column(name = "ORDER_FPRICE")
  private Integer orderFprice;
  @Column(name = "ORDER_TEXT")
  private String orderText;
  @Column(name = "ORDER_STATUS")
  private Integer orderStatus;
  @Column(name = "ORDER_TIME")
  private java.sql.Timestamp orderTime;
  @Column(name = "ORDER_OTIME")
  private java.sql.Date orderOtime;
  @Column(name = "ORDER_RTIME")
  private java.sql.Timestamp orderRtime;

  public Order() {
  }

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public Integer getOrderPrice() {
    return orderPrice;
  }

  public void setOrderPrice(Integer orderPrice) {
    this.orderPrice = orderPrice;
  }


  public Integer getCodeId() {
    return codeId;
  }

  public void setCodeId(Integer codeId) {
    this.codeId = codeId;
  }


  public Integer getOrderFre() {
    return orderFre;
  }

  public void setOrderFre(Integer orderFre) {
    this.orderFre = orderFre;
  }


  public Integer getOrderFprice() {
    return orderFprice;
  }

  public void setOrderFprice(Integer orderFprice) {
    this.orderFprice = orderFprice;
  }


  public String getOrderText() {
    return orderText;
  }

  public void setOrderText(String orderText) {
    this.orderText = orderText;
  }


  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }


  public java.sql.Timestamp getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(java.sql.Timestamp orderTime) {
    this.orderTime = orderTime;
  }


  public java.sql.Date getOrderOtime() {
    return orderOtime;
  }

  public void setOrderOtime(java.sql.Date orderOtime) {
    this.orderOtime = orderOtime;
  }


  public java.sql.Timestamp getOrderRtime() {
    return orderRtime;
  }

  public void setOrderRtime(java.sql.Timestamp orderRtime) {
    this.orderRtime = orderRtime;
  }

}
