package com.advertise.contorller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advertise.model.Advertise.pojo.Advertise;
import com.advertise.model.service.AdvertiseService;
import com.emp.model.EmployeeRoot.pojo.EmployeeRoot;
import com.emp.model.service.EmployeeService;
@MultipartConfig
@WebServlet("/adServlet")
public class AdvertiseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("adPass".equals(action)) {
			String str = req.getParameter("advId");
			Integer advId = Integer.valueOf(str);
			String str2 = req.getParameter("empId");
			Integer empId = Integer.valueOf(str2);
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.updatePass(advId, empId);
			String url = "/back-end/advertise/reviewAdvertise2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("adNotPass".equals(action)) {
			String str = req.getParameter("advId");
			Integer advId = Integer.valueOf(str);
			String str2 = req.getParameter("empId");
			Integer empId = Integer.valueOf(str2);
			AdvertiseService adSvc = new AdvertiseService();
			adSvc.update(advId, empId);
			String url = "/back-end/advertise/reviewAdvertise2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		if ("getPhoto".equals(action)) { // 秀文章圖片
			OutputStream out = res.getOutputStream();
			String adId = req.getParameter("adId");
			AdvertiseService adSvc = new AdvertiseService();
			Advertise advertise = adSvc.getByAdvId(Integer.valueOf(adId));
			res.setContentType("image/jpg");
			out.write(advertise.getAdvImg());
			out.close();
		}
		if("addAD".equals(action)) {
			AdvertiseService adSvc = new AdvertiseService();
            byte adImg[] = req.getPart("adImg").getInputStream().readAllBytes();
            if(adImg.length==0) {
            	String url = "/front-end/store/advertise/Advertise_upload.jsp";
    			RequestDispatcher successView = req.getRequestDispatcher(url);
    			successView.forward(req, res);
            }
            Integer storId = (Integer) req.getSession().getAttribute("storeId");
            Date date = new Date();
            long timeInMilliSeconds = date.getTime();
            Date date22 = new Date(timeInMilliSeconds + (long)(30*24*60*60)*(long)(1000));
            long afterThirtyDay = date22.getTime();
            java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
            java.sql.Date date2 = new java.sql.Date(afterThirtyDay);
            if(adSvc.getByStoreId(storId)==null) {
	            Advertise advertise = new Advertise();
	            advertise.setStoreId(storId);
	            advertise.setAdvStatus(Integer.valueOf(1));
	            advertise.setAdvImg(adImg);
	            advertise.setAdvText((req.getParameter("adTEXT")).trim());
	            advertise.setAdvStime(date1);
	            advertise.setAdvNtime(date2);
	            adSvc.addAD(advertise);
            }else {
            	Advertise ad=adSvc.getByStoreId(storId);
            	adSvc.deleteByStoreId(ad.getAdvId());
            		Advertise advertise = new Advertise();
	 	            advertise.setStoreId(storId);
	 	            advertise.setAdvStatus(Integer.valueOf(1));
	 	            advertise.setAdvImg(adImg);
	 	            advertise.setAdvText((req.getParameter("adTEXT")).trim());
	 	            advertise.setAdvStime(date1);
	 	            advertise.setAdvNtime(date2);
	 	            adSvc.addAD(advertise);
            }
            String url = "/index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
        }
		if("rootTest".equals(action)) {
			Integer empId = (Integer)req.getSession().getAttribute("empId");
			EmployeeService svc = new EmployeeService();
			List<EmployeeRoot> empRoot =svc.getRoot(empId);
			for(EmployeeRoot emp : empRoot) {
				if(emp.getRootId()==1) {
					String url = "/back-end/advertise/reviewAdvertiseRoot.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				}else {
						String url = "/back-end/advertise/reviewAdvertise2.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
					return;
				}
			}
		}
		if("forAd".equals(action)) {
			String str = req.getParameter("advId");
			Integer advId = Integer.valueOf(str);
			String str2 = req.getParameter("emp");
			Integer empid = Integer.valueOf(str2);
			AdvertiseService adSvc = new AdvertiseService();
			Advertise ad = adSvc.getByAdvId(advId);
			ad.setEmpId(empid);
			adSvc.update1(ad);
			String url = "/back-end/advertise/reviewAdvertiseRoot.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}




	}
}
