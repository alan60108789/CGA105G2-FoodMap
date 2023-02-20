package com.point.model.PointGoods.pojo;

import javax.persistence.*;

@Entity
@Table(catalog = "cga105g2",name = "point_goods")
public class PointGoods {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PD_ID")
  private Integer pdId;
  @Column(name = "PD_IMG")
  private byte[] pdImg;
  @Column(name ="PD_NAME" )
  private String pdName;
  @Column(name ="PD_PRICE" )
  private Integer pdPrice;
  @Column(name ="PD_TEXT" )
  private String pdText;
  @Column(name ="PD_TIME" )
  private java.sql.Timestamp pdTime;
  @Column(name = "PD_RTIME")
  private java.sql.Timestamp pdRtime;
  @Column(name ="PD_STATUS" )
  private Integer pdStatus;


  public Integer getPdId() {
    return pdId;
  }

  public void setPdId(Integer pdId) {
    this.pdId = pdId;
  }


  public byte[] getPdImg() {
    return pdImg;
  }

  public void setPdImg(byte[] pdImg) {
    this.pdImg = pdImg;
  }


  public String getPdName() {
    return pdName;
  }

  public void setPdName(String pdName) {
    this.pdName = pdName;
  }


  public Integer getPdPrice() {
    return pdPrice;
  }

  public void setPdPrice(Integer pdPrice) {
    this.pdPrice = pdPrice;
  }


  public String getPdText() {
    return pdText;
  }

  public void setPdText(String pdText) {
    this.pdText = pdText;
  }


  public java.sql.Timestamp getPdTime() {
    return pdTime;
  }

  public void setPdTime(java.sql.Timestamp pdTime) {
    this.pdTime = pdTime;
  }


  public java.sql.Timestamp getPdRtime() {
    return pdRtime;
  }

  public void setPdRtime(java.sql.Timestamp pdRtime) {
    this.pdRtime = pdRtime;
  }


  public Integer getPdStatus() {
    return pdStatus;
  }

  public void setPdStatus(Integer pdStatus) {
    this.pdStatus = pdStatus;
  }

}
