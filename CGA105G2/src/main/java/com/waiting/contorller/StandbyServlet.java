package com.waiting.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.calltable.service.TableService;
import com.code.model.Code.dao.impl.CodeJDBCDAO;
import com.foodorder.model.Meal.pojo.Meal;
import com.foodorder.model.Reserva.dao.impl.ReservaJDBCDAO;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.foodorder.model.service.FoodorderService;
import com.google.gson.Gson;
import com.google.protobuf.TextFormat.Printer;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.Store.pojo.Store;
import com.waiting.model.dao.impl.StandbyDAO;
import com.waiting.model.pojo.Standby;
import com.waiting.model.service.StandbyService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/standby")
public class StandbyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        Integer storeId = (Integer) req.getSession().getAttribute("storeId");
        Integer memId = (Integer) req.getSession().getAttribute("memId");
        Integer empId = (Integer) req.getSession().getAttribute("empId");
        StandbyService staSvc = new StandbyService();
        if ("offStoreSts".equals(action)) {
            String onOff = req.getParameter("onOff");
            // 店家狀態關閉候位
            StoreDAO storeDAO = new StoreDAO();
            storeDAO.updateStatus(storeId, 5);
            if (onOff.equals("reset")) {
                // 關閉時清空候位表(打烊)
                StandbyDAO dao = new StandbyDAO();
                dao.resetStandBy();
            }
            res.getWriter().write("sts: off");
            return;
        }
        if ("checkNum".equals(action)) {
            res.setContentType("text/html;charset=UTF-8");
            Integer myStaId = Integer.valueOf(req.getParameter("myStaId"));
            Standby standby = staSvc.getOneStandBy(myStaId);
            Integer sts = standby.getStaStatus();

            if (sts == 1) {
                PrintWriter out = res.getWriter();
                out.println(sts);
                out.flush();
                out.close();
                return;
            }
            if (sts == 2) {
                PrintWriter out = res.getWriter();
                out.println(sts);
                out.flush();
                out.close();
                return;
            }
            return;
        }
        if ("getStoreSts".equals(action)) {
            res.setContentType("text/plain");
            res.setCharacterEncoding("UTF-8");
            StoreDAO storeDAO = new StoreDAO();
            Store store = storeDAO.getById(5);
            String StoreSts = Integer.toString(store.getStoreStatus());
            res.getWriter().write(StoreSts);
            return;
        }
        if ("getOneUpdate".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            Integer staId = Integer.valueOf(req.getParameter("staId"));
            StandbyService standbySvc = new StandbyService();
            Standby standbyVo = standbySvc.getOneStandBy(staId);
            req.setAttribute("standbyVo", standbyVo);
            String url = "/front-end/Member/standby/update_status_input.jsp";
            RequestDispatcher suceeessDispatcher = req.getRequestDispatcher(url);
            suceeessDispatcher.forward(req, res);
        }
        // -------------- Update狀態----------------------
        if ("Update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            // ---接收請求參數 & 輸入錯誤處理---
            Integer staId = Integer.valueOf(req.getParameter("staId").trim());
            Integer staStatus = Integer.valueOf(req.getParameter("staStatus").trim());
            Standby standbyVo = new Standby();
            standbyVo.setStaId(staId);
            standbyVo.setStaStatus(staStatus);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("standbyVo", standbyVo);
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/front-end/Member/standby/update_status_input.jsp");
                failureView.forward(req, res);
                return;
            }
            // 開始修改狀態
            StandbyService standBySvc = new StandbyService();
            standbyVo = standBySvc.updateStandBy(staId, staStatus);
            // 3.修改完成,準備轉交
            req.setAttribute("standbyVo", standbyVo);
            String url = "/front-end/store/calltable/callTable.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        // ===============================Insert(addStandBy.jsp)============================================
        if ("insertSta".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
//*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String Storename = req.getParameter("Storename");
            Integer sid = Integer.valueOf(req.getParameter("sid"));
             req.setAttribute("Storename", Storename);
             req.setAttribute("sid", sid);

            String staName = req.getParameter("staName");
            String sta_nameReg = "^[(\u4e00-\u9fa5)]{2,10}$";
            if (staName == null || staName.trim().length() == 0) {
                errorMsgs.add("姓名請勿空白");
            } else if (!staName.trim().matches(sta_nameReg)) {
                errorMsgs.add("填寫姓名只能使用中文，且長度在2-10之間");
            }

            String staPhone = req.getParameter("staPhone").trim();
            String phoneReg = "^09\\d{8}$";
            if (staPhone == null || staPhone.trim().length() == 0) {
                errorMsgs.add("電話請勿空白");
            } else if (!staPhone.trim().matches(phoneReg)) {
                errorMsgs.add("電話格式輸入錯誤");
            }
            Integer staNumber = null;
            try {
                staNumber = Integer.valueOf(req.getParameter("staNumber"));
            } catch (NumberFormatException e) {
                if (staNumber == null) {
                    errorMsgs.add("候位人數請勿空白");
                } else if (staNumber > 10) {
                    errorMsgs.add("候位人數太多了");
                }
            }
            Standby standbyVo = new Standby();
            standbyVo.setStaName(staName);
            standbyVo.setStoreId(sid);
            standbyVo.setStaPhone(staPhone);
            standbyVo.setStaNumber(staNumber);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("standbyVo", standbyVo); // 含有輸入格式錯誤的waitingVO物件,也存入req
                req.setAttribute("Storename", Storename);
                req.setAttribute("sid", sid);
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/waiting/addStandBy.jsp");
                failureView.forward(req, res);
                return;
            }
            // ============================開始新增=========================================
            StandbyDAO dao = new StandbyDAO();
            int stsid = dao.insert(standbyVo);
            req.setAttribute("Storename", Storename);
            req.setAttribute("sid", sid);
            req.setAttribute("staPhone", staPhone);
            req.setAttribute("standbyVo", standbyVo);
            req.setAttribute("stsid", stsid);
            req.setAttribute("staNumber", staNumber);
            String url = "/front-end/Member/waiting/listOneStandby.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        // =============================call(callTable)================================
        //通知
        if ("tocall".equals(action)) {
            Integer staId = Integer.valueOf(req.getParameter("toid"));
            staSvc.updateStandBy(staId, 1);
        }
        // =============================Delete(callTable)================================
        if ("delete".equals(action)) {
            // ===接收請求參數====
            Integer staId = Integer.valueOf(req.getParameter("staId"));
            // ===========開始刪除(叫號移除候位表)================d
            StandbyService standBySvc = new StandbyService();
            standBySvc.deleteStandBy(staId);
            // ==========刪除(叫號完成)===================
            String url = "/front-end/store/calltable/callTable.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("selectStandBy".equals(action)) {
            res.setContentType("text/html;charset=UTF-8");
            StoreDAO storeDAO = new StoreDAO();
            storeDAO.updateStatus(storeId, 4);
            String date = req.getParameter("date");
            String totime = req.getParameter("totime");
            req.setAttribute("date", date);
            req.setAttribute("totime", totime);
            List<Standby> list = staSvc.getAll();
            List<Standby> staList = new ArrayList<Standby>();
            for (Standby list2 : list) {
                staList.add(list2);
            }
            Gson gson = new Gson();
            String json = gson.toJson(staList);
            res.getWriter().write(json);
            return;
        }
        if ("checkMem".equals(action)) {
            // 修改STANDBY狀態狀態
            Integer staId = Integer.valueOf(req.getParameter("staId"));
            Standby standby = staSvc.updateStandBy(staId, 2);
            // ===新增到訂位===
            String date = req.getParameter("date");
            String totime = req.getParameter("totime").trim();
            String staName = req.getParameter("staName");
            String staPhone = req.getParameter("staPhone");
            Integer staNumber = Integer.valueOf(req.getParameter("staNumber"));
            TableService tableSvc = new TableService();
            Reserva e = tableSvc.checkStandBy(storeId, 0, staName, staPhone, totime, java.sql.Date.valueOf(date), staNumber, 0, 0);
            JSONObject obj = new JSONObject();
            obj.put("REN_ID", e.getRenId());
            obj.put("NAME", e.getRenName());
            obj.put("TABLE", e.getRenTable());
            obj.put("PHONE", e.getRenPhone().substring(e.getRenPhone().length() - 4));
            obj.put("PepQ", e.getRenHeadcount());
            obj.put("PRICE", e.getRenPrice());
            obj.put("DETAIL", null);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.println(obj.toJSONString());
            return;
        }
        if ("instandby".equals(action)) {
            Integer sid = Integer.valueOf(req.getParameter("foodorder_storeId"));
            Store sd = new StoreDAO().getById(sid);
            req.setAttribute("Storename", sd.getStoreName());
            req.setAttribute("sid", sid);
            String url = "/front-end/Member/waiting/addStandBy.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        //跳轉點餐網頁
        if ("buypage".equals(action)) {
            Integer storeid = Integer.valueOf(req.getParameter("sid").trim());
            StoreDAO sdao = new StoreDAO();
            String sname = sdao.getById(storeid).getStoreName();
            req.setAttribute("sname", sname);
            req.setAttribute("sid", storeid);
            Integer pn = Integer.valueOf(req.getParameter("staNumber").trim());
            req.setAttribute("foodorder_peopleNum", pn);
            String ph = req.getParameter("staPhone").trim();
            req.setAttribute("staPhone", ph);
            FoodorderService foodorderSvc = new FoodorderService();
            List<Meal> allMealbyStoreidStatus = foodorderSvc.getAllMealbyStoreidStatus(storeid, 1);
            req.setAttribute("list", allMealbyStoreidStatus);
            ReservaJDBCDAO rjd = new ReservaJDBCDAO();
            Integer renid = rjd.getphone(storeid, ph, pn).getRenId();
            req.setAttribute("renid", renid);
            String url = "/front-end/Member/waiting/buyFood.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("codecheck".equals(action)) {
            String code = req.getParameter("codetext");
            Integer sid = Integer.valueOf(req.getParameter("sid"));
            CodeJDBCDAO jdbcdao = new CodeJDBCDAO();
            List<Integer> off = jdbcdao.getBycodeNum(code, sid);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            JSONObject obj = new JSONObject();
            if (off.size() > 0) {
                obj.put("toResult", true);
                obj.put("mon", off.get(0));
            } else {
                obj.put("toResult", false);
                obj.put("errmag", "此優惠碼無法使用");
            }
            PrintWriter out = res.getWriter();
            out.println(obj);
            out.flush();
            out.close();
        }


    }

}
