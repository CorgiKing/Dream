package org.corgiking.redis;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import redis.clients.util.SafeEncoder;

public class RedisTemplateLock {

	/**
	 * 过期参数，后跟过期的秒数
	 */
	private static final String PARAM_EX = "EX";

	/**
	 * 不存在该key才会执行set操作
	 */
	private static final String PARAM_NX = "NX";

	/**
	 * 执行成功返回值
	 */
	private static final String LOCK_SUCCESS = "OK";

	/**
	 * 
	 * @param redisTemplate
	 * @param lockKey       分布式锁的key
	 * @param lockValue     分布式锁的值,唯一识别是谁获取了锁
	 * @param timeout       锁的超时时间
	 * @param unit          时间单位
	 * @return
	 */
	public static boolean lock(RedisTemplate<String, String> redisTemplate, String lockKey, String lockValue,
			long timeout, TimeUnit unit) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

				Object ret = connection.execute("set", SafeEncoder.encodeMany(lockKey, lockValue, PARAM_EX,
						String.valueOf(TimeoutUtils.toSeconds(timeout, unit)), PARAM_NX));

				if (ret != null && LOCK_SUCCESS.equals(SafeEncoder.encode((byte[]) ret))) {
					return true;
				}
				return false;
			}
		});
	}

	/**
	 * 使用lua脚本原子性删除分布式锁。
	 * 实现谁加的分布式锁谁才能释放。
	 * 
	 * @param redisTemplate
	 * @param lockKey		分布式锁的key
	 * @param lockValue		分布式锁中的value符合才能成功释放
	 * @return
	 */
	public static boolean unLock(RedisTemplate<String, String> redisTemplate, String unlockKey, String unlockValue) {

		String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] "
				+ " then return redis.call('del', KEYS[1]) else return 0 end";
		RedisScript<Integer> redisScript = new DefaultRedisScript<>(luaScript);
		
		Integer ret = redisTemplate.execute(redisScript, Collections.singletonList(unlockKey), Collections.singletonList(unlockValue));

		if (ret.equals(1)) {
			return true;
		}
		return false;
	}

}
