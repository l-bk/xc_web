/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.pic.entity.XcPicture;
import com.thinkgem.jeesite.modules.pic.dao.XcPictureDao;

/**
 * 图片模块Service
 * @author lbk
 * @version 2018-01-30
 */
@Service
@Transactional(readOnly = true)
public class XcPictureService extends CrudService<XcPictureDao, XcPicture> {

	public XcPicture get(String id) {
		return super.get(id);
	}
	
	public List<XcPicture> findList(XcPicture xcPicture) {
		return super.findList(xcPicture);
	}
	
	public Page<XcPicture> findPage(Page<XcPicture> page, XcPicture xcPicture) {
		return super.findPage(page, xcPicture);
	}
	
	@Transactional(readOnly = false)
	public void save(XcPicture xcPicture) {
		if(StringUtils.isNotBlank(xcPicture.getPicId())){
			xcPicture.setId(xcPicture.getPicId());
		}
		super.save(xcPicture);
	}
	
	@Transactional(readOnly = false)
	public void delete(XcPicture xcPicture) {
		super.delete(xcPicture);
	}
	
}