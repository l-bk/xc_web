/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.type.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.type.entity.XcModule;

/**
 * 模块类型DAO接口
 * @author lbk
 * @version 2018-02-09
 */
@MyBatisDao
public interface XcModuleDao extends CrudDao<XcModule> {
	
}