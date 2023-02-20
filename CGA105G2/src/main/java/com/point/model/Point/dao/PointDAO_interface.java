package com.point.model.Point.dao;

import java.util.List;

import com.point.model.Point.pojo.Point;

public interface PointDAO_interface {

	public void insert(Point point);

	public void update(Point point);

	public void delete(Integer pointid);
	
	Point getByPK(Integer pointid);
	
    public List<Point> getAll();

	public List<Point> getAllByMemId(Integer memId);

}