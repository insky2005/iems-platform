package com.iems.api.restful.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iems.biz.entity.CompanyEmployee;
import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.EventInfo;
import com.iems.biz.entity.EventStaff;
import com.iems.biz.model.EventStaffModel;
import com.iems.biz.service.ICompanyEmployeeService;
import com.iems.biz.service.ICompanyInfoService;
import com.iems.biz.service.IEventInfoService;
import com.iems.biz.service.IEventStaffService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysUser;
import com.iems.core.service.IUserService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class EventStaffRestController {
	private static final Log logger = LogFactory.getLog(EventStaffRestController.class);

	@Autowired
	private ICompanyInfoService companyInfoServiceImpl;
	
	@Autowired
	private IEventInfoService eventInfoServiceImpl;

	@Autowired
	private ICompanyEmployeeService companyEmployeeServiceImpl;

	@Autowired
	private IUserService userServiceImpl;

	@Autowired
	private IEventStaffService eventStaffServiceImpl;

	@RequestMapping(value = "eventStaffs", method = RequestMethod.GET)
	public @ResponseBody PageResults<EventStaffModel> listEventInfo(
			@RequestParam(value = "companyid", required = true) String companyid,
			@RequestParam(value = "eventid", required = true) String eventid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<EventStaffModel> searchConditions = new SearchConditions<EventStaffModel>();
		searchConditions.add(new SearchCondition("eventStaff.companyid", "=", companyid));
		searchConditions.add(new SearchCondition("eventStaff.eventid", "=", eventid));
		searchConditions.add(new SearchCondition("eventStaff.name", "like", name));

		PageResults<EventStaffModel> pageResults = eventStaffServiceImpl.getEventStaffModels(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "eventStaffs/{id}", method = RequestMethod.GET)
	public @ResponseBody EventStaffModel getEventInfo(
			@PathVariable("id") String id) {
		logger.info("获取活动工作人员信息id=" + id);
		
		EventStaff eventStaff = eventStaffServiceImpl.getEventStaff(id);
		
		CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(eventStaff.getCompanyid());
		EventInfo eventInfo = eventInfoServiceImpl.getEventInfo(eventStaff.getEventid());
		CompanyEmployee companyEmployee = companyEmployeeServiceImpl.getCompanyEmployee(eventStaff.getEmployeeid());
		SysUser sysUser = userServiceImpl.getUser(companyEmployee.getUserid());

		EventStaffModel eventStaffModel = new EventStaffModel(companyInfo, eventInfo, eventStaff, companyEmployee, sysUser);
		
		return eventStaffModel;
	}
	
	@RequestMapping(value = "eventStaffs", method = RequestMethod.POST)
	public @ResponseBody Object addEventStaff(
			@RequestBody EventStaffModel eventStaffModel) {
		logger.info("注册活动工作人员信息");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(eventStaffModel);
			
			eventStaffServiceImpl.addEventStaff(eventStaffModel.getEventStaff());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "注册活动工作人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "eventStaffs/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateEventStaff(
			@PathVariable("id") String id, 
			@RequestBody EventStaffModel eventStaffModel) {
		logger.info("更新活动工作人员信息id=" + id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(eventStaffModel);
			
			EventStaff eventStaff = eventStaffServiceImpl.getEventStaff(id);

			eventStaff.setType(eventStaffModel.getEventStaff().getType());
			eventStaff.setName(eventStaffModel.getEventStaff().getName());
			eventStaff.setTelephone(eventStaffModel.getEventStaff().getTelephone());
			eventStaff.setQq(eventStaffModel.getEventStaff().getQq());
			eventStaff.setWechat(eventStaffModel.getEventStaff().getWechat());

			eventStaff.setEmail(eventStaffModel.getEventStaff().getEmail());
			eventStaff.setMobile(eventStaffModel.getEventStaff().getMobile());

			eventStaff.setDuty(eventStaffModel.getEventStaff().getDuty());
			eventStaff.setMemo(eventStaffModel.getEventStaff().getMemo());
			
			eventStaffServiceImpl.updateEventStaff(eventStaff);
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "更新活动工作人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "eventStaffs/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(
			@PathVariable("id") String id) {
		logger.info("删除活动工作人员信息id=" + id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			eventStaffServiceImpl.deleteEventStaff(id);
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "删除活动工作人员信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(EventStaffModel eventStaffModel) throws Exception {
		if (eventStaffModel == null || eventStaffModel.getEventStaff() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (StringUtils.isEmpty(eventStaffModel.getEventStaff().getName())) {
			throw new Exception("活动工作人员名称不能为空");
		}
		
		if (eventStaffModel.getIsNew()) {
			eventStaffModel.getEventStaff().setId(CounterUtil.increment(EventStaff.class.getName()));
		}

		return true;
	}


}
