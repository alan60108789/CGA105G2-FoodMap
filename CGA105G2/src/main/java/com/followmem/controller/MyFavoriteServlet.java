package com.followmem.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.art.model.Article.pojo.Article;
import com.art.model.service.ArtService;
import com.followmem.model.FollowMem.pojo.FollowMem;
import com.followmem.model.service.FollowMemService;
import com.member.model.Member.pojo.Member;
import com.member.model.service.MemberService;
import com.store.model.Store.pojo.Store;
import com.store.model.service.StoreService;
import com.subs.model.Subscribe.pojo.Subscribe;
import com.subs.model.service.SubsService;

@WebServlet("/MyFavoriteServlet")
public class MyFavoriteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

		if ("insertSubsAjax".equals(action)) {	//新增訂閱店家
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer memId = Integer.valueOf(request.getParameter("memId").trim());
			Integer storeId = Integer.valueOf(request.getParameter("storeId").trim());

			Subscribe subscribe = new Subscribe();
			subscribe.setMemId(memId);
			subscribe.setStoreId(storeId);
			StoreService storeService = new StoreService();
			Store store = storeService.getById(storeId);  //依照剛剛取得的id 去找尋該筆店家
			MemberService memberService = new MemberService();
			Member member = memberService.getById(memId);
			/***************************2.開始新增資料***************************************/
			SubsService subSvs = new SubsService();
			subscribe = subSvs.addSubscribe(storeId, memId);
			List<Subscribe> subslist = subSvs.getAllByMemIdStoreId(storeId, memId);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			request.setAttribute("store", store);//set店家讓下個頁面能收到值
			request.setAttribute("member", member);//set會員讓下個頁面能收到值
			request.setAttribute("subslist", subslist);
//			String url = "/front-end/Member/member/showStorePage.jsp";
//			RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
//			successView.forward(request, response);
			response.setContentType("text/plain");
			response.getWriter().write("ok");
		    return;

	}

		if ("deleteSubsAjax".equals(action)) { //刪除訂閱

			/***************************1.接收請求參數***************************************/
			Integer memId = Integer.valueOf(request.getParameter("memId").trim());
			Integer storeId = Integer.valueOf(request.getParameter("storeId").trim());
			StoreService storeService = new StoreService();
			Store store = storeService.getById(storeId);  //依照剛剛取得的id 去找尋該筆店家
			MemberService memberService = new MemberService();
			Member member = memberService.getById(memId);


			/***************************2.開始刪除資料***************************************/
			SubsService subsService = new SubsService();
			subsService.deleteSubscribe(storeId, memId);
			List<Subscribe> subslist = subsService.getAllByMemIdStoreId(storeId, memId);

			/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			request.setAttribute("store", store);//set店家讓下個頁面能收到值
			request.setAttribute("member", member);//set會員讓下個頁面能收到值
			request.setAttribute("subslist", subslist);
			response.setContentType("text/plain");
			response.getWriter().write("ok");
		    return;
	}

        if ("insertSubs".equals(action)) {    //新增訂閱店家
            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer memId = (Integer) request.getSession().getAttribute("memId");
            Integer storeId = Integer.valueOf(request.getParameter("subStoreId").trim());
            Subscribe subscribe = new Subscribe();
            subscribe.setMemId(memId);
            subscribe.setStoreId(storeId);
            StoreService storeService = new StoreService();
            Store store = storeService.getById(storeId);  //依照剛剛取得的id 去找尋該筆店家
            MemberService memberService = new MemberService();
            Member member = memberService.getById(memId);
            /***************************2.開始新增資料***************************************/
            SubsService subSvs = new SubsService();
            subscribe = subSvs.addSubscribe(storeId, memId);
            ArtService artService = new ArtService();
            List<Article> articlelist = artService.getAllByStoreId(storeId);
            float sum = 0;
            int commemt = articlelist.size();
            request.setAttribute("commemt", commemt);
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
            List<Subscribe> subslist = subSvs.getAllByMemIdStoreId(storeId, memId);
            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            request.setAttribute("store", store);//set店家讓下個頁面能收到值
            request.setAttribute("member", member);//set會員讓下個頁面能收到值
            request.setAttribute("subslist", subslist);
            request.setAttribute("articlelist", articlelist);
            request.setAttribute("commemt", commemt);
            String url = "/front-end/Member/member/showStorePage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
            successView.forward(request, response);
        }
        if ("deleteSubsbyPage".equals(action)) { //刪除訂閱
            /***************************1.接收請求參數***************************************/
            Integer memId = (Integer) request.getSession().getAttribute("memId");
            Integer storeId = Integer.valueOf(request.getParameter("subStoreId").trim());
            StoreService storeService = new StoreService();
            Store store = storeService.getById(storeId);  //依照剛剛取得的id 去找尋該筆店家
            MemberService memberService = new MemberService();
            Member member = memberService.getById(memId);
            ArtService artService = new ArtService();
            List<Article> articlelist = artService.getAllByStoreId(storeId);
            float sum = 0;
            int commemt = articlelist.size(); //算共有幾則評論
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
            /***************************2.開始刪除資料***************************************/
            SubsService subsService = new SubsService();
            subsService.deleteSubscribe(storeId, memId);
            List<Subscribe> subslist = subsService.getAllByMemIdStoreId(storeId, memId);
            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            request.setAttribute("store", store);//set店家讓下個頁面能收到值
            request.setAttribute("member", member);//set會員讓下個頁面能收到值
            request.setAttribute("subslist", subslist);
            request.setAttribute("articlelist", articlelist);
            request.setAttribute("commemt", commemt);
            String url = "/front-end/Member/member/showStorePage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(request, response);
        }
        if ("insertFollow".equals(action)) {    //新增追蹤會員
            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            Integer memId1 = (Integer) request.getSession().getAttribute("memId");
            Integer memId2 = Integer.valueOf(request.getParameter("memId2").trim());
            FollowMem followMem = new FollowMem();
            followMem.setMemId1(memId1);
            followMem.setMemId2(memId2);
            MemberService memberService = new MemberService();
            ArtService artService = new ArtService();
            List<Article> list = artService.getAllMem(memId2);
            Member member1 = memberService.getById(memId1);
            Member member2 = memberService.getById(memId2);
            FollowMemService followMemService = new FollowMemService();
            /***************************2.開始新增資料***************************************/
            followMem = followMemService.addFollowMem(memId1, memId2);
            List<FollowMem> followlist = followMemService.getAllByMemId1MeMId2(memId1, memId2);
            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            request.setAttribute("member1", member1);
            request.setAttribute("member2", member2);
            request.setAttribute("list", list);//set會員讓下個頁面能收到值 這是別的會員
            request.setAttribute("followlist", followlist);
            String url = "/front-end/Member/member/showMemberPage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url); // 新增成功後轉交
            successView.forward(request, response);
        }
        if ("deleteFollowbyPage".equals(action)) { //刪除追蹤
            /***************************1.接收請求參數***************************************/
            Integer memId1 = (Integer) request.getSession().getAttribute("memId");
            Integer memId2 = Integer.valueOf(request.getParameter("memId2").trim());
            /***************************2.開始刪除資料***************************************/
            FollowMemService followMemService = new FollowMemService();
            followMemService.deleteFollowMem(memId1, memId2);
            MemberService memberService = new MemberService();
            ArtService artService = new ArtService();
            List<Article> list = artService.getAllMem(memId2);
            Member member1 = memberService.getById(memId1);
            Member member2 = memberService.getById(memId2);
            List<FollowMem> followlist = followMemService.getAllByMemId1MeMId2(memId1, memId2);
            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            request.setAttribute("member1", member1);
            request.setAttribute("member2", member2);
            request.setAttribute("list", list);
            request.setAttribute("followlist", followlist);
            String url = "/front-end/Member/member/showMemberPage.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(request, response);
        }
        if ("deleteSubs".equals(action)) { //刪除訂閱
            /***************************1.接收請求參數***************************************/
            Integer storeId = Integer.valueOf(request.getParameter("storeId").trim());
            Integer memId = Integer.valueOf(request.getParameter("memId").trim());
            /***************************2.開始刪除資料***************************************/
            SubsService subsService = new SubsService();
            subsService.deleteSubscribe(storeId, memId);
            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/front-end/Member/saveArt/selectSaveArtByStore.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(request, response);
        }
        if ("deleteFollow".equals(action)) { //刪除追蹤
            /***************************1.接收請求參數***************************************/
            Integer memId1 = Integer.valueOf(request.getParameter("memId1").trim());
            Integer memId2 = Integer.valueOf(request.getParameter("memId2").trim());
            /***************************2.開始刪除資料***************************************/
            FollowMemService followMemService = new FollowMemService();
            followMemService.deleteFollowMem(memId1, memId2);
            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/front-end/Member/saveArt/selectSaveArtByMember.jsp";
            RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(request, response);
        }
    }
}