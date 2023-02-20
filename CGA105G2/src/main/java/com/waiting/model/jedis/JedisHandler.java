package com.waiting.model.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisHandler {
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static String addNumber(String key, String field) {
		// 連線 Redis
		Jedis jedis = pool.getResource();
		jedis.auth("123456");

		// 取舊資料
		String oldValue = jedis.hget(key, field);

		// 更新資料
		Integer newValue = 0;
		if (oldValue != null)
			newValue = Integer.parseInt(oldValue) + 1;
		jedis.hset(key, field, newValue.toString());

		// 關閉 Redis
		jedis.close();

		return newValue.toString();
	}

	public static String getNumber(String key, String field) {
		// 連線 Redis
		Jedis jedis = pool.getResource();
		

		// 取舊資料
		String oldValue = jedis.hget(key, field);
		if (oldValue == null) {
			oldValue = "0";
			jedis.hset(key, field, "0");
		}

		// 關閉 Redis
		jedis.close();

		return oldValue;
	}

	public static void delNumber(String key, String field) {
		// 連線 Redis
		Jedis jedis = pool.getResource();
		

		// 將資料歸零
		jedis.hset(key, field, "0");

		// 關閉 Redis
		jedis.close();
	}
}
