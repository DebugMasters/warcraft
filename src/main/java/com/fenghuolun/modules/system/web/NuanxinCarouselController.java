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
import com.fenghuolun.modules.system.entity.NuanxinCarousel;
import com.fenghuolun.modules.system.service.NuanxinCarouselService;

/**
 * nuanxin_carouselController
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Controller
@RequestMapping(value = "${adminPath}/system/nuanxinCarousel")
public class NuanxinCarouselController extends BaseController {

	@Autowired
	private NuanxinCarouselService nuanxinCarouselService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinCarousel get(Integer carouselId, boolean isNewRecord) {
		return nuanxinCarouselService.get(carouselId + "", isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("system:nuanxinCarousel:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinCarousel nuanxinCarousel, Model model) {
		model.addAttribute("nuanxinCarousel", nuanxinCarousel);
		return "modules/system/nuanxinCarouselList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("system:nuanxinCarousel:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinCarousel> listData(NuanxinCarousel nuanxinCarousel, HttpServletRequest request, HttpServletResponse response) {
		nuanxinCarousel.setPage(new Page<>(request, response));
		Page<NuanxinCarousel> page = nuanxinCarouselService.findPage(nuanxinCarousel);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("system:nuanxinCarousel:view")
	@RequestMapping(value = "form")
	public String form(NuanxinCarousel nuanxinCarousel, Model model) {
		model.addAttribute("nuanxinCarousel", nuanxinCarousel);
		return "modules/system/nuanxinCarouselForm";
	}

	/**
	 * 保存轮播图
	 */
	@RequiresPermissions("system:nuanxinCarousel:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinCarousel nuanxinCarousel) {
		nuanxinCarouselService.save(nuanxinCarousel);
		return renderResult(Global.TRUE, text("保存轮播图成功！"));
	}
	
	/**
	 * 删除轮播图
	 */
	@RequiresPermissions("system:nuanxinCarousel:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinCarousel nuanxinCarousel) {
		nuanxinCarouselService.delete(nuanxinCarousel);
		return renderResult(Global.TRUE, text("删除轮播图成功！"));
	}
	
}