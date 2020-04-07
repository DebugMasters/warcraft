/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

import com.fenghuolun.modules.user.entity.NuanxinUser;

/**
 * nuanxin_userDAO接口
 * @author zhengxiaotai
 * @version 2020-03-09
 */
@MyBatisDao
public interface NuanxinUserDao extends CrudDao<NuanxinUser> {
	public NuanxinUser getByWxOpenId(String wxOpenid);
	public int updateAccountInfo(NuanxinUser user);
}