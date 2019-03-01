package org.corgiking.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;

import redis.clients.util.SafeEncoder;

public class redisTemplateLock {

	private static final String PARAM_EX = "EX";

	private static final String PARAM_NX = "NX";

	private static final String LOCK_SUCCESS = "OK";

	public static boolean lock(RedisTemplate<String, String> redisTemplate, String key, String value, long timeout,
			TimeUnit unit) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				Object ret = connection.execute("set", SafeEncoder.encodeMany(key, value, PARAM_EX,
						String.valueOf(TimeoutUtils.toSeconds(timeout, unit)), PARAM_NX));
				if (ret != null && LOCK_SUCCESS.equals(SafeEncoder.encode((byte[]) ret))) {
					return true;
				}
				return false;
			}
		});
	}

	public static boolean unLock(RedisTemplate<String, String> redisTemplate, String key) {
		redisTemplate.delete(key);
		return true;
	}

}
