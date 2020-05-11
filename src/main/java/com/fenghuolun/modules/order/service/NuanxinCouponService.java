/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.order.entity.NuanxinCoupon;
import com.fenghuolun.modules.user.dao.NuanxinUserDao;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.order.dao.NuanxinCouponDao;

/**
 * nuanxin_couponService
 * @author zhengxiaotai
 * @version 2020-05-05
 */
@Service
@Transactional(readOnly=true)
public class NuanxinCouponService extends CrudService<NuanxinCouponDao, NuanxinCoupon> {
	
	@Autowired
	private NuanxinUserDao nuanxinUserDao;
	
	/**
	 * 获取单条数据
	 * @param nuanxinCoupon
	 * @return
	 */
	@Override
	public NuanxinCoupon get(NuanxinCoupon nuanxinCoupon) {
		return super.get(nuanxinCoupon);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinCoupon 查询条件
	 * @param nuanxinCoupon.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinCoupon> findPage(NuanxinCoupon nuanxinCoupon) {
		return super.findPage(nuanxinCoupon);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinCoupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinCoupon nuanxinCoupon) {
		if (nuanxinCoupon.getCouponId() == null || nuanxinCoupon.getCouponId().isEmpty()) {
			String couponId = "CPN" + System.currentTimeMillis() + StringUtil.randomStringNumberUpperCase(4);
			nuanxinCoupon.setCouponId(couponId);
			super.insert(nuanxinCoupon);
		}
		else {
			super.update(nuanxinCoupon);
		}
	}
	
	/**
	 * 更新状态
	 * @param nuanxinCoupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinCoupon nuanxinCoupon) {
		super.updateStatus(nuanxinCoupon);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinCoupon
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinCoupon nuanxinCoupon) {
		super.delete(nuanxinCoupon);
	}

	@Transactional(readOnly=false)
	public void addCouponAll(NuanxinCoupon coupon) {
		NuanxinUser user = new NuanxinUser();
		user.setUserStatus(1);
		List<NuanxinUser> list = nuanxinUserDao.findList(user);
		for (NuanxinUser u : list) {
			coupon.setCouponId("CPN" + System.currentTimeMillis() + StringUtil.randomStringNumberUpperCase(4));
			coupon.setUserId(u.getUserId());
			dao.insert(coupon);
		}
	}
}