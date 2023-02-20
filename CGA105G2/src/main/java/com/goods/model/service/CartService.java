package com.goods.model.service;

import java.util.List;
import java.sql.Timestamp;

import com.goods.model.Cart.pojo.Cart;
import com.goods.model.Cart.dao.CartDAO_interface;
import com.goods.model.Cart.dao.impl.CartJDBCDAO;

public class CartService {
	private CartDAO_interface dao;

	public  CartService() {
		dao = new CartJDBCDAO();
	}

	public Cart addCart(Integer memId, Integer goodsId, Integer cartNum) {

		Cart cartVO = new Cart();

		cartVO.setMemId(memId);
		cartVO.setGoodsId(goodsId);
		cartVO.setCartNum(cartNum);
		dao.insert(cartVO);
		
		return cartVO;
	}

	public Cart updateCart(Integer memId, Integer goodsId, Integer cartNum) {

		Cart cartVO = new Cart();

		cartVO.setMemId(memId);
		cartVO.setGoodsId(goodsId);
		cartVO.setCartNum(cartNum);
		dao.update(cartVO);

		return cartVO;
	}

	public void deleteCart(Integer cartno) {
		dao.delete(cartno);
	}

	public Cart getOneCart(Integer cartno) {
		return dao.getById(cartno);
	}

	public List<Cart> getAll() {
		return dao.getAll();
	}
}
