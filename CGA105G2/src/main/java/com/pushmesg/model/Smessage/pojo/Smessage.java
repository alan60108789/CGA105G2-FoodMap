package com.pushmesg.model.Smessage.pojo;

import javax.persistence.*;

@Entity
public class Smessage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SMESSAGE_ID")
  private Integer smessageId;
  @Column(name = "SUB_ID")
  private Integer subId;
  @Column(name = "SMESSAGE_TXET")
  private String smessageTxet;
  @Column(name = "SMESSAGE_TIME")
  private java.sql.Timestamp smessageTime;

	public Smessage() {
		super();
	}

	public Smessage(Integer subId, String smessageTxet) {
		super();
		this.subId = subId;
		this.smessageTxet = smessageTxet;
	}

  public Integer getSmessageId() {
    return smessageId;
  }

  public void setSmessageId(Integer smessageId) {
    this.smessageId = smessageId;
  }


  public Integer getSubId() {
    return subId;
  }

  public void setSubId(Integer subId) {
    this.subId = subId;
  }


  public String getSmessageTxet() {
    return smessageTxet;
  }

  public void setSmessageTxet(String smessageTxet) {
    this.smessageTxet = smessageTxet;
  }


  public java.sql.Timestamp getSmessageTime() {
    return smessageTime;
  }

  public void setSmessageTime(java.sql.Timestamp smessageTime) {
    this.smessageTime = smessageTime;
  }

}
