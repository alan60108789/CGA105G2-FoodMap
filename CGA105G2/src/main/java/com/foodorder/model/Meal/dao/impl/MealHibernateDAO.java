package com.foodorder.model.Meal.dao.impl;

import com.core.util.HibernateUtil;
import com.foodorder.model.Meal.dao.MealDAO_interface;
import com.foodorder.model.Meal.pojo.Meal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;


public class MealHibernateDAO implements MealDAO_interface {

	@Override
	public void insert(Meal mealVO) {
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            session.persist(mealVO);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }

	}

	@Override
	public void update(Integer id, Integer status) {
		
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE cga105g2.meal set MEAL_STATUS=:MEAL_STATUS where MEAL_ID =:MEAL_ID")
            		.setParameter("MEAL_STATUS", status)
            		.setParameter("MEAL_ID", id);
            int result = nativeQuery.executeUpdate();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Meal> getAll() {
        List<Meal> list=new ArrayList<Meal>();
        //Code是VO名稱
        final String hql="From Meal ORDER BY id";
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Meal.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
		
	}

	@Override
	public List<Meal> getByStoreIdStatus(Integer id, Integer status) {
		List<Meal> list = new ArrayList<Meal>();
		String sql="select * from cga105g2.meal where";
		String where = null;
		if (status == 0) {
			where = " STORE_ID=:STORE_ID and MEAL_STATUS=0;";
		} else if (status == 1) {
			where = " STORE_ID=:STORE_ID and MEAL_STATUS=1;";
		}
		sql = sql + where;
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<Meal> nativeQuery = session.createNativeQuery(sql, Meal.class)
            		.setParameter("STORE_ID", id);
            list = nativeQuery.list();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;

	}

	@Override
	public Meal getByMealId(Integer id) {
		Meal meal = new Meal();
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            meal = session.get(Meal.class, id);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return meal;
	}

	@Override
	public void MealUpdateCommit(Meal mealVO, Integer mealid) {
		Meal meal = new Meal();
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            NativeQuery<?> nativeQuery = session.createNativeQuery("UPDATE cga105g2.meal set MEAL_STATUS=:MEAL_STATUS where MEAL_ID =:MEAL_ID")
            		.setParameter("MEAL_STATUS", 0)
            		.setParameter("MEAL_ID", mealid);
            int result = nativeQuery.executeUpdate();
            session.persist(mealVO);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
		
	}

//	public static void main(String[] args) {
//		MealHibernateDAO dao = new MealHibernateDAO();
//		// 新增
//		Meal meal1 = new Meal(58, "義大利麵套餐", 250 , 1);
//		dao.insert(meal1);
//
//		// 修改 某餐點編號的狀態 把編號四狀態修改成上架
//		dao.update(7, 0);
//		dao.update(meal3);
//
		// 查詢全部
//		List<Meal> list = dao.getAll();
//		for (Meal e : list) {
//			System.out.println(e.getMealId());
//			System.out.println(e.getMealName());
//			System.out.println("======================================");
//		}
//
//		// 依照storeid 搭配 狀態 查詢 目的:查出該店家所有上架商品
//		List<Meal> list = dao.getByStoreIdStatus(6, 1);
//		for (Meal e : list) {
//			System.out.println(e.getMealId());
//			System.out.println(e.getMealName());
//			System.out.println("======================================");
//		}
//		
//		// 依照mealid 查詢 目的:查出該餐點資訊  如果會員訂位查詢要帶出餐點名稱 訂位id->所有餐點id->不管上架下架都要秀
//		Meal meal = dao.getByMealId(6);
//		
//		System.out.println(meal.getMealId());
//		System.out.println(meal.getMealName());
//		System.out.println("======================================");
//
//		
//		
//
//	}

}
