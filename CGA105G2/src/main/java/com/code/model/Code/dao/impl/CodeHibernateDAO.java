package com.code.model.Code.dao.impl;

import com.code.model.Code.dao.CodeDAO_interface;
import com.code.model.Code.pojo.Code;
import com.core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CodeHibernateDAO implements CodeDAO_interface {
    @Override
    public void insert(Code pojo) {
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            session.persist(pojo);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        Code code=new Code();
        code.setCodeId(id);
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            session.remove(code);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Code pojo) {
        Date today=new Date(System.currentTimeMillis());
        Code code_new=pojo;
        Code Code_old=getById(code_new.getCodeId());

        if (code_new.getEmpId()!=null){
            Code_old.setEmpId(code_new.getEmpId());
        };
        if (code_new.getCodeStatus()!=null){
            Code_old.setCodeStatus(code_new.getCodeStatus());
            Code_old.setCodeRtime(today);
        }
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            session.merge(Code_old);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void failUpdate(Integer codeStatus){
        Date today=new Date(System.currentTimeMillis());
        for (Code e:getByStatus(codeStatus)){
            if (e.getCodeNtime().before(today)){
                e.setCodeStatus(3);
                update(e);
            }
        }
    }

    @Override
    public Code getById(Integer id) {
        Code code=new Code();
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            code = session.get(Code.class, id);
            //確認送出交易
            tx.commit();
            return code;
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return new Code();
    }

    public List<Code> getByStatus(Integer codeStatus){
        List<Code> list=new ArrayList<Code>();
        //Code是VO名稱
        final String hql="From Code WHERE codeStatus="+codeStatus;
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Code.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Integer> getCodeId(String codeNum, Integer storeId) {
        return null;
    }

    @Override
    public List<Code> getBy(Integer id, String string) {
        return null;
    }

    @Override
    public List<Integer> getBycodeNum(String codeNum, Integer storeId) {
        return null;
    }

    @Override
    public List<Code> getAll() {
        List<Code> list=new ArrayList<Code>();
        //Code是VO名稱
        final String hql="From Code ORDER BY id";
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, Code.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        CodeHibernateDAO dao =new CodeHibernateDAO();
        //新增
//            Code code1=new Code(47,"LOVE3",520,"情人節優惠活動，帶她來吃飯，養他一輩子",Date.valueOf("2023-01-01"));
//            dao.insert(code1);
        //修改
//            Code code2=new Code(4,47,3,"LOVE",520,2,"情人節優惠活動，帶她來吃飯，養他一輩子",Date.valueOf("2023-03-30"));
//            Code code2=new Code(1,3);
//            Code code2=new Code(1,3,2);
//            dao.update(code2);
//        //getbyid
//            Code code3=dao.getById(5);
//            Code code3=dao.getById(code2.getCodeId());
//            System.out.println("CODE_ID:"+code3.getCodeId());
//            System.out.println("STORE_ID:"+code3.getStoreId());
//            System.out.println("EMP_ID:"+code3.getEmpId());
//            System.out.println("CODE_NUM:"+code3.getCodeNum());
//            System.out.println("CODE_OFF: $"+code3.getCodeOff());
//            System.out.println("CODE_STATUS:"+code3.getCodeStatus());
//            System.out.println("CODE_TEXT:"+code3.getCodeText());
//            System.out.println("CODE_TIME:"+code3.getCodeTime());
//            System.out.println("CODE_RTIME"+code3.getCodeRtime());
//            System.out.println("CODE_NTIME"+code3.getCodeNtime());
//            System.out.println("*******************************************");
//        //刪除
//            dao.deleteById(4);
          //排程失效failUpdate()
//            dao.failUpdate(2);
//        //getall
            List<Code> list=dao.getAll();
            for(Code e : list){
                System.out.println("CODE_ID:"+e.getCodeId());
                System.out.println("STORE_ID:"+e.getStoreId());
                System.out.println("EMP_ID:"+e.getEmpId());
                System.out.println("CODE_NUM:"+e.getCodeNum());
                System.out.println("CODE_OFF: $"+e.getCodeOff());
                System.out.println("CODE_STATUS:"+e.getCodeStatus());
                System.out.println("CODE_TEXT:"+e.getCodeText());
                System.out.println("CODE_TIME:"+e.getCodeTime());
                System.out.println("CODE_RTIME"+e.getCodeRtime());
                System.out.println("CODE_NTIME"+e.getCodeNtime());
                System.out.println("======================================");
            }


    }


}
