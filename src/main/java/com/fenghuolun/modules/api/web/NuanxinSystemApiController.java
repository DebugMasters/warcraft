package com.fenghuolun.modules.api.web;

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
import com.fenghuolun.modules.system.service.NuanxinArticleService;
import com.fenghuolun.modules.system.service.NuanxinCarouselService;
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
		return result;
	}
}
