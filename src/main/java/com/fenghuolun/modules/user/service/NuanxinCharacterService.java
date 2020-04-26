/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.user.entity.NuanxinCharacter;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.user.dao.NuanxinCharacterDao;

/**
 * nuanxin_characterService
 * @author zhengxiaotai
 * @version 2020-03-26
 */
@Service
@Transactional(readOnly=true)
public class NuanxinCharacterService extends CrudService<NuanxinCharacterDao, NuanxinCharacter> {
	
	/**
	 * 获取单条数据
	 * @param nuanxinCharacter
	 * @return
	 */
	@Override
	public NuanxinCharacter get(NuanxinCharacter nuanxinCharacter) {
		return super.get(nuanxinCharacter);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinCharacter 查询条件
	 * @param nuanxinCharacter.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinCharacter> findPage(NuanxinCharacter nuanxinCharacter) {
		return super.findPage(nuanxinCharacter);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinCharacter
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinCharacter nuanxinCharacter) {
		super.save(nuanxinCharacter);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinCharacter
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinCharacter nuanxinCharacter) {
		super.updateStatus(nuanxinCharacter);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinCharacter
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinCharacter nuanxinCharacter) {
		super.delete(nuanxinCharacter);
	}
	
	public Map<String, Object> getByAccount(String userId, String accountId, String realmType) {
		NuanxinCharacter character = new NuanxinCharacter();
		character.setUserId(userId);
		if (realmType != null && !realmType.isEmpty()) {
			character.setCharacterRealmType(Integer.parseInt(realmType));
		}
		if (accountId != null && !accountId.isEmpty()) {
			character.setCharacterAccount(accountId);
		}
		
		List<NuanxinCharacter> list = dao.findList(character);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("list", list);
		return result;
	}
	
	public Map<String, Object> getCharacterById(String characterId) {
		NuanxinCharacter character = new NuanxinCharacter();
		character.setCharacterId(characterId);
		character = dao.get(character);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("msg", "查询成功");
		result.put("data", character);
		return result;
	}

	@Transactional(readOnly=false)
	public Map<String, Object> saveCharacter(Map<String, String[]> param) {
		NuanxinCharacter character = new NuanxinCharacter();
		character.setCharacterName(param.get("characterName")[0]);
		character.setCharacterAccount(param.get("characterAccount")[0]);
		character.setCharacterRealm(param.get("characterRealm")[0]);
		character.setCharacterRealmZone(param.get("characterRealmZone")[0]);
		character.setCharacterRealmType(Integer.parseInt(param.get("characterRealmType")[0]));
		character.setCharacterClass(Integer.parseInt(param.get("characterClass")[0]));
		character.setAllianceHorde(Integer.parseInt(param.get("allianceHorde")[0]));
		character.setCharacterSpecialization(param.get("characterSpecialization")[0]);
		character.setUserId(param.get("userId")[0]);
		String id = param.get("characterId")[0];
		if (id == null || id.isEmpty()) {
			character.setCharacterId("CHR" + System.currentTimeMillis() + StringUtil.randomStringNumberUpperCase(4));
			dao.insert(character);
		}
		else {
			character.setCharacterId(id);
			dao.update(character);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("msg", "保存成功");
		return result;
	}

	@Transactional(readOnly=false)
	public Map<String, Object> deleteCharacter(String userId, String characterId) {
		NuanxinCharacter character = new NuanxinCharacter();
		character.setCharacterId(characterId);
		character.setUserId(userId);
		dao.delete(character);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", true);
		result.put("msg", "删除成功");
		return result;
	}
}