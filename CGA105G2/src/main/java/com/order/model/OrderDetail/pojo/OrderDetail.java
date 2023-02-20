package com.order.model.OrderDetail.pojo;


import javax.persistence.*;

@Entity
@IdClass(OrderDetail_PK.class)
@Table(catalog = "cga105g2",name = "order_detail")
public class OrderDetail {
  @Id
  @Column(name = "ORDER_ID")
  private Integer orderId;
  @Id
  @Column(name = "GOODS_ID")
  private Integer goodsId;
  @Column(name = "DETAIL_QUANTITY")
  private Integer detailQuantity;
  @Column(name = "DETAILPRICE")
  private Integer DetailPrice;

  public OrderDetail() {
  }

public Integer getOrderId() {
	return orderId;
}

public void setOrderId(Integer orderId) {
	this.orderId = orderId;
}

public Integer getGoodsId() {
	return goodsId;
}

public void setGoodsId(Integer goodsId) {
	this.goodsId = goodsId;
}

public Integer getDetailQuantity() {
	return detailQuantity;
}

public void setDetailQuantity(Integer detailQuantity) {
	this.detailQuantity = detailQuantity;
}

public Integer getDetailPrice() {
	return DetailPrice;
}

public void setDetailPrice(Integer detailPrice) {
	DetailPrice = detailPrice;
}


}
