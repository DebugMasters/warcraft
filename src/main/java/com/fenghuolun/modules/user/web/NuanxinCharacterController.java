/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.web;

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
import com.fenghuolun.modules.user.entity.NuanxinCharacter;
import com.fenghuolun.modules.user.service.NuanxinCharacterService;

/**
 * nuanxin_characterController
 * @author zhengxiaotai
 * @version 2020-03-26
 */
@Controller
@RequestMapping(value = "${adminPath}/user/nuanxinCharacter")
public class NuanxinCharacterController extends BaseController {

	@Autowired
	private NuanxinCharacterService nuanxinCharacterService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public NuanxinCharacter get(Integer characterId, boolean isNewRecord) {
		return nuanxinCharacterService.get(characterId + "", isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("user:nuanxinCharacter:view")
	@RequestMapping(value = {"list", ""})
	public String list(NuanxinCharacter nuanxinCharacter, Model model) {
		model.addAttribute("nuanxinCharacter", nuanxinCharacter);
		return "modules/user/nuanxinCharacterList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("user:nuanxinCharacter:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<NuanxinCharacter> listData(NuanxinCharacter nuanxinCharacter, HttpServletRequest request, HttpServletResponse response) {
		nuanxinCharacter.setPage(new Page<>(request, response));
		Page<NuanxinCharacter> page = nuanxinCharacterService.findPage(nuanxinCharacter);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("user:nuanxinCharacter:view")
	@RequestMapping(value = "form")
	public String form(NuanxinCharacter nuanxinCharacter, Model model) {
		model.addAttribute("nuanxinCharacter", nuanxinCharacter);
		return "modules/user/nuanxinCharacterForm";
	}

	/**
	 * 保存角色信息
	 */
	@RequiresPermissions("user:nuanxinCharacter:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated NuanxinCharacter nuanxinCharacter) {
		nuanxinCharacterService.save(nuanxinCharacter);
		return renderResult(Global.TRUE, text("保存角色信息成功！"));
	}
	
	/**
	 * 删除角色信息
	 */
	@RequiresPermissions("user:nuanxinCharacter:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(NuanxinCharacter nuanxinCharacter) {
		nuanxinCharacterService.delete(nuanxinCharacter);
		return renderResult(Global.TRUE, text("删除角色信息成功！"));
	}
	
}