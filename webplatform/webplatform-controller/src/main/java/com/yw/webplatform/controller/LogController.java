package com.yw.webplatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.entity.DataLogEntity;
import com.yw.common.beansUtils.entity.LogEntity;
import com.yw.common.beansUtils.entity.RawDataLogEntity;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.SystemConfigEnum;
import com.yw.common.beansUtils.utils.log.Log4jLogLevelManager;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IModelService;

/**
 *<pre>
 * 功       能: 
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("log")
@Controller
public class LogController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
    public IModelService modelService;//
	@Autowired
	public IConfigurationService configurationService;//
    
	/**
	 *<pre>
	 * 说       明: 查询数据
	 * @param request
	 * @param response
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@RequestMapping(value = "findLog")
    public String findLogV200(HttpServletRequest request, String content, String startTime, String endTime, String module, String level, String project, Integer currentPage, Integer pageSize) throws Exception{
		try {
			if (StringUtils.isBlank(project)) {
				project = "webplatform";
			}
			//默认选中的模块是：全部
			if (StringUtils.isBlank(module)) {
				module = "All";
			}
			//如果模块选择‘全部’，查询时不传入module
			if(module.equals("All")){
				module="";
			}
			if (StringUtils.isBlank(startTime)) {
				startTime = DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYY_MM_DD+" 00:00:00");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", content);
			map.put("project", project);
			map.put("module", module);
			map.put("level", level);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("currentPage", currentPage);
			map.put("pageSize", pageSize);
			
			String host = this.configurationService.sysParam().get(SystemConfigEnum.YW_LOG_PORT_8060_TCP_ADDR.toString());
			String jsonText = UrlUtil.postNoSecret(host, map, false);
			if (StringUtils.isBlank(jsonText) || jsonText.indexOf("no params") != -1
					|| jsonText.indexOf("MongoError") != -1) {
				return "log";
			}
			JSONObject jsonObject = JSONObject.parseObject(jsonText);
			List<Object> list = JavaBeanUtil.jsonToJavaBean(jsonObject.getString("data").toString(), List.class);
			List<LogEntity> data = new ArrayList<LogEntity>();
			for (Object obj : list) {
				
				LogEntity logEntity = JavaBeanUtil.jsonToJavaBean(obj.toString(), LogEntity.class);
				if (logEntity.getTime().indexOf("T") != -1) {
					logEntity.setTime(DateUtils.format(DateUtils.formatUTC(logEntity.getTime()), DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS_sss));
				}
				data.add(logEntity);
			}
			{
				JSONObject pageObject = JSONObject.parseObject(jsonObject.get("page").toString());
				request.setAttribute("list", data);
				request.setAttribute("currentPage", pageObject.get("currentPage"));
				request.setAttribute("pageSize", pageObject.get("pageSize"));
				request.setAttribute("totalCount", pageObject.get("totalCount"));
				request.setAttribute("totalPage", pageObject.get("totalPage"));
			}
			
			{
				request.setAttribute("content", content != null ? content : "");
				request.setAttribute("level", level != null ? level : "");
				request.setAttribute("module", module != null ? module : "");
				request.setAttribute("startTime", startTime != null ? startTime : "");
				request.setAttribute("endTime", endTime != null ? endTime : "");
				request.setAttribute("project", project != null ? project : "");
			}
			{
				List<String> projectList = new ArrayList<String>();
				/*List<String> moduleList = new ArrayList<String>();*/
			
				Map<String, List<String>> proAndModMap = getProAndModList(project);
				projectList = proAndModMap.get("projectList");
				/*moduleList = proAndModMap.get("moduleList");*/
				
				request.setAttribute("projectList", projectList);
				request.setAttribute("project", project);
				
				/*request.setAttribute("moduleList", moduleList);*/
				request.setAttribute("module", module);
			}
			{
				List<String> levelList = new ArrayList<String>();
				//日志搜索中，级别排序：debug,info,warn,error,fatal
				levelList.add("debug");
				levelList.add("info");
				levelList.add("warn");
				levelList.add("error");
				levelList.add("fatal");
				request.setAttribute("levelList", levelList);
				request.setAttribute("level", level);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "log";
    }

	/**
	 * <pre>
	 * 说       明: 数据日志查询
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月27日上午8:47:46
	 * Q    Q: 1784286916
	 * </pre>
	 */
	@RequestMapping(value = "findDataLog")
    public String findDataLogV200(HttpServletRequest request, String model_id, String sn, String cmd_type, String startTime, String endTime, Integer currentPage, Integer pageSize) throws Exception{
		try {
			if (StringUtils.isBlank(cmd_type)) {
				cmd_type = "All";
			}
			if(cmd_type.equals("All")){
				cmd_type = "";
			}
		 	if (StringUtils.isBlank(startTime)) {
				startTime = DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYY_MM_DD+" 00:00:00");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("model_id", model_id);
			map.put("sn", sn);
			map.put("cmd_type", cmd_type);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("currentPage", currentPage);
			map.put("pageSize", pageSize);
			
			String host = this.configurationService.sysParam().get(SystemConfigEnum.YW_DATA_LOG_PORT_8060_TCP_ADDR.toString());
			String jsonText = UrlUtil.postNoSecret(host, map, false);
			if (StringUtils.isBlank(jsonText) || jsonText.indexOf("no params") != -1
					|| jsonText.indexOf("MongoError") != -1) {
				return "dataLog";
			}
			JSONObject jsonObject = JSONObject.parseObject(jsonText);
			List<Object> list = JavaBeanUtil.jsonToJavaBean(jsonObject.getString("data").toString(), List.class);
			List<DataLogEntity> data = new ArrayList<DataLogEntity>();
			for (Object obj : list) {
				DataLogEntity dataLogEntity = JavaBeanUtil.jsonToJavaBean(obj.toString(), DataLogEntity.class);
				//将带有‘T’字母的时间，进行格式转换
				if (dataLogEntity.getTime().indexOf("T") != -1) {
					dataLogEntity.setTime(DateUtils.format(DateUtils.formatUTC(dataLogEntity.getTime()), DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS));
				}
				data.add(dataLogEntity);
			}
			{
				JSONObject pageObject = JSONObject.parseObject(jsonObject.get("page").toString());
				request.setAttribute("list", data);
				request.setAttribute("currentPage", pageObject.get("currentPage"));
				request.setAttribute("pageSize", pageObject.get("pageSize"));
				request.setAttribute("totalCount", pageObject.get("totalCount"));
				request.setAttribute("totalPage", pageObject.get("totalPage"));
			}
			{
 				request.setAttribute("model_id", model_id != null ? model_id : "");
				request.setAttribute("cmd_type", cmd_type != null ? cmd_type : "");
				request.setAttribute("sn", sn != null ? sn : "");
				request.setAttribute("startTime", startTime != null ? startTime : "");
				request.setAttribute("endTime", endTime != null ? endTime : "");
			}
			{
				List<String> cmdTypeList = new ArrayList<String>();
				cmdTypeList.add("All");
				cmdTypeList.add("read");
				cmdTypeList.add("write");
				cmdTypeList.add("status");
				cmdTypeList.add("devrequest");
				request.setAttribute("cmdTypeList", cmdTypeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "dataLog";
    }
	
	/**
	 * <pre>
	 * 说       明: 原始数据日志查询
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年8月10日上午10:15:28
	 * Q    Q: 1784286916
	 * </pre>
	 */
	@RequestMapping(value = "findRawDataLog")
	public String findRawDataLogV200(HttpServletRequest request, String ip, String startTime, String endTime, Integer currentPage, Integer pageSize) throws Exception{
		
		
		if(StringUtils.isBlank(startTime)){
			startTime = DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYY_MM_DD+" 00:00:00");
		}
		Map<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ip", ip);
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("endTime", endTime);
		paramMap.put("currentPage", currentPage);
		paramMap.put("pageSize", pageSize);
		
		String host = this.configurationService.sysParam().get(SystemConfigEnum.YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR.toString());
		String jsonText = UrlUtil.postNoSecret(host, paramMap, false);
		if(StringUtils.isBlank(jsonText) || jsonText.indexOf("no params") != -1
				|| jsonText.indexOf("MongoError") != -1){
			return "rawDataLog";
		}
		JSONObject jsonObject = JSONObject.parseObject(jsonText);
		List<Object> list = JavaBeanUtil.jsonToJavaBean(jsonObject.getString("data").toString(), List.class);
		List<RawDataLogEntity> data = new ArrayList<RawDataLogEntity>();
		for(Object obj : list){
			RawDataLogEntity entity = JavaBeanUtil.jsonToJavaBean(obj.toString(), RawDataLogEntity.class);
			if(entity.getTime().indexOf("T") != -1){
				entity.setTime(DateUtils.format(DateUtils.formatUTC(entity.getTime()), DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS));
			}
			data.add(entity);
		}
		{
			JSONObject pageObject = JSONObject.parseObject(jsonObject.getString("page").toString());
			request.setAttribute("list", data);
			request.setAttribute("currentPage", pageObject.get("currentPage"));
			request.setAttribute("pageSize", pageObject.get("pageSize"));
			request.setAttribute("totalPage", pageObject.get("totalPage"));
			request.setAttribute("totalCount", pageObject.get("totalCount"));
		}
		{
			request.setAttribute("ip", ip==null ? "":ip);
			request.setAttribute("startTime", startTime==null ? "":startTime);
			request.setAttribute("endTime", endTime==null ? "":endTime);
		}
		return "rawDataLog";
	}
	
	/**
	 * <pre>
	 * 说       明:获取所有的project和module名称 
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月13日下午1:53:58
	 * Q    Q: 17789861157
	 * </pre>
	 */
	public  Map<String, List<String>> getProAndModList(String project) throws Exception{
		Map<String, List<String>> proAndModMap = new HashMap<String, List<String>>();
		List<String> projectList = new ArrayList<String>();
		List<String> moduleList = new ArrayList<String>();
		//每个模块集合第一个模块是：全部
		moduleList.add("All");
		//不传参
		String host = this.configurationService.sysParam().get(SystemConfigEnum.YW_PROJECT_MODULE_PORT_8060_TCP_ADDR.toString());
		String jsonText = UrlUtil.postNoSecret(host);
		if (StringUtils.isBlank(jsonText) || jsonText.indexOf("no params") != -1
				|| jsonText.indexOf("MongoError") != -1) {
			proAndModMap.put("projectList", null);
			proAndModMap.put("moduleList", null);
			return proAndModMap;
		}
		
		//解析出project和module
		JSONArray jsonList = JSONObject.parseArray(jsonText);
		for(Object jsonObj:jsonList){
			JSONObject proAndMod = JSONObject.parseObject(jsonObj.toString());
			projectList.add(proAndMod.getString("project"));
			//只查找当前项目中的模块
			if(proAndMod.getString("project").equals(project)){
				JSONArray modList = JSONObject.parseArray(proAndMod.getString("module").toString());
				for(Object modObj:modList){
					moduleList.add(modObj.toString());
				}
			}
		}
		proAndModMap.put("projectList", projectList);
		proAndModMap.put("moduleList", moduleList);
		return proAndModMap;
	}
	
	/**
	 * <pre>
	 * 说       明: 根据项目返回项目模块
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月14日下午3:58:27
	 * Q    Q: 1784286916
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping("getModuleList")
	public List<String> getModuleList(String project) throws Exception{
		Map<String, List<String>> proAndModMap = getProAndModList(project);
		return  proAndModMap.get("moduleList");
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年9月4日下午2:17:26
	 * Q    Q: 308053847
	 * </pre>
	 */
	@ResponseBody
	@RequestMapping("changeLogLevel")
	public ResultUtil changeLogLevel(String level) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(level)) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("level");
		}
		
		Level level1 = Level.toLevel(level);
		LogManager.getRootLogger().setLevel(level1);
		String log = "ROOT日志级别修改为：" + level;
		System.out.println(log);
		
		return resultUtil.setData(log).setCode(ErrorTypeEnum.SUCCESS);
	}
	
}
