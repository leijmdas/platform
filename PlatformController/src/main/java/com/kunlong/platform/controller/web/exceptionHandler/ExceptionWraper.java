package com.kunlong.platform.controller.web.exceptionHandler;

import app.support.exception.AppException;
import com.alibaba.dubbo.rpc.RpcException;
import org.apache.dubbo.remoting.TimeoutException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.HashMap;
import java.util.Map;

public class ExceptionWraper {

	private ExceptionWraper() {}
	public static ExceptionInfo wrapException(Throwable e) {
		Map<String, Object> ret = getExceptionEntity(e);
		ExceptionInfo i = new ExceptionInfo();
		i.setCode(""+ret.get("code"));
		i.setMsg(""+ret.get("msg"));
		return i;
	}
	
	private static Map<String, Object> getExceptionEntity(Throwable e) {

		String code = "-1", msg = "";
		if (e != null) {
			if(e instanceof AppException) {
				AppException appEx = (AppException)e;
				code = appEx.getCode();
				msg = appEx.getMessage();
			} else if(e instanceof RpcException) {
				String[] tmp = getRpcExceptionSimpleInfo((RpcException)e);
				code = tmp[0];
				msg = tmp[1];
			}  else if(e instanceof HttpMessageNotReadableException) {
				code = "err_data_01";
				msg = "数据格式错误，请检查相关输入项！";
			} else {
				msg = e.getMessage();
			}
		} 
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("code", code);
		data.put("msg", msg);
		return data;
	}
	public static String[] getRpcExceptionSimpleInfo(RpcException rpcEx) {
		Throwable cause = rpcEx.getCause();
		if(cause == null) {
			return new String[] {"rpc_native_"+rpcEx.getCode(),rpcEx.getMessage()};
		}
		if(cause instanceof AppException) {
			AppException ex = (AppException) cause;
			return new String[] {ex.getCode(),ex.getMessage()};
		}
		if(cause instanceof TimeoutException) {
			return new String[] {"rpc_timeout","远程调用超时"};
		}
		return new String[] {"rpc_native_"+rpcEx.getCode(),"远程调用异常"};
	}
}
