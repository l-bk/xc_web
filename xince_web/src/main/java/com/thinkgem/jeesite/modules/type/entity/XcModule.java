/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.type.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 模块类型Entity
 * @author lbk
 * @version 2018-02-09
 */
public class XcModule extends DataEntity<XcModule> {
	
	private static final long serialVersionUID = 1L;
	private String moduleId;		// 唯一id，模块id
	private String moduleName;		// 模块名
	private String moduleType;		// 模块类型,0.二维码，1.测试数据
	private Integer testId;		// 测试数据id
	private Integer picId;		// 图片id
	private String moduleStatus;		// 模块状态 0.下架 1.上架
	private String modulePic;
	
	private String testName;
	private String picName;
	
	
	public XcModule() {
		super();
	}

	public XcModule(String id){
		super(id);
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public String getModuleType() {
		return moduleType;
	}

	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public Integer getPicId() {
		return picId;
	}

	public void setPicId(Integer picId) {
		this.picId = picId;
	}
	
	public String getModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(String moduleStatus) {
		this.moduleStatus = moduleStatus;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getModulePic() {
		return modulePic;
	}

	public void setModulePic(String modulePic) {
		this.modulePic = modulePic;
	}
	
	
	
}