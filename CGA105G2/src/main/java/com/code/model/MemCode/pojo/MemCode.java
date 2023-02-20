package com.code.model.MemCode.pojo;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(MemCode_PK.class)
@Table(catalog = "cga105g2",name = "mem_code")
public class MemCode {

  @Id
  @Column(name = "CODE_ID")
  private Integer codeId;
  @Id
  @Column(name = "MEM_ID")
  private Integer memId;

  public MemCode(){};
  public MemCode(Integer codeId,Integer memId){
    this.codeId=codeId;
    this.memId=memId;
  }

  public Integer getCodeId() {
    return codeId;
  }

  public void setCodeId(Integer codeId) {
    this.codeId = codeId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }

}
