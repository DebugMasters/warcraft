/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.order.entity.NuanxinWechatOrder;
import com.fenghuolun.modules.order.dao.NuanxinWechatOrderDao;

/**
 * 微信订单记录Service
 * @author zhengxiaotai
 * @version 2020-05-11
 */
@Service
@Transactional(readOnly=true)
public class NuanxinWechatOrderService extends CrudService<NuanxinWechatOrderDao, NuanxinWechatOrder> {
	
	/**
	 * 获取单条数据
	 * @param nuanxinWechatOrder
	 * @return
	 */
	@Override
	public NuanxinWechatOrder get(NuanxinWechatOrder nuanxinWechatOrder) {
		return super.get(nuanxinWechatOrder);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinWechatOrder 查询条件
	 * @param nuanxinWechatOrder.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinWechatOrder> findPage(NuanxinWechatOrder nuanxinWechatOrder) {
		return super.findPage(nuanxinWechatOrder);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinWechatOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinWechatOrder nuanxinWechatOrder) {
		super.save(nuanxinWechatOrder);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinWechatOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinWechatOrder nuanxinWechatOrder) {
		super.updateStatus(nuanxinWechatOrder);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinWechatOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinWechatOrder nuanxinWechatOrder) {
		super.delete(nuanxinWechatOrder);
	}
	
}