/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.system.entity.NuanxinRealmList;
import com.fenghuolun.modules.system.dao.NuanxinRealmListDao;

/**
 * nuanxin_realm_listService
 * @author zhengxiaotai
 * @version 2020-03-26
 */
@Service
@Transactional(readOnly=true)
public class NuanxinRealmListService extends CrudService<NuanxinRealmListDao, NuanxinRealmList> {
	
	/**
	 * 获取单条数据
	 * @param nuanxinRealmList
	 * @return
	 */
	@Override
	public NuanxinRealmList get(NuanxinRealmList nuanxinRealmList) {
		return super.get(nuanxinRealmList);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinRealmList 查询条件
	 * @param nuanxinRealmList.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinRealmList> findPage(NuanxinRealmList nuanxinRealmList) {
		return super.findPage(nuanxinRealmList);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinRealmList
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinRealmList nuanxinRealmList) {
		super.save(nuanxinRealmList);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinRealmList
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinRealmList nuanxinRealmList) {
		super.updateStatus(nuanxinRealmList);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinRealmList
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinRealmList nuanxinRealmList) {
		super.delete(nuanxinRealmList);
	}
	
}