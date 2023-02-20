package com.point.model.Point.pojo;

import javax.persistence.*;

@Entity
public class Point {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "POINT_ID")
  private Integer pointId;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "POINT_CHANGE")
  private String pointChange;
  @Column(name = "POINT_NUMBER")
  private Integer pointNumber;


  public Integer getPointId() {
    return pointId;
  }

  public void setPointId(Integer pointId) {
    this.pointId = pointId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }


  public String getPointChange() {
    return pointChange;
  }

  public void setPointChange(String pointChange) {
    this.pointChange = pointChange;
  }


  public Integer getPointNumber() {
    return pointNumber;
  }

  public void setPointNumber(Integer pointNumber) {
    this.pointNumber = pointNumber;
  }
}
