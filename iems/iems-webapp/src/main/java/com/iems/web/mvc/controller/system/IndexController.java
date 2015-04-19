package com.iems.web.mvc.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iems.core.web.controller.AbstractWebController;

@Controller
@RequestMapping(value="/system/index")
public class IndexController extends AbstractWebController {
	
	@RequestMapping(value="/index")
	public String index(){
		return forward();
	}

}