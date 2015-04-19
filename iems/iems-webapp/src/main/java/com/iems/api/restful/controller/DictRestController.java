package com.iems.api.restful.controller;

import java.util.List;

import net.sf.json.JSONObject;

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

import com.iems.core.dao.support.PageResults;
import com.iems.core.dao.support.SearchCondition;
import com.iems.core.dao.support.SearchConditions;
import com.iems.core.entity.SysDict;
import com.iems.core.service.IDictService;
import com.iems.core.util.CounterUtil;

@Controller
@RequestMapping("/v1")
public class DictRestController {
	private static final Log logger = LogFactory
			.getLog(DictRestController.class);

	@Autowired
	private IDictService dictServiceImpl;
	
	
	@RequestMapping(value = "dicts", method = RequestMethod.GET)
	public @ResponseBody PageResults<SysDict> listDict(
			@RequestParam(value = "dictcode", required = false) String dictcode,
			@RequestParam(value = "dictname", required = false) String dictname,
			@RequestParam(value = "iDisplayStart", required = false, defaultValue = "1") int iDisplayStart,
			@RequestParam(value = "iDisplayLength", required = false, defaultValue = "20") int iDisplayLength) {

		int pageNo = iDisplayStart / iDisplayLength + 1;
		int pageSize = iDisplayLength;

		SearchConditions<SysDict> searchConditions = new SearchConditions<SysDict>();
		searchConditions.add(new SearchCondition("dictcode", "like", dictcode));
		searchConditions.add(new SearchCondition("dictname", "like", dictname));
		
		PageResults<SysDict> pageResults = 
				dictServiceImpl.getDicts(pageNo, pageSize, 
				searchConditions);
		
		return pageResults;
	}

	@RequestMapping(value = "dicts/{dictid}", method = RequestMethod.GET)
	public @ResponseBody SysDict getDict(
			@PathVariable("dictid") String dictid) {
		logger.info("获取字典信息dictid=" + dictid);

		SysDict dict = dictServiceImpl.getDict(dictid);
		return dict;
	}

	@RequestMapping(value = "dicts", method = RequestMethod.POST)
	public @ResponseBody Object addDict(
			@RequestBody SysDict dict) {
		logger.info("添加字典信息id=" + dict.getDictid());

		if (dict.getDictid() == null || dict.getDictid().trim().length() == 0) {
			dict.setDictid(CounterUtil.increment(SysDict.class.getName()));
		}
		
		dictServiceImpl.addDict(dict);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "添加字典信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "dicts/{dictid}", method = RequestMethod.PUT)
	public @ResponseBody Object updateDict(
			@PathVariable("dictid") String dictid, 
			@RequestBody SysDict dict) {
		logger.info("更新字典信息id=" + dictid);

		dictServiceImpl.updateDict(dict);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新字典信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "dicts/{dictid}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteDict(
			@PathVariable("dictid") String dictid) {
		logger.info("删除字典信息id=" + dictid);

		dictServiceImpl.deleteDict(dictid);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除字典信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "dicts/{dictid}/children", method = RequestMethod.GET)
	public @ResponseBody List<SysDict> getDictChildren(
			@PathVariable("dictid") String dictid) {
		logger.info("获取字典信息的子集dictid=" + dictid);

		SysDict dict = dictServiceImpl.getDict(dictid);

		return dict.getDictchildren();
	}
	
	@RequestMapping(value = "dicts/{dictid}/parent", method = RequestMethod.GET)
	public @ResponseBody SysDict getDictParent(
			@PathVariable("dictid") String dictid) {
		logger.info("获取字典信息的父级dictid=" + dictid);

		SysDict dict = dictServiceImpl.getDict(dictid);

		return dict.getDictparent();
	}
	
	
	@RequestMapping(value = "dicts/dictcode/{dictcode}", method = RequestMethod.GET)
	public @ResponseBody SysDict getDictByDictcode(
			@PathVariable("dictcode") String dictcode) {
		logger.info("获取字典信息dictcode=" + dictcode);

		SysDict dict = dictServiceImpl.getDictByDictcode(dictcode);
		return dict;
	}
	
	@RequestMapping(value = "dicts/dictcode/{dictcode}/children", method = RequestMethod.GET)
	public @ResponseBody List<SysDict> getDictChildrenByDictcode(
			@PathVariable("dictcode") String dictcode) {
		logger.info("获取字典信息的子集dictcode=" + dictcode);

		SysDict dict = dictServiceImpl.getDictByDictcode(dictcode);

		return dict.getDictchildren();
	}
	
	@RequestMapping(value = "dicts/dictcode/{dictcode}/parent", method = RequestMethod.GET)
	public @ResponseBody SysDict getDictParentByDictcode(
			@PathVariable("dictcode") String dictcode) {
		logger.info("获取字典信息的父级dictcode=" + dictcode);

		SysDict dict = dictServiceImpl.getDictByDictcode(dictcode);

		return dict.getDictparent();
	}
}
