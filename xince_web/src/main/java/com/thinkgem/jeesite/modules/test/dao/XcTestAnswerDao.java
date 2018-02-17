/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.XcTestAnswer;

/**
 * 测试答案DAO接口
 * @author lbk
 * @version 2018-02-02
 */
@MyBatisDao
public interface XcTestAnswerDao extends CrudDao<XcTestAnswer> {
	XcTestAnswer selectNew(XcTestAnswer xcTestAnswer);
	int deleteByTestId(String testId);
}