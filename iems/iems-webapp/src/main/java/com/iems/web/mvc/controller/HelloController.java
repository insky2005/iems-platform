package com.iems.web.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iems.core.service.IUserService;
import com.iems.core.util.CounterUtil;
import com.iems.core.web.controller.AbstractWebController;

@Controller
@RequestMapping(value="/hello")
public class HelloController extends AbstractWebController {
	
	@Autowired
	private IUserService userServiceImpl;
	
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView("/hello/hello","msg","SpringMvc, Hello World!");
		return mav;
	}
	
	
	@RequestMapping(value="/hello")
	public String hello(ModelMap model){
		model.addAttribute("msg", "SpringMvc, <font color='red'>Hello World</font>!");
		
		//System.out.println(CounterUtil.increment());

		return forward();
	}
	
}