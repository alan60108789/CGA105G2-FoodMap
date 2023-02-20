package com.followmem.model.FollowMem.pojo;

import javax.persistence.*;

@Entity
@Table(catalog = "cga105g2",name = "follow_mem")
public class FollowMem implements java.io.Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FOLLOW_ID")
  private Integer followId;
  @Column(name = "MEM_ID1")
  private Integer memId1;
  @Column(name = "MEM_ID2")
  private Integer memId2;

  public FollowMem() {

  }

  public FollowMem(Integer followId, Integer memId1, Integer memId2) {
    super();
    this.followId = followId;
    this.memId1 = memId1;
    this.memId2 = memId2;
  }


  public Integer getFollowId() {
    return followId;
  }

  public void setFollowId(Integer followId) {
    this.followId = followId;
  }


  public Integer getMemId1() {
    return memId1;
  }

  public void setMemId1(Integer memId1) {
    this.memId1 = memId1;
  }


  public Integer getMemId2() {
    return memId2;
  }

  public void setMemId2(Integer memId2) {
    this.memId2 = memId2;
  }
  

public com.member.model.Member.pojo.Member getMember() {
	    com.member.model.service.MemberService memSvc = new com.member.model.service.MemberService();
	    com.member.model.Member.pojo.Member member = memSvc.getById(memId2);
	    return member;
}

}
