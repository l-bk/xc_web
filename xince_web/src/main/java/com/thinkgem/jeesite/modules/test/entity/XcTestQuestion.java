/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试问题Entity
 * @author lbk
 * @version 2018-02-04
 */
public class XcTestQuestion extends DataEntity<XcTestQuestion> {
	
	private static final long serialVersionUID = 1L;
	private String questionId;		// 唯一标识
	private String questionDetails;		// 问题内容
	private String testOptionsId;		// 选项id
	private String testId;		// 测试id
	private BigDecimal questionNum;
	private String options;//选项全部内容
	private String type;//操作类型
	private Date createTime;
	private BigDecimal skipNum;
	private String ifSkip;
	
	public XcTestQuestion() {
		super();
	}

	public XcTestQuestion(String id){
		super(id);
	}
	
	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionDetails() {
		return questionDetails;
	}

	public void setQuestionDetails(String questionDetails) {
		this.questionDetails = questionDetails;
	}

	public String getTestOptionsId() {
		return testOptionsId;
	}

	public void setTestOptionsId(String testOptionsId) {
		this.testOptionsId = testOptionsId;
	}
	
	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public BigDecimal getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(BigDecimal questionNum) {
		this.questionNum = questionNum;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getSkipNum() {
		return skipNum;
	}

	public void setSkipNum(BigDecimal skipNum) {
		this.skipNum = skipNum;
	}

	public String getIfSkip() {
		return ifSkip;
	}

	public void setIfSkip(String ifSkip) {
		this.ifSkip = ifSkip;
	}
	
	
	
}