package com.calltable.contorller;


import com.calltable.service.TableService;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.store.model.Store.pojo.Store;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        TableService tbs=new TableService();
        String action = req.getParameter("action");
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId   = (Integer) req.getSession().getAttribute("memId");
        Integer empId   = (Integer) req.getSession().getAttribute("empId");
        if("table".equals(action)){
            //進入網頁抓時段、桌數、動態產生網頁選項
            Store store=tbs.topage(storeId);
            String time[] = new String[0];
            if(store.getStoreEtime()!=null){
                time=store.getStoreEtime().split(",");
            }
            List<Integer> tablelist=new ArrayList<>();
            if (store.getStoreTable()!=0){
                for (int i=1;i<=store.getStoreTable();i++){
                    tablelist.add(i);
                }
            }else{
                String url = "/front-end/store/food_order/food_order.do?action=food_order_button";
                RequestDispatcher noView = req.getRequestDispatcher(url);
                noView.forward(req, res);
                return;
            }
            req.setAttribute("time",time);
            req.setAttribute("table",tablelist.toString());
            String url = "/front-end/store/calltable/callTable.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
            return;
        }
        //查詢訂位資訊
        if("search".equals(action)){
            String date= req.getParameter("date");
            String totime=req.getParameter("totime").trim();
            Map<String,JSONArray> ans=tbs.search(storeId,date,totime,0);
            Map<String,JSONArray> use=tbs.search(storeId,date,totime,2);
            JSONArray json=ans.get("json");
            JSONArray usejson=use.get("json");
            JSONArray tablehave=ans.get("tablehave");
            req.setAttribute("date",date);
            req.setAttribute("totime",totime);
            req.setAttribute("list",json.toJSONString());
            req.setAttribute("listq",json.size());
            req.setAttribute("tablehave",tablehave.toJSONString());
            req.setAttribute("usejson",usejson.toJSONString());
            String url = "/TableServlet?action=table";
            req.setAttribute("open",1);
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
            return;
        }
        //重新整理
        if("reload".equals(action)){
            String date= req.getParameter("date");
            String totime=req.getParameter("totime");
            req.setAttribute("date",date);
            req.setAttribute("totime",totime);
            String url = "/TableServlet?action=search";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
            return;
        }
        //帶位
        if("totable".equals(action)){
            Integer id= Integer.valueOf(req.getParameter("toid"));
            Integer table= Integer.valueOf(req.getParameter("table"));
            Reserva pojo=new Reserva();
            pojo.setRenId(id);
            pojo.setRenTable(table);
            tbs.totable(pojo);
            return;
        }
        //報到
        if("check".equals(action)){
            Integer id= Integer.valueOf(req.getParameter("toid"));
            Integer table= Integer.valueOf(req.getParameter("table"));
            Reserva pojo=new Reserva();
            pojo.setRenId(id);
            pojo.setRenStatus(2);
            pojo.setRenTable(table);
            tbs.totable(pojo);
            String date = req.getParameter("date");
            String totime = req.getParameter("totime").trim();
            Map<String,JSONArray> use=tbs.search(storeId,date,totime,2);
            JSONArray usejson=use.get("json");
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.println(usejson.toJSONString());
            return;
        }
        if ("out".equals(action)){
            Integer id= Integer.valueOf(req.getParameter("toid"));
            Reserva pojo=new Reserva();
            pojo.setRenId(id);
            pojo.setRenStatus(3);
            tbs.totable(pojo);
            return;
        }
    }
}
