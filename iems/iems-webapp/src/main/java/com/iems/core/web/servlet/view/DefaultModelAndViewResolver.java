package com.iems.core.web.servlet.view;

import java.lang.reflect.Method;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

public class DefaultModelAndViewResolver implements ModelAndViewResolver {
	public static final String SUCCESS = "";

	public ModelAndView resolveModelAndView(Method handlerMethod,
			Class handlerType, Object returnValue,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
		if ((returnValue instanceof String)) {
			String returnValStr = (String) returnValue;
			if (returnValStr.startsWith("/")) {
				return new ModelAndView(returnValStr)
						.addAllObjects(implicitModel);
			}
			String view = null;
			if (returnValStr == null || returnValStr.length() <= 0)
				view = handlerMethod.getName();
			else {
				view = returnValStr;
			}
			String handlerTypeName = StringUtils.uncapitalize(handlerType
					.getSimpleName());
			int index = handlerTypeName.lastIndexOf("Controller");
			if (index != -1) {
				handlerTypeName = handlerTypeName.substring(0, index);
			}
			view = handlerTypeName + "/" + view;
			/*
			 * Package p = handlerType.getPackage(); if (null != p) { view =
			 * p.getName().replaceAll("\\.", "/") + "/" + view; }
			 */
			return new ModelAndView(view).addAllObjects(implicitModel);
		}

		return ModelAndViewResolver.UNRESOLVED;
	}
}
