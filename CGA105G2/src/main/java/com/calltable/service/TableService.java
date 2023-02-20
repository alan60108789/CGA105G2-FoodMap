package com.calltable.service;

import com.foodorder.model.Meal.dao.impl.MealJDBCDAO;
import com.foodorder.model.Reserva.dao.impl.ReservaJDBCDAO;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.foodorder.model.ReservaDetail.dao.impl.ReservaDetailJDBCDAO;
import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.Store.pojo.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableService {

    public Store topage(Integer storeId){
        //STORE_ETIME 訂位時段
        //STORE_TABLE 桌數
        Store store=new StoreDAO().getById(storeId);
        Store storetopage=new Store();
        storetopage.setStoreEtime(store.getStoreEtime());
        storetopage.setStoreTable(store.getStoreTable());
        return storetopage;
    }
    //訂位列表
    public Map<String,JSONArray> search(Integer storeId,String date,String time,Integer stat){
        Map<String,JSONArray> ansmap = new LinkedHashMap<String,JSONArray>();
        JSONArray json=new JSONArray();
        JSONObject obj=null;
        ReservaJDBCDAO reservaJDBCDAO=new ReservaJDBCDAO();
        List<Reserva> Rlist= reservaJDBCDAO.getByStoreIdRendate(storeId,date,time,stat);
        JSONArray tablehave=new JSONArray();
        for (Reserva e :Rlist){
            //table:reserva
            //姓名 REN_NAME
            //桌號 REN_TABLE
            //電話後4碼 REN_PHONE
            //人數 REN_HEADCOUNT
            //總金額 REN_PRICE
            obj=new JSONObject();
            Integer renId=e.getRenId();
            obj.put("REN_ID",renId);
            obj.put("NAME",e.getRenName());
            if (e.getRenTable()>0){
                tablehave.add(e.getRenTable());
            }
            obj.put("TABLE",e.getRenTable());
            String phone=e.getRenPhone();
            obj.put("PHONE",phone.substring(phone.length() - 4));
            obj.put("PepQ",e.getRenHeadcount());
            obj.put("PRICE",e.getRenPrice());
            //table:RESERVA_DETAIL 餐點明細
            //餐點編號 MEAL_ID
            //數量RD_QUANTITY
            ReservaDetailJDBCDAO rvddao=new ReservaDetailJDBCDAO();
            List<ReservaDetail> mmdlist=rvddao.getByPK(renId,"renId");
            JSONArray json2=new JSONArray();
            JSONObject obj2=null;
            for (ReservaDetail i:mmdlist){
                obj2=new JSONObject();
                Integer eatnameid=i.getMealId();
                MealJDBCDAO meadao=new MealJDBCDAO();
                String eatname=meadao.getByMealId(eatnameid).getMealName();
                obj2.put("EAT_NAME",eatname);
                Integer q=i.getRdQuantity();
                obj2.put("EAT_Q",q);
                json2.add(obj2);
            }
            obj.put("DETAIL",json2);
            json.add(obj);
        }
        List<Reserva> Rlist2= reservaJDBCDAO.getByStoreIdRendate(storeId,date,time,2);
        for (Reserva i :Rlist2){
            if (i.getRenTable()>0){
                tablehave.add(i.getRenTable());
            }
        }
        ansmap.put("tablehave",tablehave);
        ansmap.put("json",json);
        return ansmap;
    }
    public Boolean totable(Reserva pojo){
        ReservaJDBCDAO rjd=new ReservaJDBCDAO();
        rjd.update(pojo);
        return true;
    }


    // 候位新增至訂位
    public Reserva checkStandBy(Integer storeId, Integer memId, String renName, String renPhone, String renTime,
                                java.sql.Date renDate, Integer renHeadcount, Integer renPrice, Integer renFprice ) {
        ReservaJDBCDAO resDao =new ReservaJDBCDAO();
        Reserva reserva = new Reserva();
        reserva.setStoreId(storeId);
        reserva.setMemId(memId);
        reserva.setRenName(renName);
        reserva.setRenPhone(renPhone);
        reserva.setRenTime(renTime);
        reserva.setRenDate(renDate);
        reserva.setRenHeadcount(renHeadcount);
        reserva.setRenPrice(renPrice);
        reserva.setRenFprice(renFprice);
        resDao.insertToSta(reserva);
        Integer s= Integer.valueOf(reserva.getRenPhone());
        reserva=resDao.gettable(s);
        reserva.setRenTable(0);
        resDao.update(reserva);
        return reserva;
    }




}
