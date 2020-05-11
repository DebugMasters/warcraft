/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.web;

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
import com.fenghuolun.modules.order.entity.NuanxinWechatOrder;
import com.fenghuolun.modules.order.service.NuanxinWechatOrderService;

/**
 * 微信订单记录Controller
 * @author zhengxiaotai
 * @version 2020-05-11
 */
@Controller
@RequestMapping(value = "${adminPath}/order/nuanxinWechatOrder")
public class NuanxinWechatOrderController extends BaseController {

	@Autowired
	private NuanxinWechatOrderService nuanxinWechatOrderService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinWechatOrder get(String id, boolean isNewRecord) {
		return nuanxinWechatOrderService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("order:nuanxinWechatOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinWechatOrder nuanxinWechatOrder, Model model) {
		model.addAttribute("nuanxinWechatOrder", nuanxinWechatOrder);
		return "modules/order/nuanxinWechatOrderList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("order:nuanxinWechatOrder:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinWechatOrder> listData(NuanxinWechatOrder nuanxinWechatOrder, HttpServletRequest request, HttpServletResponse response) {
		nuanxinWechatOrder.setPage(new Page<>(request, response));
		Page<NuanxinWechatOrder> page = nuanxinWechatOrderService.findPage(nuanxinWechatOrder);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("order:nuanxinWechatOrder:view")
	@RequestMapping(value = "form")
	public String form(NuanxinWechatOrder nuanxinWechatOrder, Model model) {
		model.addAttribute("nuanxinWechatOrder", nuanxinWechatOrder);
		return "modules/order/nuanxinWechatOrderForm";
	}

	/**
	 * 保存微信订单
	 */
	@RequiresPermissions("order:nuanxinWechatOrder:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinWechatOrder nuanxinWechatOrder) {
		nuanxinWechatOrderService.save(nuanxinWechatOrder);
		return renderResult(Global.TRUE, text("保存微信订单成功！"));
	}
	
	/**
	 * 删除微信订单
	 */
	@RequiresPermissions("order:nuanxinWechatOrder:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinWechatOrder nuanxinWechatOrder) {
		nuanxinWechatOrderService.delete(nuanxinWechatOrder);
		return renderResult(Global.TRUE, text("删除微信订单成功！"));
	}
	
}