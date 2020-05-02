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
import com.fenghuolun.modules.order.entity.NuanxinCoupon;
import com.fenghuolun.modules.order.service.NuanxinCouponService;

/**
 * nuanxin_couponController
 * @author zhengxiaotai
 * @version 2020-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/order/nuanxinCoupon")
public class NuanxinCouponController extends BaseController {

	@Autowired
	private NuanxinCouponService nuanxinCouponService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinCoupon get(String couponId, boolean isNewRecord) {
		return nuanxinCouponService.get(couponId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("order:nuanxinCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinCoupon nuanxinCoupon, Model model) {
		model.addAttribute("nuanxinCoupon", nuanxinCoupon);
		return "modules/order/nuanxinCouponList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("order:nuanxinCoupon:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinCoupon> listData(NuanxinCoupon nuanxinCoupon, HttpServletRequest request, HttpServletResponse response) {
		nuanxinCoupon.setPage(new Page<>(request, response));
		Page<NuanxinCoupon> page = nuanxinCouponService.findPage(nuanxinCoupon);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("order:nuanxinCoupon:view")
	@RequestMapping(value = "form")
	public String form(NuanxinCoupon nuanxinCoupon, Model model) {
		model.addAttribute("nuanxinCoupon", nuanxinCoupon);
		return "modules/order/nuanxinCouponForm";
	}

	/**
	 * 保存nuanxin_coupon
	 */
	@RequiresPermissions("order:nuanxinCoupon:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinCoupon nuanxinCoupon) {
		nuanxinCouponService.save(nuanxinCoupon);
		return renderResult(Global.TRUE, text("保存nuanxin_coupon成功！"));
	}
	
	/**
	 * 删除nuanxin_coupon
	 */
	@RequiresPermissions("order:nuanxinCoupon:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinCoupon nuanxinCoupon) {
		nuanxinCouponService.delete(nuanxinCoupon);
		return renderResult(Global.TRUE, text("删除nuanxin_coupon成功！"));
	}
	
}