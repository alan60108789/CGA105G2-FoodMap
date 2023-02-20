package com.art.contorller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.art.model.Article.pojo.Article;
import com.art.model.service.ArtService;
import com.member.model.Member.pojo.Member;
import com.member.model.service.MemberService;
import com.point.model.Point.pojo.Point;
import com.point.model.service.PointService;
import com.store.model.Store.dao.StoreDAO_interface;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.Store.pojo.Store;
import com.store.model.service.StoreService;

@MultipartConfig(fileSizeThreshold=1024*1024*10,
maxFileSize=1024*1024*50,
maxRequestSize=1024*1024*100)
@WebServlet("/front-end/Member/art/ArtServlet")
public class ArtServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memId   = (Integer) req.getSession().getAttribute("memId");
		Integer empId   = (Integer) req.getSession().getAttribute("empId");
		if ("insertArt".equals(action)) { //新增發文 來自postArt的請求
			List<String> errorMsgs = new LinkedList<String>(); //放錯誤訊息用的
			req.setAttribute("errorMsgs", errorMsgs);
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			storeId = Integer.valueOf(req.getParameter("storeId").trim());
			Integer artScore = null;
			try {
				artScore = Integer.valueOf(req.getParameter("artScore").trim());
			} catch (NullPointerException e) {
				errorMsgs.add("請給評分");
			}
			String artTag = req.getParameter("artTag");
			String artHeader = req.getParameter("artHeader");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
			String enameReg2 = "^.{1,2000}$";
			if (artHeader == null || artHeader.trim().length() == 0) {
				errorMsgs.add("標題請勿空白");
			} else if(!artHeader.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("標題: 只能是中、英文字母、數字和_ , 限制為50字以內");
            }
			String artText = req.getParameter("artText");
			if (artText == null || artText.trim().length() == 0) {
				errorMsgs.add("評語請勿空白");
			} else if(!artText.trim().matches(enameReg2)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("評論限制為2000字以內");
            }
			byte[] artImg =req.getPart("artImg").getInputStream().readAllBytes();
			if (artImg == null || artText.trim().length() == 0) {
				errorMsgs.add("請上傳圖片");
			}
			StoreService storeservice = new StoreService();
			Store store = storeservice.getById(storeId);

			Article article = new Article();
			article.setMemId(memId);
			article.setStoreId(storeId);
			article.setArtHeader(artHeader);
			article.setArtText(artText);
			article.setArtImg(artImg);
			article.setArtScore(artScore);
			article.setArtTag(artTag);
			//   ====================增加點數=======================
			String pointChange = req.getParameter("pointChange");
			Integer pointNumber = Integer.valueOf(req.getParameter("pointNumber").trim());
			Point point = new Point();
			point.setMemId(memId);
			point.setPointChange(pointChange);
			point.setPointNumber(pointNumber);
			MemberService memSvc = new MemberService();
			Member member = memSvc.meminfo(memId);
			member=memSvc.updmemPoint(memId,Integer.valueOf(member.getMemPoint()+10));
			// ====================增加點數=======================
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("article", article); // 含有輸入格式錯誤的empVO物件,也存入req //保留key-in錯誤的資料
				req.setAttribute("store", store);
				req.setAttribute("toResult",false);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/art/postArt.jsp"); //如果不為空 回到此頁重新輸入
				failureView.forward(req, res);
				return;
			}
			/***************************2.開始新增資料***************************************/
			ArtService artSvc = new ArtService();
			PointService pointservice = new PointService();
			article = artSvc.addArt(memId, storeId, artHeader, artText, artImg, artScore, artTag);
			point = pointservice.addPoint(memId, pointChange, pointNumber); //增加點數成功
			req.setAttribute("toResult",true);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/   //要set東西進去 另一個頁面才抓得到
			String url = "/front-end/Member/art/listArt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
		}
		if("getPhoto".equals(action)) { //秀文章圖片
			OutputStream out = res.getOutputStream();
			String artId = req.getParameter("artId"); 
			ArtService artService = new ArtService();
			Article article = artService.getOneArt(Integer.parseInt(artId));
			res.setContentType("image/jpg");
			out.write(article.getArtImg());
			out.close();
		}
		if ("getOneArt_For_Update".equals(action)) { // 來自listArt.jsp的請求
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數****************************************/
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			/***************************2.開始查詢資料****************************************/
			ArtService artSvc = new ArtService();
			Article article = artSvc.getOneArt(artId); //為了抓到是哪筆文章
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			String param = "?artId=" +article.getArtId()+  //設定更新的資料取之前的
								"&memId="  +article.getMemId()+
						       "&storeId="  +article.getStoreId()+
						       "&artHeader="  +article.getArtHeader()+
						       "&artText="+article.getArtText()+
						       "&artImg="    +article.getArtImg()+
						       "&artScore="   +article.getArtScore()+
						       "&storeName="   +article.getStore().getStoreName()+
						       "&memberName="   +article.getMember().getMemName();
			String url = "/front-end/Member/art/update_art_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_art_input.jsp
			successView.forward(req, res);
		}
		if ("artUpdate".equals(action)) { // 來自update_emp_input.jsp的請求
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer artId = Integer.valueOf(req.getParameter("artId").trim()); //對到jsp頁面的 410行
			 memId = Integer.valueOf(req.getParameter("memId").trim());
			 storeId = Integer.valueOf(req.getParameter("storeId").trim());
			Integer artScore = Integer.valueOf(req.getParameter("artScore"));
			String artHeader = req.getParameter("artHeader");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
			String enameReg2 = "^.{1,2000}$";
			if (artHeader == null || artHeader.trim().length() == 0) {
				errorMsgs.put("artHeader","標題請勿空白");
			} else if(!artHeader.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("artHeader","標題: 只能是中、英文字母、數字和_ , 限制為50字以內");
			}
			String artText = req.getParameter("artText");
			if (artText == null || artText.trim().length() == 0) {
				errorMsgs.put("artText","評語請勿空白");
			} else if(!artText.trim().matches(enameReg2)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("artText","評論限制為2000字以內");
			}
			byte[] artImg =req.getPart("artImg").getInputStream().readAllBytes();
			ArtService artSvc = new ArtService();
			if(artImg.length==0) {   //如果沒有要更改圖片 就預設用原本的
				Article article = artSvc.getOneArt(artId);
				artImg = article.getArtImg();
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/art/update_art_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			/***************************2.開始修改資料*****************************************/
			Article article = artSvc.updateArt(artId, memId, storeId, artHeader, artText, artImg, artScore);
			MemberService memberService = new MemberService();
			Member member = memberService.getById(memId); //這邊可以直接創立一個Member物件
			StoreDAO storeDAO = new StoreDAO();
			Store store = storeDAO.getById(storeId); //評價店家的資訊也加進去
			List<Article> list = artSvc.getAllMem(article.getMemId()); 
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("article", article); // 資料庫update成功後,正確的的empVO物件,存入req
			req.setAttribute("member", member);
			req.setAttribute("store", store);
			req.setAttribute("list", list);
			String url = "/front-end/Member/art/listArt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listArt.jsp
			successView.forward(req, res);
		}
		if("toart".equals(action)){
			Integer sid = Integer.valueOf(req.getParameter("listAllFoodOrderScoreStoreid"));
			StoreService storeservice = new StoreService();
			Store store = storeservice.getById(sid);
			req.setAttribute("store", store);
			String url = "/front-end/Member/art/postArt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交postArt.jsp
			successView.forward(req, res);
		}
	}
}