package com.followmem.model.service;

import java.util.List;

import com.followmem.model.FollowMem.dao.FollowMem_interface;
import com.followmem.model.FollowMem.dao.impl.FollowMemJDBCDAO;
import com.followmem.model.FollowMem.pojo.FollowMem;

public class FollowMemService {
	
	private FollowMem_interface dao;
	
	public FollowMemService() {
		dao = new FollowMemJDBCDAO();
	}
	
	public FollowMem addFollowMem(Integer memId1, Integer memId2) {

		FollowMem followMem = new FollowMem();
		
		followMem.setMemId1(memId1);
		followMem.setMemId2(memId2);

		dao.insert(followMem);

		return followMem;
	}
	
	public void deleteFollowMem(Integer memId1, Integer memId2) {
		dao.delete(memId1, memId2);
	}
	
	public List<FollowMem> getAllByMemId1(Integer memId) {
		return dao.getAllByMemId1(memId);
	}
	public List<FollowMem> getAllByMemId1MeMId2(Integer memId1, Integer memId2) {
		return dao.getAllByMemId1MemId2(memId1, memId2);
	}
}
