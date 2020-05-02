/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.web;

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
import com.fenghuolun.modules.system.entity.NuanxinRealmList;
import com.fenghuolun.modules.system.service.NuanxinRealmListService;

/**
 * nuanxin_realm_listController
 * @author zhengxiaotai
 * @version 2020-04-28
 */
@Controller
@RequestMapping(value = "${adminPath}/system/nuanxinRealmList")
public class NuanxinRealmListController extends BaseController {

	@Autowired
	private NuanxinRealmListService nuanxinRealmListService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinRealmList get(String realmId, boolean isNewRecord) {
		return nuanxinRealmListService.get(realmId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("system:nuanxinRealmList:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinRealmList nuanxinRealmList, Model model) {
		model.addAttribute("nuanxinRealmList", nuanxinRealmList);
		return "modules/system/nuanxinRealmListList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("system:nuanxinRealmList:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinRealmList> listData(NuanxinRealmList nuanxinRealmList, HttpServletRequest request, HttpServletResponse response) {
		nuanxinRealmList.setPage(new Page<>(request, response));
		Page<NuanxinRealmList> page = nuanxinRealmListService.findPage(nuanxinRealmList);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("system:nuanxinRealmList:view")
	@RequestMapping(value = "form")
	public String form(NuanxinRealmList nuanxinRealmList, Model model) {
		model.addAttribute("nuanxinRealmList", nuanxinRealmList);
		return "modules/system/nuanxinRealmListForm";
	}

	/**
	 * 保存nuanxin_realm_list
	 */
	@RequiresPermissions("system:nuanxinRealmList:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinRealmList nuanxinRealmList) {
		nuanxinRealmListService.save(nuanxinRealmList);
		return renderResult(Global.TRUE, text("保存服务器信息成功！"));
	}
	
	/**
	 * 删除nuanxin_realm_list
	 */
	@RequiresPermissions("system:nuanxinRealmList:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinRealmList nuanxinRealmList) {
		nuanxinRealmListService.delete(nuanxinRealmList);
		return renderResult(Global.TRUE, text("删除服务器信息成功！"));
	}
	
}