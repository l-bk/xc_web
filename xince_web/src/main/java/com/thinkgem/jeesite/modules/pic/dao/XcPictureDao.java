/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.pic.entity.XcPicture;

/**
 * 图片模块DAO接口
 * @author lbk
 * @version 2018-01-30
 */
@MyBatisDao
public interface XcPictureDao extends CrudDao<XcPicture> {
	
}