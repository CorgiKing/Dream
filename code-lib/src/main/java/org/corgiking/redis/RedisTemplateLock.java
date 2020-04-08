package org.corgiking.redis;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import redis.clients.util.SafeEncoder;

public class RedisTemplateLock {


	/**
	 * 
	 * @param redisTemplate
	 * @param lockKey       分布式锁的key
	 * @param lockValue     分布式锁的值,唯一识别是谁获取了锁
	 * @param timeout       锁的超时时间
	 * @param unit          时间单位
	 * @return
	 */
	public static boolean lock(StringRedisTemplate redisTemplate, String lockKey, String lockValue,
							   long timeout, TimeUnit unit) {
		return redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, unit);
	}

	/**
	 * 使用lua脚本原子性删除分布式锁。
	 * 实现谁加的分布式锁谁才能释放。
	 * 
	 * @param redisTemplate
	 * @param unlockKey		分布式锁的key
	 * @param unlockValue		分布式锁中的value符合才能成功释放
	 * @return
	 */
	public static boolean unLock(StringRedisTemplate redisTemplate, String unlockKey, String unlockValue) {

		String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1]  then " +
				"return redis.call('del', KEYS[1]) " +
				"else " +
				"return 0 " +
				"end";
		RedisScript<Long> redisScript = new DefaultRedisScript<>(luaScript, Long.class);

		Long ret = redisTemplate.execute(redisScript, Collections.singletonList(unlockKey), unlockValue);

		if (ret.equals(1L)) {
			return true;
		}
		return false;
	}

}
