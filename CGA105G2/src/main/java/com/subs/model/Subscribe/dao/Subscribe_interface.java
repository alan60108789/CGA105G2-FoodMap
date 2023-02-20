package com.subs.model.Subscribe.dao;

import com.subs.model.Subscribe.pojo.Subscribe;

import java.util.List;



public interface Subscribe_interface {
    public void insert(Subscribe Subscribe);
    public void delete(Integer storeId, Integer memId);
    public Subscribe getByMemId(Integer memId);
    public Subscribe getByMemIdStoreId(Integer storeId, Integer memId);
    public List<Subscribe> getAll();
    public List<Subscribe> getAllByMemId(Integer memId);
    public List<Subscribe> getAllByMemIdStoreId(Integer storeId, Integer memId);
}
