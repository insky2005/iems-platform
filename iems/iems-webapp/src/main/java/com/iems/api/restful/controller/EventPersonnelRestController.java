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

import com.iems.biz.entity.EventInfo;
import com.iems.biz.entity.EventPersonnel;
import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.model.EventPersonnelModel;
import com.iems.biz.service.IEventInfoService;
import com.iems.biz.service.IEventPersonnelService;
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
public class EventPersonnelRestController {
	private static final Log logger = LogFactory.getLog(EventPersonnelRestController.class);

	@Autowired
	private ICompanyInfoService companyInfoServiceImpl;
	@Autowired
	private IEventInfoService eventInfoServiceImpl;
	@Autowired
	private IUserService userServiceImpl;

	@Autowired
	private IEventPersonnelService eventPersonnelServiceImpl;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private SaltSource saltSource;

	@RequestMapping(value = "eventPersonnels", method = RequestMethod.GET)
	public @ResponseBody PageResults<EventPersonnelModel> listEventPersonnel(
			@RequestParam(value = "companyid", required = true) String companyid,
			@RequestParam(value = "eventid", required = true) String eventid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<EventPersonnelModel> searchConditions = new SearchConditions<EventPersonnelModel>();
		searchConditions.add(new SearchCondition("eventPersonnel.companyid", "=", companyid));
		searchConditions.add(new SearchCondition("eventPersonnel.eventid", "=", eventid));
		searchConditions.add(new SearchCondition("eventPersonnel.name", "like", name));

		PageResults<EventPersonnelModel> pageResults = eventPersonnelServiceImpl.getEventPersonnelModels(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "eventPersonnels/{id}", method = RequestMethod.GET)
	public @ResponseBody EventPersonnelModel getEventPersonnel(
			@PathVariable("id") String id) {
		logger.info("获取活动参会人员信息id=" + id);
		
		EventPersonnel eventPersonnel = eventPersonnelServiceImpl.getEventPersonnel(id);
		
		CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(eventPersonnel.getCompanyid());
		EventInfo eventInfo = eventInfoServiceImpl.getEventInfo(eventPersonnel.getEventid());
		SysUser sysUser = userServiceImpl.getUser(eventPersonnel.getUserid());
		
		EventPersonnelModel eventPersonnelModel = new EventPersonnelModel(companyInfo, eventInfo, eventPersonnel, sysUser);
		
		return eventPersonnelModel;
	}
	
	@RequestMapping(value = "eventPersonnels", method = RequestMethod.POST)
	public @ResponseBody Object addEventPersonnel(
			@RequestBody EventPersonnelModel eventPersonnelModel) {
		logger.info("注册信息");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(eventPersonnelModel);
			
			userServiceImpl.addUser(eventPersonnelModel.getSysUser());
			
			eventPersonnelServiceImpl.addEventPersonnel(eventPersonnelModel.getEventPersonnel());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "注册活动参会人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "eventPersonnels/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateEventPersonnel(
			@PathVariable("id") String id, 
			@RequestBody EventPersonnelModel eventPersonnelModel) {
		logger.info("更新活动参会人员信息id=" + id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(eventPersonnelModel);
			
			EventPersonnel eventPersonnel = eventPersonnelServiceImpl.getEventPersonnel(id);
			SysUser user = userServiceImpl.getUser(eventPersonnel.getUserid());
			
			eventPersonnel.setType(eventPersonnelModel.getEventPersonnel().getType());
			eventPersonnel.setName(eventPersonnelModel.getEventPersonnel().getName());
			eventPersonnel.setTelephone(eventPersonnelModel.getEventPersonnel().getTelephone());
			eventPersonnel.setQq(eventPersonnelModel.getEventPersonnel().getQq());
			eventPersonnel.setWechat(eventPersonnelModel.getEventPersonnel().getWechat());
			
			eventPersonnel.setDuty(eventPersonnelModel.getEventPersonnel().getDuty());
			eventPersonnel.setMemo(eventPersonnelModel.getEventPersonnel().getMemo());

			user.setUsername(eventPersonnelModel.getSysUser().getUsername());
			user.setEmail(eventPersonnelModel.getSysUser().getEmail());
			user.setMobile(eventPersonnelModel.getSysUser().getMobile());
			if (eventPersonnelModel.getSysUser().getPassword() != null && eventPersonnelModel.getSysUser().getPassword().length() > 0){
				user.setPassword(eventPersonnelModel.getSysUser().getPassword());
			}
			
			userServiceImpl.updateUser(user);
			eventPersonnelServiceImpl.updateEventPersonnel(eventPersonnel);
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "更新活动参会人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "eventPersonnels/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(
			@PathVariable("id") String id) {
		logger.info("删除活动参会人员信息id=" + id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			EventPersonnel eventPersonnel = eventPersonnelServiceImpl.getEventPersonnel(id);
			
			userServiceImpl.deleteUser(eventPersonnel.getUserid());
			eventPersonnelServiceImpl.deleteEventPersonnel(eventPersonnel.getId());
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "删除活动参会人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(EventPersonnelModel eventPersonnelModel) throws Exception {
		if (eventPersonnelModel == null
				|| eventPersonnelModel.getSysUser() == null
				|| eventPersonnelModel.getEventPersonnel() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (StringUtils.isEmpty(eventPersonnelModel.getEventPersonnel().getName())) {
			throw new Exception("活动参会人员姓名不能为空");
		}
		
		if (StringUtils.isEmpty(eventPersonnelModel.getSysUser().getUsername())) {
			throw new Exception("活动参会人员帐户不能为空");
		}
		
		if (eventPersonnelModel.getIsNew()) {
			if (eventPersonnelModel.getSysUser().getPassword() == null || eventPersonnelModel.getSysUser().getPassword().length() <= 0){
				throw new Exception("密码不能为空");
			}
			if (eventPersonnelModel.getPasscheck() == null || eventPersonnelModel.getPasscheck().length() <= 0){
				throw new Exception("确认密码不能为空");
			}
			if (!eventPersonnelModel.getSysUser().getPassword().equals(eventPersonnelModel.getPasscheck())){
				throw new Exception("密码与确认密码不一致");
			}
			
			eventPersonnelModel.getEventPersonnel().setId(CounterUtil.increment(EventPersonnel.class.getName()));
			eventPersonnelModel.getSysUser().setUserid(CounterUtil.increment(SysUser.class.getName()));

			eventPersonnelModel.getEventPersonnel().setUserid(eventPersonnelModel.getSysUser().getUserid());
			
		} else {
			if (eventPersonnelModel.getSysUser().getPassword() != null && eventPersonnelModel.getSysUser().getPassword().length() > 0){
				if (eventPersonnelModel.getPasscheck() != null && eventPersonnelModel.getPasscheck().length() > 0){
					if (!eventPersonnelModel.getSysUser().getPassword().equals(eventPersonnelModel.getPasscheck())){
						throw new Exception("密码与确认密码不一致");
					}
				}
			}
		}
		
		// EventPersonnel.email mobile
		eventPersonnelModel.getEventPersonnel().setEmail(eventPersonnelModel.getSysUser().getEmail());
		eventPersonnelModel.getEventPersonnel().setMobile(eventPersonnelModel.getSysUser().getMobile());
		

		// Usertype
		eventPersonnelModel.getSysUser().setUsertype(EventPersonnel.class.getName());
		
		// Password
		if (eventPersonnelModel.getSysUser().getPassword() != null && eventPersonnelModel.getSysUser().getPassword().length() > 0){
			Object salt = null;
	
			if (this.saltSource != null) {
				salt = this.saltSource.getSalt(eventPersonnelModel.getSysUser());
			}
			eventPersonnelModel.getSysUser().setPassword(
				passwordEncoder.encodePassword(eventPersonnelModel.getSysUser().getPassword(), salt)
			);
		}
		
		return true;
	}


}
