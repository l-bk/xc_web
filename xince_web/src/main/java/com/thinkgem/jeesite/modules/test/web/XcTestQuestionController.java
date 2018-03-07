/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.test.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
 * 测试问题Controller
 * @author lbk
 * @version 2018-02-04
 */
@Controller
@RequestMapping(value = "${adminPath}/test/xcTestQuestion")
public class XcTestQuestionController extends BaseController {

	@Autowired
	private XcTestQuestionService xcTestQuestionService;


	@Autowired
	private XcTestOptionsService optionsService;

	@ModelAttribute
	public XcTestQuestion get(@RequestParam(required=false) String id) {
		XcTestQuestion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xcTestQuestionService.get(id);
		}
		if (entity == null){
			entity = new XcTestQuestion();
		}
		return entity;
	}

	@RequiresPermissions("test:xcTestQuestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(XcTestQuestion xcTestQuestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XcTestQuestion> page = xcTestQuestionService.findPage(new Page<XcTestQuestion>(request, response), xcTestQuestion); 
		List<XcTestQuestion> list=page.getList();
		List<XcTestQuestion> newList = new ArrayList();
		for(XcTestQuestion question:list){
			XcTestOptions options=new XcTestOptions();
			options.setTestQuestionId(question.getQuestionId());
			List<XcTestOptions> optionList=optionsService.findList(options);
			String str="";
			for(int i=0;i<optionList.size();i++) {
//				if("0".equals(optionList.get(i).getIfSkip())){
					str +=optionList.get(i).getOptionsKeyword() + ":"+optionList.get(i).getOptionsDetails() +
							"(" +optionList.get(i).getOptionsPoint()+"分)";
					
//				}else if ("1".equals(optionList.get(i).getIfSkip())){
//					XcTestQuestion newQues=xcTestQuestionService.get(String.valueOf(optionList.get(i).getSkipQuestionId()));
//					str +=optionList.get(i).getOptionsKeyword() + ":跳转到第"+newQues.getQuestionNum()+"题";
//				}
				if(i != optionList.size()-1) {
					str += "<br/>";
				}
			}
			if(!"A".equals(str)) {
				question.setOptions(str);
			}
			newList.add(question);
		}
		page.setList(newList);
		model.addAttribute("page", page);
		return "modules/test/xcTestQuestionList";
	}

	@RequiresPermissions("test:xcTestQuestion:view")
	@RequestMapping(value = "form")
	public String form(XcTestQuestion xcTestQuestion, Model model) {
		if(StringUtils.isNotBlank(xcTestQuestion.getQuestionId())) {
			xcTestQuestion=xcTestQuestionService.get(xcTestQuestion.getQuestionId());
		}
		int allNum=xcTestQuestionService.selectCount(xcTestQuestion.getTestId());
		model.addAttribute("xcTestQuestion", xcTestQuestion);
		model.addAttribute("allNum",allNum);
		return "modules/test/xcTestQuestionForm";
	}

	@RequiresPermissions("test:xcTestQuestion:edit")
	@RequestMapping(value = "save")
	public String save( XcTestQuestion xcTestQuestion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xcTestQuestion)){
			return form(xcTestQuestion, model);
		}
		if(StringUtils.isBlank(xcTestQuestion.getQuestionId())) {//保存

			xcTestQuestionService.save(xcTestQuestion);
			XcTestQuestion newQuestion=xcTestQuestionService.selectNew(xcTestQuestion);

			if(StringUtils.isNotBlank(xcTestQuestion.getOptions())) {
				String allOptions[] =xcTestQuestion.getOptions().split(",");
				for(String str:allOptions) {
					String oneOptions[] = str.split("-");
					XcTestOptions options= new XcTestOptions();
					options.setOptionsKeyword(oneOptions[0]); //编号
					options.setIfSkip(oneOptions[1]);//是否跳题
					if("0".equals(oneOptions[1])){ //不跳题
						options.setOptionsDetails(oneOptions[4]);
					}else if ("1" .equals(oneOptions[1])){//跳题
						XcTestQuestion newQues=new XcTestQuestion();
						newQues.setTestId(xcTestQuestion.getTestId());
						newQues.setQuestionNum(Integer.valueOf(oneOptions[2]));
						XcTestQuestion ques=xcTestQuestionService.selectByQuesNumAndTestId(newQues);
						options.setSkipQuestionId(Integer.valueOf(ques.getQuestionId()));
						options.setOptionsDetails(oneOptions[4]);
					}
					options.setOptionsPoint(oneOptions[3]);
					options.setTestQuestionId(newQuestion.getQuestionId());
					optionsService.save(options);
				}
			}
			if("keepSave".equals(xcTestQuestion.getType())) { //继续添加选项
				addMessage(redirectAttributes, "保存成功，继续添加问题");
				return "redirect:"+Global.getAdminPath()+"/test/xcTestQuestion/form?testId="+xcTestQuestion.getTestId();
			}else if("save".equals(xcTestQuestion.getType())) {//跳到添加结果页
				addMessage(redirectAttributes, "保存成功，添加结果");
				return "redirect:"+Global.getAdminPath()+"/test/xcTestAnswer/form?testId="+xcTestQuestion.getTestId();
			}else {
				addMessage(redirectAttributes, "保存成功");
				return "redirect:"+Global.getAdminPath()+"/test/xcTestQuestion/?repage&testId="+xcTestQuestion.getTestId();
			}
		}else {//修改
			xcTestQuestionService.save(xcTestQuestion);
			return "redirect:"+Global.getAdminPath()+"/test/xcTestQuestion/?repage&testId="+xcTestQuestion.getTestId();
		}
	}

	@RequiresPermissions("test:xcTestQuestion:edit")
	@RequestMapping(value = "delete")
	public String delete(XcTestQuestion xcTestQuestion, RedirectAttributes redirectAttributes) {
		XcTestQuestion newQue=xcTestQuestionService.get(xcTestQuestion.getQuestionId());
		xcTestQuestionService.delete(xcTestQuestion);
		optionsService.deleteByQuestionId(xcTestQuestion.getQuestionId());
		if(StringUtils.isNotBlank(xcTestQuestion.getQuestionId())) {
			XcTestQuestion newQues=new XcTestQuestion();
			newQues.setTestId(newQue.getTestId());
			List<XcTestQuestion> quesList = xcTestQuestionService.findList(newQues);
			for(XcTestQuestion ques : quesList) {
				if(ques.getQuestionNum() >newQue.getQuestionNum()) {
					ques.setQuestionNum(ques.getQuestionNum()-1);
					xcTestQuestionService.updateQuestion(ques);
				}
			}
		}
		addMessage(redirectAttributes, "删除测试问题成功");
		return "redirect:"+Global.getAdminPath()+"/test/xcTestQuestion/?repage&testId="+xcTestQuestion.getTestId();
	}

}