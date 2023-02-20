package com.point.model.service;

import java.util.List;

import com.art.model.Article.pojo.Article;
import com.point.model.Point.dao.PointDAO_interface;
import com.point.model.Point.dao.impl.PointDAO;
import com.point.model.Point.pojo.Point;

public class PointService {
	private PointDAO_interface dao;

	public PointService() {
		dao = new PointDAO();
	}

	public Point addPoint(Integer memId, String pointChange, Integer pointNumber) {

		Point point = new Point();

		point.setMemId(memId);
		point.setPointChange(pointChange);
		point.setPointNumber(pointNumber);
		dao.insert(point);

		return point;
	}

	public Point updatePoint(Integer pointId, Integer memId, String pointChange, Integer pointNumber) {

		Point point = new Point();
		point.setPointId(pointId);
		point.setMemId(memId);
		point.setPointChange(pointChange);
		point.setPointNumber(pointNumber);
		dao.update(point);

		return point;
	}

	public void deletePoint(Integer pointId) {
		dao.delete(pointId);
	}

	public Point getOnePoint(Integer pointId) {
		return dao.getByPK(pointId);
	}

	public List<Point> getAll() {
		return dao.getAll();
	}
	
	public List<Point> getAllMem(Integer memId) {
		return dao.getAllByMemId(memId);
	}
}
