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
import com.thinkgem.jeesite.modules.test.entity.XcTestOptions;
import com.thinkgem.jeesite.modules.test.dao.XcTestOptionsDao;

/**
 * 测试选项Service
 * @author lbk
 * @version 2018-02-02
 */
@Service
@Transactional(readOnly = true)
public class XcTestOptionsService extends CrudService<XcTestOptionsDao, XcTestOptions> {
	
	@Autowired
	private XcTestOptionsDao optionsDao;

	public XcTestOptions get(String id) {
		return super.get(id);
	}
	
	public List<XcTestOptions> findList(XcTestOptions xcTestOptions) {
		return super.findList(xcTestOptions);
	}
	
	public Page<XcTestOptions> findPage(Page<XcTestOptions> page, XcTestOptions xcTestOptions) {
		return super.findPage(page, xcTestOptions);
	}
	
	@Transactional(readOnly = false)
	public void save(XcTestOptions xcTestOptions) {
		if(StringUtils.isNotBlank(xcTestOptions.getOptionsId())) {
			xcTestOptions.setId(xcTestOptions.getOptionsId());
		}
		super.save(xcTestOptions);
	}
	
	@Transactional(readOnly = false)
	public void delete(XcTestOptions xcTestOptions) {
		super.delete(xcTestOptions);
	}
	
	public int selectCount(String testQuestionId) {
		return optionsDao.selectCount(testQuestionId);
	}
}