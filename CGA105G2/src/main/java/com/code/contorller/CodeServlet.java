package com.code.contorller;


import com.code.model.Code.pojo.Code;
import com.code.model.service.CodeService;
import com.emp.model.Employee.dao.impl.EmployeeJDBCDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CodeService codeSvc=new CodeService();
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId   = (Integer) req.getSession().getAttribute("memId");
        Integer empId   = (Integer) req.getSession().getAttribute("empId");
        if("addCode".equals(action)){
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            String codeNum = req.getParameter("codeNum");
            String codeNumReg =  "^[(a-zA-Z0-9)]{4,8}$";
            if(!codeNum.trim().matches(codeNumReg)) {
                errorMsgs.add("優惠券碼: 只能是4~8碼英文及數字的組合");};
                Integer codeOff = null;
            try {
                codeOff=Integer.valueOf(req.getParameter("codeOff").trim());
            } catch (NumberFormatException e) {
                errorMsgs.add("折扣金額請輸入數字");
            }
            java.sql.Date codeNtime = java.sql.Date.valueOf(req.getParameter("codeNtime").trim());
            String codeText = req.getParameter("codeText");
            Code code=codeSvc.todao(codeNum,codeOff,codeNtime,codeText);
            String url = "/front-end/store/code/addCode.jsp";
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("Code",code);
                req.setAttribute("toResult",false);
                RequestDispatcher failureView = req.getRequestDispatcher(url);
                failureView.forward(req, res);
                return;
            }
            codeSvc.addEmp(storeId, codeNum, codeOff, codeNtime, codeText);
            if (errorMsgs.isEmpty()){
                req.setAttribute("toResult",true);
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);
            }
        }
        if("storeCodeAll".equals(action)){
            JSONArray json =codeSvc.storeCodeAll(storeId);
            req.setAttribute("list_store", json);
            String url = "/front-end/store/code/selectAllCode.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("memCodeAllU".equals(action)){
            JSONArray json =codeSvc.memCodeAllU(memId);
            req.setAttribute("list_memU", json);
            String url = "/front-end/Member/code/listAllCode.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("memCodeAll".equals(action)){
            JSONArray json =codeSvc.memCodeAll(memId);
            req.setAttribute("list_mem", json);
            String url = "/front-end/Member/code/selectCodeHistory.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("reviewCoupon".equals(action)){
            Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
            req.setAttribute("errorMsgs", errorMsgs);
            Integer root=codeSvc.Coupon_root(empId);
            JSONArray json=codeSvc.empCode(empId);
            JSONArray empaccs=codeSvc.empacc();
            String empacc=new EmployeeJDBCDAO().findByEMP_ID(empId).getEmpAcc();
            String url = "/back-end/code/reviewCoupon.jsp";
            String errorString="";
            if (json.size()==0){
                if (root==4||root==1){
                    errorString="親愛的"+empacc+"員工您好，您目前無審核案件";
                }else {
                    errorString="親愛的"+empacc+"員工您好，您並無審核權限，請洽詢您的主管";
                }
                errorMsgs.put("error",errorString);
            }
            req.setAttribute("empaccs",empaccs);
            req.setAttribute("root", root);
            req.setAttribute("list_out", json);
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("empTo".equals(action)){
            Integer codeid = Integer.valueOf(req.getParameter("codeId"));
            Integer toempId = Integer.valueOf(req.getParameter("empId"));
            Boolean ans=codeSvc.toempId(codeid,toempId);
            req.setAttribute("ans",ans);
            String url = "/CodeServlet?action=reviewCoupon";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("codestat".equals(action)){
            Integer codeid = Integer.valueOf(req.getParameter("codeId"));
            Integer status = Integer.valueOf(req.getParameter("status"));
            Boolean ans=codeSvc.tostat(codeid,status);
            req.setAttribute("ans",ans);
            String url = "/CodeServlet?action=reviewCoupon";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("CouponPass".equals(action)){
            JSONArray json =codeSvc.statCodeAll();
            req.setAttribute("list_stat", json);
            String url = "/back-end/code/reviewCouponPass.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if("send".equals(action)){
            Integer codeid = Integer.valueOf(req.getParameter("codeId"));
            Boolean ans=null;
            List<String> Msgs = new LinkedList<String>();
            Integer comm=codeSvc.send(codeid,storeId);
            JSONArray json =codeSvc.storeCodeAll(storeId);
            req.setAttribute("Msgs", Msgs);
            if(comm>=0){
                ans=true;
                req.setAttribute("comm", comm);
                Msgs.add("新增成功");
            }else {
                ans=false;
                Msgs.add("目前無會員追蹤，無法發送優惠券");
                req.setAttribute("comm", 0);
            }
            req.setAttribute("list_store", json);
            req.setAttribute("ans", ans);
            String url = "/front-end/store/code/selectAllCode.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }
}


