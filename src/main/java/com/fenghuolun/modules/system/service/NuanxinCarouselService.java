/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.system.entity.NuanxinCarousel;
import com.fenghuolun.modules.system.dao.NuanxinCarouselDao;

/**
 * nuanxin_carouselService
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Service
@Transactional(readOnly=true)
public class NuanxinCarouselService extends CrudService<NuanxinCarouselDao, NuanxinCarousel> {
	
	/**
	 * 获取单条数据
	 * @param nuanxinCarousel
	 * @return
	 */
	@Override
	public NuanxinCarousel get(NuanxinCarousel nuanxinCarousel) {
		return super.get(nuanxinCarousel);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinCarousel 查询条件
	 * @param nuanxinCarousel.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinCarousel> findPage(NuanxinCarousel nuanxinCarousel) {
		return super.findPage(nuanxinCarousel);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinCarousel
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinCarousel nuanxinCarousel) {
		super.save(nuanxinCarousel);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinCarousel
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinCarousel nuanxinCarousel) {
		super.updateStatus(nuanxinCarousel);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinCarousel
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinCarousel nuanxinCarousel) {
		super.delete(nuanxinCarousel);
	}
	
}