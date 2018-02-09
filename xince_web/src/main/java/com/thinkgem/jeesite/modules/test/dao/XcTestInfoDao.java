/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.XcTestInfo;

/**
 * 测试模块DAO接口
 * @author lbk
 * @version 2018-02-01
 */
@MyBatisDao
public interface XcTestInfoDao extends CrudDao<XcTestInfo> {
	public XcTestInfo selectByCreateTime();
}