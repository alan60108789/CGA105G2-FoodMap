package com.art.model.Article.dao;

import com.art.model.Article.pojo.Article;

import java.util.List;


public interface ArtDAO_interface {
    
	public void insert(Article Article);
    List<Article> getAllByMemIdArtId(Integer memId, Integer artId);
    public void update(Article Article);
    public void delete(Integer artId);
    public Article getByMemId(Integer memId);
    public List<Article> getAll();
    Article getByArtId(Integer artId);
    public List<Article> getAllByMemId(Integer memId);
    public List<Article> getAllByStoreId(Integer storeId);
    public List<Article> getAllByMemIdStoreId(Integer memId, Integer storeId); //盟鎮的
}
