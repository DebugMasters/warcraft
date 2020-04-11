package com.fenghuolun.modules.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuolun.modules.system.dao.NuanxinDictDataDao;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.DictData;

/**
 * nuanxin_configService
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Service
@Transactional(readOnly=true)
public class NuanxinDictDataService extends CrudService<NuanxinDictDataDao, DictData> {

	@Autowired
	private NuanxinDictDataDao nuanxinDictDataDao;

	public List<String> getSpec(String characterClass) {
		return nuanxinDictDataDao.getSpec(characterClass);
	}
}
