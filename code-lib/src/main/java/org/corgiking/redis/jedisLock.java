package org.corgiking.redis;

import redis.clients.jedis.Jedis;

public class jedisLock {

	private static final String LOCK_SUCCESS = "OK";
	private static final String NX = "NX";

	private Jedis jedis;

	public boolean lock(String key, String value, String expx, long time) {
		String ret = jedis.set(key, value, NX, expx, time);
		if (LOCK_SUCCESS.equals(ret)) {
			return true;
		}
		return false;
	}

	public boolean unlock(String... key) {
		Long delNum = jedis.del(key);
		if (delNum != null && delNum > 0) {
			return true;
		}
		return false;
	}

}
