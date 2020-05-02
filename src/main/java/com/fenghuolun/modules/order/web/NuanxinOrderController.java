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
import com.fenghuolun.modules.order.entity.NuanxinOrder;
import com.fenghuolun.modules.order.service.NuanxinOrderService;
import com.fenghuolun.modules.user.entity.NuanxinCharacter;
import com.fenghuolun.modules.user.service.NuanxinCharacterService;

/**
 * nuanxin_orderController
 * @author zhengxiaotai
 * @version 2020-04-13
 */
@Controller
@RequestMapping(value = "${adminPath}/order/nuanxinOrder")
public class NuanxinOrderController extends BaseController {

	@Autowired
	private NuanxinOrderService nuanxinOrderService;
	@Autowired
	private NuanxinCharacterService nuanxinCharacterService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinOrder get(String orderId, boolean isNewRecord) {
		return nuanxinOrderService.get(orderId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("order:nuanxinOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinOrder nuanxinOrder, Model model) {
		model.addAttribute("nuanxinOrder", nuanxinOrder);
		return "modules/order/nuanxinOrderList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("order:nuanxinOrder:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinOrder> listData(NuanxinOrder nuanxinOrder, HttpServletRequest request, HttpServletResponse response) {
		nuanxinOrder.setPage(new Page<>(request, response));
		Page<NuanxinOrder> page = nuanxinOrderService.findPage(nuanxinOrder);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("order:nuanxinOrder:view")
	@RequestMapping(value = "form")
	public String form(NuanxinOrder nuanxinOrder, Model model) {
		NuanxinCharacter character = nuanxinCharacterService.getById(nuanxinOrder.getCharacterId());
		nuanxinOrder.setAccountName(character.getAccountName());
		nuanxinOrder.setRealmName(character.getRealmName());
		model.addAttribute("nuanxinOrder", nuanxinOrder);
		return "modules/order/nuanxinOrderForm";
	}

	/**
	 * 保存订单信息
	 */
	@RequiresPermissions("order:nuanxinOrder:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinOrder nuanxinOrder) {
		nuanxinOrderService.save(nuanxinOrder);
		return renderResult(Global.TRUE, text("保存订单信息成功！"));
	}
	
	/**
	 * 删除订单信息
	 */
	@RequiresPermissions("order:nuanxinOrder:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinOrder nuanxinOrder) {
		nuanxinOrderService.delete(nuanxinOrder);
		return renderResult(Global.TRUE, text("删除订单信息成功！"));
	}
	
}