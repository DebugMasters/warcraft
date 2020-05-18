/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.fenghuolun.modules.order.entity.NuanxinWechatOrder;

/**
 * 微信订单记录DAO接口
 * @author zhengxiaotai
 * @version 2020-05-11
 */
@MyBatisDao
public interface NuanxinWechatOrderDao extends CrudDao<NuanxinWechatOrder> {
	public long totalIncome();
}