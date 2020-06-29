package com.fenghuolun.modules.system.web;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import com.fenghuolun.modules.system.service.NuanxinConfigService;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.user.service.NuanxinUserService;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.RoseType;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

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
	@Autowired
	private NuanxinConfigService nuanxinConfigService;

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
	
	@ResponseBody
	@PostMapping(value = "orderTypeChart")
	public Map<String, Object> orderTypeChart(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -90);
		List<NuanxinOrder> list = nuanxinOrderService.countByType(calendar.getTime());
		Pie pie = new Pie();
		pie.name("代练类型");
		pie.radius("15%", "70%");
		pie.roseType(RoseType.radius);
		pie.avoidLabelOverlap(false);
		pie.label().normal().show(true);
		pie.label().emphasis().show(true);
		pie.label().emphasis().textStyle().fontSize(10);
		pie.label().emphasis().textStyle().fontWeight("bold");
		for (NuanxinOrder order : list) {
			PieData data = new PieData(order.getOrderCatalog(), order.getOrderStatus());
			pie.data(data);
		}
		Option option = new Option();
		option.tooltip().trigger(Trigger.item);
		option.tooltip().formatter("{a} <br/>{b}: {c} ({d}%)");
		option.series(pie);
		result.put("data", GsonUtil.format(option));
		return result;
	}
	
	@ResponseBody
	@PostMapping(value = "monthOrderChart")
	public Map<String, Object> monthOrderChart(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -365);
		List<NuanxinOrder> list = nuanxinOrderService.countByMonth(calendar.getTime());
		System.out.println(list.size());
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.axisLine().lineStyle().color("#FFF");
		xAxis.axisLabel().color("#FFF");
		ValueAxis yAxis = new ValueAxis();
		yAxis.axisLine().show(false);
		yAxis.splitLine().lineStyle().type(LineType.dashed);
		yAxis.axisLine().lineStyle().color("#FFF");
		yAxis.axisLabel().color("#FFF");
		Line line = new Line();
		line.smooth(true);
		line.symbol(Symbol.circle);
		line.symbolSize(8);
		line.itemStyle().normal().color("#FFF");
		for (NuanxinOrder order : list) {
			xAxis.data(order.getOrderCatalog());
			line.data(order.getOrderStatus());
		}
		Option option = new Option();
		option.xAxis(xAxis);
		option.yAxis(yAxis);
		option.series(line);
		result.put("data", GsonUtil.format(option));
		return result;
	}
	
	/*
	 * 加密key和iv
	 */
	@ResponseBody
	@RequestMapping(value = "getDecryptInfo")
	public Map<String, Object> getDecryptInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
        User user = UserUtils.getUser();
        if (user == null || user.getId() == null) {
        	result.put("success", false);
			result.put("msg", "权限不足");
			return result;
        }
		result.put("key", nuanxinConfigService.getByConfigKey("nx.decrypt.key").getConfigValue());
		result.put("iv", nuanxinConfigService.getByConfigKey("nx.decrypt.iv").getConfigValue());
    	result.put("success", true);
		result.put("msg", "成功");
		return result;
	}
}
