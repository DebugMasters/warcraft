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
import com.fenghuolun.modules.user.entity.NuanxinInvite;
import com.fenghuolun.modules.user.service.NuanxinInviteService;

/**
 * nuanxin_inviteController
 * @author zhengxiaotai
 * @version 2020-05-18
 */
@Controller
@RequestMapping(value = "${adminPath}/user/nuanxinInvite")
public class NuanxinInviteController extends BaseController {

	@Autowired
	private NuanxinInviteService nuanxinInviteService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinInvite get(String inviteId, boolean isNewRecord) {
		return nuanxinInviteService.get(inviteId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:nuanxinInvite:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinInvite nuanxinInvite, Model model) {
		model.addAttribute("nuanxinInvite", nuanxinInvite);
		return "modules/user/nuanxinInviteList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:nuanxinInvite:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinInvite> listData(NuanxinInvite nuanxinInvite, HttpServletRequest request, HttpServletResponse response) {
		nuanxinInvite.setPage(new Page<>(request, response));
		Page<NuanxinInvite> page = nuanxinInviteService.findPage(nuanxinInvite);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:nuanxinInvite:view")
	@RequestMapping(value = "form")
	public String form(NuanxinInvite nuanxinInvite, Model model) {
		model.addAttribute("nuanxinInvite", nuanxinInvite);
		return "modules/user/nuanxinInviteForm";
	}

	/**
	 * 保存邀请
	 */
	@RequiresPermissions("user:nuanxinInvite:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinInvite nuanxinInvite) {
		nuanxinInviteService.save(nuanxinInvite);
		return renderResult(Global.TRUE, text("保存邀请成功！"));
	}
	
	/**
	 * 删除邀请
	 */
	@RequiresPermissions("user:nuanxinInvite:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinInvite nuanxinInvite) {
		nuanxinInviteService.delete(nuanxinInvite);
		return renderResult(Global.TRUE, text("删除邀请成功！"));
	}
	
}