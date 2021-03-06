package com.fenghuolun.modules.api.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuolun.modules.user.service.NuanxinAccountService;
import com.fenghuolun.modules.user.service.NuanxinCharacterService;
import com.fenghuolun.modules.user.service.NuanxinInviteService;
import com.fenghuolun.modules.user.service.NuanxinUserService;
import com.jeesite.common.web.BaseController;

/**
 * User API Controller
 * @author zhengxiaotai
 * @version 2020-03-02
 */

@Controller
@RequestMapping(value = "${adminPath}/api/user")
public class NuanxinUserApiController extends BaseController {
	
	@Autowired
	private NuanxinUserService nuanxinUserService;
	@Autowired
	private NuanxinAccountService nuanxinAccountService;
	@Autowired
	private NuanxinCharacterService nuanxinCharacterService;
	@Autowired
	private NuanxinInviteService nuanxinInviteService;
	
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
	
	/*
	 * 修改个人信息
	 */
	@ResponseBody
	@RequestMapping(value = "saveUserInfo")
	public Map<String, Object> saveAccountInfo(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		return nuanxinUserService.updateUserInfo(userId, account, password, mobile);
	}
	
	/*
	 * 获取子账户列表
	 */
	@ResponseBody
	@RequestMapping(value = "getAccountList")
	public Map<String, Object> getAccountList(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		return nuanxinAccountService.getAccountList(userId);
	}
	
	/*
	 * 新增子账户
	 */
	@ResponseBody
	@RequestMapping(value = "saveAccount")
	public Map<String, Object> saveAccount(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String accountName = request.getParameter("accountName");
		return nuanxinAccountService.saveAccount(userId, accountName);
	}
	
	/*
	 * 删除子账户
	 */
	@ResponseBody
	@RequestMapping(value = "deleteAccount")
	public Map<String, Object> deleteAccount(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String accountId = request.getParameter("accountId");
		return nuanxinAccountService.deleteAccount(userId, accountId);
	}
	
	@ResponseBody
	@RequestMapping(value = "getCharacterList")
	public Map<String, Object> getCharacterList(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String accountId = request.getParameter("accountId");
		String realmType = request.getParameter("realmType");
		return nuanxinCharacterService.getByAccount(userId, accountId, realmType);
	}
	
	@ResponseBody
	@RequestMapping(value = "getCharacter")
	public Map<String, Object> getCharacter(HttpServletRequest request, HttpServletResponse response) {
		String characterId = request.getParameter("characterId");
		return nuanxinCharacterService.getCharacterById(characterId);
	}
	
	@ResponseBody
	@RequestMapping(value = "saveCharacter")
	public Map<String, Object> saveCharacter(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String characterName = request.getParameter("characterName");
		if (userId == null || userId.isEmpty()) {
			Map<String, Object> result = new HashMap<>();
			result.put("success", false);
			result.put("msg", "用户信息错误");
			return result;
		}
		if (characterName == null || characterName.isEmpty()) {
			Map<String, Object> result = new HashMap<>();
			result.put("success", false);
			result.put("msg", "角色名不能为空");
			return result;
		}
		return nuanxinCharacterService.saveCharacter(request.getParameterMap());
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteCharacter")
	public Map<String, Object> deleteCharacter(HttpServletRequest request, HttpServletResponse response) {
		String characterId = request.getParameter("characterId");
		String userId = request.getParameter("userId");
		return nuanxinCharacterService.deleteCharacter(userId, characterId);
	}
	
	/*
	 * 接受邀请
	 */
	@ResponseBody
	@RequestMapping(value = "completeInvite")
	public Map<String, Object> completeInvite(HttpServletRequest request, HttpServletResponse response) {
		String inviteUserId = request.getParameter("inviteUserId");
		String invitedUserId = request.getParameter("invitedUserId");
		return nuanxinInviteService.completeInvite(inviteUserId, invitedUserId);
	}
}
