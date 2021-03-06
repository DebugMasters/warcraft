/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

import java.util.List;

import com.fenghuolun.modules.order.entity.NuanxinOrder;

/**
 * nuanxin_orderDAO接口
 * @author zhengxiaotai
 * @version 2020-04-13
 */
@MyBatisDao
public interface NuanxinOrderDao extends CrudDao<NuanxinOrder> {
	public List<NuanxinOrder> orderList(NuanxinOrder order);
	public long todaysOrder();
	public List<NuanxinOrder> countByType(NuanxinOrder order);
	public List<NuanxinOrder> countByMonth(NuanxinOrder order);
}