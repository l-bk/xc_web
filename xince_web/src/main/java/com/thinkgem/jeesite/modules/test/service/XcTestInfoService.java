/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.test.entity.XcTestInfo;
import com.thinkgem.jeesite.modules.test.dao.XcTestInfoDao;

/**
 * 测试模块Service
 * @author lbk
 * @version 2018-02-01
 */
@Service
@Transactional(readOnly = true)
public class XcTestInfoService extends CrudService<XcTestInfoDao, XcTestInfo> {

	@Autowired
	private XcTestInfoDao infoDao;
	
	public XcTestInfo get(String id) {
		return super.get(id);
	}
	
	public List<XcTestInfo> findList(XcTestInfo xcTestInfo) {
		return super.findList(xcTestInfo);
	}
	
	public Page<XcTestInfo> findPage(Page<XcTestInfo> page, XcTestInfo xcTestInfo) {
		return super.findPage(page, xcTestInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(XcTestInfo xcTestInfo) {
		if(StringUtils.isNotBlank(xcTestInfo.getTestId())) {
			xcTestInfo.setId(xcTestInfo.getTestId());
		}
		super.save(xcTestInfo);
	}	
	
	@Transactional(readOnly = false)
	public void delete(XcTestInfo xcTestInfo) {
		super.delete(xcTestInfo);
	}

	public XcTestInfo selectByCreateTime() {
		return infoDao.selectByCreateTime();
	}
}