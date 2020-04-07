/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.service.TreeService;
import com.fenghuolun.modules.system.entity.NuanxinTradeCatalog;
import com.fenghuolun.modules.system.dao.NuanxinTradeCatalogDao;

/**
 * nuanxin_trade_catalogService
 * @author zhengxiaotai
 * @version 2020-04-07
 */
@Service
@Transactional(readOnly=true)
public class NuanxinTradeCatalogService extends TreeService<NuanxinTradeCatalogDao, NuanxinTradeCatalog> {
	
	/**
	 * 获取单条数据
	 * @param nuanxinTradeCatalog
	 * @return
	 */
	@Override
	public NuanxinTradeCatalog get(NuanxinTradeCatalog nuanxinTradeCatalog) {
		return super.get(nuanxinTradeCatalog);
	}
	
	/**
	 * 查询列表数据
	 * @param nuanxinTradeCatalog
	 * @return
	 */
	@Override
	public List<NuanxinTradeCatalog> findList(NuanxinTradeCatalog nuanxinTradeCatalog) {
		return super.findList(nuanxinTradeCatalog);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinTradeCatalog
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinTradeCatalog nuanxinTradeCatalog) {
		super.save(nuanxinTradeCatalog);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinTradeCatalog
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinTradeCatalog nuanxinTradeCatalog) {
		super.updateStatus(nuanxinTradeCatalog);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinTradeCatalog
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinTradeCatalog nuanxinTradeCatalog) {
		super.delete(nuanxinTradeCatalog);
	}
	
	public List<NuanxinTradeCatalog> getByParentId(String parentId, int catalogType) {
		if (parentId == null || parentId.isEmpty()) {
			parentId = "0";
		}
		NuanxinTradeCatalog catalog = new NuanxinTradeCatalog();
		catalog.setParentCode(parentId);
		catalog.setCatalogType(catalogType);
		List<NuanxinTradeCatalog> list = dao.findList(catalog);
		return list;
	}
}