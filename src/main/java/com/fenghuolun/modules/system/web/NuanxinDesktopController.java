package com.fenghuolun.modules.system.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuolun.modules.order.entity.NuanxinOrder;
import com.fenghuolun.modules.order.service.NuanxinOrderService;
import com.fenghuolun.modules.order.service.NuanxinWechatOrderService;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.user.service.NuanxinUserService;
import com.jeesite.common.web.BaseController;

/**
 * 后台主页相关接口
 * @author zhengxiaotai
 * @version 2020-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/system/nuanxinDesktop")
public class NuanxinDesktopController extends BaseController {

	@Autowired
	private NuanxinUserService nuanxinUserService;
	@Autowired
	private NuanxinOrderService nuanxinOrderService;
	@Autowired
	private NuanxinWechatOrderService nuanxinWechatOrderService;

	@ResponseBody
	@PostMapping(value = "cardData")
	public Map<String, Object> cardData(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		long userCount = nuanxinUserService.findCount(new NuanxinUser());
		long orderCount = nuanxinOrderService.findCount(new NuanxinOrder());
		long todaysOrder = (long) nuanxinOrderService.todaysOrder().get("count");
		long totalIncome = nuanxinWechatOrderService.totalIncome();
		result.put("userCount", userCount);
		result.put("orderCount", orderCount);
		result.put("todaysOrder", todaysOrder);
		result.put("totalIncome", totalIncome);
		return result;
	}
}
