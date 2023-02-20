package com.msg.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();


	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String sender, String receiver, String message) { //存歷史聊天紀錄
		// 對雙方來說 都要存著歷史聊天紀錄
		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString(); //分成接受發送兩個key
		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString(); //分成接受發送兩個key
		Jedis jedis = pool.getResource();
		jedis.rpush(senderKey, message);
		jedis.rpush(receiverKey, message); //全都存起來 但因為redis是noSQL 就算資料重複儲存 違反正規化也沒差
												//不用依照MySQL的正規化
		jedis.close();
	}

}
