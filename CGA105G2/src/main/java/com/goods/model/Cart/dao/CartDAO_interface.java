package com.goods.model.Cart.dao;

import com.goods.model.Cart.pojo.Cart;

import java.util.List;



public interface CartDAO_interface {
		public void insert(Cart cart);
	    public void update(Cart cart);
	    public void delete(Integer cartno);


	Cart getById(Integer cartno);

	public List <Cart> getAll();
	    
}
