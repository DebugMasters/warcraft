/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.system.entity.NuanxinArticle;
import com.fenghuolun.modules.system.dao.NuanxinArticleDao;

/**
 * nuanxin_articleService
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Service
@Transactional(readOnly=true)
public class NuanxinArticleService extends CrudService<NuanxinArticleDao, NuanxinArticle> {
	
	/**
	 * 获取单条数据
	 * @param nuanxinArticle
	 * @return
	 */
	@Override
	public NuanxinArticle get(NuanxinArticle nuanxinArticle) {
		return super.get(nuanxinArticle);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinArticle 查询条件
	 * @param nuanxinArticle.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinArticle> findPage(NuanxinArticle nuanxinArticle) {
		return super.findPage(nuanxinArticle);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinArticle
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinArticle nuanxinArticle) {
		super.save(nuanxinArticle);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinArticle
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinArticle nuanxinArticle) {
		super.updateStatus(nuanxinArticle);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinArticle
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinArticle nuanxinArticle) {
		super.delete(nuanxinArticle);
	}
	
}