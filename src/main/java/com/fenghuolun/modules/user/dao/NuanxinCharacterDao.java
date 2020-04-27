/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;

import java.util.List;

import com.fenghuolun.modules.user.entity.NuanxinCharacter;

/**
 * nuanxin_characterDAO接口
 * @author zhengxiaotai
 * @version 2020-03-26
 */
@MyBatisDao
public interface NuanxinCharacterDao extends CrudDao<NuanxinCharacter> {
	public int deleteByAccountId(String accountId);
	public List<NuanxinCharacter> findListNew(NuanxinCharacter nuanxinCharacter);
}