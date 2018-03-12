/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试模块Entity
 * @author lbk
 * @version 2018-02-01
 */
public class XcTestInfo extends DataEntity<XcTestInfo> {
	
	private static final long serialVersionUID = 1L;
	private String testId;		// 唯一标识
	private String testTitle;		// 测试标题
	private String testSubject;		// 测试主题
	private String testPic;		// 测试图片
	private String testType;		// 测试类型  0分数类型  1.跳题类型
	private String payFlag;		// 付费标记 1.免费，2.付费
	private String testPresentation;		// 测试介绍
	private String testNum;		// 题目数
	private String testUseTime;		// 预计用时
	private BigDecimal testPrice;		// 原价
	private BigDecimal testPreferentialPrice;		// 优惠价
	private String testReportLength;		// 报告长度
	private String testPrompt;		// 温馨提示
	private Date createTime;
	private String ifShow;
	private String testUseNum;
	private String testLogo;
	private String  rewardNum;
	
	public XcTestInfo() {
		super();
	}

	public XcTestInfo(String id){
		super(id);
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}
	
	public String getTestTitle() {
		return testTitle;
	}

	public void setTestTitle(String testTitle) {
		this.testTitle = testTitle;
	}
	
	public String getTestSubject() {
		return testSubject;
	}

	public void setTestSubject(String testSubject) {
		this.testSubject = testSubject;
	}
	
	public String getTestPic() {
		return testPic;
	}

	public void setTestPic(String testPic) {
		this.testPic = testPic;
	}
	
	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public String getPayFlag() {
		return payFlag;
	}

	public void setPayFlag(String payFlag) {
		this.payFlag = payFlag;
	}
	public String getTestPresentation() {
		return testPresentation;
	}

	public void setTestPresentation(String testPresentation) {
		this.testPresentation = testPresentation;
	}
	
	public String getTestNum() {
		return testNum;
	}

	public void setTestNum(String testNum) {
		this.testNum = testNum;
	}
	
	public String getTestUseTime() {
		return testUseTime;
	}

	public void setTestUseTime(String testUseTime) {
		this.testUseTime = testUseTime;
	}
	
	public BigDecimal getTestPrice() {
		return testPrice;
	}

	public void setTestPrice(BigDecimal testPrice) {
		this.testPrice = testPrice;
	}

	public BigDecimal getTestPreferentialPrice() {
		return testPreferentialPrice;
	}

	public void setTestPreferentialPrice(BigDecimal testPreferentialPrice) {
		this.testPreferentialPrice = testPreferentialPrice;
	}

	public String getTestReportLength() {
		return testReportLength;
	}

	public void setTestReportLength(String testReportLength) {
		this.testReportLength = testReportLength;
	}
	
	public String getTestPrompt() {
		return testPrompt;
	}

	public void setTestPrompt(String testPrompt) {
		this.testPrompt = testPrompt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIfShow() {
		return ifShow;
	}

	public void setIfShow(String ifShow) {
		this.ifShow = ifShow;
	}

	public void setTestUseNum(String testUseNum) {
		this.testUseNum = testUseNum;
	}

	public String getTestUseNum() {
		return testUseNum;
	}

	public String getTestLogo() {
		return testLogo;
	}

	public void setTestLogo(String testLogo) {
		this.testLogo = testLogo;
	}

	public String getRewardNum() {
		return rewardNum;
	}

	public void setRewardNum(String rewardNum) {
		this.rewardNum = rewardNum;
	}
	
	
	
}