package com.advertise.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advertise.model.Advertise.dao.Advertise_interface;
import com.advertise.model.Advertise.dao.impl.AdvertiseJDBCDAO;
import com.advertise.model.Advertise.pojo.Advertise;
import com.member.model.Member.dao.impl.MemberDAO;
import com.member.model.Member.pojo.Member;
import com.store.model.Store.dao.impl.StoreDAO;

public class AdvertiseService {
	private Advertise_interface dao;
	
	public AdvertiseService() {
		dao = new AdvertiseJDBCDAO();
	}
	
	public List<Advertise> getStatus(){
		return dao.getByStatus(1);
	}
	public List<Advertise> getStatusPass(){
		List<Advertise>  advertise = new ArrayList();
		Date date = new Date();
		for(Advertise ad:dao.getByStatus(2)) {
			if(date.before(ad.getAdvNtime())) {
				advertise.add(ad);
			}
		}
		return advertise;
	}
	public List<Advertise> getAll(){
		return dao.getAll();
	}
	public Advertise getByAdvId(Integer advId) {
		return dao.getByAdvId(advId);
	}

	
	public void update(Integer advId,Integer empId){
		Advertise advertise = dao.getByAdvId(advId);
		advertise.setAdvStatus(3);
		advertise.setEmpId(empId);
		dao.update(advertise);
		
	}
	public void updatePass(Integer advId,Integer empId){
		Advertise advertise = dao.getByAdvId(advId);
		advertise.setAdvStatus(2);
		advertise.setEmpId(empId);
		dao.update(advertise);
		
	}
	public void addAD(Advertise advertise)
    {
        dao.insert(advertise);
    }
	public void update1(Advertise advertise){
		dao.update(advertise);

	}
	public Advertise getByStoreId(Integer storeId){
		return dao.getByStoreId(storeId);
	}
	public void deleteByStoreId(Integer advId){
		 dao.delete(advId);
	}
}
