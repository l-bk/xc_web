/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

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
import com.thinkgem.jeesite.modules.pic.entity.XcPicture;
import com.thinkgem.jeesite.modules.pic.service.XcPictureService;
import com.thinkgem.jeesite.modules.test.entity.XcTestInfo;
import com.thinkgem.jeesite.modules.test.entity.XcTestQuestion;
import com.thinkgem.jeesite.modules.test.service.XcTestAnswerService;
import com.thinkgem.jeesite.modules.test.service.XcTestInfoService;
import com.thinkgem.jeesite.modules.test.service.XcTestOptionsService;
import com.thinkgem.jeesite.modules.test.service.XcTestQuestionService;
import com.thinkgem.jeesite.modules.type.entity.XcModule;
import com.thinkgem.jeesite.modules.type.service.XcModuleService;

/**
 * 测试模块Controller
 * @author lbk
 * @version 2018-02-01
 */
@Controller
@RequestMapping(value = "${adminPath}/test/xcTestInfo")
public class XcTestInfoController extends BaseController {

	@Autowired
	private XcTestInfoService xcTestInfoService;
	
	@Autowired
	private XcTestAnswerService answerService;
	
	@Autowired 
	private XcTestQuestionService questionService;
	
	@Autowired
	private XcTestOptionsService optionsService;
	
	@Autowired
	private XcPictureService xcPicService;
	
	@Autowired
	private XcModuleService moduleService;
	
	@ModelAttribute
	public XcTestInfo get(@RequestParam(required=false) String id) {
		XcTestInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xcTestInfoService.get(id);
		}
		if (entity == null){
			entity = new XcTestInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("test:xcTestInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(XcTestInfo xcTestInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XcTestInfo> page = xcTestInfoService.findPage(new Page<XcTestInfo>(request, response), xcTestInfo); 
		model.addAttribute("page", page);
		return "modules/test/xcTestInfoList";
	}

	@RequiresPermissions("test:xcTestInfo:view")
	@RequestMapping(value = "form")
	public String form(XcTestInfo xcTestInfo, Model model) {
		if(StringUtils.isNotBlank(xcTestInfo.getTestId())) {
			xcTestInfo=xcTestInfoService.get(xcTestInfo.getTestId());
		}
		model.addAttribute("xcTestInfo", xcTestInfo);
//		if("0".equals(xcTestInfo.getTestType())){
			return "modules/test/xcTestInfoForm";
//		}else if ("1".equals(xcTestInfo.getTestType())){
//			return "module/test/xcTestInfoSkipForm";
//		}
//		return null;
	}

	@RequiresPermissions("test:xcTestInfo:edit")
	@RequestMapping(value = "save")
	public String save(XcTestInfo xcTestInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xcTestInfo)){
			return form(xcTestInfo, model);
		}
		xcTestInfoService.save(xcTestInfo);
		if(StringUtils.isNotBlank(xcTestInfo.getTestId())){
			addMessage(redirectAttributes, "修改信息成功");
			return "redirect:"+Global.getAdminPath()+"/test/xcTestInfo/?repage";
		}else{
			xcTestInfo=xcTestInfoService.selectByCreateTime();
			addMessage(redirectAttributes, "保存测试信息成功,请添加问题及选项");
			String s=xcTestInfo.getTestType();
			return "redirect:"+Global.getAdminPath()+"/test/xcTestQuestion/form?testType="+xcTestInfo.getTestType()+"&testId="+xcTestInfo.getTestId();
		}
	}
	
	@RequiresPermissions("test:xcTestInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(XcTestInfo xcTestInfo, RedirectAttributes redirectAttributes) {
		XcModule module =new XcModule();
		module.setTestId(Integer.valueOf(xcTestInfo.getTestId()));
		List<XcModule>moduleList=moduleService.findList(module);
		XcPicture xcPic =new XcPicture();
		xcPic.setTestId(xcTestInfo.getTestId());
		List<XcPicture> picList=xcPicService.findList(xcPic);
		String msgStr="删除失败！该测试数据被";
		if(moduleList.size() >0 ){
			msgStr += " 模块信息 ";
		}
		if(picList.size() > 0 ){
			if(moduleList.size()>0){
				msgStr += " 和 图片信息 ";
			}else{
				msgStr += " 图片信息 ";
			}
		}
		msgStr += "所关联,请修改对应信息再执行删除操作";
		if(moduleList.size() == 0 && picList.size() == 0){
		xcTestInfoService.delete(xcTestInfo);
		XcTestQuestion question =new XcTestQuestion();
		question.setTestId(xcTestInfo.getTestId());
		List<XcTestQuestion> questionList= questionService.findList(question);
		for(XcTestQuestion ques :questionList){
			XcTestQuestion newQues =new XcTestQuestion();
			newQues.setQuestionId(ques.getQuestionId());
			questionService.delete(newQues);
			optionsService.deleteByQuestionId(ques.getQuestionId());
			
		}
		
		answerService.deleteByTestId(xcTestInfo.getTestId());
		msgStr="删除测试数据成功！";
		}
		addMessage(redirectAttributes, msgStr);
		return "redirect:"+Global.getAdminPath()+"/test/xcTestInfo/?repage";
	}

}