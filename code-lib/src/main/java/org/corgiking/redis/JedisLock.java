package org.corgiking.redis;

import java.util.Collections;

import redis.clients.jedis.Jedis;

public class JedisLock {

	private static final String LOCK_SUCCESS = "OK";
	private static final String NX = "NX";

	public static boolean lock(Jedis jedis, String key, String value, String expx, long time) {
		String ret = jedis.set(key, value, NX, expx, time);
		if (LOCK_SUCCESS.equals(ret)) {
			return true;
		}
		return false;
	}

	public static boolean unlock(Jedis jedis, String unlockKey, String unlockValue) {
		String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] "
				+ " then return redis.call('del', KEYS[1]) else return 0 end";
		
		Object ret = jedis.eval(luaScript, Collections.singletonList(unlockKey), Collections.singletonList(unlockValue));
		if (ret.equals(1)) {
			return true;
		}
		return false;
	}

}
