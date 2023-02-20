package com.point.contorller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.goods.model.Goods.pojo.Goods;
import com.goods.model.service.GoodsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.member.model.Member.pojo.Member;
import com.member.model.service.MemberService;
import com.point.model.Point.pojo.Point;
import com.point.model.PointGoods.pojo.PointGoods;
import com.point.model.PointOrder.pojo.PointOrder;
import com.point.model.service.PointGoodsService;

import com.point.model.service.PointOrderService;
import com.point.model.service.PointService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/PointServlet")
@MultipartConfig
public class PointServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memId = (Integer) req.getSession().getAttribute("memId");
		Integer empId   = (Integer) req.getSession().getAttribute("empId");

		if ("getOne_For_Display".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer pdId = Integer.valueOf(req.getParameter("pdId").trim());
				/***************************2.開始查詢資料*****************************************/
				PointGoodsService pointGoodsSvc = new PointGoodsService();
				PointGoods pointgoods = pointGoodsSvc.getPointGood(pdId);
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/Member/point/listPointGood.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("pointgoods", pointgoods);
				String url = "/front-end/Member/point/pointItemPage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { 
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			byte[] pdImg = req.getPart("pdImg").getInputStream().readAllBytes();
			String pdName = req.getParameter("pdName");
			String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.put("pdName", "商品名稱請勿空白");
			} else if (!pdName.trim().matches(pdNameReg)) { 
				errorMsgs.put("pdName", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
			}

			Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("pdPrice").trim());
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.put("pdPrice", "商品單價請填數字");
			}
			String pdText = req.getParameter("pdText").trim();
			if (pdText == null || pdText.trim().length() == 0) {
				errorMsgs.put("pdText", "商品介紹請勿空白");
			}
			Integer pdStatus = Integer.valueOf(req.getParameter("pdStatus").trim());
			PointGoods pointgoods = new PointGoods();
			pointgoods.setPdImg(pdImg);
			pointgoods.setPdName(pdName);
			pointgoods.setPdPrice(pdPrice);
			pointgoods.setPdText(pdText);
			pointgoods.setPdStatus(pdStatus);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgood/addPointGood.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			pointgoods = pointGoodsSvc.addPointGood(pdImg ,pdName, pdPrice, pdText, null, null, pdStatus);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/pointgood/listPointGood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			Integer pdId = Integer.valueOf(req.getParameter("pdId").trim());
			/*************************** 2.開始查詢資料 ****************************************/
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			PointGoods pointgoods = pointGoodsSvc.getPointGood(pdId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?pdId=" + pointgoods.getPdId() + "&pdName=" + pointgoods.getPdName() + "&pdPrice="
					+ pointgoods.getPdPrice() + "&pdText=" + pointgoods.getPdText() + "&pdStatus="
					+ pointgoods.getPdStatus();
			String url = "/back-end/pointgood/updPointGood.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("update".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer pdId = Integer.valueOf(req.getParameter("pdId").trim());
			byte[] pdImg = req.getPart("pdImg").getInputStream().readAllBytes();
			if (pdImg.length == 0) {
				PointGoods pointgoods = new PointGoodsService().getPointGood(pdId);
				pdImg=pointgoods.getPdImg();
			}
			String pdName = req.getParameter("pdName");
			String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.put("pdName", "商品名稱請勿空白");
			} else if (!pdName.trim().matches(pdNameReg)) { 
				errorMsgs.put("pdName", "只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
			}
			Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("pdPrice").trim());
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.put("pdPrice", "商品單價請填數字");
			}
			String pdText = req.getParameter("pdText").trim();
			if (pdText == null || pdText.trim().length() == 0) {
				errorMsgs.put("pdText", "商品介紹請勿空白");
			}
			Integer pdStatus = Integer.valueOf(req.getParameter("pdStatus").trim());
			PointGoods pointgoods = new PointGoods();
			pointgoods.setPdId(pdId);
			pointgoods.setPdImg(pdImg);
			pointgoods.setPdName(pdName);
			pointgoods.setPdPrice(pdPrice);
			pointgoods.setPdText(pdText);
			pointgoods.setPdStatus(pdStatus);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgood/updPointGood.jsp");
				failureView.forward(req, res);
				return; 
			}
			/*************************** 2.開始新增資料 ***************************************/
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			pointgoods = pointGoodsSvc.updatePointGood(pdId, pdImg, pdName, pdPrice, pdText, null, pdStatus);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("pointgoods", pointgoods); 
			String url = "/back-end/pointgood/listPointGood.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("getPdImg".equals(action)) {
			ServletOutputStream out = res.getOutputStream();
			String pdImg = req.getParameter("pdId");
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			PointGoods pointgoods = pointGoodsSvc.getPointGood(Integer.parseInt(pdImg));
			res.setContentType("Image/jpg");
			res.setContentLength(pointgoods.getPdImg().length);
			out.write(pointgoods.getPdImg());
			out.close();
		}
		if ("updateOrder".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer poId = Integer.valueOf(req.getParameter("poId").trim());
			Integer poStatus = Integer.valueOf(req.getParameter("poStatus").trim());
			PointOrder pointorder = new PointOrder();
			pointorder.setPoStatus(poId);
			pointorder.setPoStatus(poStatus);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/pointgood/backPointOrder.jsp");
				failureView.forward(req, res);
				return; 
			}
			/*************************** 2.開始新增資料 ***************************************/
			PointOrderService pointOrderSvc = new PointOrderService();
			pointorder = pointOrderSvc.updateOrderStatus(poStatus, null, poId);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("pointorder", pointorder); 
			String url = "/back-end/pointgood/backPointOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("listPoint".equals(action)) {
			PointService pointSvc = new PointService();
			List<Point> list = pointSvc.getAllMem(memId);
			MemberService memSvc = new MemberService();
			Member Member = memSvc.meminfo(memId);
			req.setAttribute("list",list);
			req.setAttribute("Member", Member);
			String url = "/front-end/Member/point/listPoint.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			}
		if ("exchangeRewards".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer pdId = Integer.valueOf(req.getParameter("pdId"));
			PointGoodsService pointGoodsSvc = new PointGoodsService();
			PointGoods pointgoods = pointGoodsSvc.getPointGood(pdId);
			MemberService memSvc = new MemberService();
			Member member = memSvc.meminfo(memId);
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/Member/point/listPointGood.jsp");
				failureView.forward(req, res);
				return;//程式中斷
				}
			PointService pointSvc = new PointService();
			JSONObject obj=new JSONObject();
			if (member.getMemPoint()< pointgoods.getPdPrice()){
				obj.put("how","error");
				obj.put("see", "點數不足");
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.println(obj);
				out.flush();
				out.close();
				return;//程式中斷
			}else {
				obj.put("how", "success");
				obj.put("see", "兌換成功");
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.println(obj);
				Point point = pointSvc.addPoint(memId, "兌換" + pointgoods.getPdName(), -1 * pointgoods.getPdPrice());
				PointOrderService pointorderSvc = new PointOrderService();
				PointOrder pointorder = pointorderSvc.addPointOrder(memId, pdId, pointgoods.getPdPrice(), null, 0, null);
				member=memSvc.updmemPoint(memId,Integer.valueOf(member.getMemPoint()-pointgoods.getPdPrice()));
				out.flush();
				out.close();
				return;//程式中斷
			}
		}
		
	}
}
