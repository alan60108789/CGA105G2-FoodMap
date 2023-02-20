package com.advertise.model.Advertise.dao;

import com.advertise.model.Advertise.pojo.Advertise;

import java.util.List;



public interface Advertise_interface {
	public void insert(Advertise advertiseVO);
    public void update(Advertise advertiseVO);
    public void delete(Integer advId);
    public Advertise getByAdvId(Integer advId);
    public Advertise getByStoreId(Integer storeId);
    public List<Advertise> getByStatus(Integer advStatus);
    public List<Advertise> getAll();
}
