/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.type.web;

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
import com.thinkgem.jeesite.modules.pic.entity.XcPicture;
import com.thinkgem.jeesite.modules.pic.service.XcPictureService;
import com.thinkgem.jeesite.modules.test.entity.XcTestInfo;
import com.thinkgem.jeesite.modules.test.service.XcTestInfoService;
import com.thinkgem.jeesite.modules.type.entity.XcModule;
import com.thinkgem.jeesite.modules.type.service.XcModuleService;

/**
 * 模块类型Controller
 * @author lbk
 * @version 2018-02-09
 */
@Controller
@RequestMapping(value = "${adminPath}/type/xcModule")
public class XcModuleController extends BaseController {

	@Autowired
	private XcModuleService xcModuleService;
	
	@Autowired
	private XcTestInfoService testInfoService;
	
	@Autowired
	private XcPictureService picService;
	
	@ModelAttribute
	public XcModule get(@RequestParam(required=false) String id) {
		XcModule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xcModuleService.get(id);
		}
		if (entity == null){
			entity = new XcModule();
		}
		return entity;
	}
	
	@RequiresPermissions("type:xcModule:view")
	@RequestMapping(value = {"list", ""})
	public String list(XcModule xcModule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XcModule> page = xcModuleService.findPage(new Page<XcModule>(request, response), xcModule); 
		List<XcModule> list=page.getList();
		List<XcModule> newlist=new ArrayList();
		for(XcModule module:list) {
			if("0".equals(module.getModuleType())) {
				XcPicture pic=picService.get(String.valueOf(module.getPicId()));
				module.setPicName(pic.getPicName());
			}else if("1".equals(module.getModuleType())) {
				XcTestInfo testInfo=testInfoService.get(String.valueOf(module.getTestId()));
				module.setTestName(testInfo.getTestSubject());
			}
			newlist.add(module);
		}
		page.setList(newlist);
		model.addAttribute("page", page);
		return "modules/type/xcModuleList";
	}

	@RequiresPermissions("type:xcModule:view")
	@RequestMapping(value = "form")
	public String form(XcModule xcModule, Model model) {
		List<XcPicture> picList =new ArrayList();
		List<XcTestInfo> infoList=new ArrayList();
		if(StringUtils.isNotBlank(xcModule.getModuleId())) {
			xcModule=xcModuleService.get(xcModule.getModuleId());		
		}
			XcPicture pic = new XcPicture();
			pic=new XcPicture();
			pic.setPicType("2");
			pic.setPicStatus("1");
			picList=picService.findList(pic);
			XcTestInfo testInfo =new XcTestInfo();
			testInfo =new XcTestInfo();
			infoList=testInfoService.findList(testInfo);
		model.addAttribute("testInfo",infoList);
		model.addAttribute("pic",picList);
		model.addAttribute("xcModule", xcModule);
		return "modules/type/xcModuleForm";
	}

	@RequiresPermissions("type:xcModule:edit")
	@RequestMapping(value = "save")
	public String save(XcModule xcModule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xcModule)){
			return form(xcModule, model);
		}
		xcModuleService.save(xcModule);
		addMessage(redirectAttributes, "保存模块类型成功");
		return "redirect:"+Global.getAdminPath()+"/type/xcModule/?repage";
	}
	
	@RequiresPermissions("type:xcModule:edit")
	@RequestMapping(value = "delete")
	public String delete(XcModule xcModule, RedirectAttributes redirectAttributes) {
		xcModuleService.delete(xcModule);
		addMessage(redirectAttributes, "删除模块类型成功");
		return "redirect:"+Global.getAdminPath()+"/type/xcModule/?repage";
	}

}