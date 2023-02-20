package com.msg.contorller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.msg.jedis.JedisHandleMessage;
import com.msg.model.ChatMessage;
import com.msg.model.State;


@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {	//Concurrent並行 把鎖定範圍縮小 不要取到同個值就好
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); //因為要確認是誰 要用map string給使用者名稱(可以改用會員編號) 對應的Session物件
	Gson gson = new Gson(); //java Websocket api 的資料格式 gson = google 處理 json的 api
	@OnOpen  //當雙方打開
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		System.out.println("OPPEN");
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);  //用map物件管理 
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet(); //把所有使用者名字拿出來
		State stateMessage = new State("open", userName, userNames); //open 像是 action  username是誰上限 後面全部使用者
		String stateMessageJson = gson.toJson(stateMessage); //直接轉成json字串(格式)
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) { //拿出所有值 告訴大家 誰上線了
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {

		System.out.println("OPPEN");
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class); //gson方法fromJson 第二個參數 去找class
		String sender = chatMessage.getSender(); //發送者
		String receiver = chatMessage.getReceiver(); //接收者
		
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory)); //資料拿出再推送
				System.out.println("history = " + gson.toJson(cmHistory));
				return;
			}
		}
		
		
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
			userSession.getAsyncRemote().sendText(message);
			JedisHandleMessage.saveChatMessage(sender, receiver, message); //存歷史聊天紀錄
		}
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) { //有人離線
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
