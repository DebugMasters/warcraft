package com.fenghuolun.modules.system.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.Config;

/**
 * nuanxin_configDAO接口
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@MyBatisDao
public interface NuanxinConfigDao extends CrudDao<Config> {
	public Config getByConfigKey(String key);
}
