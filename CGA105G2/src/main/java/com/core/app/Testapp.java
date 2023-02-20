package com.core.app;

import com.code.model.Code.dao.impl.CodeJDBCDAO;
import com.code.model.Code.pojo.Code;
import com.code.model.service.CodeService;
import com.core.util.HibernateUtil;
import com.emp.model.EmployeeRoot.dao.impl.EmployeeRootJDBCDAO;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.store.model.Store.pojo.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Testapp {
    public static void main(String[] args) {
////        測試啟動
//        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
////        開啟資料庫
//        Session session=sessionFactory.openSession();
//        Store store=session.get(Store.class,1);
//
//        System.out.println(store.getStoreName());
////        關閉
//        HibernateUtil.shutdown();
//        Store store=new Store();
//        store.setStoreName("Anthony");


//        Testapp app=new Testapp();
//        新增
//        Integer id=app.insert(store);
//        System.out.println(id);
//        刪除
//        app.deleteById(7);
//        Integer empId=10;  //主管:1
//        CodeService codeSVR=new CodeService();
//        System.out.println(codeSVR.reviewCoupon(empId));
            new CodeService();
            Integer rootId=new CodeService().Coupon_root(1);
            List<Code> list=new CodeJDBCDAO().getBy(1,"狀態");
            JSONArray json = new JSONArray( );
            JSONObject map=null;
            Integer id=6;
        for(Code e : list){
            if (e.getEmpId()==id) {
                map = new JSONObject();
                map.put("CODE_ID", e.getCodeId());
                map.put("EMP_ID",e.getEmpId());
                map.put("CODE_STATUS", e.getCodeStatus());
                map.put("CODE_OFF", e.getCodeOff());
                map.put("CODE_NUM", e.getCodeNum());
                map.put("CODE_TEXT", e.getCodeText());
                map.put("CODE_NTIME", e.getCodeNtime().toString());
                json.add(map);
            }
        }
        for (Object e:json){
            System.out.println("A:"+e);
        }






    }

    public  Integer insert(Code code){
//        宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //        開啟資料庫
        Session session=sessionFactory.openSession();
        try {
//        開啟交易
            Transaction tx=session.beginTransaction();
//        交易物件
            session.persist(code);
//        確認送出交易
            tx.commit();
//        取得回傳
            return code.getCodeId();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }


    }

    public  void deleteById(Integer id){
//        宣告
        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //        開啟資料庫
        Session session=sessionFactory.openSession();
        try {
//        開啟交易
            Transaction tx=session.beginTransaction();
//        交易物件
            Store store =new Store();
            store.setStoreId(id);
            session.remove(store);
//        確認送出交易
            tx.commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }


    }
}
