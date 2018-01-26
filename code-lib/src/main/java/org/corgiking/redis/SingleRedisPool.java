package org.corgiking.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SingleRedisPool {

	public static void main(String[] args) {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		
		
		JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379, 10000, "admin");
		Jedis jedis = jedisPool.getResource();
		
		
		
	}

}
