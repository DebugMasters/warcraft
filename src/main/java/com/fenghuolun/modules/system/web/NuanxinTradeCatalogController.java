/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.web;

import java.util.List;
import java.util.Map;

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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.fenghuolun.modules.system.entity.NuanxinTradeCatalog;
import com.fenghuolun.modules.system.service.NuanxinTradeCatalogService;

/**
 * nuanxin_trade_catalogController
 * @author zhengxiaotai
 * @version 2020-04-07
 */
@Controller
@RequestMapping(value = "${adminPath}/system/nuanxinTradeCatalog")
public class NuanxinTradeCatalogController extends BaseController {

	@Autowired
	private NuanxinTradeCatalogService nuanxinTradeCatalogService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinTradeCatalog get(String catalogCode, boolean isNewRecord) {
		return nuanxinTradeCatalogService.get(catalogCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinTradeCatalog nuanxinTradeCatalog, Model model) {
		model.addAttribute("nuanxinTradeCatalog", nuanxinTradeCatalog);
		return "modules/system/nuanxinTradeCatalogList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<NuanxinTradeCatalog> listData(NuanxinTradeCatalog nuanxinTradeCatalog) {
		if (StringUtils.isBlank(nuanxinTradeCatalog.getParentCode())) {
			nuanxinTradeCatalog.setParentCode(NuanxinTradeCatalog.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(nuanxinTradeCatalog.getCatalogName())){
			nuanxinTradeCatalog.setParentCode(null);
		}
		if (nuanxinTradeCatalog.getCatalogType() != null){
			nuanxinTradeCatalog.setParentCode(null);
		}
		if (nuanxinTradeCatalog.getMoney() != null){
			nuanxinTradeCatalog.setParentCode(null);
		}
		List<NuanxinTradeCatalog> list = nuanxinTradeCatalogService.findList(nuanxinTradeCatalog);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:view")
	@RequestMapping(value = "form")
	public String form(NuanxinTradeCatalog nuanxinTradeCatalog, Model model) {
		// 创建并初始化下一个节点信息
		nuanxinTradeCatalog = createNextNode(nuanxinTradeCatalog);
		model.addAttribute("nuanxinTradeCatalog", nuanxinTradeCatalog);
		return "modules/system/nuanxinTradeCatalogForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public NuanxinTradeCatalog createNextNode(NuanxinTradeCatalog nuanxinTradeCatalog) {
		if (StringUtils.isNotBlank(nuanxinTradeCatalog.getParentCode())){
			nuanxinTradeCatalog.setParent(nuanxinTradeCatalogService.get(nuanxinTradeCatalog.getParentCode()));
		}
		if (nuanxinTradeCatalog.getIsNewRecord()) {
			NuanxinTradeCatalog where = new NuanxinTradeCatalog();
			where.setParentCode(nuanxinTradeCatalog.getParentCode());
			NuanxinTradeCatalog last = nuanxinTradeCatalogService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				nuanxinTradeCatalog.setTreeSort(last.getTreeSort() + 30);
				nuanxinTradeCatalog.setCatalogCode(IdGen.nextCode(last.getCatalogCode()));
			}else if (nuanxinTradeCatalog.getParent() != null){
				nuanxinTradeCatalog.setCatalogCode(nuanxinTradeCatalog.getParent().getCatalogCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (nuanxinTradeCatalog.getTreeSort() == null){
			nuanxinTradeCatalog.setTreeSort(NuanxinTradeCatalog.DEFAULT_TREE_SORT);
		}
		return nuanxinTradeCatalog;
	}

	/**
	 * 保存代练类型
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinTradeCatalog nuanxinTradeCatalog) {
		nuanxinTradeCatalogService.save(nuanxinTradeCatalog);
		return renderResult(Global.TRUE, text("保存代练类型成功！"));
	}
	
	/**
	 * 停用代练类型
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(NuanxinTradeCatalog nuanxinTradeCatalog) {
		NuanxinTradeCatalog where = new NuanxinTradeCatalog();
		where.setStatus(NuanxinTradeCatalog.STATUS_NORMAL);
		where.setParentCodes("," + nuanxinTradeCatalog.getId() + ",");
		long count = nuanxinTradeCatalogService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该代练类型包含未停用的子代练类型！"));
		}
		nuanxinTradeCatalog.setStatus(NuanxinTradeCatalog.STATUS_DISABLE);
		nuanxinTradeCatalogService.updateStatus(nuanxinTradeCatalog);
		return renderResult(Global.TRUE, text("停用代练类型成功"));
	}
	
	/**
	 * 启用代练类型
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(NuanxinTradeCatalog nuanxinTradeCatalog) {
		nuanxinTradeCatalog.setStatus(NuanxinTradeCatalog.STATUS_NORMAL);
		nuanxinTradeCatalogService.updateStatus(nuanxinTradeCatalog);
		return renderResult(Global.TRUE, text("启用代练类型成功"));
	}
	
	/**
	 * 删除代练类型
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinTradeCatalog nuanxinTradeCatalog) {
		nuanxinTradeCatalogService.delete(nuanxinTradeCatalog);
		return renderResult(Global.TRUE, text("删除代练类型成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<NuanxinTradeCatalog> list = nuanxinTradeCatalogService.findList(new NuanxinTradeCatalog());
		for (int i=0; i<list.size(); i++){
			NuanxinTradeCatalog e = list.get(i);
			// 过滤非正常的数据
			if (!NuanxinTradeCatalog.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getCatalogCode(), e.getCatalogName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("system:nuanxinTradeCatalog:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(NuanxinTradeCatalog nuanxinTradeCatalog){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		nuanxinTradeCatalogService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}