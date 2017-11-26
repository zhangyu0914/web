package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.dto.PushMsgDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.PushMsgEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.SystemConfigEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IPushMsgService;


/**
 *<pre>
 * 功       能: 推送消息
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@Service("pushMsgService")
public class PushMsgServiceImpl extends BaseMapperImpl  implements IPushMsgService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IConfigurationService configurationService;//
	
	/**
	 * <pre>
	 * 说       明: 推送MQTT消息
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月14日下午4:43:09
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil pushMQTT(PushMQTTEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getSn())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("sn");
		}
		String jsonStr = JavaBeanUtil.javaBeanToString(entity);
		if (this.pushMsgEntityMapper.insert(new PushMsgEntity(UUIDUtil.getUUID(), entity.getSn(), jsonStr)) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil;
	}
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(PushMsgEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.pushMsgEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(PushMsgEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.pushMsgEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(PushMsgEntity entity, InterfacePage<PushMsgEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sn", entity.getSn());
		map.put("instance_id", entity.getAppInstanceId());
		map.put("startTime", entity.getStartSearchTime());
		map.put("endTime", entity.getEndSearchTime());
		map.put("currentPage", page.getPageNo());
		map.put("pageSize", page.getPageSize());
		
		String host = this.configurationService.sysParam().get(SystemConfigEnum.YW_MQTT_PORT_8060_TCP_ADDR.toString());
		log.info("-----host------"+host);
		String jsonText = UrlUtil.postNoSecret(host, map, false);
		if (StringUtils.isBlank(jsonText) || jsonText.indexOf("no params") != -1
				|| jsonText.indexOf("MongoError") != -1) {
			log.info("无数据");
			return resultUtil;
		}
		JSONObject jsonObject = JSONObject.parseObject(jsonText);
		List<Object> list = JavaBeanUtil.jsonToJavaBean(jsonObject.getString("data").toString(), List.class);
		List<PushMsgDto> listDto = new ArrayList<PushMsgDto>();
		JSONObject jsonObj = null;
		List<AppAccountEntity> appAccount = null;
		PushMsgDto dto = null;
		for (Object obj : list) {
			
			dto = new PushMsgDto();
			jsonObj = JSONObject.parseObject(obj.toString());
			dto.setTid(jsonObj.getString("_id"));
			dto.setAppInstanceId(jsonObj.getString("instance_id"));
			appAccount = this.appAccountEntityMapper.findAll(new AppAccountEntity(null, dto.getAppInstanceId(), null));
			if (!StringUtils.isBlank(appAccount)
					&& !appAccount.isEmpty()) {
				
				dto.setAppName(appAccount.get(0).getAppName());
				dto.setAppReportName(appAccount.get(0).getAppReportName());
			}
			dto.setSn(jsonObj.getString("sn"));
			dto.setMsg(jsonObj.getString("data_content"));
			if (!StringUtils.isBlank(dto.getMsg())) {
				dto.setMsgDsc(dto.getMsg().substring(0,20));
			}
			dto.setCreateTime(DateUtils.formatUTCStr(jsonObj.getString("time")+"", null));
			listDto.add(dto);	//封装成DTO数据
		}
		
		//获取总数量
		JSONObject pageObject = JSONObject.parseObject(jsonObject.get("page").toString());
		page.setTotalCount((Integer)pageObject.get("totalCount"));
		log.info("数据返回");
		return resultUtil.setData(listDto).setPage(page).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public PushMsgEntity findOne(PushMsgEntity entity)
			throws Exception {
		
		List<PushMsgEntity> list = this.pushMsgEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public PushMsgEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<PushMsgEntity> list = this.pushMsgEntityMapper.findAll(
				new PushMsgEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
