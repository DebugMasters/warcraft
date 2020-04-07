package com.fenghuolun.modules.api.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenghuolun.modules.system.entity.NuanxinArticle;
import com.fenghuolun.modules.system.entity.NuanxinCarousel;
import com.fenghuolun.modules.system.entity.NuanxinRealmList;
import com.fenghuolun.modules.system.entity.NuanxinTradeCatalog;
import com.fenghuolun.modules.system.service.NuanxinArticleService;
import com.fenghuolun.modules.system.service.NuanxinCarouselService;
import com.fenghuolun.modules.system.service.NuanxinRealmListService;
import com.fenghuolun.modules.system.service.NuanxinTradeCatalogService;
import com.jeesite.common.web.BaseController;

/**
 * nuanxin_carouselController
 * @author zhengxiaotai
 * @version 2020-03-02
 */

@Controller
@RequestMapping(value = "${adminPath}/api/system")
public class NuanxinSystemApiController extends BaseController {

	@Autowired
	private NuanxinCarouselService nuanxinCarouselService;
	@Autowired
	private NuanxinArticleService nuanxinArticleService;
	@Autowired
	private NuanxinRealmListService nuanxinRealmListService;
	@Autowired
	private NuanxinTradeCatalogService NuanxinTradeCatalogService;
	
	/*
	 * 主页信息
	 */
	@ResponseBody
	@RequestMapping(value = "getIndexInfo")
	public Map<String, Object> getIndexInfo(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<>();
		List<NuanxinCarousel> carouselList = nuanxinCarouselService.findList(new NuanxinCarousel());
		List<NuanxinArticle> articleList = nuanxinArticleService.findList(new NuanxinArticle());
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("carouselList", carouselList);
		result.put("articleList", articleList);
		result.put("notificationList", new ArrayList<String>());
		return result;
	}
	
	/*
	 * 服务器列表
	 */
	@ResponseBody
	@RequestMapping(value = "getRealmList")
	public Map<String, Object> getRealmList(HttpServletRequest request, HttpServletResponse response){
		String realmZone = request.getParameter("realmZone");
		String realmType = request.getParameter("realmType");
		NuanxinRealmList realmList = new NuanxinRealmList();
		realmList.setRealmZone(Integer.parseInt(realmZone));
		realmList.setRealmType(Integer.parseInt(realmType));
		List<NuanxinRealmList> list = nuanxinRealmListService.findList(realmList);
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("realmList", list);
		return result;
	}
	
	/*
	 * 代练类别列表
	 */
	@ResponseBody
	@RequestMapping(value = "getCatalogList")
	public Map<String, Object> getCatalogList(HttpServletRequest request, HttpServletResponse response){
		String parentId = request.getParameter("parentId");
		String catalogType = request.getParameter("catalogType");
		List<NuanxinTradeCatalog> list = NuanxinTradeCatalogService.getByParentId(parentId, Integer.parseInt(catalogType));
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("catalogList", list);
		return result;
	}
}
