package com.code.model.MemCode.dao.impl;


import com.code.model.MemCode.dao.MemCodeDAO_interface;
import com.code.model.MemCode.pojo.MemCode;
import com.core.common.Common;
import com.core.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.common.Common.PASSWORD;
import static com.core.common.Common.USER;

public class MemCodeHibermateDAO implements MemCodeDAO_interface {
    @Override
    public void insert(MemCode pojo) {
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
        new SQLException();
    }

    @Override
    public void delete(MemCode pojo) {
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            session.remove(pojo);
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void update(MemCode pojo) {
        new SQLException();
    }

    @Override
    public void update(MemCode pojo1, MemCode pojo2) {
        String sql="update cga105g2.mem_code set CODE_ID=?,MEM_ID=? where CODE_ID=? and MEM_ID=?";
        try(Connection con= DriverManager.getConnection(Common.URL, USER, PASSWORD);
            PreparedStatement pstmt=con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            pstmt.setInt(1,pojo2.getCodeId());
            pstmt.setInt(2,pojo2.getMemId());
            pstmt.setInt(3,pojo1.getCodeId());
            pstmt.setInt(4,pojo1.getMemId());
            pstmt.executeUpdate();
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. "
                    + se.getMessage());
        }
    }

    @Override
    public MemCode getById(Integer id) {
        return null;
    }


    @Override
    public List<MemCode> getByPK(Integer id, String pk) {
        List<MemCode> list=new ArrayList<MemCode>();
        //Code是VO名稱
        String hql="From MemCode WHERE";
        if (pk.equals("codeId")){
            hql+=" codeId="+id;
        }else {
            hql+=" memId="+id;
        }
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, MemCode.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<MemCode> getAll() {
        List<MemCode> list=new ArrayList<MemCode>();
        //Code是VO名稱
        final String hql="From MemCode";
        //宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //開啟資料庫
        Session session=sessionFactory.openSession();
        try {
            //開啟交易
            Transaction tx=session.beginTransaction();
            //交易物件
            list = session.createQuery(hql, MemCode.class).getResultList();
            //確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        MemCodeHibermateDAO dao =new MemCodeHibermateDAO();
          //新增
//        for (int i=5;i<=8;i++){
//            MemCode memCode1=new MemCode(3,i);
//            dao.insert(memCode1);
//        }
//        //刪除
//            MemCode memCode4=new MemCode(1,1);
//            dao.delete(memCode4);
//
//        //getByPK
        //(1)codeId:查詢有優惠券id=1的會員有哪些
            List<MemCode> list1=dao.getByPK(3,"codeId");
            System.out.println("有此優惠券的會員如下:");
            for (MemCode e:list1){
                System.out.print(+e.getMemId()+"號、");
            }
            System.out.println();
            System.out.println("*******************************************");
        //(2)memId:查詢會員id=5的會員有哪些優惠券id
            List<MemCode> list2=dao.getByPK(1,"memId");
            System.out.println("此會員擁有的優惠券如下:");
            for (MemCode e:list2){
                System.out.print(e.getCodeId()+"號、");
            }
            System.out.println();
            System.out.println("*******************************************");
//        //getall
//            List<MemCode> list=dao.getAll();
//            for(MemCode e : list){
//                System.out.print("CodeId:"+e.getCodeId()+"\t");
//                System.out.print("MemId:"+e.getMemId()+"\n");
//            }
    }
}
