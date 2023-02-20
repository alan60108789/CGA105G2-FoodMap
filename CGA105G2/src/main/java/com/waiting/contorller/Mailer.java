package com.waiting.contorller;

import com.member.model.Member.dao.impl.MemberDAO;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	// sender
	private final String user = "zxchuahua2417@gmail.com";
	private final String pass = "lpmwmxsueszwtxfk";
	// to
	private final String costomer = "zxchuahua2417@gmail.com";

	public void send(String name, String phone, String mail, String message) {
		// Get the session object
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		// host: smtp.gmail.com
		props.setProperty("mail.host", "smtp.gmail.com");
		// host port 465
		props.put("mail.smtp.port", "465");
		// 帳號驗證
		props.put("mail.smtp.auth", "true");
		// javaMail 實作SSL連線
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// set port 465
		props.put("mail.smtp.socketFactory.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		try {
			// compose message
			MimeMessage sendmsg = new MimeMessage(session);
			// 寄件
			sendmsg.setSender(new InternetAddress(user));
			sendmsg.setFrom(new InternetAddress(user));
			// 收件者
			sendmsg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(costomer));
			sendmsg.setSubject(name + "的意見回饋");
			sendmsg.setContent("<h1>name :" + name + "</h1>" + "<h1>phone :" + phone + "</h1>" + "<h1>Email :" + mail
					+ "</h1>" + "<h2>message :" + message + "</h2>", "text/html;charset=UTF-8");
			// send message
			Transport transport = session.getTransport();
			Transport.send(sendmsg);
			transport.close();
			System.out.println("Done");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public void sendAccount(String name, String mail,String ServerName) {
		// Get the session object
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		// host: smtp.gmail.com
		props.setProperty("mail.host", "smtp.gmail.com");
		// host port 465
		props.put("mail.smtp.port", "465");
		// 帳號驗證
		props.put("mail.smtp.auth", "true");
		// javaMail 實作SSL連線
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// set port 465
		props.put("mail.smtp.socketFactory.port", "465");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		try {
			// compose message
			MimeMessage sendmsg = new MimeMessage(session);
			// 寄件
			sendmsg.setSender(new InternetAddress(user));
			sendmsg.setFrom(new InternetAddress(user));
			// 收件者
			sendmsg.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(mail));
			Integer id=new MemberDAO().srhmail(mail);
			sendmsg.setSubject("FoodMap註冊成功通知");
			System.out.println();
			sendmsg.setContent(
					"<div style='width:600px;height:350px;border:1px solid rgba(128,128,128,0.4);text-align:center;margin:20px auto;'><h1 style='background-color:rgba(255,183,0,0.956);margin:0;padding-top:15px;height:60px;vertical-align:middle;'>註冊成功</h1><h1>"
							+ name
							+ "你好:</h1><h1>你已成功註冊FoodMap帳號<br>請點擊按鈕開通帳號</h1><a href='http://"+ServerName+":8081/CGA105G2/LonginServlet?action=open&id="+id+"' style='display:block;text-decoration:none;background-color:rgba(6,4,106,0.956);padding:15px 32px;margin:4px 2px;border:none;border-radius:10px;color:white;text-align:center;cursor:pointer;display:inline-block;font-size:16px;font-family:Lucida Console;'>進入FoodMap</a></div>",
					"text/html;charset=UTF-8");
			// send message
			Transport transport = session.getTransport();
			Transport.send(sendmsg);
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
