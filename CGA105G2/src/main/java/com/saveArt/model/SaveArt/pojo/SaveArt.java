package com.saveArt.model.SaveArt.pojo;


import javax.persistence.*;

@Entity
@IdClass(SaveArt_PK.class)
@Table(catalog = "cga105g2",name = "save_art")
public class SaveArt implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ART_ID")
  private Integer artId;
  @Id
  @Column(name = "MEM_ID")
  private Integer memId;


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
