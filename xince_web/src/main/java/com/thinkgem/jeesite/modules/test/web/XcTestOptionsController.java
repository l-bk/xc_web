/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.test.entity.XcTestOptions;
import com.thinkgem.jeesite.modules.test.entity.XcTestQuestion;
import com.thinkgem.jeesite.modules.test.service.XcTestOptionsService;
import com.thinkgem.jeesite.modules.test.service.XcTestQuestionService;

/**
 * 测试选项Controller
 * @author lbk
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${adminPath}/test/xcTestOptions")
public class XcTestOptionsController extends BaseController {

	@Autowired
	private XcTestOptionsService xcTestOptionsService;
	
	@Autowired
	private XcTestQuestionService questionService;
	
	
	
	@ModelAttribute
	public XcTestOptions get(@RequestParam(required=false) String id) {
		XcTestOptions entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xcTestOptionsService.get(id);
		}
		if (entity == null){
			entity = new XcTestOptions();
		}
		return entity;
	}
	
	@RequiresPermissions("test:xcTestOptions:view")
	@RequestMapping(value = {"list", ""})
	public String list(XcTestOptions xcTestOptions, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XcTestOptions> page = xcTestOptionsService.findPage(new Page<XcTestOptions>(request, response), xcTestOptions); 
		List<XcTestOptions> list=page.getList();
		List<XcTestOptions> newList=new ArrayList();
		for(XcTestOptions option:list){
			if(option.getSkipQuestionId() != null && !"".equals(option.getSkipQuestionId())){
				XcTestQuestion ques=questionService.get(String.valueOf(option.getSkipQuestionId()));
				option.setSkipNum(ques.getQuestionNum());
			}
			newList.add(option);
		}
		page.setList(newList);
		model.addAttribute("page", page);
		return "modules/test/xcTestOptionsList";
	}

	@RequiresPermissions("test:xcTestOptions:view")
	@RequestMapping(value = "form")
	public String form(XcTestOptions xcTestOptions, Model model) {
		if(StringUtils.isNoneBlank(xcTestOptions.getOptionsId())) {
			xcTestOptions=xcTestOptionsService.get(xcTestOptions.getOptionsId());
		}
		int num=xcTestOptionsService.selectCount(xcTestOptions.getTestQuestionId());
		model.addAttribute("num",num);
		XcTestQuestion ques = questionService.get(xcTestOptions.getTestQuestionId());
		List<Integer> quesNum= new ArrayList();
		if(ques != null){
			XcTestQuestion newQues =new XcTestQuestion();
			newQues.setTestId(ques.getTestId());
			List<XcTestQuestion> quesList=questionService.findList(newQues);
			for(XcTestQuestion question:quesList){
				if(question.getQuestionNum().compareTo(ques.getQuestionNum())==0){
					continue;
				}
				quesNum.add(question.getQuestionNum().intValue());
			}
		}
		model.addAttribute("quesNum",quesNum);
		model.addAttribute("xcTestOptions", xcTestOptions);
		return "modules/test/xcTestOptionsForm";
	}

	@RequiresPermissions("test:xcTestOptions:edit")
	@RequestMapping(value = "save")
	public String save(XcTestOptions xcTestOptions, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xcTestOptions)){
			return form(xcTestOptions, model);
		}
		if("1".equals(xcTestOptions.getIfSkip())){
			XcTestQuestion xcTestQuestion = questionService.get(xcTestOptions.getTestQuestionId());
			XcTestQuestion ques= new XcTestQuestion();
			ques.setTestId(xcTestQuestion.getTestId());
			ques.setQuestionNum(xcTestOptions.getSkipNum());
			XcTestQuestion newQues=questionService.selectByQuesNumAndTestId(ques);
			xcTestOptions.setSkipQuestionId(Integer.valueOf(newQues.getQuestionId()));
		}
		xcTestOptionsService.save(xcTestOptions);
		addMessage(redirectAttributes, "保存测试选项成功");
		return "redirect:"+Global.getAdminPath()+"/test/xcTestOptions/?repage&testQuestionId=" +xcTestOptions.getTestQuestionId();
	}
	
	@RequiresPermissions("test:xcTestOptions:edit")
	@RequestMapping(value = "delete")
	public String delete(XcTestOptions xcTestOptions, RedirectAttributes redirectAttributes) {
		xcTestOptionsService.delete(xcTestOptions);
		addMessage(redirectAttributes, "删除测试选项成功");
		return "redirect:"+Global.getAdminPath()+"/test/xcTestOptions/?repage&testQuestionId=" +xcTestOptions.getTestQuestionId();
	}

}