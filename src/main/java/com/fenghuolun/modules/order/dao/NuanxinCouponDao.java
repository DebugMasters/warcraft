/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

import java.util.List;

import com.fenghuolun.modules.order.entity.NuanxinCoupon;

/**
 * nuanxin_couponDAO接口
 * @author zhengxiaotai
 * @version 2020-05-02
 */
@MyBatisDao
public interface NuanxinCouponDao extends CrudDao<NuanxinCoupon> {
	public List<NuanxinCoupon> getByUser(String userId);
}