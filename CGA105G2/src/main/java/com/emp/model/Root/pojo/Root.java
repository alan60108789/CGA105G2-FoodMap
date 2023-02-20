package com.emp.model.Root.pojo;

import javax.persistence.*;

@Entity
public class Root {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROOT_ID")
  private Integer rootId;
  @Column(name = "ROOT_TEXT")
  private String rootText;


  public Integer getRootId() {
    return rootId;
  }

  public void setRootId(Integer rootId) {
    this.rootId = rootId;
  }


  public String getRootText() {
    return rootText;
  }

  public void setRootText(String rootText) {
    this.rootText = rootText;
  }

}
