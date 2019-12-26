package com.kunlong.platform.controller.web;

import app.support.session.ISessionHolder;
import cn.integriti.center.api.model.SysUserDTO;
import cn.integriti.center.api.service.SysUserApiService;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.util.SessionHolder;
import com.kunlong.platform.support.service.AuthService;

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
	AuthService.AuthToken login(String username, String password) {
		SysUserDTO su = userService.checkPass(1,username,password);
		AuthService.AuthToken at  = this.authService.createToken("web:user:"+su.getId());
		ISessionHolder sessionHolder = SessionHolder.create(at.getToken());
		sessionHolder.setAttribute(SessionKeyEnum.WEB_USER.getKey(), su);
		return at;
	}
	
}
