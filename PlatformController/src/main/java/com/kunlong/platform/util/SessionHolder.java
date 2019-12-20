package com.kunlong.platform.util;

import com.kunlong.platform.consts.RequestContextConst;
import com.kunlong.platform.util.support.CurrentRequestContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 分布式SESSION
 * @name SessionHolder
 * @author zz  | www.xwparking.com
 * @date 2018年11月13日  
 * @description:
 */
public class SessionHolder implements app.support.session.ISessionHolder {

	private static String SESSION_PREFIX = "session:";

	private static ExecutorService EXECUTORS = Executors.newCachedThreadPool();

	private String sessionID = null;
	private static RedisUtil redisUtil;
	public static long TIMEOUT = 7200;

	private SessionHolder(String sessionKey) {
		sessionID = SESSION_PREFIX + sessionKey;
	}

	public String getSessionID() {
		return this.sessionID;
	}

	public static SessionHolder getInstance(String key) {
		return new SessionHolder(key);
	}

	public static void config(String sessionPrefix, long timeout) {
		TIMEOUT = timeout;
		SESSION_PREFIX = sessionPrefix;
		StringRedisSerializer stringSerializer = new StringRedisSerializer();
		RedisTemplate<String, String> tpl = RedisUtil.getInstance().cloneRedisTemplate();
		tpl.setKeySerializer(stringSerializer);
		tpl.setValueSerializer(stringSerializer);
		tpl.setHashKeySerializer(stringSerializer);
		tpl.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

		redisUtil = new RedisUtil(tpl);
	}

	public static SessionHolder create(String sessionKey) {
		return create(sessionKey, TIMEOUT);
	}

	public static SessionHolder create(String sessionKey, long timeout) {

		SessionHolder holder = new SessionHolder(sessionKey);
		holder.setAttribute("timeout", timeout);
		holder.initHash(timeout);
		return holder;
	}

	private void initHash(long timeout) {
		redisUtil.initHash(getSessionID(), timeout);
	}

	public Map<Object, Object> getValues() {
		return redisUtil.getHash(getSessionID());
	}

	public Object getAttribute(String propName) {
		return redisUtil.getHash(getSessionID(), propName);
	}

	public void setAttribute(String propName, Object propValue) {
		redisUtil.putHash(getSessionID(), propName, propValue);
	}

	public void setValues(Map<Object, Object> values) {
		redisUtil.putHashs(getSessionID(), values);
	}

	public boolean exists() {
		return redisUtil.hasKey(getSessionID());
	}

	public void remove() {
		redisUtil.delete(getSessionID());
	}

	@SuppressWarnings("unchecked")
	public static Map<Object, Object> getCurrentSessionValues() {
		Object vals = CurrentRequestContext.getAttribute(RequestContextConst.KEY_SESSIONVAL);
		if (vals == null) {
			String key = CurrentRequestContext.getAttribute(RequestContextConst.KEY_SESSIONKEY, String.class);

			Map<Object, Object> sessionValues = SessionHolder.getInstance(key).getValues();
			if(sessionValues != null && !sessionValues.isEmpty()) {
				CurrentRequestContext.setAttribute(RequestContextConst.KEY_SESSIONVAL, vals);
				vals = sessionValues;
			} else {
				return Collections.emptyMap();
			}
			
		}
		return (Map<Object, Object>) (vals);
	}

	@Override
	public void flush() {
		final String sessionID = this.getSessionID();
		EXECUTORS.submit(new Runnable() {

			@Override
			public void run() {
				Long expire = redisUtil.getExpireValue(sessionID);
				if (expire < TIMEOUT) {
					redisUtil.expireValue(sessionID, TIMEOUT);
				}
			}

		});
	}
}
