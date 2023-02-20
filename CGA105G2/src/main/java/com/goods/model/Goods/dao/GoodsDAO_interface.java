package com.goods.model.Goods.dao;

import com.goods.model.Goods.pojo.Goods;

import java.util.List;

public interface GoodsDAO_interface {
	public void insert(Goods goods);
    public void update(Goods goods);
    public void delete(Integer goodsno);
    public Goods getById(Integer goodsno);
    public List <Goods> getAll();
}
