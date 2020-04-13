package com.fenghuolun.modules.api.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuolun.modules.order.service.NuanxinOrderService;

/**
 * Order API Controller
 * @author zhengxiaotai
 * @version 2020-04-13
 */

@Controller
@RequestMapping(value = "${adminPath}/api/order")
public class NuanxinOrderApiController {
	
	@Autowired
	private NuanxinOrderService nuanxinOrderService;
	
	/*
	 * 下单
	 */
	@ResponseBody
	@RequestMapping(value = "saveOrder")
	public Map<String, Object> saveOrder(HttpServletRequest request, HttpServletResponse response) {
		double orderMoney = Double.parseDouble(request.getParameter("orderMoney"));
		String characterId = request.getParameter("characterId");
		if (orderMoney <= 0) {
			Map<String, Object> result = new HashMap<>();
			result.put("success", false);
			result.put("msg", "订单金额错误");
			return result;
		}
		if (characterId == null || characterId.isEmpty()) {
			Map<String, Object> result = new HashMap<>();
			result.put("success", false);
			result.put("msg", "角色信息错误");
			return result;
		}
		return nuanxinOrderService.saveOrder(request.getParameterMap());
	}
}
