package com.goods.contorller;

import com.goods.model.Goods.pojo.Goods;
import com.goods.model.service.GoodsService;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/front-end/Member/goods/goods.do")
@MultipartConfig
public class GoodsServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("getOne_For_Display".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            String str = req.getParameter("goodsId");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入商品編號");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/goods/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            Integer goodsId = null;
            try {
                goodsId = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("商品編號格式不正確");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/goods/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            /*************************** 2.開始查詢資料 *****************************************/
            GoodsService goodsSvc = new GoodsService();
            Goods goods = goodsSvc.getOneGoods(goodsId);
            if (goods == null) {
                errorMsgs.add("查無資料");
            }
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/goods/select_page.jsp");
                failureView.forward(req, res);
                return;// 程式中斷
            }
            /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
            req.setAttribute("goods", goods); // 資料庫取出的goods物件,存入req
            String url = "/front-end/Member/goods/listOneGoods.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("getOne_For_Update".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*************************** 1.接收請求參數 ****************************************/
            Integer goodsId = Integer.valueOf(req.getParameter("goodsId"));
            /*************************** 2.開始查詢資料 ****************************************/
            GoodsService goodsSvc = new GoodsService();
            Goods goods = goodsSvc.getOneGoods(goodsId);
            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("goods", goods); // 資料庫取出的goods物件,存入req
            String url = "/front-end/Member/goods/update_goods_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_goods_input.jsp
            successView.forward(req, res);
        }
        if ("update".equals(action)) { // 來自update_goods_input.jsp的請求
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            Integer goodsId = Integer.valueOf(req.getParameter("goodsId").trim());
            Integer storeId = null;
            try {
                storeId = Integer.valueOf(req.getParameter("storeId").trim());
            } catch (NumberFormatException e) {
                storeId = 0;
                errorMsgs.add("請填選擇店家.");
            }
            byte[] goodsImg = null;
            Part part = req.getPart("goodsImg");
            InputStream in = part.getInputStream();
            goodsImg = new byte[in.available()];
            in.read(goodsImg);
            in.close();
            if (goodsImg.length == 0) {
                goodsImg = new GoodsService().getOneGoods(goodsId).getGoodsImg();
            }
            String goodsName = req.getParameter("goodsName");
            String goodsNameReg = "^[(\u4e00-\u9fa5)]{2,15}$";
            if (goodsName == null || goodsName.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!goodsName.trim().matches(goodsNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中文, 且長度必需在2到15之間");
            }
            Integer goodsStatus = null;
            try {
                goodsStatus = Integer.valueOf(req.getParameter("goodsStatus").trim());
            } catch (NumberFormatException e) {
                goodsStatus = 0;
                errorMsgs.add("狀態請填數字");
            }
            Integer goodsPrice = null;
            try {
                goodsPrice = Integer.valueOf(req.getParameter("goodsPrice").trim());
            } catch (NumberFormatException e) {
                goodsPrice = 0;
                errorMsgs.add("價格請填數字");
            }
            String goodsText = req.getParameter("goodsText");
            String goodsTextReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
            if (goodsText == null || goodsText.trim().length() == 0) {
                errorMsgs.add("說明: 請勿空白");
            } else if (!goodsText.trim().matches(goodsTextReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("說明: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
            Goods goods = new Goods();
            goods.setGoodsId(goodsId);
            goods.setStoreId(storeId);
            goods.setGoodsImg(goodsImg);
            goods.setGoodsName(goodsName);
            goods.setGoodsStatus(goodsStatus);
            goods.setGoodsPrice(goodsPrice);
            goods.setGoodsText(goodsText);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("goods", goods); // 含有輸入格式錯誤的goods物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/goods/update_goods_input.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }
            /*************************** 2.開始修改資料 *****************************************/
            GoodsService goodsSvc = new GoodsService();
            goods = goodsSvc.updateGoods(goodsId, storeId, goodsImg, goodsName, goodsStatus, goodsPrice, goodsText);
            /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
            req.setAttribute("goods", goods); // 資料庫update成功後,正確的的goods物件,存入req
            String url = "/front-end/Member/goods/listOneGoods.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneGoods.jsp
            successView.forward(req, res);
        }
        if ("insert".equals(action)) { // 來自addGoods.jsp的請求
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            Integer storeId = null;
            try {
                storeId = Integer.valueOf(req.getParameter("storeId").trim());
            } catch (NumberFormatException e) {
                storeId = 0;
                errorMsgs.add("請填選擇店家.");
            }
            byte[] goodsImg = null;
            Part part = req.getPart("goodsImg");
            InputStream in = part.getInputStream();
            goodsImg = new byte[in.available()];
            in.read(goodsImg);
            in.close();
            String goodsName = req.getParameter("goodsName");
            String goodsNameReg = "^[(\u4e00-\u9fa5)]{2,15}$";
            if (goodsName == null || goodsName.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!goodsName.trim().matches(goodsNameReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中文, 且長度必需在2到15之間");
            }
            Integer goodsStatus = null;
            try {
                goodsStatus = Integer.valueOf(req.getParameter("goodsStatus").trim());
            } catch (NumberFormatException e) {
                goodsStatus = 0;
                errorMsgs.add("狀態請填數字");
            }
            Integer goodsPrice = null;
            try {
                goodsPrice = Integer.valueOf(req.getParameter("goodsPrice").trim());
            } catch (NumberFormatException e) {
                goodsPrice = 0;
                errorMsgs.add("價格請填數字");
            }
            String goodsText = req.getParameter("goodsText");
            String goodsTextReg = "^[(\u4e00-\u9fa5)]{2,50}$";
            if (goodsText == null || goodsText.trim().length() == 0) {
                errorMsgs.add("商品名稱: 請勿空白");
            } else if (!goodsText.trim().matches(goodsTextReg)) { // 以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("商品名稱: 只能是中, 且長度必需在2到50之間");
            }
            Goods goods = new Goods();
            goods.setStoreId(storeId);
            goods.setGoodsImg(goodsImg);
            goods.setGoodsName(goodsName);
            goods.setGoodsStatus(goodsStatus);
            goods.setGoodsPrice(goodsPrice);
            goods.setGoodsText(goodsText);
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("goods", goods); // 含有輸入格式錯誤的goods物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Member/goods/addGoods.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** 2.開始新增資料 ***************************************/
            GoodsService goodsSvc = new GoodsService();
            goods = goodsSvc.addGoods(storeId, goodsImg, goodsName, goodsStatus, goodsPrice, goodsText);
            /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/Member/goods/listAllGoods.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
        if ("delete".equals(action)) {
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            /*************************** 1.接收請求參數 ***************************************/
            Integer goodsId = Integer.valueOf(req.getParameter("goodsId"));
            /*************************** 2.開始刪除資料 ***************************************/
            GoodsService goodsSvc = new GoodsService();
            goodsSvc.deleteGoods(goodsId);
            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/front-end/Member/goods/listAllGoods.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
        if ("getGoodsImg".equals(action)) {
            ServletOutputStream out = res.getOutputStream();
            String goodsString = req.getParameter("goodsId");
            GoodsService goodsSvc = new GoodsService();
            Goods goods = goodsSvc.getOneGoods(Integer.parseInt(goodsString));
            res.setContentType("Image/jpg");
            res.setContentLength(goods.getGoodsImg().length);
            out.write(goods.getGoodsImg());
            out.close();
        }
    }

}
