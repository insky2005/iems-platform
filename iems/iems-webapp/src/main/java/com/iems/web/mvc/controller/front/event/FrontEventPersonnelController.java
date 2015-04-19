package com.iems.web.mvc.controller.front.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iems.core.web.controller.AbstractWebController;

@Controller
@RequestMapping(value="/front/event/eventPersonnel")
public class FrontEventPersonnelController extends AbstractWebController {
	@RequestMapping(value="/register")
	public String register(){
		return forward();
	}
}