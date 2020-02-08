package com.kunlong.platform.controller.web;

import app.support.session.ISessionHolder;
import com.kunlong.dubbo.sys.model.SysUserDTO;
import com.kunlong.dubbo.sys.service.SysUserApiService;
import com.kunlong.core.exception.BusinessException;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.context.PfContext;
import com.kunlong.platform.tasklog.aspect.SysLoggerAnnotation;
import com.kunlong.platform.util.SessionHolder;
import com.kunlong.platform.support.service.AuthService;

import com.kunlong.platform.utils.JsonResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/auth")
@Controller
public class AuthController extends BaseController {

	@Autowired
	private AuthService authService;

	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysUserApiService userService;

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	//@PreAuthorize("hasAuthority('ADM')")
	@RequestMapping(value="login",method = RequestMethod.POST)
	@ResponseBody
	@SysLoggerAnnotation(value="login",security=true)
	public 	JsonResult<AuthService.AuthToken> login(String username, String password, String verifyCode) {
		String code = AppKlongContext.getLoginContext().getPicCode();
		if (!verifyCode.equalsIgnoreCase(code)) {
			//throw new RuntimeException("验证码不正确!");
		}

		try {
			SysUserDTO su = userService.checkPass(1, username, password);
			AuthService.AuthToken authToken  = this.authService.createToken("web:user:"+su.getId());
			ISessionHolder sessionHolder = SessionHolder.create(authToken.getToken());
			sessionHolder.setAttribute(SessionKeyEnum.WEB_USER.getKey(), su);
			System.out.println(PfContext.getCurrentSysUser());
			return JsonResult.success(authToken);
		}catch(BusinessException e)
		{
			return JsonResult.failure(null,e.getMsg());
		}

	}
	
}
