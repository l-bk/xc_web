/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

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
import com.thinkgem.jeesite.modules.test.entity.XcTestAnswer;
import com.thinkgem.jeesite.modules.test.service.XcTestAnswerService;

/**
 * 测试答案Controller
 * @author lbk
 * @version 2018-02-02
 */
@Controller
@RequestMapping(value = "${adminPath}/test/xcTestAnswer")
public class XcTestAnswerController extends BaseController {

	@Autowired
	private XcTestAnswerService xcTestAnswerService;
	
	@ModelAttribute
	public XcTestAnswer get(@RequestParam(required=false) String id) {
		XcTestAnswer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xcTestAnswerService.get(id);
		}
		if (entity == null){
			entity = new XcTestAnswer();
		}
		return entity;
	}
	
	@RequiresPermissions("test:xcTestAnswer:view")
	@RequestMapping(value = {"list", ""})
	public String list(XcTestAnswer xcTestAnswer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XcTestAnswer> page = xcTestAnswerService.findPage(new Page<XcTestAnswer>(request, response), xcTestAnswer); 
		model.addAttribute("page", page);
		model.addAttribute("testType",xcTestAnswer.getTestType());
		return "modules/test/xcTestAnswerList";
	}

	@RequiresPermissions("test:xcTestAnswer:view")
	@RequestMapping(value = "form")
	public String form(XcTestAnswer xcTestAnswer, Model model) {
		String testType = xcTestAnswer.getTestType();
		if(StringUtils.isNotBlank(xcTestAnswer.getAnswerId())){
			xcTestAnswer=xcTestAnswerService.get(xcTestAnswer.getAnswerId());
			xcTestAnswer.setTestType(testType);
		}
		model.addAttribute("xcTestAnswer", xcTestAnswer);
		return "modules/test/xcTestAnswerForm";
	}

	@RequiresPermissions("test:xcTestAnswer:edit")
	@RequestMapping(value = "save")
	public String save(XcTestAnswer xcTestAnswer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xcTestAnswer)){
			return form(xcTestAnswer, model);
		}
		xcTestAnswerService.save(xcTestAnswer);
		addMessage(redirectAttributes, "保存测试答案成功");
		return "redirect:"+Global.getAdminPath()+"/test/xcTestAnswer/?repage&testId="+xcTestAnswer.getTestId()+"&testType="+xcTestAnswer.getTestType();
	}
	
	@RequiresPermissions("test:xcTestAnswer:edit")
	@RequestMapping(value = "delete")
	public String delete(XcTestAnswer xcTestAnswer, RedirectAttributes redirectAttributes) {
		xcTestAnswerService.delete(xcTestAnswer);
		addMessage(redirectAttributes, "删除测试答案成功");
		return "redirect:"+Global.getAdminPath()+"/test/xcTestAnswer/?repage&testId="+xcTestAnswer.getTestId()+"&testType="+xcTestAnswer.getTestType();
	}

}