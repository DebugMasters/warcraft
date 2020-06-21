/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.order.entity.NuanxinCoupon;
import com.fenghuolun.modules.order.entity.NuanxinOrder;
import com.fenghuolun.modules.user.dao.NuanxinCharacterDao;
import com.fenghuolun.modules.user.dao.NuanxinUserDao;
import com.fenghuolun.modules.user.entity.NuanxinCharacter;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.utils.WechatUtil;
import com.fenghuolun.modules.order.dao.NuanxinCouponDao;
import com.fenghuolun.modules.order.dao.NuanxinOrderDao;

/**
 * nuanxin_orderService
 * @author zhengxiaotai
 * @version 2020-04-13
 */
@Service
@Transactional(readOnly=true)
public class NuanxinOrderService extends CrudService<NuanxinOrderDao, NuanxinOrder> {
	
	@Autowired
	private NuanxinUserDao nuanxinUserDao;
	@Autowired
	private NuanxinCharacterDao nuanxinCharacterDao;
	@Autowired
	private NuanxinCouponDao nuanxinCouponDao;
	
	/**
	 * 获取单条数据
	 * @param nuanxinOrder
	 * @return
	 */
	@Override
	public NuanxinOrder get(NuanxinOrder nuanxinOrder) {
		return super.get(nuanxinOrder);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinOrder 查询条件
	 * @param nuanxinOrder.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinOrder> findPage(NuanxinOrder nuanxinOrder) {
		return super.findPage(nuanxinOrder);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinOrder nuanxinOrder) {
		super.save(nuanxinOrder);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinOrder nuanxinOrder) {
		super.updateStatus(nuanxinOrder);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinOrder
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinOrder nuanxinOrder) {
		super.delete(nuanxinOrder);
	}
	
	@Transactional(readOnly=false)
	public Map<String, Object> saveOrder(Map<String, String[]> param) {
		Map<String, Object> result = new HashMap<>();
		String characterId = param.get("characterId")[0];
		NuanxinCharacter character = new NuanxinCharacter();
		character.setCharacterId(characterId);
		character = nuanxinCharacterDao.get(character);
		if (character == null) {
			result.put("success", false);
			result.put("msg", "角色信息错误");
			return result;
		}
		
		NuanxinOrder order = new NuanxinOrder();
		order.setUserId(param.get("userId")[0]);
		order.setOrderType(Integer.parseInt(param.get("orderType")[0]));
		order.setOrderServer(Integer.parseInt(param.get("orderServer")[0]));
		order.setOrderCatalog(param.get("orderCatalog")[0]);
		order.setOrderCatalog1(param.get("orderCatalog1")[0]);
		order.setOrderCatalog2(param.get("orderCatalog2")[0]);
		order.setOrderCatalog3(param.get("orderCatalog3")[0]);
		order.setOrderMoney(Double.parseDouble(param.get("orderMoney")[0]));
		order.setOrderStatus(0);
		order.setCharacterId(param.get("characterId")[0]);
		order.setCharacterInfo(param.get("characterInfo")[0]);
		order.setCharacterRealm(param.get("characterRealm")[0]);
		order.setCharacterSpec(param.get("characterSpec")[0]);
		order.setCreateTime(new Date());
		order.setAccountId(param.get("accountId")[0]);
		order.setAccountPassword(param.get("accountPassword")[0]);
		order.setSaveguard(Integer.parseInt(param.get("saveguard")[0]));
		order.setPhone(param.get("phone")[0]);
		order.setNote(param.get("note")[0]);
		
		String orderId = param.get("orderId")[0];
		if (orderId != null && !orderId.isEmpty()) {
			// 有订单号的需要更新
			order.setOrderId(orderId);
			dao.update(order);
		}
		else {
			// 没有订单号的需要新增
			order.setOrderId("ODR" + System.currentTimeMillis() + StringUtil.randomStringNumberUpperCase(4));
			dao.insert(order);
			
			// 优惠券状态
			String couponId = param.get("couponId")[0];
			if (couponId != null && !couponId.isEmpty()) {
				NuanxinCoupon coupon = new NuanxinCoupon();
				coupon.setCouponId(couponId);
				coupon = nuanxinCouponDao.get(coupon);
				if (coupon != null) {
					coupon.setOrderId(order.getOrderId());
					coupon.setCouponStatus(2);
					nuanxinCouponDao.update(coupon);
				}
			}
		}
		
		NuanxinUser user = new NuanxinUser();
		user.setUserId(param.get("userId")[0]);
		user = nuanxinUserDao.get(user);
		String openId = user.getWxOpenid();
		String remoteAddr = param.get("remoteAddr")[0];
		Map<String, Object> payInfo = WechatUtil.unifiedOrder(openId, Integer.parseInt(param.get("orderMoney")[0]), 
				remoteAddr, user.getUserId() + "_" + order.getOrderId());
		
		result.put("success", true);
		result.put("msg", "下单成功");
		result.put("payInfo", payInfo);
		return result;
	}
	
	public Map<String, Object> orderList(String userId, String orderStatus) {
		Map<String, Object> result = new HashMap<>();
		NuanxinOrder order = new NuanxinOrder();
		order.setUserId(userId);
		if (orderStatus != null && !orderStatus.isEmpty()) {
			order.setOrderStatus(Integer.parseInt(orderStatus));
		}
		List<NuanxinOrder> list = dao.orderList(order);
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("orderList", list);
		return result;
	}
	
	public Map<String, Object> todaysOrder() {
		Map<String, Object> result = new HashMap<>();
		long count = dao.todaysOrder();
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("count", count);
		return result;
	}
	
	public Map<String, Object> availableCoupon(String userId) {
		Map<String, Object> result = new HashMap<>();
		List<NuanxinCoupon> list = nuanxinCouponDao.getByUser(userId);
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("couponList", list);
		return result;
	}
	
	public Map<String, Object> orderDetail(String userId, String orderId) {
		Map<String, Object> result = new HashMap<>();
		NuanxinOrder order = new NuanxinOrder();
		order.setUserId(userId);
		order.setOrderId(orderId);
		List<NuanxinOrder> list = dao.findList(order);
		if (list == null || list.size() == 0) {
			result.put("success", false);
			result.put("msg", "订单未找到");
			return result;
		}
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("data", list.get(0));
		return result;
	}

	@Transactional(readOnly=false)
	public Map<String, Object> cancelOrder(String userId, String orderId) {
		Map<String, Object> result = new HashMap<>();
		NuanxinOrder order = new NuanxinOrder();
		order.setUserId(userId);
		order.setOrderId(orderId);
		order = dao.get(order);
		if (order == null) {
			result.put("success", false);
			result.put("msg", "订单未找到");
			return result;
		}
		order.setOrderStatus(-1);
		dao.update(order);
		result.put("success", true);
		result.put("msg", "取消成功");
		return result;
	}
	
	public List<NuanxinOrder> countByType(Date startTime) {
		NuanxinOrder order = new NuanxinOrder();
		order.setCreateTime(startTime);
		List<NuanxinOrder> list = dao.countByType(order);
		return list;
	}
	
	public List<NuanxinOrder> countByMonth(Date startTime) {
		NuanxinOrder order = new NuanxinOrder();
		order.setCreateTime(startTime);
		List<NuanxinOrder> list = dao.countByMonth(order);
		return list;
	}
}