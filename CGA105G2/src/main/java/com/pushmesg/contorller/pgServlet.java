package com.pushmesg.contorller;
import com.pushmesg.model.Smessage.dao.impl.SmessageJDBCDAO;
import com.pushmesg.model.service.pgService;
import org.json.simple.JSONArray;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/pgServlet")
public class pgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        pgService svc=new pgService();
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId   = (Integer) req.getSession().getAttribute("memId");
        Integer empId   = (Integer) req.getSession().getAttribute("empId");
        if("addpg".equals(action)){
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);

            String pglab=req.getParameter("pglab");
            if (pglab.isEmpty()){
                errorMsgs.put("pglab","標題請勿空白");
            }
            String pgtext=req.getParameter("pgtext");
            if(pgtext.isEmpty()){
                errorMsgs.put("pgtext","內容請勿空白");
            }
            req.setAttribute("pglab",pglab);
            req.setAttribute("pgtext", pgtext);
            if (!errorMsgs.isEmpty()){
                String url = "/front-end/store/pushmesg/addpg.jsp";
                RequestDispatcher failureView = req.getRequestDispatcher(url);
                failureView.forward(req, res);
                return;
            }
            String totaltext=pglab+":"+pgtext;
            Integer comm=svc.send(storeId,totaltext);
            List<String> Msgs = new LinkedList<String>();
            Boolean ans=null;
            if(comm>=0){
                ans=true;
                req.setAttribute("comm", comm);
                errorMsgs.put("sendOK","推播成功，已推播");
            }else {
                ans=false;
                errorMsgs.put("sendO","目前無會員追蹤，無法推播訊息");
                req.setAttribute("comm", 0);
            }
            req.setAttribute("ans", ans);
            String url = "/front-end/store/pushmesg/addpg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("allpg".equals(action)){
            JSONArray json =svc.all(memId);
            req.setAttribute("list", json);
            String url = "/front-end/Member/pushmesg/listAlpg.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("seeOK".equals(action)){
            SmessageJDBCDAO sjdbc=new SmessageJDBCDAO();
            Integer pgid= Integer.valueOf(req.getParameter("pgid"));
            sjdbc.deleteById(pgid);
        }
    }
}
