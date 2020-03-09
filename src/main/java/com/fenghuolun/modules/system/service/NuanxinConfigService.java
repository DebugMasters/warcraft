package com.fenghuolun.modules.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuolun.modules.system.dao.NuanxinConfigDao;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.Config;

/**
 * nuanxin_configService
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@Service
@Transactional(readOnly=true)
public class NuanxinConfigService extends CrudService<NuanxinConfigDao, Config> {

	@Autowired
	private NuanxinConfigDao nuanxinConfigDao;

	public Config getByConfigKey(String key) {
		return nuanxinConfigDao.getByConfigKey(key);
	}
}
