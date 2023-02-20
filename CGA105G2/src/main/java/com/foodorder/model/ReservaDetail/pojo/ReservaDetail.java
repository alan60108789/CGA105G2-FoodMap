package com.foodorder.model.ReservaDetail.pojo;


import javax.persistence.*;

@Entity
@IdClass(ReservaDetail_PK.class)
@Table(catalog = "cga105g2",name = "reserva_detail")
public class ReservaDetail {

  @Id
  @Column(name ="REN_ID")
  private Integer renId;
  @Id
  @Column(name = "MEAL_ID")
  private Integer mealId;

  @Column(name = "RD_QUANTITY")
  private Integer rdQuantity;
  @Column(name = "PD_PRICE")
  private Integer pdPrice;



  public ReservaDetail() {
    super();
  }

  public ReservaDetail(Integer renId, Integer mealId, Integer rdQuantity, Integer pdPrice) {
    super();
    this.renId = renId;
    this.mealId = mealId;
    this.rdQuantity = rdQuantity;
    this.pdPrice = pdPrice;
  }



  public Integer getRenId() {
    return renId;
  }

  public void setRenId(Integer renId) {
    this.renId = renId;
  }


  public Integer getMealId() {
    return mealId;
  }

  public void setMealId(Integer mealId) {
    this.mealId = mealId;
  }

  public Integer getRdQuantity() {
    return rdQuantity;
  }

  public void setRdQuantity(Integer rdQuantity) {
    this.rdQuantity = rdQuantity;
  }


  public Integer getPdPrice() {
    return pdPrice;
  }

  public void setPdPrice(Integer pdPrice) {
    this.pdPrice = pdPrice;
  }


}
