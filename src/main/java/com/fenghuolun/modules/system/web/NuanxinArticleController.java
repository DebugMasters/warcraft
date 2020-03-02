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
import com.fenghuolun.modules.system.entity.NuanxinArticle;
import com.fenghuolun.modules.system.service.NuanxinArticleService;

/**
 * nuanxin_articleController
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/system/nuanxinArticle")
public class NuanxinArticleController extends BaseController {

	@Autowired
	private NuanxinArticleService nuanxinArticleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinArticle get(String articleId, boolean isNewRecord) {
		return nuanxinArticleService.get(articleId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("system:nuanxinArticle:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinArticle nuanxinArticle, Model model) {
		model.addAttribute("nuanxinArticle", nuanxinArticle);
		return "modules/system/nuanxinArticleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("system:nuanxinArticle:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinArticle> listData(NuanxinArticle nuanxinArticle, HttpServletRequest request, HttpServletResponse response) {
		nuanxinArticle.setPage(new Page<>(request, response));
		Page<NuanxinArticle> page = nuanxinArticleService.findPage(nuanxinArticle);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("system:nuanxinArticle:view")
	@RequestMapping(value = "form")
	public String form(NuanxinArticle nuanxinArticle, Model model) {
		model.addAttribute("nuanxinArticle", nuanxinArticle);
		return "modules/system/nuanxinArticleForm";
	}

	/**
	 * 保存文章
	 */
	@RequiresPermissions("system:nuanxinArticle:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinArticle nuanxinArticle) {
		nuanxinArticleService.save(nuanxinArticle);
		return renderResult(Global.TRUE, text("保存文章成功！"));
	}
	
	/**
	 * 删除文章
	 */
	@RequiresPermissions("system:nuanxinArticle:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinArticle nuanxinArticle) {
		nuanxinArticleService.delete(nuanxinArticle);
		return renderResult(Global.TRUE, text("删除文章成功！"));
	}
	
}