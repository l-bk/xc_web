/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 测试答案Entity
 * @author lbk
 * @version 2018-02-02
 */
public class XcTestAnswer extends DataEntity<XcTestAnswer> {
	
	private static final long serialVersionUID = 1L;
	private String answerId;		// 唯一标识
	private String answerPic;		// 答案图片
	private String answerSketch;		// 答案简述
	private String answerKeyword;		// 答案关键字
	private String answerDetails;		// 答案详情
	private BigDecimal answerPointGt;		// 总分数大于
	private BigDecimal answerPointLt;		// 总分数小于
	private String answerQRCode;		// 二维码
	private String testId;		// 测试id
	private String delFlag;
	private Date createTime;
	
	private String testType;
	public XcTestAnswer() {
		super();
	}

	public XcTestAnswer(String id){
		super(id);
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
	public String getAnswerPic() {
		return answerPic;
	}

	public void setAnswerPic(String answerPic) {
		this.answerPic = answerPic;
	}
	
	public String getAnswerSketch() {
		return answerSketch;
	}

	public void setAnswerSketch(String answerSketch) {
		this.answerSketch = answerSketch;
	}
	
	public String getAnswerKeyword() {
		return answerKeyword;
	}

	public void setAnswerKeyword(String answerKeyword) {
		this.answerKeyword = answerKeyword;
	}
	
	@Length(min=0, max=1024, message="答案详情长度必须介于 0 和 1024 之间")
	public String getAnswerDetails() {
		return answerDetails;
	}

	public void setAnswerDetails(String answerDetails) {
		this.answerDetails = answerDetails;
	}
	
	public BigDecimal getAnswerPointLt() {
		return answerPointLt;
	}

	public void setAnswerPointLt(BigDecimal answerPointLt) {
		this.answerPointLt = answerPointLt;
	}

	public BigDecimal getAnswerPointGt() {
		return answerPointGt;
	}

	public void setAnswerPointGt(BigDecimal answerPointGt) {
		this.answerPointGt = answerPointGt;
	}
	
	public String getAnswerQRCode() {
		return answerQRCode;
	}

	public void setAnswerQRCode(String answerQRCode) {
		this.answerQRCode = answerQRCode;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	
	
}