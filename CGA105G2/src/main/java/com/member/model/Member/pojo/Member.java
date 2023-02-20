package com.member.model.Member.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Member implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "MEM_STATUS")
  private Integer memStatus;
  @Column(name = "MEM_ACC")
  private String memAcc;
  @Column(name = "MEM_PWD")
  private String memPwd;
  @Column(name = "MEM_MAIL")
  private String memMail;
  @Column(name = "MEM_PIC")
  private byte[] memPic;
  @Column(name = "MEM_NAME")
  private String memName;
  @Column(name = "MEM_RECIPIENT")
  private String memRecipient;
  @Column(name = "MEM_TW_ID")
  private String memTwId;
  @Column(name = "MEM_BIRTHDAY")
  private java.sql.Date memBirthday;
  @Column(name = "MEM_PHONE")
  private String memPhone;
  @Column(name = "MEM_POSTAL_CODE")
  private Integer memPostalCode;
  @Column(name = "MEM_CITY")
  private String memCity;
  @Column(name = "MEM_DISTRICT")
  private String memDistrict;
  @Column(name = "MEM_ADDRESS")
  private String memAddress;
  @Column(name = "MEM_TEXT")
  private String memText;
  @Column(name = "MEM_TIME")
  private java.sql.Timestamp memTime;
  @Column(name = "MEM_POINT")
  private Integer memPoint;

  public Member() {
    super();
  }
  public Member(String memAcc, String memPwd, String memMail, String memName, String memRecipient, String memTwId,
                Date memBirthday, String memPhone, Integer memPostalCode, String memCity, String memDistrict, String memAddress) {
    super();
    this.memAcc = memAcc;
    this.memPwd = memPwd;
    this.memMail = memMail;
    this.memName = memName;
    this.memRecipient = memRecipient;
    this.memTwId = memTwId;
    this.memBirthday = memBirthday;
    this.memPhone = memPhone;
    this.memPostalCode = memPostalCode;
    this.memCity = memCity;
    this.memDistrict = memDistrict;
    this.memAddress = memAddress;
  }



  public Member(Integer memId, String memAcc, String memPwd, String memMail, byte[] memPic, String memName,
                String memRecipient, String memTwId, Date memBirthday, String memPhone, Integer memPostalCode,
                String memCity, String memDistrict, String memAddress, String memText) {
    super();
    this.memId = memId;
    this.memAcc = memAcc;
    this.memPwd = memPwd;
    this.memMail = memMail;
    this.memPic = memPic;
    this.memName = memName;
    this.memRecipient = memRecipient;
    this.memTwId = memTwId;
    this.memBirthday = memBirthday;
    this.memPhone = memPhone;
    this.memPostalCode = memPostalCode;
    this.memCity = memCity;
    this.memDistrict = memDistrict;
    this.memAddress = memAddress;
    this.memText = memText;
  }
  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }


  public Integer getMemStatus() {
    return memStatus;
  }

  public void setMemStatus(Integer memStatus) {
    this.memStatus = memStatus;
  }


  public String getMemAcc() {
    return memAcc;
  }

  public void setMemAcc(String memAcc) {
    this.memAcc = memAcc;
  }


  public String getMemPwd() {
    return memPwd;
  }

  public void setMemPwd(String memPwd) {
    this.memPwd = memPwd;
  }


  public String getMemMail() {
    return memMail;
  }

  public void setMemMail(String memMail) {
    this.memMail = memMail;
  }


  public byte[] getMemPic() {
    return memPic;
  }

  public void setMemPic(byte[] memPic) {
    this.memPic = memPic;
  }


  public String getMemName() {
    return memName;
  }

  public void setMemName(String memName) {
    this.memName = memName;
  }


  public String getMemRecipient() {
    return memRecipient;
  }

  public void setMemRecipient(String memRecipient) {
    this.memRecipient = memRecipient;
  }


  public String getMemTwId() {
    return memTwId;
  }

  public void setMemTwId(String memTwId) {
    this.memTwId = memTwId;
  }


  public java.sql.Date getMemBirthday() {
    return memBirthday;
  }

  public void setMemBirthday(java.sql.Date memBirthday) {
    this.memBirthday = memBirthday;
  }


  public String getMemPhone() {
    return memPhone;
  }

  public void setMemPhone(String memPhone) {
    this.memPhone = memPhone;
  }


  public Integer getMemPostalCode() {
    return memPostalCode;
  }

  public void setMemPostalCode(Integer memPostalCode) {
    this.memPostalCode = memPostalCode;
  }


  public String getMemCity() {
    return memCity;
  }

  public void setMemCity(String memCity) {
    this.memCity = memCity;
  }


  public String getMemDistrict() {
    return memDistrict;
  }

  public void setMemDistrict(String memDistrict) {
    this.memDistrict = memDistrict;
  }


  public String getMemAddress() {
    return memAddress;
  }

  public void setMemAddress(String memAddress) {
    this.memAddress = memAddress;
  }


  public String getMemText() {
    return memText;
  }

  public void setMemText(String memText) {
    this.memText = memText;
  }


  public java.sql.Timestamp getMemTime() {
    return memTime;
  }

  public void setMemTime(java.sql.Timestamp memTime) {
    this.memTime = memTime;
  }


  public Integer getMemPoint() {
    return memPoint;
  }

  public void setMemPoint(Integer memPoint) {
    this.memPoint = memPoint;
  }

}
