package com.saml.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController {
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	@RequestMapping("/projectManage")
	public ModelAndView projectManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/demand/projectManage");
		return mav;
	}
	@RequestMapping("/envBasicTypeManage")
	public ModelAndView envBasicTypeManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/envBasicTypeManage");
		return mav;
	}
	@RequestMapping("/envExtendedTypeManage")
	public ModelAndView envExtendedTypeManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/envExtendedTypeManage");
		return mav;
	}
	@RequestMapping("/typeInterfaceManage")
	public ModelAndView typeInterfaceManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/typeInterfaceManage");
		return mav;
	}
	@RequestMapping("/componentManage")
	public ModelAndView componentManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/componentManage");
		return mav;
	}
	@RequestMapping("/containerManage")
	public ModelAndView containerManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/containerManage");
		return mav;
	}
	@RequestMapping("/frameManage")
	public ModelAndView frameManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/frameManage");
		return mav;
	}
	@RequestMapping("/frameworkManage")
	public ModelAndView frameworkManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/modeling/frameworkManage");
		return mav;
	}
	@RequestMapping("/originalDemandManage")
	public ModelAndView originalDemandManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/demand/originalDemandManage");
		return mav;
	}
	@RequestMapping("/demandAnalysisManage")
	public ModelAndView demandAnalysisManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/demand/demandAnalysisManage");
		return mav;
	}
	@RequestMapping("/structureVe")
	public ModelAndView structureVe(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/verification/structureVe");
		return mav;
	}
	@RequestMapping("/processVe")
	public ModelAndView processVe(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/verification/processVe");
		return mav;
	}
	@RequestMapping("/result")
	public ModelAndView result(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/result");
		return mav;
	}
	@RequestMapping("/demandBasedStrManage")
	public ModelAndView demandBasedStrManage(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/demand/demandBasedStrManage");
		return mav;
	}
	
}
