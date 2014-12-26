package com.iems.core.web.controller;

public abstract class AbstractWebController {
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
