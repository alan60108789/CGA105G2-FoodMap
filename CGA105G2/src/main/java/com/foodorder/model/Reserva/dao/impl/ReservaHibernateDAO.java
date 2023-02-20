package com.foodorder.model.Reserva.dao.impl;

import com.code.model.Code.pojo.Code;
import com.core.common.Common;
import com.core.util.HibernateUtil;
import com.foodorder.model.Reserva.dao.ReservaDAO_interface;
import com.foodorder.model.Reserva.pojo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;





public class ReservaHibernateDAO implements ReservaDAO_interface {

	@Override
	public void insert(Reserva reservaVO) {
		if (reservaVO.getCodeId() != null) {
	        //宣告
	        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
	        //開啟資料庫
	        Session session=sessionFactory.openSession();
	        try {
	            //開啟交易
	            Transaction tx=session.beginTransaction();
	            //交易物件
	            NativeQuery<?> nativeQuery = session.createNativeQuery("INSERT INTO cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, CODE_ID, REN_PRICE, REN_FPRICE) VALUES(:STORE_ID, :MEM_ID, :REN_NAME, :REN_PHONE, :REN_TIME, :REN_DATE, :REN_HEADCOUNT, :CODE_ID, :REN_PRICE, :REN_FPRICE)")
	            		.setParameter("STORE_ID", reservaVO.getStoreId())
	            		.setParameter("MEM_ID", reservaVO.getMemId())
	            		.setParameter("REN_NAME", reservaVO.getRenName())
		        		.setParameter("REN_PHONE", reservaVO.getRenPhone())
		        		.setParameter("REN_TIME", reservaVO.getRenTime())
		        		.setParameter("REN_DATE", reservaVO.getRenDate())
		        		.setParameter("REN_HEADCOUNT", reservaVO.getRenHeadcount())
		        		.setParameter("CODE_ID", reservaVO.getCodeId())
		        		.setParameter("REN_PRICE", reservaVO.getRenPrice())
		        		.setParameter("REN_FPRICE", reservaVO.getRenFprice());
	            int result = nativeQuery.executeUpdate();
	            //確認送出交易
	            tx.commit();
	        }catch (Exception e){
	            session.getTransaction().rollback();
	            e.printStackTrace();
	        }
		}
		
		if (reservaVO.getCodeId() == null) {
	        //宣告
	        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
	        //開啟資料庫
	        Session session=sessionFactory.openSession();
	        try {
	            //開啟交易
	            Transaction tx=session.beginTransaction();
	            //交易物件
	            NativeQuery<?> nativeQuery = session.createNativeQuery("insert into cga105g2.reserva (STORE_ID, MEM_ID, REN_NAME, REN_PHONE, REN_TIME, REN_DATE, REN_HEADCOUNT, REN_PRICE, REN_FPRICE) values(:STORE_ID, :MEM_ID, :REN_NAME, :REN_PHONE, :REN_TIME, :REN_DATE, :REN_HEADCOUNT, :REN_PRICE, :REN_FPRICE)")
	            		.setParameter("STORE_ID", reservaVO.getStoreId())
	            		.setParameter("MEM_ID", reservaVO.getMemId())
	            		.setParameter("REN_NAME", reservaVO.getRenName())
		        		.setParameter("REN_PHONE", reservaVO.getRenPhone())
		        		.setParameter("REN_TIME", reservaVO.getRenTime())
		        		.setParameter("REN_DATE", reservaVO.getRenDate())
		        		.setParameter("REN_HEADCOUNT", reservaVO.getRenHeadcount())
		        		.setParameter("REN_PRICE", reservaVO.getRenPrice())
		        		.setParameter("REN_FPRICE", reservaVO.getRenFprice());
	            int result = nativeQuery.executeUpdate();
	            //確認送出交易
	            tx.commit();
	        }catch (Exception e){
	            session.getTransaction().rollback();
	            e.printStackTrace();
	        }
		}

	}

	@Override
	public void insertToSta(Reserva reservaVO) {

	}

