package com.point.model.service;

import java.util.List;

import com.member.model.Member.pojo.Member;
import com.point.model.Point.dao.impl.PointDAO;
import com.point.model.Point.pojo.Point;
import com.point.model.PointGoods.dao.PointGoodsDAO_interface;
import com.point.model.PointGoods.dao.impl.PointGoodsDAO;
import com.point.model.PointGoods.pojo.PointGoods;
import com.point.model.PointOrder.pojo.PointOrder;

import java.util.Set;

public class PointGoodsService {
	private PointGoodsDAO_interface dao;

	public PointGoodsService() {
		dao = new PointGoodsDAO();
	}

	public PointGoods addPointGood(byte[] pdImg, String pdName, Integer pdPrice, String pdText, java.sql.Timestamp pdTime, java.sql.Timestamp pdRtime, Integer pdStatus) {

		PointGoods pointgoods= new PointGoods();
		pointgoods.setPdImg(pdImg);
		pointgoods.setPdName(pdName);
		pointgoods.setPdPrice(pdPrice);
		pointgoods.setPdText(pdText);
		pointgoods.setPdTime(null);
		pointgoods.setPdRtime(null);
		pointgoods.setPdStatus(pdStatus);
		dao.insert(pointgoods);
		return pointgoods;
		
	}

	public PointGoods updatePointGood(Integer pdId, byte[] pdImg, String pdName, Integer pdPrice, String pdText, java.sql.Timestamp pdRtime, Integer pdStatus) {
		PointGoods pointgoods= new PointGoods();
		pointgoods.setPdId(pdId);
		pointgoods.setPdImg(pdImg);
		pointgoods.setPdName(pdName);
		pointgoods.setPdPrice(pdPrice);
		pointgoods.setPdText(pdText);
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		pointgoods.setPdRtime(sqlTimestamp);
		pointgoods.setPdStatus(pdStatus);
		dao.update(pointgoods);
		return pointgoods;
	}

	public void deletePointGood(Integer pdId) {
		dao.delete(pdId);
	}

	public PointGoods getPointGood(Integer pdId) {
		return dao.getByPK(pdId);
	}
	
	public List<PointGoods> getAll() {
		return dao.getAll();
	}
	
	public List<PointGoods> getAlready() {
		return dao.getAlready();
	}
}
