package com.goods.model.Cart.pojo;

public class Cart implements java.io.Serializable{
	
	private Integer memId;
	private Integer goodsId;
	private Integer cartNum;
	
	public Cart() {
		
	}
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getCartNum() {
		return cartNum;
	}
	public void setCartNum(Integer cartNum) {
		this.cartNum = cartNum;
	}	
}