package com.subs.model.service;

import java.util.List;

import com.subs.model.Subscribe.dao.Subscribe_interface;
import com.subs.model.Subscribe.dao.impl.SubscribeJDBCDAO;
import com.subs.model.Subscribe.pojo.Subscribe;

public class SubsService {
	
	private Subscribe_interface dao;
	
	public SubsService() {
		dao = new SubscribeJDBCDAO();
	}
	
	public Subscribe addSubscribe(Integer storeId, Integer memId) {

		Subscribe subscribe = new Subscribe();
		
		subscribe.setStoreId(storeId);
		subscribe.setMemId(memId);

		dao.insert(subscribe);

		return subscribe;
	}
	
	public void deleteSubscribe(Integer storeId, Integer memId) {
		dao.delete(storeId, memId);
	}
	
	public Subscribe getByMemIdStoreId(Integer storeId, Integer memId) {
		return dao.getByMemIdStoreId(storeId, memId);
	}
	
	public List<Subscribe> getAllByMemId(Integer memId) {
		return dao.getAllByMemId(memId);
	
	}
	
	public List<Subscribe> getAllByMemIdStoreId(Integer storeId, Integer memId) {
		return dao.getAllByMemIdStoreId(storeId, memId);
	
	}
}