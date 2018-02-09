/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.XcTestOptions;

/**
 * 测试选项DAO接口
 * @author lbk
 * @version 2018-02-02
 */
@MyBatisDao
public interface XcTestOptionsDao extends CrudDao<XcTestOptions> {
	int selectCount(String testQuestionId);
}