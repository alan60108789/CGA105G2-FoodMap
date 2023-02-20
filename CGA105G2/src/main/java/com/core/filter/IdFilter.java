package com.core.filter;

import com.pushmesg.model.service.pgService;
import com.store.model.Store.dao.impl.StoreDAO;
import com.store.model.service.StoreService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//PageFilter這個類別被標記為 @WebFilter("/*") 代表所有請求都會通過這個。
@WebFilter("/*")
public class IdFilter extends HttpFilter {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getSession().getAttribute("storeId") == null || request.getSession().getAttribute("memId") == null || request.getSession().getAttribute("empId") == null) {
            request.getSession().setAttribute("storeId", 0);
            request.getSession().setAttribute("memId", 0);
            request.getSession().setAttribute("empId", 0);
        }
//		獲得請求路徑的jsp檔案名稱
//		request.getServletPath() 方法獲取請求的服務路徑。這個方法返回請求的路徑，不包括上下文路徑和查詢字符串。例如，如果請求的 URL 是 "http://example.com/myapp/hello"，則該方法將返回 "/hello"。
        String ServerName = request.getServerName();//localhost
        String requestCon = request.getContextPath();   //CGA105G2
        String requestPath = request.getServletPath();  //LonginServlet
        String info = request.getPathInfo();// index.jsp
        String requestQuery = request.getQueryString();
        Integer sId = (Integer) request.getSession().getAttribute("storeId");
        Integer mId = (Integer) request.getSession().getAttribute("memId");
        Integer eId = (Integer) request.getSession().getAttribute("empId");
        Integer storeplan = 0;
        if (sId >= 1) {
            StoreService storeSvc = new StoreService();
            storeplan = storeSvc.getById(sId).getStorePlan();
            request.setAttribute("storeplan", storeplan);
        }
        Integer notify = 0;
        if (mId >= 1) {
            pgService pgs = new pgService();
            notify = pgs.see(mId);
            request.setAttribute("notify", notify);
        }
//		存在cookie方法
//		Cookie cookie = new Cookie("username", "John");
//		response.addCookie(cookie);
        if (requestPath.matches(".*index.jsp") || requestPath.matches(".*LonginServlet") || requestPath.matches(".*LognIn.*") ||
                requestPath.matches(".*Register.*") || requestPath.matches(".*PassWord.*") || requestPath.matches(".*search.*") ||
                requestPath.matches(".*Page.*") || requestPath.matches(".*addStandBy.*") || requestPath.matches(".*standby.*") ||
                requestPath.matches(".*ECpay.*") || requestPath.matches(".*employeeLogin.jsp.*") || requestPath.matches(".*forget.*") ||
                requestPath.matches(".*adServlet.*") || requestPath.matches(".*Advertise.*") || requestPath.matches(".*Waiting.*") ||
                requestPath.matches(".*search.*") || requestPath.matches(".*contactUs.jsp.*") || requestPath.matches(".*Loader.*")
                || requestPath.matches(".*SendMailServlet.*") || requestPath.matches(".*FriendWS.*") || requestPath.matches(".*open.*")
        ) {
            if(requestPath.equals("/adServlet")){
                System.out.println("doFilter-0區:廣告區");
                chain.doFilter(request, response);
                return;
            }
            System.out.println("doFilter-1區:免登入區");
            if(mId >= 1){
                chain.doFilter(request, response);
                return;
            }
            if (requestQuery != null) {
                String pattern = "action=(.*?)&";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(requestQuery);
                String togourl = null;
                if (m.find()) {
                    String result = m.group(1);
                    if (result.equals("MemberPage")) {
                        togourl = requestPath + "?" + requestQuery;
                        request.getSession().setAttribute("togourl", togourl);
                        request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp").forward(request, response);
                        return;
                    }
                    chain.doFilter(request, response);
                    return;
                }
                chain.doFilter(request, response);
                return;
            }
            chain.doFilter(request, response);
            return;
        } else if (sId >= 1 && (storeplan == 0 || storeplan.equals(null))) {
            System.out.println("else-1區:未購買方案區");
            Integer plan3q = new StoreDAO().getByplan();
            request.setAttribute("plan3q", plan3q);
            String url = "/front-end/store/Login/forgetplan.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        } else if ((requestPath.matches(".*/back-end/.*") && eId >= 1)) {
            System.out.println("doFilter-2區:後台登入區");
            chain.doFilter(request, response);
        } else if ((requestPath.matches(".*/front-end/Member/.*") && mId >= 1)) {
            System.out.println("doFilter-3區:會員登入區");
            chain.doFilter(request, response);
        } else if (((requestPath.matches(".*/front-end/store/.*") && sId >= 1 && storeplan >= 1)) ||
                ((requestPath.matches(".*StoreStart_blank.*") && sId >= 1 && storeplan >= 1))) {
            System.out.println("doFilter-4區:店家登入區");
            long miliseconds = System.currentTimeMillis();
            int day = (new Date(miliseconds)).getDate();
            request.setAttribute("planday", day);
            chain.doFilter(request, response);
        } else if (requestPath.matches(".*Servlet") && (eId >= 1 || sId >= 1 || mId >= 1)) {
            System.out.println("doFilter-5區:Servlet跳轉區");
            chain.doFilter(request, response);
        } else if (requestPath.matches(".*css") || requestPath.matches(".*js") || requestPath.matches(".*assets.*")) {
            System.out.println("doFilter-6區:css&js區");
            chain.doFilter(request, response);
        } else if (requestPath.matches(".*/back-end/.*") && eId <= 0) {
            System.out.println("else-2區:後台else區");
            request.getRequestDispatcher("/back-end/emp/employeeLogin.jsp").forward(request, response);
        } else if ((sId >= 1) && (mId >= 1)) {
            System.out.println("doFilter-7區:後台區");
            chain.doFilter(request, response);
        } else if (requestPath.matches(".*/CGA105G2/")) {
            System.out.println("doFilter-8區:首頁區");
            chain.doFilter(request, response);
        } else {
            System.out.println("else-3區:else區");
            request.getRequestDispatcher("/front-end/Member/member/memberLognIn.jsp").forward(request, response);
        }
    }
}