package com.iems.core.util;

import com.iems.core.entity.SysConfig;

public class ConfigUtil {
	
	
	public static SysConfig getSysConfig() {
		SysConfig sysConfig = new SysConfig();
		sysConfig.setConfigid("1");
		sysConfig.setSystemcode("iEvent");
		sysConfig.setSystemname("iEvent");
		
		return sysConfig;
	}

}
