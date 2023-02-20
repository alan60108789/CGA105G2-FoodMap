package com.foodorder.model.ReservaDetail.dao;

import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;

import java.util.List;


public interface ReservaDetailDAO_interface {
    public void insert(ReservaDetail reservaDetailVO);
    public List<ReservaDetail> getByPK(Integer id, String pk);
    public List<ReservaDetail> getAll();
}
