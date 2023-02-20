package com.msg.model.Message.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Message {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MES_ID")
  private Integer mesId;
  @Column(name = "FOLLOW_ID")
  private Integer followId;
  @Column(name = "MES_TEXT")
  private String mesText;
  @Column(name = "MES_TIME")
  private java.sql.Timestamp mesTime;

  public Message() {

  }

  public Message(Integer mesId, Integer followId, String mesText, Timestamp mesTime) {
    super();
    this.mesId = mesId;
    this.followId = followId;
    this.mesText = mesText;
    this.mesTime = mesTime;
  }


  public Integer getMesId() {
    return mesId;
  }

  public void setMesId(Integer mesId) {
    this.mesId = mesId;
  }


  public Integer getFollowId() {
    return followId;
  }

  public void setFollowId(Integer followId) {
    this.followId = followId;
  }


  public String getMesText() {
    return mesText;
  }

  public void setMesText(String mesText) {
    this.mesText = mesText;
  }


  public java.sql.Timestamp getMesTime() {
    return mesTime;
  }

  public void setMesTime(java.sql.Timestamp mesTime) {
    this.mesTime = mesTime;
  }

}
