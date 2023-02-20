package com.msg.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
	private static JedisPool pool = null; //宣告連線池子只有一個

	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPool() {
		if (pool == null) {  //第一次  double check
			synchronized (JedisPoolUtil.class) { //判斷有無池子 如果有池子就不用再進來 直接return pool
				if (pool == null) {
					JedisPoolConfig config = new JedisPoolConfig(); //池子初始化
					config.setMaxTotal(8); //設定最大的聊天 最多等多久
					config.setMaxIdle(8);
					config.setMaxWaitMillis(10000);
					pool = new JedisPool(config, "localhost", 6379);
				}
			}
		}
		return pool;
	}

	// 可在ServletContextListener的contextDestroyed裡呼叫此方法註銷Redis連線池
	public static void shutdownJedisPool() {
		if (pool != null)
			pool.destroy();
	}
}
