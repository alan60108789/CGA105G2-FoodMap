package com.store.contorller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.code.contorller.CodeServlet;
import com.code.model.service.CodeService;
import com.emp.model.Employee.dao.impl.EmployeeJDBCDAO;
import com.member.model.Member.pojo.Member;
import com.member.model.service.MemberService;
import com.store.model.Store.pojo.Store;
import com.store.model.service.StoreService;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import org.json.simple.JSONArray;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/Member/StoreServlet")
public class StoreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        Integer storeId = (Integer) request.getSession().getAttribute("storeId");
        Integer memid = (Integer) request.getSession().getAttribute("memId");
        Integer empId = (Integer) request.getSession().getAttribute("empId");
        //  update
        if ("update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String memname = request.getParameter("MEM_NAME");
            String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (memname == null || memname.trim().length() == 0) {
                errorMsgs.add("姓名: 請勿空白");
            } else if (!memname.trim().matches(memnameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
            String memacc = request.getParameter("MEM_ACC").trim();
            if (memacc == null || memacc.trim().length() == 0) {
                errorMsgs.add("帳號請勿空白");
            }
            String memrecipient = request.getParameter("MEM_RECIPIENT").trim();
            if (memrecipient == null || memrecipient.trim().length() == 0) {
                errorMsgs.add("姓名欄位請勿空白");
            }
            String memtwid = request.getParameter("MEM_TW_ID").trim();
            String memtwidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (memtwid == null || memtwid.trim().length() == 0) {
                errorMsgs.add("身分證字號請勿空白");
            } else if (!memtwid.trim().matches(memtwidReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("身分證字號格式錯誤");
            }
            java.sql.Date membirthday = null;
            try {
                membirthday = java.sql.Date.valueOf(request.getParameter("MEM_BIRTHDAY").trim());
            } catch (IllegalArgumentException e) {
                membirthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }
            String memphone = request.getParameter("MEM_PHONE").trim();
            if (memphone == null || memphone.trim().length() == 0) {
                errorMsgs.add("電話號碼請勿空白");
            }
            Integer mempostalcode = Integer.valueOf(request.getParameter("MEM_POSTAL_CODE").trim());
            if (mempostalcode == null) {
                errorMsgs.add("郵遞區號請勿空白");
            }
            String memcity = request.getParameter("MEM_CITY").trim();
            if (memcity == null || memcity.trim().length() == 0) {
                errorMsgs.add("城市請勿空白");
            }
            String memdistrict = request.getParameter("MEM_DISTRICT").trim();
            if (memdistrict == null || memdistrict.trim().length() == 0) {
                errorMsgs.add("地區請勿空白");
            }
            String memaddress = request.getParameter("MEM_ADDRESS").trim();
            if (memaddress == null || memaddress.trim().length() == 0) {
                errorMsgs.add("地址請勿空白");
            }
            String memmail = request.getParameter("MEM_MAIL").trim();
            if (memmail == null || memmail.trim().length() == 0) {
                errorMsgs.add("電子信箱請勿空白");
            }
            String memtext = request.getParameter("MEM_TEXT").trim();
            byte[] mempic = request.getPart("MEM_PIC").getInputStream().readAllBytes();
            Member Member = new Member();
            Member.setMemName(memname);
            Member.setMemAcc(memacc);
            Member.setMemRecipient(memrecipient);
            Member.setMemTwId(memtwid);
            Member.setMemBirthday(membirthday);
            Member.setMemPhone(memphone);
            Member.setMemPostalCode(mempostalcode);
            Member.setMemCity(memcity);
            Member.setMemDistrict(memdistrict);
            Member.setMemAddress(memaddress);
            Member.setMemMail(memmail);
            Member.setMemText(memtext);
            Member.setMemPic(mempic);
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("Member", Member);
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/Member/member/memberIInfo.jsp");
                failureView.forward(request, response);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            MemberService memSvc = new MemberService();
            Member = memSvc.updateMem(memid, memname, memacc, memrecipient, memtwid, membirthday, memphone, mempostalcode,
                    memcity, memdistrict, memaddress, memmail, memtext, mempic);
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/index.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //  search1
        if ("Search".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String srh = request.getParameter("search");
            String srhms = request.getParameter("Search");
            if (srh == null || (srh.trim()).length() == 0) {
                errorMsgs.add("請輸內容");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = request.getRequestDispatcher("/index.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            /*************************** 2.開始查詢資料 *****************************************/
            if ("searchMember".equals(srhms)) {
                MemberService memSvc = new MemberService();
                List<Member> Member = memSvc.getMember(srh);
                if (Member == null) {
                    errorMsgs.add("查無資料");
                    RequestDispatcher failureView = request.getRequestDispatcher("/index.jsp");
                    failureView.forward(request, response);
                    return;// 程式中斷
                }
                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
                request.setAttribute("memId", Member);
                String url = "/front-end/Member/member/searchMember.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url);
                successView.forward(request, response);
            }
        }
        if ("MemberInfo".equals(action)) {
            MemberService memSvc = new MemberService();
            Member Member = memSvc.meminfo(memid);
            request.setAttribute("Member", Member);
            String url = "/front-end/Member/member/memberIInfo.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //顯示所有審核案件
        if ("reviewStore".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            CodeService codeService = new CodeService();
            Integer root = codeService.Coupon_root(empId);
            JSONArray empaccs = codeService.empacc();
            StoreService storeService = new StoreService();
            JSONArray json = storeService.empstore(empId);
            String empacc = new EmployeeJDBCDAO().findByEMP_ID(empId).getEmpAcc();
            String url = "/back-end/store/reviewStore.jsp";
            String errorString = "";
            if (json.size() == 0) {
                if (root == 4 || root == 1) {
                    errorString = "親愛的" + empacc + "員工您好，您目前無審核案件，可以去玩手機";
                } else {
                    errorString = "親愛的" + empacc + "員工您好，依據設定顯示，您並無審核權限，請洽詢您的主管";
                }
                errorMsgs.put("error", errorString);
            }
            request.setAttribute("empaccs", empaccs);
            request.setAttribute("root", root);
            request.setAttribute("list_out", json);
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //選擇員工
        if ("empTo".equals(action)) {
            storeId = Integer.valueOf(request.getParameter("storeId"));
            Integer toempId = Integer.valueOf(request.getParameter("empId"));
            StoreService storeService = new StoreService();
            Boolean ans = storeService.toempId(storeId, toempId);
            request.setAttribute("ans", ans);
            String url = "/Member/StoreServlet?action=reviewStore";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //員工按下通過
        if ("storestat".equals(action)) {
            storeId = Integer.valueOf(request.getParameter("storeId"));
            Integer status = Integer.valueOf(request.getParameter("status"));
            StoreService storeService = new StoreService();
            Boolean ans = storeService.tostat(storeId, status);
            request.setAttribute("ans", ans);
            //如果不通過
            if (status == 0) {
                Store store0 = storeService.getById(storeId);
                store0.setStoreAcc(null);
                store0.setStorePwd(null);
                storeService.update(store0);
            }
            String url = "/Member/StoreServlet?action=reviewStore";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //查詢
        if ("StorePass".equals(action)) {
            StoreService storeService = new StoreService();
            JSONArray json = storeService.statStoreAll();
            request.setAttribute("list_stat", json);
            String url = "/back-end/store/reviewStorePass.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //  chgpwd(update)
        if ("chgpwd".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String storepwd = request.getParameter("STORE_PWD").trim();
            String storepwd2 = request.getParameter("STORE_PWD2").trim();
            String storepwd3 = request.getParameter("STORE_PWD3").trim();
            if (storepwd == null || storepwd.trim().length() == 0) {
                errorMsgs.add("原始密碼請勿空白");
            }
            if (storepwd2 == null || storepwd2.trim().length() == 0) {
                errorMsgs.add("新密碼請勿空白");
            }
            if (storepwd3 == null || storepwd3.trim().length() == 0) {
                errorMsgs.add("確認密碼請勿空白");
            }
            if (!storepwd2.equals(storepwd3)) {
                errorMsgs.add("確認密碼請與密碼相同");
            }
            Store Store = new Store();
            Store.setStorePwd(storepwd2);
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("Store", Store);
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/store/Login/changepwd2.jsp");
                failureView.forward(request, response);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            StoreService strSvc = new StoreService();
            Store store1 = strSvc.signin(strSvc.getById(storeId).getStoreAcc(), storepwd);
            Integer storeid2 = store1.getStoreId();
            if (storeid2 == 0) {
                errorMsgs.add("原始密碼錯誤");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/store/Login/changepwd2.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            } else {
                Store = strSvc.changepwd(storeId, storepwd2);
            }
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/store/Login/changepwd2.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //update2(store info)
        if ("update2".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            String storeaddress = request.getParameter("STORE_ADDRESS");
            if (storeaddress == null || storeaddress.trim().length() == 0) {
                errorMsgs.put("STORE_ADDRESS", "*店家地址請勿空白");
            }
            String storeacc = request.getParameter("STORE_ACC");
            if (storeacc == null || storeacc.trim().length() == 0) {
                errorMsgs.put("STORE_ACC", "帳號請勿空白");
            }
            String storehours = request.getParameter("STORE_HOURS");
            if (storehours == null || storehours.trim().length() == 0) {
                errorMsgs.put("STORE_HOURS", "帳號請勿空白");
            }
            String storecomid = request.getParameter("STORE_COM_ID");
            if (!storecomid.trim().matches("^[0-9]{8}$")) {
                errorMsgs.put("STORE_COM_ID", "統編格式不正確");
            }
            String storephone1 = request.getParameter("STORE_PHONE1");
            String storemail = request.getParameter("STORE_MAIL");
            if (!storemail.trim().matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$")) {
                errorMsgs.put("STORE_MAIL", "email格式不正確");
            }
            String storetext = request.getParameter("STORE_TEXT");
            String storeweb = request.getParameter("STORE_WEB");
            String storecomaddress = request.getParameter("STORE_COM_ADDRESS");
            if (storecomaddress == null || storecomaddress.trim().length() == 0) {
                errorMsgs.put("STORE_COM_ADDRESS", "帳號請勿空白");
            }
            String storetwid = request.getParameter("STORE_TW_ID");
            if (storetwid == null || storetwid.trim().length() == 0) {
                errorMsgs.put("STORE_TW_ID", "身分證請勿空白");
            } else if (!storetwid.trim().matches("^[a-zA-Z]\\d{9}$")) {
                errorMsgs.put("STORE_TW_ID", "身分證格式不正確");
            }
            String storephone2 = request.getParameter("STORE_PHONE2");
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/store/Login/storeRegister2.jsp");
                failureView.forward(request, response);
                return;
            }
            Integer storeid = (Integer) request.getSession().getAttribute("storeId");
            StoreService strsrv = new StoreService();
            strsrv.update(storeid, storeaddress, storeacc, storehours, storecomid, storephone1, storemail, storetext,
                    storeweb, storecomaddress, storephone2, storetwid);
            String url = "/Member/StoreServlet?action=searchinfo";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //   searchinfo(store info) ------------------------------------------------------------------------------------------------------------
        if ("searchinfo".equals(action)) {
            StoreService strsvc = new StoreService();
            Store Store=strsvc.getById(storeId);
            request.setAttribute("Store", Store);
            String url = "/front-end/store/Login/storeRegister2.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(request, response);

        }
    }
}