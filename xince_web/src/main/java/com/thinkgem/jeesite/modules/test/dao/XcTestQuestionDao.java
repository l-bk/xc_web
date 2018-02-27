/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.dao;

import java.math.BigDecimal;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.test.entity.XcTestQuestion;

/**
 * 测试问题DAO接口
 * @author lbk
 * @version 2018-02-04
 */
@MyBatisDao
public interface XcTestQuestionDao extends CrudDao<XcTestQuestion> {
	int selectCount (String testId);
	XcTestQuestion selectNew(XcTestQuestion xcTestQuestion);
	XcTestQuestion selectByQuesNumAndTestId(XcTestQuestion xcTestQuestion);
	Integer selectMaxQuestionNum(String testId);
}