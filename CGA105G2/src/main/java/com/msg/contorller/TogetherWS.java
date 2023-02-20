package com.msg.contorller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;


@ServerEndpoint("/TogetherWS/{userName}")
public class TogetherWS {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		connectedSessions.add(userSession);  //這行是重點
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) { //這邊message拿到json的資料
		for (Session session : connectedSessions) {  //for each迴圈
			if (session.isOpen())  //確認連線還在
				session.getAsyncRemote().sendText(message); //推送收到的訊息 (如果是一對一聊天 這邊還抓到特定人)
		}
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
