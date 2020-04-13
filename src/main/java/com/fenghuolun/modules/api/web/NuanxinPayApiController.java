package com.fenghuolun.modules.api.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fenghuolun.modules.utils.WechatUtil;

@Controller
@RequestMapping(value = "${adminPath}/api/pay")
public class NuanxinPayApiController {
	
	@RequestMapping(value = "wechatPayCallback")
	public void wechatPayCallback(HttpServletRequest request, HttpServletResponse response) {
		try {
			WechatUtil.payCallback(request);
			response.getWriter().write("success", 0, 7);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
