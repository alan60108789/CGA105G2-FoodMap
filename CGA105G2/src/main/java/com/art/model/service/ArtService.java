package com.art.model.service;

import java.util.List;

import com.art.model.Article.dao.ArtDAO_interface;
import com.art.model.Article.dao.impl.ArtJDBCDAO;
import com.art.model.Article.pojo.Article;

public class ArtService {

	private ArtDAO_interface dao;
	
	public ArtService() {
		dao = new ArtJDBCDAO();
	}
	
	public Article addArt(Integer memId, Integer storeId, String artHeader, String artText, byte[] artImg,
			Integer artScore,String artTag) {

		Article artVO = new Article();
		
		artVO.setMemId(memId);
		artVO.setStoreId(storeId);
		artVO.setArtHeader(artHeader);
		artVO.setArtText(artText);
		artVO.setArtImg(artImg);
		artVO.setArtScore(artScore);
		artVO.setArtTag(artTag);
		dao.insert(artVO);

		return artVO;
	}

	public Article updateArt(Integer artId,Integer memId, Integer storeId, String artHeader, String artText, byte[] artImg,
			Integer artScore) {

		Article artVO = new Article();

		artVO.setArtId(artId);
		artVO.setMemId(memId);
		artVO.setStoreId(storeId);
		artVO.setArtHeader(artHeader);
		artVO.setArtText(artText);
		artVO.setArtImg(artImg);
		artVO.setArtScore(artScore);
		dao.update(artVO);

		return artVO;

	}
	public Article getOneMem(Integer memId) {
		return dao.getByMemId(memId);
	}
	
	public Article getOneArt(Integer artId) {
		return dao.getByArtId(artId);
	}

	public List<Article> getAllMem(Integer memId) {
		return dao.getAllByMemId(memId);
	}
	
	public List<Article> getAllByStoreId(Integer storeId) {
		return dao.getAllByStoreId(storeId);
	}
	
	public List<Article> getAllByMemIdStoreId(Integer memId, Integer storeId) {
		return dao.getAllByMemIdStoreId(memId, storeId);
	}

	public List<Article> getAll() {
		return dao.getAll();
	}

	
	}
