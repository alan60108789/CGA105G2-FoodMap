package com.followmem.model.FollowMem.dao;

import com.followmem.model.FollowMem.pojo.FollowMem;

import java.util.List;



public interface FollowMem_interface {
    public void insert(FollowMem FollowMem);
    public void delete(Integer memId1, Integer memId2);
    public FollowMem getByMemId1(Integer memId1);
    public List<FollowMem> getAll();
    public List<FollowMem> getAllByMemId1(Integer memId);
    public List<FollowMem> getAllByMemId1MemId2(Integer memId1, Integer memId2);
}
