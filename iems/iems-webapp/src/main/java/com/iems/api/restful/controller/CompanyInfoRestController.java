package com.iems.api.restful.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.UserInfo;
import com.iems.biz.model.CompanyInfoModel;
import com.iems.biz.service.ICompanyInfoService;
import com.iems.biz.service.IUserInfoService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class CompanyInfoRestController {
	private static final Log logger = LogFactory.getLog(CompanyInfoRestController.class);

	@Autowired
	private ICompanyInfoService companyInfoServiceImpl;

	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IUserInfoService userInfoServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	@RequestMapping(value = "companyInfos", method = RequestMethod.GET)
	public @ResponseBody PageResults<CompanyInfoModel> listCompanyInfo(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<CompanyInfoModel> searchConditions = new SearchConditions<CompanyInfoModel>();
		searchConditions.add(new SearchCondition("companyInfo.name", "like", name));

		PageResults<CompanyInfoModel> pageResults = companyInfoServiceImpl.getCompanyInfoModels(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "companyInfos/{id}", method = RequestMethod.GET)
	public @ResponseBody CompanyInfoModel getCompanyInfo(
			@PathVariable("id") String id) {
		logger.info("获取企业信息id=" + id);
		
		CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(id);
		
		UserInfo companyAdmin = userInfoServiceImpl.getUserInfoByCompanyid(companyInfo.getId());
		SysUser sysUser = userServiceImpl.getUser(companyAdmin.getUserid());

		CompanyInfoModel companyInfoModel = new CompanyInfoModel(sysUser, companyInfo, companyAdmin);
		
		return companyInfoModel;
	}
	
	@RequestMapping(value = "companyInfos", method = RequestMethod.POST)
	public @ResponseBody Object addCompanyInfo(
			@RequestBody CompanyInfoModel companyInfoModel) {
		logger.info("注册企业信息");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(companyInfoModel);
			
			userServiceImpl.addUser(companyInfoModel.getSysUser());
			userInfoServiceImpl.addUserInfo(companyInfoModel.getCompanyAdmin());
			
			companyInfoServiceImpl.addCompanyInfo(companyInfoModel.getCompanyInfo());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "注册企业信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "companyInfos/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateCompanyInfo(
			@PathVariable("id") String id, 
			@RequestBody CompanyInfoModel companyInfoModel) {
		logger.info("更新企业信息id=" + id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(companyInfoModel);
			
			CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(id);

			companyInfo.setName(companyInfoModel.getCompanyInfo().getName());
			// TODO: 
			companyInfo.setType(companyInfoModel.getCompanyInfo().getType());
			companyInfo.setAddress(companyInfoModel.getCompanyInfo().getAddress());
			companyInfo.setTelephone(companyInfoModel.getCompanyInfo().getTelephone());
			companyInfo.setFax(companyInfoModel.getCompanyInfo().getFax());
			
			companyInfoServiceImpl.updateCompanyInfo(companyInfo);
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "更新企业信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "companyInfos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(
			@PathVariable("id") String id) {
		logger.info("删除企业信息id=" + id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(id);
			
			UserInfo companyAdmin = userInfoServiceImpl.getUserInfoByCompanyid(companyInfo.getId());
			
			userInfoServiceImpl.deleteUserInfo(companyAdmin.getId());
			userServiceImpl.deleteUser(companyAdmin.getUserid());
	
			companyInfoServiceImpl.deleteCompanyInfo(companyInfo.getId());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "删除企业信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(CompanyInfoModel companyInfoModel) throws Exception {
		if (companyInfoModel == null || companyInfoModel.getCompanyInfo() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (StringUtils.isEmpty(companyInfoModel.getCompanyInfo().getName())) {
			throw new Exception("企业名称不能为空");
		}
		
		if (companyInfoModel.getIsNew()) {
			companyInfoModel.getCompanyInfo().setId(CounterUtil.increment(CompanyInfo.class.getName()));
			
			if (companyInfoModel.getSysUser().getPassword() == null || companyInfoModel.getSysUser().getPassword().length() <= 0){
				throw new Exception("密码不能为空");
			}
			if (companyInfoModel.getPasscheck() == null || companyInfoModel.getPasscheck().length() <= 0){
				throw new Exception("确认密码不能为空");
			}
			if (!companyInfoModel.getSysUser().getPassword().equals(companyInfoModel.getPasscheck())){
				throw new Exception("密码与确认密码不一致");
			}
			
			companyInfoModel.getCompanyAdmin().setId(CounterUtil.increment(UserInfo.class.getName()));
			companyInfoModel.getSysUser().setUserid(CounterUtil.increment(SysUser.class.getName()));

			companyInfoModel.getCompanyAdmin().setUserid(companyInfoModel.getSysUser().getUserid());
			companyInfoModel.getCompanyAdmin().setCompanyid(companyInfoModel.getCompanyInfo().getId());

			// Usertype
			companyInfoModel.getSysUser().setUsertype(UserInfo.class.getName());
			
			// Password
			if (companyInfoModel.getSysUser().getPassword() != null && companyInfoModel.getSysUser().getPassword().length() > 0){
				Object salt = null;
	
				if (this.saltSource != null) {
					salt = this.saltSource.getSalt(companyInfoModel.getSysUser());
				}
				companyInfoModel.getSysUser().setPassword(
					passwordEncoder.encodePassword(companyInfoModel.getSysUser().getPassword(), salt)
				);
			}
			
		} else {
			
		}

		return true;
	}


}
