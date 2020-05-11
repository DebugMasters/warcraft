/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.fenghuolun.modules.order.entity.NuanxinCoupon;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.user.service.NuanxinUserService;

/**
 * nuanxin_userController
 * @author zhengxiaotai
 * @version 2020-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/user/nuanxinUser")
public class NuanxinUserController extends BaseController {

	@Autowired
	private NuanxinUserService nuanxinUserService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinUser get(String userId, boolean isNewRecord) {
		return nuanxinUserService.get(userId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:nuanxinUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinUser nuanxinUser, Model model) {
		model.addAttribute("nuanxinUser", nuanxinUser);
		return "modules/user/nuanxinUserList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:nuanxinUser:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinUser> listData(NuanxinUser nuanxinUser, HttpServletRequest request, HttpServletResponse response) {
		nuanxinUser.setPage(new Page<>(request, response));
		Page<NuanxinUser> page = nuanxinUserService.findPage(nuanxinUser);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:nuanxinUser:view")
	@RequestMapping(value = "form")
	public String form(NuanxinUser nuanxinUser, Model model) {
		model.addAttribute("nuanxinUser", nuanxinUser);
		return "modules/user/nuanxinUserForm";
	}

	/**
	 * 保存用户信息
	 */
	@RequiresPermissions("user:nuanxinUser:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinUser nuanxinUser) {
		nuanxinUserService.save(nuanxinUser);
		return renderResult(Global.TRUE, text("保存用户信息成功！"));
	}
	
	/**
	 * 删除用户信息
	 */
	@RequiresPermissions("user:nuanxinUser:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinUser nuanxinUser) {
		nuanxinUserService.delete(nuanxinUser);
		return renderResult(Global.TRUE, text("删除用户信息成功！"));
	}
	
	@RequiresPermissions("user:nuanxinUser:view")
	@RequestMapping(value = "addCoupon")
	public String addCoupon(NuanxinUser nuanxinUser, Model model) {
		NuanxinCoupon nuanxinCoupon = new NuanxinCoupon();
		nuanxinCoupon.setUserId(nuanxinUser.getUserId());
		model.addAttribute("nuanxinCoupon", nuanxinCoupon);
		return "modules/user/addCoupon";
	}
	
}