package com.fenghuolun.modules.api.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuolun.modules.user.service.NuanxinUserService;
import com.jeesite.common.web.BaseController;

/**
 * nuanxin_carouselController
 * @author zhengxiaotai
 * @version 2020-03-02
 */

@Controller
@RequestMapping(value = "${adminPath}/api/user")
public class NuanxinUserApiController extends BaseController {
	
	@Autowired
	private NuanxinUserService nuanxinUserService;
	
	/*
	 * 登陆
	 */
	@ResponseBody
	@RequestMapping(value = "login")
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String userName = request.getParameter("userName");
		String image = request.getParameter("image");
		return nuanxinUserService.login(code, userName, image);
	}
	
	/*
	 * 个人信息
	 */
	@ResponseBody
	@RequestMapping(value = "getUserInfo")
	public Map<String, Object> getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		return nuanxinUserService.getUserInfo(userId);
	}
}