	@Override
	public void update(Reserva reservaVO) {
		
        Date today=new Date(System.currentTimeMillis());
		Reserva reserva_new = reservaVO;
		Reserva reserva_old = getById(reservaVO.getRenId());
		if (reserva_new.getRenStatus() != null) {
			reserva_old.setRenStatus(reserva_new.getRenStatus());
//			reserva_old.setRenDate(today);
		}
		if (reserva_new.getRenTable() != null) {
			reserva_old.setRenTable(reserva_new.getRenTable());
		}
		//如果排程更改失效時 訂位要改成null就開啟else 如果就維持當時有設定位置 就可以不用加else
//		else {
//			// 因為初始值為null,如果不寫預設帶入0
//			reserva_old.setRenTable(null);
//		}
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE cga105g2.reserva set REN_STATUS= :REN_STATUS, REN_TABLE=:REN_TABLE, REN_DATE=:REN_DATE where REN_ID = :REN_ID")
            		.setParameter("REN_STATUS", reserva_old.getRenStatus())
            		.setParameter("REN_TABLE", reserva_old.getRenTable())
            		.setParameter("REN_DATE", reserva_old.getRenDate())
            		.setParameter("REN_ID", reserva_old.getRenId());
            int result = nativeQuery.executeUpdate();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }

	}

	@Override
	public Reserva getById(Integer id) {
		Reserva reserva=new Reserva();
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            reserva = session.get(Reserva.class, id);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return reserva;
	}

	@Override
	public List<Reserva> getAll() {
        List<Reserva> list=new ArrayList<Reserva>();
        //Code是VO名稱
        final String hql="From Reserva ORDER BY id";
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Reserva.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
	}

	public List<Reserva> getByStatus(Integer renStatus) {
        List<Reserva> list=new ArrayList<Reserva>();
        //Code是VO名稱
        final String hql="From Reserva WHERE renStatus="+renStatus;
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Reserva.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
	}

	public void failUpdate(Integer renStatus) {
		long miliseconds = System.currentTimeMillis();
		Date today = new Date(miliseconds);
		System.out.println(today);
		for (Reserva e : getByStatus(renStatus)) {
			System.out.println(e.getRenDate());
			System.out.println(today);
			System.out.println(e.getRenTable());
			if (e.getRenDate().before(today)) {
				Reserva reserva_old = new Reserva();
				reserva_old.setRenId(e.getRenId());
				reserva_old.setRenStatus(4);// 4為失效
				System.out.println(e.getRenId());
				update(reserva_old);
			}
		}
	}

	public static void main(String[] args) {
//		ReservaHibernateDAO dao = new ReservaHibernateDAO();
//		// 新增
//		Reserva reserva1 = new Reserva(6, 4, "林嘉誠", "0912345678", "12:00", Date.valueOf("2023-01-01"), 4, 800, 800);
//		dao.insert(reserva1);
//		Reserva reserva2 = new Reserva(6, 5, "高瑜蔓", "0912345678", "11:00", Date.valueOf("2023-01-01"), 4, 2, 800, 700);
//		System.out.println(reserva2.getCodeId());
//		dao.insert(reserva2);
//		// 修改
//		Reserva reserva3 = new Reserva(3,2,2);
//		dao.update(reserva3);
//		// getbyid
//		Reserva reserva3 = new Reserva(3,2,2);
//		Reserva reserva4 = dao.getById(reserva3.getCodeId());
//		Reserva reserva4 = dao.getById(4);
//		Reserva reserva4 = dao.getById(7);
//		System.out.println(reserva4==null);
//		System.out.println(reserva4.getRenName());
//		System.out.println("*******************************************");

//		List<Reserva> byStatus = dao.getByStatus(4);
//		for (Reserva e : byStatus) {
//			System.out.println(e.getRenId());
//		}
//		// 排程失效failUpdate()
//		dao.failUpdate(0);
//		dao.failUpdate(2);
		// getall
//		List<Reserva> list = dao.getAll();
//		for (Reserva e : list) {
//			System.out.println(e.getRenId());
//			System.out.println(e.getRenName());
//			System.out.println("======================================");
//		}

	}

	@Override
	public List<Reserva> getByStoreIdRendate(Integer storeid, String rendate, String rentime, Integer renstatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertWithReservaDetails(Reserva reservaVO, List list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reserva> getBymemIdrenStatus(Integer memid, Integer renstatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRenstatusByRenid(Integer renid, Integer renstatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Reserva> getBymemId(Integer memid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> getBystoreId(Integer storeid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reserva> getBystoreIdrenStatus(Integer storeid, Integer renstatus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva gettable(Integer id) {
		return null;
	}


}
