package com.waiting.contorller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.Member.pojo.Member;
import com.member.model.service.MemberService;
import com.order.model.Order.dao.impl.OrderJDBCDAO;
import com.order.model.Order.pojo.Order;
import com.order.model.OrderDetail.dao.impl.OrderDetailJDBCDAO;
import com.order.model.OrderDetail.pojo.OrderDetail;

@WebServlet("/frontSelect")

public class frontSelectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		Integer storeId = (Integer) req.getSession().getAttribute("storeId");
		Integer memberId = (Integer) req.getSession().getAttribute("memId");
		Integer orderId = (Integer) req.getSession().getAttribute("orderId");
		if ("getOneMember".equals(action)) {
			String memberInput = req.getParameter("memberInput").trim();
			List<String> errorMsgs = new LinkedList<String>();
			if (memberInput == null || memberInput.length() == 0) {
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/frontSelect/frontSelect.jsp");
				failView.forward(req, resp);
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("<%=request.getContextPath()%>/back-end/frontSelect/frontSelect.jsp");
				failureView.forward(req, resp);
				return;// 程式中斷
			}
			memberId = Integer.valueOf(memberInput);
			MemberService memberSvc = new MemberService();
			Member member = memberSvc.getById(memberId);
			if (member == null) {
				RequestDispatcher failView = req.getRequestDispatcher("/back-end/frontSelect/frontSelect.jsp");
				failView.forward(req, resp);
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("<%=request.getContextPath()%>/back-end/frontSelect/frontSelect.jsp");
				failureView.forward(req, resp);
				return;// 程式中s斷
			}
			req.setAttribute("member", member);
			String url = "/back-end/frontSelect/frontSelectOne.jsp";
			RequestDispatcher success = req.getRequestDispatcher(url);
			success.forward(req, resp);
		}
		if ("getbyorderid".equals(action)) {
			Integer orderid= Integer.valueOf(req.getParameter("OrderId"));
			Order order=new OrderJDBCDAO().getById(orderid);
			req.setAttribute("order", order);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
