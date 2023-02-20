package com.likeart.model.LikeArt.pojo;

import javax.persistence.*;

@Entity
public class LikeArt implements java.io.Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "LIKE_ID")
  private Integer likeId;
  @Column(name = "ART_ID")
  private Integer artId;
  @Column(name = "MEM_ID")
  private Integer memId;

  public LikeArt() {

  }

  public LikeArt(Integer likeId, Integer artId, Integer memId) {
    this.likeId = likeId;
    this.artId = artId;
    this.memId = memId;
  }


  public Integer getLikeId() {
    return likeId;
  }

  public void setLikeId(Integer likeId) {
    this.likeId = likeId;
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

}
