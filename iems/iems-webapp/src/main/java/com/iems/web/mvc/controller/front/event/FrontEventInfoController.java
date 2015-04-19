package com.iems.web.mvc.controller.front.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iems.core.web.controller.AbstractWebController;

@Controller
@RequestMapping(value="/front/event/eventInfo")
public class FrontEventInfoController extends AbstractWebController {
	@RequestMapping(value="/index")
	public String index(){
		return forward();
	}

}