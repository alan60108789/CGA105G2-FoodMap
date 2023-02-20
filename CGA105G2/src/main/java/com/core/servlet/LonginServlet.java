package com.core.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.code.model.Code.dao.impl.CodeJDBCDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodorder.model.Meal.dao.impl.MealJDBCDAO;
import com.foodorder.model.Meal.pojo.Meal;
import com.foodorder.model.Reserva.dao.impl.ReservaJDBCDAO;
import com.foodorder.model.Reserva.pojo.Reserva;
import com.foodorder.model.ReservaDetail.dao.impl.ReservaDetailJDBCDAO;
import com.foodorder.model.ReservaDetail.pojo.ReservaDetail;
import com.google.gson.Gson;
import com.member.model.Member.dao.impl.MemberDAO;
import com.pushmesg.model.service.pgService;
import com.store.model.Store.dao.impl.StoreDAO;
import com.waiting.contorller.Mailer;
import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;
import org.json.simple.JSONArray;

import com.art.model.Article.pojo.Article;
import com.art.model.service.ArtService;
import com.emp.model.Employee.pojo.Employee;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.emp.model.service.EmployeeService;
import com.followmem.model.FollowMem.pojo.FollowMem;
import com.followmem.model.service.FollowMemService;
import com.member.model.service.MemberService;
import com.point.model.Point.pojo.Point;
import com.point.model.service.PointService;
import com.member.model.Member.pojo.Member;
import com.store.model.Store.pojo.Store;
import com.store.model.service.StoreService;
import com.subs.model.Subscribe.pojo.Subscribe;
import com.subs.model.service.SubsService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@MultipartConfig
@WebServlet("/LonginServlet")
public class LonginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static AllInOne domain;

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
        if ("insert".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String memname = request.getParameter("MEM_NAME");
            request.setAttribute("memname",memname);
            String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (memname == null || memname.trim().length() == 0) {
                errorMsgs.add("姓名: 請勿空白");
            } else if (!memname.trim().matches(memnameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String memacc = request.getParameter("MEM_ACC").trim();
            request.setAttribute("memacc",memacc);
            if (memacc == null || memacc.trim().length() == 0) {
                errorMsgs.add("帳號請勿空白");
            } else if (!memacc.matches("^[a-zA-Z0-9]+$")) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("帳號: 只能是英文字母和數字組成，不能有特殊符號、_等");
            }
            String mempwd = request.getParameter("MEM_PWD").trim();
            if (mempwd == null || mempwd.trim().length() == 0) {
                errorMsgs.add("密碼請勿空白");
            }
            String mempwd2 = request.getParameter("MEM_PWD2").trim();
            if (mempwd2 == null || mempwd2.trim().length() == 0) {
                errorMsgs.add("確認密碼請勿空白");
            }
            String memrecipient = request.getParameter("MEM_RECIPIENT").trim();
            request.setAttribute("memrecipient",memrecipient);
            if (memrecipient == null || memrecipient.trim().length() == 0) {
                errorMsgs.add("姓名欄位請勿空白");
            }
            String memtwid = request.getParameter("MEM_TW_ID").trim();
            String memtwidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            request.setAttribute("memtwid",memtwid);
            if (memtwid == null || memtwid.trim().length() == 0) {
                errorMsgs.add("身分證字號請勿空白");
            } else if (!memtwid.trim().matches(memtwidReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("身分證字號格式錯誤");
            }
            java.sql.Date membirthday = null;
            try {
                membirthday = java.sql.Date.valueOf(request.getParameter("MEM_BIRTHDAY").trim());
                request.setAttribute("membirthday",membirthday);
            } catch (IllegalArgumentException e) {
                membirthday = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }
            String memphone = request.getParameter("MEM_PHONE").trim();
            request.setAttribute("memphone",memphone);
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
            request.setAttribute("memaddress",memaddress);
            if (memaddress == null || memaddress.trim().length() == 0) {
                errorMsgs.add("地址請勿空白");
            }
            String memmail = request.getParameter("MEM_MAIL").trim();
            request.setAttribute("memmail",memmail);
            if (memmail == null || memmail.trim().length() == 0) {
                errorMsgs.add("電子信箱請勿空白");
            }
            Member Member = new Member();
            MemberService memSvc = new MemberService();


            memSvc.srhmail(memmail);
            if (!(memSvc.srhacc(memacc) == null)) {
                errorMsgs.add("帳號已被註冊");
            }
            if (!(memSvc.srhacc(memacc) == null)) {
                errorMsgs.add("Email已被註冊");
            }

            Member = memSvc.topojo(memname, memacc, mempwd, memrecipient, memtwid, membirthday, memphone, mempostalcode,
                    memcity, memdistrict, memaddress, memmail);
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("Member", Member);
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/Member/member/memberRegister.jsp");

                failureView.forward(request, response);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            Member = memSvc.addMem(memname, memacc, mempwd, memrecipient, memtwid, membirthday, memphone, mempostalcode,
                    memcity, memdistrict, memaddress, memmail);
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            //註冊成功寄email
            String ServerName=request.getServerName();
            Mailer mailer =new Mailer();
            mailer.sendAccount(memname, memmail,ServerName);
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/Member/member/regisetDone.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("Signinm".equals(action)) {
            List<String> errorMsgm = new LinkedList<String>();
            request.setAttribute("errorMsgm", errorMsgm);
            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String memacc = request.getParameter("MEM_ACC");
            String mempwd = request.getParameter("MEM_PWD");
            Member member = new Member();
            member.setMemAcc(memacc);
            request.setAttribute("member", member);
            if (memacc == null || (memacc.trim()).length() == 0 || mempwd == null || (mempwd.trim()).length() == 0) {
                errorMsgm.add("帳密不可為空白");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            MemberService memSvc = new MemberService();
            member = memSvc.signin(memacc, mempwd);
            Integer memid = member.getMemId();
            if (memid == 0) {
                errorMsgm.add("查無帳號");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            Integer stat = member.getMemStatus();
            if(stat==1){
                errorMsgm.add("帳號尚未開通，請至mail信箱收信開通帳號");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            request.getSession().setAttribute("memberName", memSvc.getById(memid).getMemName());
            request.getSession().setAttribute("memId", memid);
            String wanttogo= (String) request.getSession().getAttribute("togourl");
            if(wanttogo!=null){
                RequestDispatcher togoView = request.getRequestDispatcher(wanttogo);
                request.getSession().setAttribute("togourl", null);
                togoView.forward(request, response);
                return;// 程式中斷
            }
            String url ="/LonginServlet?action=todo";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("open".equals(action)) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Boolean Rust=new MemberDAO().open(id);
            request.setAttribute("Rust",Rust);
            String url ="/front-end/Member/member/open.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        // signin store
        if ("Signins".equals(action)) {
            List<String> errorMsgS = new LinkedList<String>();
            request.setAttribute("errorMsgS", errorMsgS);
            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String storeacc = request.getParameter("STORE_ACC");
            String storepwd = request.getParameter("STORE_PWD");
            Store store = new Store();
            store.setStoreAcc(storeacc);
            request.setAttribute("store", store);
            request.setAttribute("storeacc", storeacc);
            request.setAttribute("errorMsgS", errorMsgS);
            if (storeacc == null || (storeacc.trim()).length() == 0 || storepwd == null || (storepwd.trim()).length() == 0) {
                errorMsgS.add("帳密不可為空白");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            StoreService storeSvc = new StoreService();
            Store Store = storeSvc.signin(storeacc, storepwd);
            Integer storeid = Store.getStoreId();
            if (storeid == 0) {
                errorMsgS.add("查無帳號");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            /*************************** 判斷狀態 *************/
            Integer storestatus = storeSvc.getById(storeid).getStoreStatus();
            Integer storeplan = storeSvc.getById(storeid).getStorePlan();
            if (storestatus == 1) {
                errorMsgS.add("帳號審核中");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            if (storestatus == 3) {
                errorMsgS.add("此帳號遭停權，請聯繫客服");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            request.getSession().setAttribute("storeId", storeid);
            request.getSession().setAttribute("StoreName", storeSvc.getById(storeid).getStoreName());
                String url = "/index.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url);
                successView.forward(request, response);

        }
        // signin emp
        if ("login".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            String acc = request.getParameter("empAcc");
            String pwd = request.getParameter("empPwd");
            EmployeeService employeeService = new EmployeeService();
            Employee employee = employeeService.getAcc(acc);
            if (employee == null) {
                errorMsgs.add("*請輸入正確的員工帳號");
                RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/employeeLogin.jsp");
                failureView.forward(request, response);
                return;
            }
            String empPwd = employee.getEmpPwd();
            if (!(pwd.equals(empPwd))) {
                errorMsgs.add("*請輸入正確的員工密碼");
                RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/employeeLogin.jsp");
                failureView.forward(request, response);
                return;
            }
            if(employee.getEmpStatus()==1) {
            	 errorMsgs.add("*員工已被停權");
            	 RequestDispatcher failureView = request.getRequestDispatcher("/back-end/emp/employeeLogin.jsp");
                 failureView.forward(request, response);
                 return;
            }
            Integer empId = employee.getEmpId();
            List<EmployeeRoot> empRoot = employeeService.getRoot(empId);
            HttpSession session = request.getSession();
            session.setAttribute("loginEmployee", employee);
            session.setAttribute("empRoot", empRoot);
            session.setAttribute("empId", empId);
            String url = "/index.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("todo".equals(action)){
            pgService pgs=new pgService();
            Integer notify= pgs.see((Integer) request.getSession().getAttribute("memId"));
            request.setAttribute("notify",notify);
            String url = "/index.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("out".equals(action)) {
            request.getSession().setAttribute("memId", 0);
            request.getSession().setAttribute("storeId", 0);
            request.getSession().setAttribute("empId", 0);
            String url = "/index.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("byStoreName".equals(action)) { // 來自index.jsp的請求
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String storeName = request.getParameter("storeName");
//            if (storeName.substring(0,1).equals("@")){
//                String text=storeName.replace("@", "");
//                String url = "/LonginServlet?action=byMemName&storeName="+text;
//                request.setAttribute("storeName", text);
//                RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 searchStore.jsp
//                successView.forward(request, response);
//                return;
//            }
            if (storeName == null || (storeName.trim()).length() == 0) {
                errorMsgs.put("error1", "錯誤:請輸入要查詢的文字");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/index.jsp");
                failureView.forward(request, response);
                return;//程式中斷
            }
            /***************************2.開始查詢資料*****************************************/
            StoreService storeService = new StoreService();
            List<Store> list = storeService.getStoreName(storeName);
            if (list.size() == 0) {
                errorMsgs.put("error2", "查無資料，請換一個搜索字");
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/index.jsp");
                failureView.forward(request, response);
                return;//程式中斷
            }
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            request.setAttribute("list", list); // 資料庫取出的list物件,存入req
            String url = "/front-end/Member/member/searchStore.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 searchStore.jsp
            successView.forward(request, response);
        }
        if ("StorePage".equals(action)) { //來自searchStore的請求 要轉交到店家頁面
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer memId = (Integer) request.getSession().getAttribute("memId");
            Integer storeId = Integer.valueOf(request.getParameter("SearchstoreId").trim());    //拿到我設定的input hidden
            /***************************2.開始查詢資料*****************************************/
            StoreService storeService = new StoreService();
            Store store = storeService.getById(storeId);  //依照剛剛取得的id 去找尋該筆店家
            Integer sts=store.getStoreStatus();
            Integer st=0;
            if (store.getStoreEtime()!=null){
                st=store.getStoreEtime().length();
            }
            Integer mlist=new MealJDBCDAO().getByStoreIdStatus(storeId,1).size();
            Integer canopen=sts*st*mlist;
            request.setAttribute("canopen", canopen);   //這邊在算店家的評分 加上四捨五入的方法
            request.setAttribute("sts", sts);
            MemberService memberService = new MemberService();
            Member member = memberService.getById(memId);
            ArtService artService = new ArtService();
            List<Article> articlelist = artService.getAllByStoreId(storeId);
            float sum = 0;
            int commemt = articlelist.size();
            request.setAttribute("commemt", commemt);//算共有幾則評論
            for (int i = 0; i < articlelist.size(); i++) {
                Article article = articlelist.get(i);
                float a = article.getArtScore();
                sum = sum + a;
            }
            try {
                DecimalFormat df = new DecimalFormat("#.0");
                float StoreScore = Float.valueOf(df.format(sum / articlelist.size()));
                request.setAttribute("StoreScore", StoreScore);   //這邊在算店家的評分 加上四捨五入的方法
            } catch (NumberFormatException e) {

            }
            SubsService subSvs = new SubsService();
            List<Subscribe> subslist = subSvs.getAllByMemIdStoreId(storeId, memId);
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            request.setAttribute("store", store);//set店家讓下個頁面能收到值
            request.setAttribute("member", member);//set會員讓下個頁面能收到值
            request.setAttribute("articlelist", articlelist);
            request.setAttribute("subslist", subslist);
            String url = "/front-end/Member/member/showStorePage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 showStorePage.jsp
            successView.forward(request, response);
        }
        if ("byMemName".equals(action)) { // 來自index.jsp的請求，這是搜尋會員
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String memAcc = request.getParameter("storeName");
            if (memAcc == null || (memAcc.trim()).length() == 0) {
                errorMsgs.put("error1", "請輸入要查詢的文字");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/index.jsp");
                failureView.forward(request, response);
                return;//程式中斷
            }
            /***************************2.開始查詢資料*****************************************/
            MemberService memService = new MemberService();
            List<Member> list = memService.getAllByAcc(memAcc); //依照會員的帳號去搜
            if (list.size() == 0) {
                errorMsgs.put("error2", "查無資料");
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/index.jsp");
                failureView.forward(request, response);
                return;//程式中斷
            }
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            request.setAttribute("list", list); // 資料庫取出的list物件,存入req
            String url = "/front-end/Member/member/searchMember.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 searchMember.jsp
            successView.forward(request, response);
        }
        //==========================查看會員頁面=================================
        if ("MemberPage".equals(action)) { //來自searchMember的請求 要轉交到會員頁面
            request.setCharacterEncoding("UTF-8");
            Integer nameid= (Integer) request.getSession().getAttribute("memId");
            if (nameid==0){
                String url = "/front-end/Member/member/memberLognIn.jsp";
                RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 showMemberPage.jsp會員頁面
                successView.forward(request, response);
                return;
            }

            String name= new MemberDAO().getById(nameid).getMemName();
            request.setAttribute("userName", name);
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer memId1 = (Integer) request.getSession().getAttribute("memId");
            Integer memId2 = Integer.valueOf(request.getParameter("SearchMemberId").trim());    //拿到我設定的input hidden
            /***************************2.開始查詢資料*****************************************/
            MemberService memService = new MemberService();
            Member member2 = memService.getById(memId2); //別的會員
            Member member1 = memService.getById(memId1); //登入的本人
            ArtService artService = new ArtService();
            List<Article> list = artService.getAllMem(memId2);
            request.setAttribute("userName", new MemberDAO().getById(memId2).getMemName());
            FollowMemService followMemService = new FollowMemService();
            List<FollowMem> followlist = followMemService.getAllByMemId1MeMId2(memId1, memId2);
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            request.setAttribute("member1", member1);
            request.setAttribute("member2", member2); //set店家讓下個頁面能收到值
            request.setAttribute("list", list); //這是文章的list
            request.setAttribute("followlist", followlist); //追蹤的list
            String url = "/front-end/Member/member/showMemberPage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 showMemberPage.jsp會員頁面
            successView.forward(request, response);
        }
        if ("quithitgame".equals(action)) { //來自searchMember的請求 要轉交到會員頁面
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer memId1 = (Integer) request.getSession().getAttribute("memId");
            Integer memId2 = Integer.valueOf(request.getParameter("SearchMemberId").trim());    //拿到我設定的input hidden
            /***************************2.開始查詢資料*****************************************/
            MemberService memService = new MemberService();
            Member member2 = memService.getById(memId2); //別的會員
            Member member1 = memService.getById(memId1); //登入的本人
            ArtService artService = new ArtService();
            List<Article> list = artService.getAllMem(memId2);
            FollowMemService followMemService = new FollowMemService();
            List<FollowMem> followlist = followMemService.getAllByMemId1MeMId2(memId1, memId2);
            Point point = new Point();
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
			PointService pointservice = new PointService();
			point = pointservice.addPoint(memId1, "玩遊戲", 1);
            MemberService memSvc = new MemberService();
            Member member = memSvc.meminfo(memId1);
            member=memSvc.updmemPoint(memId1,Integer.valueOf(member.getMemPoint()+1));
            request.setAttribute("member1", member1);
            request.setAttribute("member2", member2); //set店家讓下個頁面能收到值
            request.setAttribute("list", list); //這是文章的list
            request.setAttribute("followlist", followlist); //追蹤的list
            request.setAttribute("toResult",true);
            String url = "/front-end/Member/member/showMemberPage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交原本的會員頁面
            successView.forward(request, response);
        }


        if ("getOtherMemberArticlePhoto".equals(action)) { //秀文章圖片
            OutputStream out = response.getOutputStream();
            String artId = request.getParameter("artId");
            ArtService artService = new ArtService();
            Article article = artService.getOneArt(Integer.parseInt(artId));
            out.write(article.getArtImg());
            out.close();
        }
        if ("getOtherMemberPhoto".equals(action)) { //秀會員圖片
            OutputStream out = response.getOutputStream();
            String memId = request.getParameter("memId");
            MemberService memberService = new MemberService();
            Member member = memberService.getById(Integer.parseInt(memId));
            out.write(member.getMemPic());
            out.close();
        }
        //  forget1(update)
        if ("forget1".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String memacc = request.getParameter("MEM_ACC").trim();
            String mempwd = request.getParameter("MEM_PWD").trim();
            String mempwd2 = request.getParameter("MEM_PWD2").trim();
            if (memacc == null || memacc.trim().length() == 0) {
                errorMsgs.add("帳號請勿空白");
            }
            if (mempwd == null || mempwd.trim().length() == 0) {
                errorMsgs.add("密碼請勿空白");
            }
            if (mempwd2 == null || mempwd2.trim().length() == 0) {
                errorMsgs.add("確認密碼請勿空白");
            }
            if (!mempwd2.equals(mempwd)) {
                errorMsgs.add("確認密碼請與密碼相同");
            }
            Member Member = new Member();
            Member.setMemAcc(memacc);
            Member.setMemPwd(mempwd);
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("Member", Member);
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/Member/member/forget1.jsp");
                failureView.forward(request, response);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            MemberService memSvc = new MemberService();
            Member = memSvc.forget1(memacc, mempwd);
            Member member1 = memSvc.signin(memacc, mempwd);
            Integer memid = member1.getMemId();
            if (memid == 0) {
                errorMsgs.add("查無帳號");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/forget1.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/Member/member/memberLognIn.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);

        }
        //  forget2(update)
        if ("forget2".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            request.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            String storeacc = request.getParameter("STORE_ACC").trim();
            String storepwd = request.getParameter("STORE_PWD").trim();
            String storepwd2 = request.getParameter("STORE_PWD2").trim();
            if (storeacc == null || storeacc.trim().length() == 0) {
                errorMsgs.add("帳號請勿空白");
            }
            if (storepwd == null || storepwd.trim().length() == 0) {
                errorMsgs.add("密碼請勿空白");
            }
            if (storepwd2 == null || storepwd2.trim().length() == 0) {
                errorMsgs.add("確認密碼請勿空白");
            }
            if (!storepwd2.equals(storepwd)) {
                errorMsgs.add("確認密碼請與密碼相同");
            }
            Store Store = new Store();
            Store.setStoreAcc(storeacc);
            Store.setStorePwd(storepwd);
            if (!errorMsgs.isEmpty()) {
                request.setAttribute("Store", Store);
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/Member/member/forget2.jsp");
                failureView.forward(request, response);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            StoreService strSvc = new StoreService();
            Store = strSvc.forget1(storeacc, storepwd);
            Store store1 = strSvc.signin(storeacc, storepwd);
            Integer storeid = store1.getStoreId();
            if (storeid == 0) {
                errorMsgs.add("查無帳號");
                RequestDispatcher failureView = request.getRequestDispatcher("/front-end/Member/member/forget2.jsp");
                failureView.forward(request, response);
                return;// 程式中斷
            }
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/Member/member/memberLognIn.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //searchstore1
        if ("searchstore1".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            String storecity = request.getParameter("STORE_CITY").trim();
            String storedistrict = request.getParameter("STORE_DISTRICT").trim();
            StoreService strsrv = new StoreService();
            JSONArray json = strsrv.getAllByAddress(storecity, storedistrict);
            String errorString = "";
            if (json.size() == 0) {
                errorString = "目前" + storecity + storedistrict + "沒有店家可以申請，若有疑問請聯絡客服";
            }
            request.setAttribute("list_out", json);
            String url = "/front-end/store/Login/storeRegister0.2.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //update1
        if ("updatest1".equals(action)) {
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            Integer storeid = Integer.valueOf(request.getParameter("STORE_ID").trim());
            StoreService strsrv = new StoreService();
            Store store = strsrv.getById(storeid);
            String errorString = "";
            request.setAttribute("Store", store);
            request.setAttribute("storeId", storeid);
            String url = "/front-end/store/Login/storeRegister.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //inserts(store info)
        if ("inserts".equals(action)) {
            StoreService strsrv = new StoreService();
            Integer storeid = Integer.valueOf(request.getParameter("storeId"));
            Store store = strsrv.getById(storeid);
            request.setAttribute("Store", store);
            Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
            request.setAttribute("errorMsgs", errorMsgs);
            String storeacc = request.getParameter("STORE_ACC").trim();
            if (storeacc == null || storeacc.trim().length() == 0) {
                errorMsgs.put("STORE_ACC", "帳號請勿空白");
            }
            String storepwd = request.getParameter("STORE_PWD").trim();
            if (storepwd == null || storepwd.trim().length() == 0) {
                errorMsgs.put("STORE_PWD", "密碼請勿空白");
            }
            String storepwd2 = request.getParameter("STORE_PWD2").trim();
            if (storepwd2 == null || storepwd2.trim().length() == 0) {
                errorMsgs.put("STORE_PWD2", "確認密碼請勿空白");
            }
            if (!storepwd2.equals(storepwd)) {
                errorMsgs.put("STORE_PWD2", "確認密碼請與密碼相同");
            }
            String storephone1 = request.getParameter("STORE_PHONE1").trim();
            String storecomaddress = request.getParameter("STORE_COM_ADDRESS").trim();
            if (storecomaddress == null || storecomaddress.trim().length() == 0) {
                errorMsgs.put("STORE_COM_ADDRESS", "申請人姓名請勿空白");
            }
            String storetwid = request.getParameter("STORE_TW_ID").trim();
            if (storetwid == null || storetwid.trim().length() == 0) {
                errorMsgs.put("STORE_TW_ID", "身分證請勿空白");
            } else if (!storetwid.trim().matches("^[a-zA-Z]\\d{9}$")) {
                errorMsgs.put("STORE_TW_ID", "身分證格式不正確");
            }
            String storephone2 = request.getParameter("STORE_PHONE2").trim();
            if (storephone2 == null || storephone2.trim().length() == 0) {
                errorMsgs.put("STORE_PHONE2", "申請人電話請勿空白");
            }
            if (strsrv.getByAcc(storeacc)) {
                errorMsgs.put("STORE_ACC", "帳號名稱已被註冊");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = request
                        .getRequestDispatcher("/front-end/store/Login/storeRegister.jsp");
                failureView.forward(request, response);
                return;
            }
            request.setAttribute("toResult", true);
            strsrv.inserts(storeid, storeacc, storepwd, storephone1, storecomaddress, storephone2, storetwid);
            String url = "/front-end/store/Login/storeRegister.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("gotobuy".equals(action)){
            Integer sid= Integer.valueOf(request.getParameter("sid").trim());
            Integer rid= Integer.valueOf(request.getParameter("renid").trim());
            String[] mealid =request.getParameterValues("mealid");
            String[] mealq =request.getParameterValues("mealq");
            ReservaDetail rdvo=new ReservaDetail();
            ReservaDetailJDBCDAO rdjdbc=new ReservaDetailJDBCDAO();
//            總金額
            Integer allp=0;
//            新增到餐點資料庫
            for (int i=0;i<mealid.length;i++){
//                REN_ID
                rdvo.setRenId(rid);
//                MEAL_ID
                Integer mid=Integer.valueOf(mealid[i].trim());
                rdvo.setMealId(mid);
//                RD_QUANTITY
                Integer mq=Integer.valueOf(mealq[i].trim());
                rdvo.setRdQuantity(mq);
//                PD_PRICE
                Integer price=new MealJDBCDAO().getByMealId(mid).getMealPrice();
                Integer tp=price*mq;
                allp+=tp;
                rdvo.setPdPrice(tp);
                rdjdbc.insert(rdvo);
            }
//            查詢優惠券
            String codet= request.getParameter("codetext");
            CodeJDBCDAO jdbcdao = new CodeJDBCDAO();
            List<Integer> off = jdbcdao.getBycodeNum(codet, sid);
//            折扣金額
            Integer pass=0;
            request.setAttribute("Result",0);
            Integer codeid=0;
            if (off.size()>0){
                pass=off.get(0);
                codeid=jdbcdao.getCodeId(codet,sid).get(0);
                request.setAttribute("codeid",codeid);
            }
//            判斷刷卡金額
            if (allp>=pass){
                allp-=pass;
            }else {
                allp=0;
                String url ="/CGA105G2/LonginServlet?action=ecpayblack&sid="+sid+"&rid="+rid+"&allp="+(allp+pass)+"&codeid="+codeid;
                request.setAttribute("Result",false);
                RequestDispatcher successView = request.getRequestDispatcher(url);
                successView.forward(request, response);
                return;
            }
            // 根據表單建立收款連結 (中文編碼有問題)
            // 使用者跳轉至綠界的交易流程網站
            // 按照流程輸入卡號..... (中文編碼!)
            // 測試卡號: 一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
            // 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，
            // 例如在 2016/04/20 當天作測試，請設定 05/2016(含)之後的有效月年，否則回應刷卡失敗。
            // 手機請輸入正確，因為會傳驗證碼
            // 檢查後台: 信用卡收單 - 交易明細 - 查詢
            domain = new AllInOne("");
            AioCheckOutOneTime obj = new AioCheckOutOneTime();
            // 從 view 獲得資料，依照 https://developers.ecpay.com.tw/?p=2866 獲得必要的參數
            // MerchantTradeNo  : 必填 特店訂單編號 (不可重複，因此需要動態產生)
            obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
            // MerchantTradeDate  : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
            obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            // TotalAmount  : 必填 交易金額
            obj.setTotalAmount(String.valueOf(allp));
            // TradeDesc  : 必填 交易描述
            obj.setTradeDesc("StoreID:" + sid);
            // ItemName  : 必填 商品名稱
            obj.setItemName("Food Map eat");
            // ReturnURL   : 必填  我用不到所以是隨便填一個英文字
            obj.setReturnURL("a");
            // OrderResultURL   : 選填 消費者完成付費後。重新導向的位置
            String url="http://"+request.getServerName()+":8081/CGA105G2/LonginServlet?action=ecpayblack&sid="+sid+"&rid="+rid+"&allp="+(allp+pass)+"&codeid="+codeid;
            obj.setClientBackURL(url);
            obj.setNeedExtraPaidInfo("N");
            // 回傳form訂單 並自動將使用者導到 綠界
            String form = domain.aioCheckOut(obj, null);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("<html><body>" + form + "</body></html>");
        }
        if ("ecpayblack".equals(action)) {
            Integer rid= Integer.valueOf(request.getParameter("rid"));
            request.setAttribute("rid",rid);
            ReservaJDBCDAO rjdbc=new ReservaJDBCDAO();
            Reserva vo=rjdbc.getById(rid);
            Integer allp= Integer.valueOf(request.getParameter("allp"));
            //原價
            vo.setRenPrice(allp);
            Integer cid= Integer.valueOf(request.getParameter("codeid"));
            Integer tp=allp;

            //優惠券
            vo.setCodeId(cid);
            if (cid!=0){
                Integer off=new CodeJDBCDAO().getById(cid).getCodeOff();
                tp-=off;
            };
            if(tp<=0){
                request.setAttribute("Result",1);
            }
            request.setAttribute("Result",2);
            //支付金額
            vo.setRenFprice(tp);
            rjdbc.wattingupdate(vo);
            Integer sid= Integer.valueOf(request.getParameter("sid"));
            StoreDAO sdao=new StoreDAO();
            String sname=sdao.getById(sid).getStoreName();
            request.setAttribute("sname",sname);
            String url = "/front-end/Member/waiting/listOneStandby.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        if ("ecpay".equals(action)) {
            Integer sID = (Integer) request.getSession().getAttribute("storeId");
            Integer plan= Integer.valueOf(request.getParameter("plan").trim());
            // 根據表單建立收款連結 (中文編碼有問題)
            // 使用者跳轉至綠界的交易流程網站
            // 按照流程輸入卡號..... (中文編碼!)
            // 測試卡號: 一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
            // 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，
            // 例如在 2016/04/20 當天作測試，請設定 05/2016(含)之後的有效月年，否則回應刷卡失敗。
            // 手機請輸入正確，因為會傳驗證碼
            // 檢查後台: 信用卡收單 - 交易明細 - 查詢
            domain = new AllInOne("");
            AioCheckOutOneTime obj = new AioCheckOutOneTime();
            // 從 view 獲得資料，依照 https://developers.ecpay.com.tw/?p=2866 獲得必要的參數
            // MerchantTradeNo  : 必填 特店訂單編號 (不可重複，因此需要動態產生)
            obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
            // MerchantTradeDate  : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
            obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
            // TotalAmount  : 必填 交易金額
            Integer money = Integer.valueOf(request.getParameter("planMoney"));
            obj.setTotalAmount(String.valueOf(money));
            // TradeDesc  : 必填 交易描述
            obj.setTradeDesc("StoreID:" + sID);
            // ItemName  : 必填 商品名稱
            obj.setItemName("FoodMap StorePlan:"+plan);
            // ReturnURL   : 必填  我用不到所以是隨便填一個英文字
            obj.setReturnURL("a");
            // OrderResultURL   : 選填 消費者完成付費後。重新導向的位置
            String url=null;
            if (plan==1){
                url = "http://"+request.getServerName()+":8081/CGA105G2/LonginServlet?action=plan1&storeId="+sID;
            }
            if (plan==2){
                url = "http://"+request.getServerName()+":8081/CGA105G2/LonginServlet?action=plan2&storeId="+sID;
            }
            if (plan==3){
                url = "http://"+request.getServerName()+":8081/CGA105G2/LonginServlet?action=plan3&storeId="+sID;
            }
            obj.setClientBackURL(url);
            obj.setNeedExtraPaidInfo("N");
            // 回傳form訂單 並自動將使用者導到 綠界
            String form = domain.aioCheckOut(obj, null);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("<html><body>" + form + "</body></html>");
        }
        //  plan1(update)
        if ("plan1".equals(action)) {
            Integer storeId = Integer.valueOf(request.getParameter("storeId"));
            request.getSession().setAttribute("storeId",storeId);
            request.setAttribute("toResult", true);
            StoreService strSvc = new StoreService();
            Store Store = strSvc.updateplan(storeId,1);
            String url = "/front-end/store/food_order/food_order.do?action=food_order_button";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //  plan2(update)
        if ("plan2".equals(action)) {
            Integer storeId = Integer.valueOf(request.getParameter("storeId"));
            request.getSession().setAttribute("storeId",storeId);
            request.setAttribute("toResult", true);
            StoreService strSvc = new StoreService();
            Store Store = strSvc.updateplan(storeId,2);
            String url = "/front-end/store/food_order/food_order.do?action=food_order_button";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
        //  plan2(update)
        if ("plan3".equals(action)) {
            Integer storeId = Integer.valueOf(request.getParameter("storeId"));
            request.getSession().setAttribute("storeId",storeId);
            request.setAttribute("toResult", true);
            StoreService strSvc = new StoreService();
            Store Store = strSvc.updateplan(storeId,3);
            String url = "/front-end/store/food_order/food_order.do?action=food_order_button";
            RequestDispatcher successView = request.getRequestDispatcher(url);
            successView.forward(request, response);
        }
    }
}