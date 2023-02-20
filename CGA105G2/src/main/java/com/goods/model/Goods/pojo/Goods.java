package com.goods.model.Goods.pojo;

import javax.persistence.*;

@Entity
public class Goods {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "GOODS_ID")
  private Integer goodsId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "GOODS_IMG")
  private byte[] goodsImg;
  @Column(name = "GOODS_NAME")
  private String goodsName;
  @Column(name = "GOODS_STATUS")
  private Integer goodsStatus;
  @Column(name = "GOODS_PRICE")
  private Integer goodsPrice;
  @Column(name = "GOODS_TEXT")
  private String goodsText;
  @Column(name = "GOODS_TIME")
  private java.sql.Timestamp goodsTime;
  @Column(name = "GOODS_RTIME")
  private java.sql.Timestamp goodsRtime;

  public Goods() {
  }


  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public byte[] getGoodsImg() {
    return goodsImg;
  }

  public void setGoodsImg(byte[] goodsImg) {
    this.goodsImg = goodsImg;
  }


  public String getGoodsName() {
    return goodsName;
  }

  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }


  public Integer getGoodsStatus() {
    return goodsStatus;
  }

  public void setGoodsStatus(Integer goodsStatus) {
    this.goodsStatus = goodsStatus;
  }


  public Integer getGoodsPrice() {
    return goodsPrice;
  }

  public void setGoodsPrice(Integer goodsPrice) {
    this.goodsPrice = goodsPrice;
  }


  public String getGoodsText() {
    return goodsText;
  }

  public void setGoodsText(String goodsText) {
    this.goodsText = goodsText;
  }


  public java.sql.Timestamp getGoodsTime() {
    return goodsTime;
  }

  public void setGoodsTime(java.sql.Timestamp goodsTime) {
    this.goodsTime = goodsTime;
  }


  public java.sql.Timestamp getGoodsRtime() {
    return goodsRtime;
  }

  public void setGoodsRtime(java.sql.Timestamp goodsRtime) {
    this.goodsRtime = goodsRtime;
  }

}
