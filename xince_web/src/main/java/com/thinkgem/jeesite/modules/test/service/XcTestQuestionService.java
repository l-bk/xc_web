/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.test.entity.XcTestQuestion;
import com.thinkgem.jeesite.modules.test.dao.XcTestQuestionDao;

/**
 * 测试问题Service
 * @author lbk
 * @version 2018-02-04
 */
@Service
@Transactional(readOnly = true)
public class XcTestQuestionService extends CrudService<XcTestQuestionDao, XcTestQuestion> {

	@Autowired
	private XcTestQuestionDao questionDao;
	
	public XcTestQuestion get(String id) {
		return super.get(id);
	}
	
	public List<XcTestQuestion> findList(XcTestQuestion xcTestQuestion) {
		return super.findList(xcTestQuestion);
	}
	
	public Page<XcTestQuestion> findPage(Page<XcTestQuestion> page, XcTestQuestion xcTestQuestion) {
		return super.findPage(page, xcTestQuestion);
	}
	
	@Transactional(readOnly = false)
	public void save(XcTestQuestion xcTestQuestion) {
		if(StringUtils.isBlank(xcTestQuestion.getQuestionId())) {
			Integer num=questionDao.selectMaxQuestionNum(xcTestQuestion.getTestId());
			int number=1;
			if(num != null) {
				number=num.intValue()+1;
			}
			xcTestQuestion.setQuestionNum(number);
		}else{
			xcTestQuestion.setId(xcTestQuestion.getQuestionId());
		}
		
		super.save(xcTestQuestion);
	}
	
	@Transactional(readOnly = false)
	public void delete(XcTestQuestion xcTestQuestion) {
		super.delete(xcTestQuestion);
	}
	
	public XcTestQuestion selectNew(XcTestQuestion xcTestQuestion){
		return questionDao.selectNew(xcTestQuestion);
	}
	
	@Transactional(readOnly= false)
	public int selectCount(String testId){
		return questionDao.selectCount(testId);
	}
	
	@Transactional(readOnly=false)
	public XcTestQuestion selectByQuesNumAndTestId(XcTestQuestion xcTestQuestion){
		return questionDao.selectByQuesNumAndTestId(xcTestQuestion);
	}
	
	@Transactional(readOnly = false)
	public void updateQuestion(XcTestQuestion xcTestQustion) {
		questionDao.update(xcTestQustion);
	}
}