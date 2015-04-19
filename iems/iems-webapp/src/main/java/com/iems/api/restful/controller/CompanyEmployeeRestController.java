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

import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.model.CompanyEmployeeModel;
import com.iems.biz.service.ICompanyEmployeeService;
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
public class CompanyEmployeeRestController {
	private static final Log logger = LogFactory.getLog(CompanyEmployeeRestController.class);

	@Autowired
	private IUserService userServiceImpl;
	@Autowired
	private IUserInfoService userInfoServiceImpl;

	@Autowired
	private ICompanyInfoService companyInfoServiceImpl;
	@Autowired
	private ICompanyEmployeeService companyEmployeeServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	@RequestMapping(value = "companyEmployees", method = RequestMethod.GET)
	public @ResponseBody PageResults<CompanyEmployeeModel> listCompanyEmployee(
			@RequestParam(value = "companyid", required = true) String companyid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<CompanyEmployeeModel> searchConditions = new SearchConditions<CompanyEmployeeModel>();
		searchConditions.add(new SearchCondition("companyEmployee.companyid", "=", companyid));
		searchConditions.add(new SearchCondition("companyEmployee.name", "like", name));

		PageResults<CompanyEmployeeModel> pageResults = companyEmployeeServiceImpl.getCompanyEmployeeModels(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "companyEmployees/{id}", method = RequestMethod.GET)
	public @ResponseBody CompanyEmployeeModel getCompanyEmployee(
			@PathVariable("id") String id) {
		logger.info("获取企业员工信息id=" + id);
		
		CompanyEmployee companyEmployee = companyEmployeeServiceImpl.getCompanyEmployee(id);
		CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(companyEmployee.getCompanyid());
		SysUser sysUser = userServiceImpl.getUser(companyEmployee.getUserid());
		
		CompanyEmployeeModel companyEmployeeModel = new CompanyEmployeeModel(sysUser, companyInfo, companyEmployee);
		
		return companyEmployeeModel;
	}
	
	@RequestMapping(value = "companyEmployees", method = RequestMethod.POST)
	public @ResponseBody Object addCompanyEmployee(
			@RequestBody CompanyEmployeeModel companyEmployeeModel) {
		logger.info("注册信息");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(companyEmployeeModel);
			
			userServiceImpl.addUser(companyEmployeeModel.getSysUser());
			companyEmployeeServiceImpl.addCompanyEmployee(companyEmployeeModel.getCompanyEmployee());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "注册企业员工信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "companyEmployees/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateCompanyEmployee(
			@PathVariable("id") String id, 
			@RequestBody CompanyEmployeeModel companyEmployeeModel) {
		logger.info("更新企业员工信息id=" + id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(companyEmployeeModel);
			
			CompanyEmployee companyEmployee = companyEmployeeServiceImpl.getCompanyEmployee(id);
			SysUser user = userServiceImpl.getUser(companyEmployee.getUserid());
			
			companyEmployee.setName(companyEmployeeModel.getCompanyEmployee().getName());
			companyEmployee.setStaffno(companyEmployeeModel.getCompanyEmployee().getStaffno());
			companyEmployee.setDepartment(companyEmployeeModel.getCompanyEmployee().getDepartment());
			companyEmployee.setJob(companyEmployeeModel.getCompanyEmployee().getJob());
			companyEmployee.setTelephone(companyEmployeeModel.getCompanyEmployee().getTelephone());
			companyEmployee.setQq(companyEmployeeModel.getCompanyEmployee().getQq());
			companyEmployee.setWechat(companyEmployeeModel.getCompanyEmployee().getWechat());
			
			user.setUsername(companyEmployeeModel.getSysUser().getUsername());
			user.setEmail(companyEmployeeModel.getSysUser().getEmail());
			user.setMobile(companyEmployeeModel.getSysUser().getMobile());
			if (companyEmployeeModel.getSysUser().getPassword() != null && companyEmployeeModel.getSysUser().getPassword().length() > 0){
				user.setPassword(companyEmployeeModel.getSysUser().getPassword());
			}
			
			userServiceImpl.updateUser(user);
			companyEmployeeServiceImpl.updateCompanyEmployee(companyEmployee);
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "更新企业员工信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "companyEmployees/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(
			@PathVariable("id") String id) {
		logger.info("删除企业员工信息id=" + id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			CompanyEmployee companyEmployee = companyEmployeeServiceImpl.getCompanyEmployee(id);
			
			userServiceImpl.deleteUser(companyEmployee.getUserid());
			companyEmployeeServiceImpl.deleteCompanyEmployee(companyEmployee.getId());
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "删除企业员工信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(CompanyEmployeeModel companyEmployeeModel) throws Exception {
		if (companyEmployeeModel == null
				|| companyEmployeeModel.getSysUser() == null
				|| companyEmployeeModel.getCompanyEmployee() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (StringUtils.isEmpty(companyEmployeeModel.getSysUser().getUsername())) {
			throw new Exception("企业员工帐户不能为空");
		}
		
		if (StringUtils.isEmpty(companyEmployeeModel.getCompanyEmployee().getName())) {
			throw new Exception("企业员工姓名不能为空");
		}
		
		if (companyEmployeeModel.getIsNew()) {
			if (companyEmployeeModel.getSysUser().getPassword() == null || companyEmployeeModel.getSysUser().getPassword().length() <= 0){
				throw new Exception("密码不能为空");
			}
			if (companyEmployeeModel.getPasscheck() == null || companyEmployeeModel.getPasscheck().length() <= 0){
				throw new Exception("确认密码不能为空");
			}
			if (!companyEmployeeModel.getSysUser().getPassword().equals(companyEmployeeModel.getPasscheck())){
				throw new Exception("密码与确认密码不一致");
			}
			
			companyEmployeeModel.getCompanyEmployee().setId(CounterUtil.increment(CompanyEmployee.class.getName()));
			companyEmployeeModel.getSysUser().setUserid(CounterUtil.increment(SysUser.class.getName()));

			companyEmployeeModel.getCompanyEmployee().setUserid(companyEmployeeModel.getSysUser().getUserid());
			
		} else {
			if (companyEmployeeModel.getSysUser().getPassword() != null && companyEmployeeModel.getSysUser().getPassword().length() > 0) {
				if (companyEmployeeModel.getPasscheck() != null && companyEmployeeModel.getPasscheck().length() > 0) {
					if (!companyEmployeeModel.getSysUser().getPassword().equals(companyEmployeeModel.getPasscheck())) {
						throw new Exception("密码与确认密码不一致");
					}
				}
			}
		}
		
		// CompanyEmployee.email mobile
		companyEmployeeModel.getCompanyEmployee().setEmail(companyEmployeeModel.getSysUser().getEmail());
		companyEmployeeModel.getCompanyEmployee().setMobile(companyEmployeeModel.getSysUser().getMobile());
		

		// Usertype
		companyEmployeeModel.getSysUser().setUsertype(CompanyEmployee.class.getName());
		
		// Password
		if (companyEmployeeModel.getSysUser().getPassword() != null && companyEmployeeModel.getSysUser().getPassword().length() > 0) {
			Object salt = null;
	
			if (this.saltSource != null) {
				salt = this.saltSource.getSalt(companyEmployeeModel.getSysUser());
			}
			companyEmployeeModel.getSysUser().setPassword(
				passwordEncoder.encodePassword(companyEmployeeModel.getSysUser().getPassword(), salt)
			);
		}
		
		return true;
	}


}
