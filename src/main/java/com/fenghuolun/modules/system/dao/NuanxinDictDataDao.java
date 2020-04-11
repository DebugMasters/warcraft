package com.fenghuolun.modules.system.dao;

import java.util.List;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.sys.entity.DictData;

/**
 * 字典项DAO接口
 * @author zhengxiaotai
 * @version 2020-03-02
 */
@MyBatisDao
public interface NuanxinDictDataDao extends CrudDao<DictData> {
	public List<String> getSpec(String characterClass);
}
