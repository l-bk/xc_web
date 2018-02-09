/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.pic.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

/**
 * 图片模块Controller
 * @author lbk
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/pic/xcPicture")
public class XcPictureController extends BaseController {

	@Autowired
	private XcPictureService xcPictureService;
	
	@ModelAttribute
	public XcPicture get(@RequestParam(required=false) String id) {
		XcPicture entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xcPictureService.get(id);
		}
		if (entity == null){
			entity = new XcPicture();
		}
		return entity;
	}
	
	@RequiresPermissions("pic:xcPicture:view")
	@RequestMapping(value = {"list", ""})
	public String list(XcPicture xcPicture, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<XcPicture> page = xcPictureService.findPage(new Page<XcPicture>(request, response), xcPicture); 
		model.addAttribute("page", page);
		if(StringUtils.isNotBlank(xcPicture.getPicType())) {
			model.addAttribute("picType",xcPicture.getPicType());
		}
		return "modules/pic/xcPictureList";
	}

	@RequiresPermissions("pic:xcPicture:view")
	@RequestMapping(value = "form")
	public String form(XcPicture xcPicture, Model model) {
		model.addAttribute("xcPicture", xcPicture);
		return "modules/pic/xcPictureForm";
	}

	@RequiresPermissions("pic:xcPicture:edit")
	@RequestMapping(value = "save")
	public String save(XcPicture xcPicture, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xcPicture)){
			return form(xcPicture, model);
		}
		xcPictureService.save(xcPicture);
		addMessage(redirectAttributes, "保存图片成功");
		return "redirect:"+Global.getAdminPath()+"/pic/xcPicture/?repage&picType="+xcPicture.getPicType();
	}
	
	@RequiresPermissions("pic:xcPicture:edit")
	@RequestMapping(value = "delete")
	public String delete(XcPicture xcPicture, RedirectAttributes redirectAttributes) throws IOException {
//		InputStream inStream =getClass().getResourceAsStream("/jeesite.properties");  
//		Properties pro= new Properties();
//		pro.load(inStream);
//		String basedir = pro.getProperty("userfiles.basedir");
		XcPicture newPic=xcPictureService.get(xcPicture.getPicId());
//		String fileName=newPic.getPicPath().replace("/xince_web", basedir);
//		String fileName=newPic.getPicPath().replace("/xince_web", "F:/pic");
//		File file=new File(fileName);
//		if(file.delete()) {
			xcPictureService.delete(xcPicture);
			addMessage(redirectAttributes, "删除图片成功");
//		}else {
//			addMessage(redirectAttributes,"删除图片失败");
//		}
		return "redirect:"+Global.getAdminPath()+"/pic/xcPicture/?repage&picType="+xcPicture.getPicType();
	}
	
	
	@RequiresPermissions("pic:xcPicture:edit")
	@RequestMapping(value="updateStatus")
	public String updateStatus(HttpServletRequest request,XcPicture xcPicture,Model model, RedirectAttributes redirectAttributes) {
		if(xcPicture != null & StringUtils.isNotBlank(xcPicture.getPicStatus())) {
			if(StringUtils.isNotBlank(xcPicture.getPicId())){
				xcPicture.setId(xcPicture.getPicId());
			}
		}
		xcPictureService.save(xcPicture);
		return "redirect:"+Global.getAdminPath()+"/pic/xcPicture/?repage&picType="+xcPicture.getPicType();
	}
	
}