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
		return nuanxinCharacterService.getByAccount(userId, accountId);
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
		String characterId = request.getParameter("characterId");
		String characterName = request.getParameter("characterName");
		String characterAccount = request.getParameter("characterAccount");
		String characterRealm = request.getParameter("characterRealm");
		String characterRealmType = request.getParameter("characterRealmType");
		String characterClass = request.getParameter("characterClass");
		String allianceHorde = request.getParameter("allianceHorde");
		String characterSpecialization = request.getParameter("characterSpecialization");
		String userId = request.getParameter("userId");
		if (characterName == null || characterName.isEmpty()) {
			Map<String, Object> result = new HashMap<>();
			result.put("success", false);
			result.put("msg", "角色名不能为空");
			return result;
		}
		return nuanxinCharacterService.saveCharacter(characterId, characterName, characterAccount, 
				characterRealm, Integer.parseInt(characterRealmType), Integer.parseInt(characterClass), Integer.parseInt(allianceHorde), 
				characterSpecialization, userId);
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteCharacter")
	public Map<String, Object> deleteCharacter(HttpServletRequest request, HttpServletResponse response) {
		String characterId = request.getParameter("characterId");
		String userId = request.getParameter("userId");
		return nuanxinCharacterService.deleteCharacter(userId, characterId);
	}
	
	@ResponseBody
	@RequestMapping(value = "saveAccountInfo")
	public Map<String, Object> saveAccountInfo(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		return nuanxinUserService.updateAccountInfo(userId, account, password, mobile);
	}
}
