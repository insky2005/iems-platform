package com.iems.core.web.servlet.view;

import java.lang.reflect.Method;

import org.springframework.ui.ExtendedModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import com.iems.core.util.ConfigUtil;

public class DefaultModelAndViewResolver implements ModelAndViewResolver {
	public static final String SUCCESS = "";

	public ModelAndView resolveModelAndView(Method handlerMethod,
			Class handlerType, Object returnValue,
			ExtendedModelMap implicitModel, NativeWebRequest webRequest) {
		
		String ctx = webRequest.getContextPath();
		implicitModel.addAttribute("ctx", ctx);
		
		String systemName = ConfigUtil.getSysConfig().getSystemname();
		implicitModel.addAttribute("systemName", systemName);
		
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
			
			String handlerTypePackageName = handlerType.getPackage().getName();System.out.println("handlerTypePackageName=="+handlerTypePackageName);
			
			String[] viewFolders = handlerTypePackageName.split("\\.");
			String viewFolder = "";
			for (int i=viewFolders.length-1; i>=0; i--) {
				if ("controller".equals(viewFolders[i])) {
					break;
				}
				String vf = viewFolders[i];
				if ("front".equals(vf)) {
					viewFolder = "html/" + viewFolder;
				}
				viewFolder = vf + "/" + viewFolder;
			}
			
			String handlerTypeName = StringUtils.uncapitalize(handlerType
					.getSimpleName());
			int index = handlerTypeName.lastIndexOf("Controller");
			if (index != -1) {
				handlerTypeName = handlerTypeName.substring(0, index);
			}
			
			view = viewFolder + handlerTypeName + "/" + view;
			/*
			 * Package p = handlerType.getPackage(); if (null != p) { view =
			 * p.getName().replaceAll("\\.", "/") + "/" + view; }
			 */
			return new ModelAndView(view).addAllObjects(implicitModel);
		}

		return ModelAndViewResolver.UNRESOLVED;
	}
}
