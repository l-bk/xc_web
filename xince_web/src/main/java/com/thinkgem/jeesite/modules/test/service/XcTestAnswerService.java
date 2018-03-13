/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.XcTestAnswer;
import com.thinkgem.jeesite.modules.test.dao.XcTestAnswerDao;

/**
 * 测试答案Service
 * @author lbk
 * @version 2018-02-02
 */
@Service
@Transactional(readOnly = true)
public class XcTestAnswerService extends CrudService<XcTestAnswerDao, XcTestAnswer> {

	@Autowired 
	private XcTestAnswerDao answerDao;
	
	public XcTestAnswer get(String id) {
		return super.get(id);
	}
	
	public List<XcTestAnswer> findList(XcTestAnswer xcTestAnswer) {
		return super.findList(xcTestAnswer);
	}
	
	public Page<XcTestAnswer> findPage(Page<XcTestAnswer> page, XcTestAnswer xcTestAnswer) {
		return super.findPage(page, xcTestAnswer);
	}
	
	@Transactional(readOnly = false)
	public void save(XcTestAnswer xcTestAnswer) {
		if(StringUtils.isNotBlank(xcTestAnswer.getAnswerId())){
			xcTestAnswer.setId(xcTestAnswer.getAnswerId());
		}
		if(StringUtils.isNoneBlank(xcTestAnswer.getAnswerNum())){
			xcTestAnswer.setAnswerNum(xcTestAnswer.getAnswerNum().toUpperCase());
		}
		super.save(xcTestAnswer);
	}
	
	@Transactional(readOnly = false)
	public void delete(XcTestAnswer xcTestAnswer) {
		super.delete(xcTestAnswer);
	}
	
	public XcTestAnswer selectNew(XcTestAnswer xcTestAnswer){
		return answerDao.selectNew(xcTestAnswer);
	}
	
	@Transactional(readOnly=false)
	public int deleteByTestId(String testId){
		return answerDao.deleteByTestId(testId);
	}
}