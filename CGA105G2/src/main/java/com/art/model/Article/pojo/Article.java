package com.art.model.Article.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
//@Table(catalog = "cga105g2",name = "article")
public class Article implements java.io.Serializable{
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ART_ID")
  private Integer artId;
  @Column(name = "MEM_ID")
  private Integer memId;
  @Column(name = "STORE_ID")
  private Integer storeId;
  @Column(name = "ART_HEADER")
  private String artHeader;
  @Column(name = "ART_TEXT")
  private String artText;
  @Column(name = "ART_IMG")
  private byte[] artImg;
  @Column(name = "ART_TAG")
  private String artTag;
  @Column(name = "ART_STATUS")
  private Integer artStatus;
  @Column(name = "ART_TIME")
  private java.sql.Timestamp artTime;
  @Column(name = "ART_RETIME")
  private java.sql.Timestamp artRetime;
  @Column(name = "ART_SUMLIKE")
  private Integer artSumlike;
  @Column(name = "ART_SCORE")
  private Integer artScore;

  public Article() {

  }

  public Article(Integer memId, Integer storeId, String artHeader, String artText, byte[] artImg,
			Integer artScore) {
		this.memId = memId;
		this.storeId = storeId;
		this.artHeader = artHeader;
		this.artText = artText;
		this.artImg = artImg;
		this.artScore = artScore;
	}

  public Article(Integer artId, Integer memId, Integer storeId, String artHeader, String artText, byte[] artImg,
               String artTag, Integer artStatus, Timestamp artTime, Timestamp artRetime, Integer artSumlike,
               Integer artScore) {
    this.artId = artId;
    this.memId = memId;
    this.storeId = storeId;
    this.artHeader = artHeader;
    this.artText = artText;
    this.artImg = artImg;
    this.artTag = artTag;
    this.artStatus = artStatus;
    this.artTime = artTime;
    this.artRetime = artRetime;
    this.artSumlike = artSumlike;
    this.artScore = artScore;
  }


  public Integer getArtId() {
    return artId;
  }

  public void setArtId(Integer artId) {
    this.artId = artId;
  }


  public Integer getMemId() {
    return memId;
  }

  public void setMemId(Integer memId) {
    this.memId = memId;
  }


  public Integer getStoreId() {
    return storeId;
  }

  public void setStoreId(Integer storeId) {
    this.storeId = storeId;
  }


  public String getArtHeader() {
    return artHeader;
  }

  public void setArtHeader(String artHeader) {
    this.artHeader = artHeader;
  }


  public String getArtText() {
    return artText;
  }

  public void setArtText(String artText) {
    this.artText = artText;
  }


  public byte[] getArtImg() {
    return artImg;
  }

  public void setArtImg(byte[] artImg) {
    this.artImg = artImg;
  }


  public String getArtTag() {
    return artTag;
  }

  public void setArtTag(String artTag) {
    this.artTag = artTag;
  }


  public Integer getArtStatus() {
    return artStatus;
  }

  public void setArtStatus(Integer artStatus) {
    this.artStatus = artStatus;
  }


  public java.sql.Timestamp getArtTime() {
    return artTime;
  }

  public void setArtTime(java.sql.Timestamp artTime) {
    this.artTime = artTime;
  }


  public java.sql.Timestamp getArtRetime() {
    return artRetime;
  }

  public void setArtRetime(java.sql.Timestamp artRetime) {
    this.artRetime = artRetime;
  }


  public Integer getArtSumlike() {
    return artSumlike;
  }

  public void setArtSumlike(Integer artSumlike) {
    this.artSumlike = artSumlike;
  }


  public Integer getArtScore() {
    return artScore;
  }

  public void setArtScore(Integer artScore) {
    this.artScore = artScore;
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
