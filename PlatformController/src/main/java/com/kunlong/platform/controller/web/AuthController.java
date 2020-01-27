package com.kunlong.platform.controller.web;

import app.support.session.ISessionHolder;
import cn.kunlong.center.api.model.SysUserDTO;
import cn.kunlong.center.api.service.SysUserApiService;
import cn.kunlong.center.core.exception.BusinessException;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.util.SessionHolder;
import com.kunlong.platform.support.service.AuthService;

import com.kunlong.platform.utils.JsonResult;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping(value="login",method = RequestMethod.POST)
	public @ResponseBody
	JsonResult<AuthService.AuthToken> login(String username, String password, String verifyCode) {
		String code = AppKlongContext.getLoginContext().getPicCode();
		if (!verifyCode.equalsIgnoreCase(code)) {
			//throw new RuntimeException("验证码不正确!");
		}

		try {
			SysUserDTO su = userService.checkPass(1, username, password);
			AuthService.AuthToken at  = this.authService.createToken("web:user:"+su.getId());
			ISessionHolder sessionHolder = SessionHolder.create(at.getToken());
			sessionHolder.setAttribute(SessionKeyEnum.WEB_USER.getKey(), su);
			return JsonResult.success(at);
		}catch(BusinessException e)
		{
			return JsonResult.failure(null,e.getMsg());
		}

	}
	
}
