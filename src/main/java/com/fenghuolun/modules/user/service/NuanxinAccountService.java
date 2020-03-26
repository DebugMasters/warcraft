/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.user.entity.NuanxinAccount;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.user.dao.NuanxinAccountDao;
import com.fenghuolun.modules.user.dao.NuanxinCharacterDao;

/**
 * nuanxin_accountService
 * @author zhengxiaotai
 * @version 2020-03-24
 */
@Service
@Transactional(readOnly=true)
public class NuanxinAccountService extends CrudService<NuanxinAccountDao, NuanxinAccount> {
	
	@Autowired
	private NuanxinCharacterDao nuanxinCharacterDao;
	
	/**
	 * 获取单条数据
	 * @param nuanxinAccount
	 * @return
	 */
	@Override
	public NuanxinAccount get(NuanxinAccount nuanxinAccount) {
		return super.get(nuanxinAccount);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinAccount 查询条件
	 * @param nuanxinAccount.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinAccount> findPage(NuanxinAccount nuanxinAccount) {
		return super.findPage(nuanxinAccount);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinAccount nuanxinAccount) {
		super.save(nuanxinAccount);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinAccount nuanxinAccount) {
		super.updateStatus(nuanxinAccount);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinAccount
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinAccount nuanxinAccount) {
		super.delete(nuanxinAccount);
	}
	
	public Map<String, Object> getAccountList(String userId) {
		NuanxinAccount account = new NuanxinAccount();
		account.setUserId(userId);
		List<NuanxinAccount> list = dao.findList(account);
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("accountList", list);
		return result;
	}
	
	@Transactional(readOnly=false)
	public Map<String, Object> saveAccount(String userId, String accountName) {
		Map<String, Object> result = new HashMap<>();
		try {
			NuanxinAccount account = new NuanxinAccount();
			account.setAccountId("ACC" + System.currentTimeMillis() + StringUtil.randomStringNumberUpperCase(4));
			account.setUserId(userId);
			account.setAccountName(accountName);
			dao.insert(account);
			result.put("success", true);
			result.put("msg", "插入成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "插入错误");
		}
		return result;
	}
	
	@Transactional(readOnly=false)
	public Map<String, Object> deleteAccount(String userId, String accountId) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 先删子账户下的角色
			nuanxinCharacterDao.deleteByAccountId(accountId);
			NuanxinAccount account = new NuanxinAccount();
			account.setAccountId(accountId);
			// 再删子账户
			dao.delete(account);
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "删除错误");
		}
		return result;
	}
}