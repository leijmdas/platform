package com.kunlong.platform.dubbo.api;

import app.support.context.DefaultRequestContextFactory;
import app.support.context.RequestContext;
import app.support.exception.AppException;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.sql.DataTruncation;
import java.util.UUID;

public class DubboRequestFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(DubboRequestFilter.class);
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		long startTime = System.currentTimeMillis();
		String id = UUID.randomUUID().toString();
		logger.debug("[开始]服务[id:{},url:{}]",id,invoker.getUrl());
	//	invocation.getAttachment().put("async","false");
		RpcContext ctx = RpcContext.getContext();
		Object opBy = ctx.getAttachment("opBy");
		RequestContext rctx = DefaultRequestContextFactory.getInstance().create();
		rctx.setAttribute("opBy", opBy==null?null:(Integer.parseInt(opBy.toString())));
		try {
			Result r =  invoker.invoke(invocation);

			return wrapException(id,r);
			//return r; //lids
		} finally {
			logger.debug("[结束]服务[id:{}.耗时:{} ms]",id,System.currentTimeMillis() - startTime);
			
		}
		
	}

	private Result wrapException(String reqId,Result r) {
		Throwable ex = r.getException();
		if(ex == null) {
			return (Result)r;
		}
		logger.error("服务异常[id:{}]",reqId,ex);
		//统一异常，全部转化为AppException
		AppException apiEx = null;
		if(ex instanceof AppException) {
			AppException tmp = (AppException)ex;
			//依然需要转化
			apiEx = new AppException(tmp.getCode(),tmp.getMessage());
		} else if(DataAccessException.class.isAssignableFrom(ex.getClass())){
			apiEx = this.wrapDataAccessException((DataAccessException)ex);
		} else {
			apiEx = new AppException("error_unknown",ex.getMessage());
		}
		RpcException rpcException = new RpcException(RpcException.BIZ_EXCEPTION,ex.getMessage(),apiEx);
		//r.setException(rpcException);
		return r;
	}
	private AppException wrapDataAccessException(DataAccessException dEx) {
		
		Throwable cause = dEx.getCause();
		if(cause != null) {
			if(cause instanceof DataTruncation) {
				return new AppException("error_data_param","数据参数异常,请检查参数类型、长度等信息！");
			}
		}
		return new AppException("error_data_unknown","数据访问异常！");
	}
}
