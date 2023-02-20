package com.saveArt.model.SaveArt.dao;

import com.saveArt.model.SaveArt.pojo.SaveArt;

import java.util.List;



public interface SaveArt_interface {
    public void insert(SaveArt SaveArt);
    public void delete(Integer artId, Integer memId);
    public SaveArt getByMemId(Integer memId);
    public List<SaveArt> getAll();
    public List<SaveArt> getAllById(Integer memId);
}
