package com.likeart.model.LikeArt.dao;

import com.likeart.model.LikeArt.pojo.LikeArt;

import java.util.List;


public interface LikeArt_interface {
    public void insert(LikeArt LikeArt);
    public void delete(Integer likeId);
    public LikeArt getById(Integer ArtId);
    public List<LikeArt> getAll();
    
}
