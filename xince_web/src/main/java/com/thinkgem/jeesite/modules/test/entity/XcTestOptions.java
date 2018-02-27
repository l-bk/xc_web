/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试选项Entity
 * @author lbk
 * @version 2018-02-02
 */
public class XcTestOptions extends DataEntity<XcTestOptions> {
	
	private static final long serialVersionUID = 1L;
	private String optionsId;		// options_id
	private String optionsKeyword;		// 选项关键字
	private String optionsDetails;		// 选项内容
	private String optionsPoint;		// 选项分数
	private String testQuestionId;		// 问题id
	private Date createTime;
	private Integer num;
	private String ifSkip;
	private Integer skipQuestionId;
	private Integer skipNum;
	
	public XcTestOptions() {
		super();
	}

	public XcTestOptions(String id){
		super(id);
	}

	public String getOptionsId() {
		return optionsId;
	}

	public void setOptionsId(String optionsId) {
		this.optionsId = optionsId;
	}
	
	public String getOptionsKeyword() {
		return optionsKeyword;
	}

	public void setOptionsKeyword(String optionsKeyword) {
		this.optionsKeyword = optionsKeyword;
	}
	
	public String getOptionsDetails() {
		return optionsDetails;
	}

	public void setOptionsDetails(String optionsDetails) {
		this.optionsDetails = optionsDetails;
	}
	
	public String getOptionsPoint() {
		return optionsPoint;
	}

	public void setOptionsPoint(String optionsPoint) {
		this.optionsPoint = optionsPoint;
	}
	
	public String getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(String testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getIfSkip() {
		return ifSkip;
	}

	public void setIfSkip(String ifSkip) {
		this.ifSkip = ifSkip;
	}

	public Integer getSkipQuestionId() {
		return skipQuestionId;
	}

	public void setSkipQuestionId(Integer skipQuestionId) {
		this.skipQuestionId = skipQuestionId;
	}

	public Integer getSkipNum() {
		return skipNum;
	}

	public void setSkipNum(Integer skipNum) {
		this.skipNum = skipNum;
	}

	
	
	
}