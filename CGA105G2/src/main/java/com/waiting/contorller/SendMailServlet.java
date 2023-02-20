package com.waiting.contorller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SendMailServlet"})
public class SendMailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String mail = req.getParameter("email");
        String message = req.getParameter("message");
        if ("sendMail".equals(action)) {
            Mailer mailer = new Mailer();
            mailer.send(name, phone, mail, message);
            String url = "/CGA105G2/BlankPage/contactUsDone.jsp";
            resp.sendRedirect(url);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
