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

import com.iems.biz.entity.CompanyInfo;
import com.iems.biz.entity.EventInfo;
import com.iems.biz.entity.EventInfoExt;
import com.iems.biz.model.EventInfoModel;
import com.iems.biz.service.ICompanyInfoService;
import com.iems.biz.service.IEventInfoExtService;
import com.iems.biz.service.IEventInfoService;
import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class EventInfoRestController {
	private static final Log logger = LogFactory.getLog(EventInfoRestController.class);

	@Autowired
	private ICompanyInfoService companyInfoServiceImpl;
	
	@Autowired
	private IEventInfoService eventInfoServiceImpl;

	@Autowired
	private IEventInfoExtService eventInfoExtServiceImpl;

	@RequestMapping(value = "eventInfos", method = RequestMethod.GET)
	public @ResponseBody PageResults<EventInfoModel> listEventInfo(
			@RequestParam(value = "companyid", required = true) String companyid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {
		//iDisplayStart=0
		//iDisplayLength=2
		
		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<EventInfoModel> searchConditions = new SearchConditions<EventInfoModel>();
		searchConditions.add(new SearchCondition("eventInfo.companyid", "=", companyid));
		searchConditions.add(new SearchCondition("eventInfo.name", "like", name));

		PageResults<EventInfoModel> pageResults = eventInfoServiceImpl.getEventInfoModels(pageNo, pageSize, 
				searchConditions);
		return pageResults;
	}

	@RequestMapping(value = "eventInfos/{id}", method = RequestMethod.GET)
	public @ResponseBody EventInfoModel getEventInfo(
			@PathVariable("id") String id) {
		logger.info("获取活动信息id=" + id);
		
		EventInfo eventInfo = eventInfoServiceImpl.getEventInfo(id);
		
		EventInfoExt eventInfoExt = eventInfoExtServiceImpl.getEventInfoExt(eventInfo.getId());

		CompanyInfo companyInfo = companyInfoServiceImpl.getCompanyInfo(eventInfo.getCompanyid());

		EventInfoModel eventInfoModel = new EventInfoModel(companyInfo, eventInfo, eventInfoExt);
		
		return eventInfoModel;
	}
	
	@RequestMapping(value = "eventInfos", method = RequestMethod.POST)
	public @ResponseBody Object addEventInfo(
			@RequestBody EventInfoModel eventInfoModel) {
		logger.info("注册活动信息");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(eventInfoModel);
			
			eventInfoExtServiceImpl.addEventInfoExt(eventInfoModel.getEventInfoExt());
			
			eventInfoServiceImpl.addEventInfo(eventInfoModel.getEventInfo());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "注册活动信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}

		return jsonObject;
	}

	@RequestMapping(value = "eventInfos/{id}", method = RequestMethod.PUT)
	public @ResponseBody Object updateEventInfo(
			@PathVariable("id") String id, 
			@RequestBody EventInfoModel eventInfoModel) {
		logger.info("更新活动信息id=" + id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			valid(eventInfoModel);
			
			EventInfo eventInfo = eventInfoServiceImpl.getEventInfo(id);

			eventInfo.setName(eventInfoModel.getEventInfo().getName());
			eventInfo.setMemo(eventInfoModel.getEventInfo().getMemo());
			
			EventInfoExt eventInfoExt = eventInfoExtServiceImpl.getEventInfoExt(eventInfo.getId());

			eventInfoExt.setTxt(eventInfoModel.getEventInfoExt().getTxt());
			
			eventInfoExtServiceImpl.addEventInfoExt(eventInfoExt);

			eventInfoServiceImpl.updateEventInfo(eventInfo);
	
			jsonObject.put("result", true);
			jsonObject.put("msg", "更新活动信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	@RequestMapping(value = "eventInfos/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(
			@PathVariable("id") String id) {
		logger.info("删除活动信息id=" + id);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", false);

		try {
			EventInfo eventInfo = eventInfoServiceImpl.getEventInfo(id);
			
			EventInfoExt eventInfoExt = eventInfoExtServiceImpl.getEventInfoExt(eventInfo.getId());
			
			eventInfoExtServiceImpl.deleteEventInfoExt(eventInfoExt.getEventid());
	
			eventInfoServiceImpl.deleteEventInfo(eventInfo.getId());
			
			jsonObject.put("result", true);
			jsonObject.put("msg", "删除活动信息成功");
		} catch (Exception ex) {
			jsonObject.put("msg", ex.getMessage());
		}
		
		return jsonObject;
	}

	private boolean valid(EventInfoModel eventInfoModel) throws Exception {
		if (eventInfoModel == null || eventInfoModel.getEventInfo() == null) {
			throw new Exception("传入对象为空");
		}
		
		if (StringUtils.isEmpty(eventInfoModel.getEventInfo().getName())) {
			throw new Exception("活动名称不能为空");
		}
		
		if (eventInfoModel.getIsNew()) {
			eventInfoModel.getEventInfo().setId(CounterUtil.increment(EventInfo.class.getName()));
			
			eventInfoModel.getEventInfoExt().setEventid(eventInfoModel.getEventInfo().getId());
		}

		return true;
	}


}
