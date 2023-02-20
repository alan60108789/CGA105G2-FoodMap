package com.code.model.Code.pojo;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Code {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CODE_ID")
  private Integer codeId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "EMP_ID",insertable = false)
  private Integer empId;
  @Column(name = "CODE_NUM")
  private String codeNum;
  @Column(name = "CODE_OFF")
  private Integer codeOff;
  @Column(name = "CODE_STATUS",insertable = false)
  private Integer codeStatus;
  @Column(name = "CODE_TEXT")
  private String codeText;
  @Column(name = "CODE_TIME",insertable = false)
  private java.sql.Timestamp codeTime;
  @Column(name = "CODE_RTIME",insertable = false)
  private java.sql.Date codeRtime;
  @Column(name = "CODE_NTIME")
  private java.sql.Date codeNtime;

  public Code(){};
  public Code(Integer codeId,Integer storeId,Integer empId,String codeNum,Integer codeOff,Integer codeStatus,String codeText,java.sql.Timestamp codeTime,java.sql.Date codeRtime,java.sql.Date codeNtime){
    this.codeId=codeId;
    this.storeId=storeId;
    this.empId=empId;
    this.codeNum=codeNum;
    this.codeOff=codeOff;
    this.codeStatus=codeStatus;
    this.codeText=codeText;
    this.codeTime=codeTime;
    this.codeRtime=codeRtime;
    this.codeNtime=codeNtime;
  };

  public Code(Integer storeId,String codeNum,Integer codeOff){
    this.storeId=storeId;
    this.codeNum=codeNum;
    this.codeOff=codeOff;
  }
  public Code(Integer storeId,String codeNum,Integer codeOff,String codeText,java.sql.Date codeNtime){
    this.storeId=storeId;
    this.codeNum=codeNum;
    this.codeOff=codeOff;
    this.codeText=codeText;
    this.codeNtime=codeNtime;
  };

  public Code(String codeNum,Integer codeOff,java.sql.Date codeNtime,String codeText){
    this.codeNum=codeNum;
    this.codeOff=codeOff;
    this.codeText=codeText;
    this.codeNtime=codeNtime;
  };

  public Code(Integer codeId,Integer empId,Integer codeStatus){
    this.codeId=codeId;
    this.empId=empId;
    this.codeStatus=codeStatus;
  };

  public Code(Integer codeId,Integer empId){
    this.codeId=codeId;
    this.empId=empId;
  };

  public Code(String codeNum,Integer storeId){
    this.codeNum=codeNum;
    this.storeId=storeId;
  }



  public Code(int codeId, int storeId, int empId, String love, int codeOff, int codeStatus, String codeText, LocalDate now, Date codeNtime) {
  }


  public Integer getCodeId() {
    return codeId;
  }

  public void setCodeId(Integer codeId) {
    this.codeId = codeId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }


  public String getCodeNum() {
    return codeNum;
  }

  public void setCodeNum(String codeNum) {
    this.codeNum = codeNum;
  }


  public Integer getCodeOff() {
    return codeOff;
  }

  public void setCodeOff(Integer codeOff) {
    this.codeOff = codeOff;
  }


  public Integer getCodeStatus() {
    return codeStatus;
  }

  public void setCodeStatus(Integer codeStatus) {
    this.codeStatus = codeStatus;
  }


  public String getCodeText() {
    return codeText;
  }

  public void setCodeText(String codeText) {
    this.codeText = codeText;
  }


  public java.sql.Timestamp getCodeTime() {
    return codeTime;
  }

  public void setCodeTime(java.sql.Timestamp codeTime) {
    this.codeTime = codeTime;
  }


  public java.sql.Date getCodeRtime() {
    return codeRtime;
  }

  public void setCodeRtime(java.sql.Date codeRtime) {
    this.codeRtime = codeRtime;
  }


  public java.sql.Date getCodeNtime() {
    return codeNtime;
  }

  public void setCodeNtime(java.sql.Date codeNtime) {
    this.codeNtime = codeNtime;
  }

}
