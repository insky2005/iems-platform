package com.iems.core.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.NameValuePair;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iems.core.util.HttpClientUtil;

public abstract class AbstractWebController {
	
	@RequestMapping(value="/index")
	public String index(){
		return forward();
	}
	
	@RequestMapping(value="/list")
	public String list(){
		return forward();
	}
	
	@RequestMapping(value="/add")
	public String add(){
		return forward();
	}

	@RequestMapping(value="/edit")
	public String edit(){
		return forward();
	}
	
	@RequestMapping(value="/oauth2api")
	public String oauth2api(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		
		// oauth2
		String api_url = request.getParameter("api_url");
		//String access_token = request.getParameter("access_token");
		
		NameValuePair[] data = null;
		
		
		String resp = HttpClientUtil.get(api_url, data);
		
		
		return forward();
	}


	
	protected final String forward() {
		return "";
	}

	protected final String forward(String view) {
		return view;
	}

	protected final String forwardError() {
		return "/template/error";
	}
}
