package com.subs.model.Subscribe.pojo;

import javax.persistence.*;

@Entity
public class Subscribe implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SUB_ID")
  private Integer subId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "MEM_ID")
  private Integer memId;

  public Subscribe() {

  }

  public Subscribe(Integer subId, Integer storeId, Integer memId) {
    this.subId = subId;
    this.storeId = storeId;
    this.memId = memId;
  }


  public Integer getSubId() {
    return subId;
  }

  public void setSubId(Integer subId) {
    this.subId = subId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }
  
  public com.store.model.Store.pojo.Store getStore() {
	    com.store.model.service.StoreService storeSvc = new com.store.model.service.StoreService();
	    com.store.model.Store.pojo.Store store = storeSvc.getById(storeId);
	    return store;
}

public com.member.model.Member.pojo.Member getMember() {
	    com.member.model.service.MemberService memSvc = new com.member.model.service.MemberService();
	    com.member.model.Member.pojo.Member member = memSvc.getById(memId);
	    return member;
}

}
