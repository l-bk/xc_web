/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 图片模块Entity
 * @author lbk
 * @version 2018-01-30
 */
public class XcPicture extends DataEntity<XcPicture> {
	
	private static final long serialVersionUID = 1L;
	private String picId;		// 唯一标识
	private String picPath;		// 图片路径
	private String picType;		// 图片类型: 1.轮播图 ，2.二维码
	private String picStatus;	//图片状态 1 上架  2 下架
	private String picName;
	
	public XcPicture() {
		super();
	}

	public XcPicture(String id){
		super(id);
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	
	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getPicStatus() {
		return picStatus;
	}

	public void setPicStatus(String picStatus) {
		this.picStatus = picStatus;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	
}