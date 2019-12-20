package com.kunlong.platform.util.support;



import app.support.context.DefaultRequestContextFactory;
import app.support.context.RequestContext;

public class CurrentRequestContext {
	
	private CurrentRequestContext() {

	}

	/**
	 * @return 当前请求上下文
	 */
	public static RequestContext getContext() {
		return DefaultRequestContextFactory.getInstance().getRequestContext();
	}
	public static Object getAttribute(String key) {
		RequestContext ctx = getContext();
		if (ctx == null) return null;
		Object obj = ctx.getAttribute(key);
		if (obj == null) {
			return null;
		}
		return obj;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(String key, Class<T> clazz) {
		Object obj = getAttribute(key);
		return obj == null?null:(T)obj;
	}

	public static void setAttribute(String key, Object val) {
		RequestContext ctx = getContext();
		if (ctx == null) {
			ctx = DefaultRequestContextFactory.getInstance().create();
		}
		ctx.setAttribute(key, val);
	}
	
}
