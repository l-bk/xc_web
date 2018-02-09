/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.type.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.type.entity.XcModule;
import com.thinkgem.jeesite.modules.type.dao.XcModuleDao;

/**
 * 模块类型Service
 * @author lbk
 * @version 2018-02-09
 */
@Service
@Transactional(readOnly = true)
public class XcModuleService extends CrudService<XcModuleDao, XcModule> {

	public XcModule get(String id) {
		return super.get(id);
	}
	
	public List<XcModule> findList(XcModule xcModule) {
		return super.findList(xcModule);
	}
	
	public Page<XcModule> findPage(Page<XcModule> page, XcModule xcModule) {
		return super.findPage(page, xcModule);
	}
	
	@Transactional(readOnly = false)
	public void save(XcModule xcModule) {
		if(StringUtils.isNoneBlank(xcModule.getModuleId())) {
			xcModule.setId(xcModule.getModuleId());
		}
		super.save(xcModule);
	}
	
	@Transactional(readOnly = false)
	public void delete(XcModule xcModule) {
		super.delete(xcModule);
	}
	
}