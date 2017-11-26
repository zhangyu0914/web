package com.yw.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.yw.common.api.IConfigurationService;
import com.yw.common.beansUtils.dto.ConfigurationDto;
import com.yw.common.beansUtils.entity.ConfigurationEntity;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.SpringUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnumValue;
import com.yw.common.beansUtils.utils.enums.SystemConfigEnum;
import com.yw.common.beansUtils.utils.file.ConfigUtil;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.spring.PropertyConfigurer;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.common.mapper.BaseMapperImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <pre>
 * 功       能: 字典数据表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 13:17:22
 * Q    Q: 308053847
 * </pre>
 */
@Service("configurationService")
public class ConfigurationServiceImpl extends BaseMapperImpl implements
		IConfigurationService {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月5日下午2:42:01
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultUtil insertConfiguration(ConfigurationEntity entity)
			throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getVersionInt(),
						entity.getKey(), entity.getValue(), entity.getRemark())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("dictionaryKey,dictionaryValue,remark");
		}
		if (!StringUtils.isBlank(this.findOne(new ConfigurationEntity(entity
				.getVersionInt(), entity.getKey(), null)))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_EXISTS);// 数据已存在
		}
		entity.setTid(UUIDUtil.getUUID());
		if (this.configurationEntityMapper.insert(entity) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}

	/**
	 * <pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(ConfigurationEntity entity) throws Exception {

		entity.setTid(UUID.randomUUID().toString());
		return this.configurationEntityMapper.insert(entity);
	}

	/**
	 * <pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer update(ConfigurationEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid())
				|| StringUtils.isBlankOr(entity.getTid())) {

			return null;
		}
		return this.configurationEntityMapper.updateById(entity);
	}

	/**
	 * <pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
	 * </pre>
	 */
	@Override
	public ResultUtil<List<ConfigurationDto>> findPage(
			ConfigurationEntity entity, InterfacePage<ConfigurationEntity> page)
			throws Exception {
		ResultUtil<List<ConfigurationDto>> resultUtil = new ResultUtil<List<ConfigurationDto>>();
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.configurationEntityMapper.getCount(entity));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.configurationEntityMapper.findAll(entity));
		List<ConfigurationDto> listDto = new ArrayList<ConfigurationDto>();
		ConfigurationDto dto = null;
		for (ConfigurationEntity ce : page.getList()) {

			dto = new ConfigurationDto(ce);
			listDto.add(dto); // 封装成DTO数据
		}
		return resultUtil.setData(listDto).setPage(page)
				.setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
	 * </pre>
	 */
	@Override
	public ConfigurationEntity findOne(ConfigurationEntity entity)
			throws Exception {

		List<ConfigurationEntity> list = this.configurationEntityMapper
				.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
	 * </pre>
	 */
	@Override
	public ConfigurationEntity findById(String tid) throws Exception {

		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<ConfigurationEntity> list = this.configurationEntityMapper
				.findAll(new ConfigurationEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * <pre>
	 * 说       明: 查询某一条数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
	 * </pre>
	 */
	@Override
	public List<ConfigurationEntity> findAll(ConfigurationEntity entity)
			throws Exception {

		return this.configurationEntityMapper.findAll(entity);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午1:25:52
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public boolean initSysConfiguration(ConfigurationEntity entity)
			throws Exception {

		if (StringUtils.isBlankOr(entity, entity.getVersionInt())) {

			return false;
		}
		List<ConfigurationEntity> list = this.configurationEntityMapper.findAll(entity);
		if (list.isEmpty()) {
			return false;
		}
		Field[] allField = ConfigurationEnum.class.getFields();
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		for (Field field : allField) {
			fieldMap.put(field.getName(), field);
		}
		Class<?> cls = ConfigurationEnum.class;
		String dictionaryKey = "";
		Map<String, ConfigurationEntity> map = new HashMap<String, ConfigurationEntity>();
		for (ConfigurationEntity ConfigurationEntity : list) {

			dictionaryKey = ConfigurationEntity.getKey().trim().toUpperCase();
			if (fieldMap.containsKey(dictionaryKey)) {

				ConfigurationEnumValue e = (ConfigurationEnumValue) Enum
						.valueOf((Class<? extends Enum>) cls,
								ConfigurationEntity.getKey().trim()
										.toUpperCase());

				e.setKey(ConfigurationEntity.getKey());
				e.setValue(ConfigurationEntity.getValue());

				map.put(ConfigurationEntity.getKey(), ConfigurationEntity);
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * 说       明: 初始化基础数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月21日上午9:22:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public boolean initData() throws Exception {

		RedisUtil.init();// 初始化Redis
		return this.initSysConfiguration(new ConfigurationEntity(ConfigUtil
				.getServerVersion()));// 初始化当前版本配置信息
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月21日上午9:26:34
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public Map<String, String> sysParam() throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		String YW_DEVMGR_PORT_9002_TCP_ADDR=System.getenv(SystemConfigEnum.IS_DOCKER.toString());//控制
		if (StringUtils.isBlank(YW_DEVMGR_PORT_9002_TCP_ADDR)) {
			YW_DEVMGR_PORT_9002_TCP_ADDR=ConfigurationEnum.SERVER_URL.getValue();
		}else{
			YW_DEVMGR_PORT_9002_TCP_ADDR="http://yw_devmgr:9002";
		}
		
		String YW_LOG_PORT_8060_TCP_ADDR=System.getenv(SystemConfigEnum.IS_DOCKER.toString());//日志
		if (StringUtils.isBlank(YW_LOG_PORT_8060_TCP_ADDR)) {
			YW_LOG_PORT_8060_TCP_ADDR=ConfigurationEnum.LOG_URL.getValue();
		}else{
			YW_LOG_PORT_8060_TCP_ADDR="http://yw_log:8060/api/v1/list";
		}
		
		String YW_REDIS_PORT_6379_TCP_ADDR=System.getenv(SystemConfigEnum.IS_DOCKER.toString());//REDIS
		if (StringUtils.isBlank(YW_REDIS_PORT_6379_TCP_ADDR)) {
			YW_REDIS_PORT_6379_TCP_ADDR=ConfigUtil.get("REDIS_HOST");
		}
		
		String YW_MQTT_PORT_8060_TCP_ADDR=System.getenv(SystemConfigEnum.IS_DOCKER.toString());//MQTT
		if (StringUtils.isBlank(YW_MQTT_PORT_8060_TCP_ADDR)) {
			YW_MQTT_PORT_8060_TCP_ADDR=ConfigurationEnum.PUSHMSG_URL.getValue();
		}else{
			YW_MQTT_PORT_8060_TCP_ADDR="http://yw_log:8060/api/v1/data/list";
		}
		
		String YW_MYSQL_PORT_3306_TCP_ADDR = System.getenv().get(SystemConfigEnum.IS_DOCKER.toString());//数据库
		if (StringUtils.isBlank(YW_MYSQL_PORT_3306_TCP_ADDR)) {
		        
			ServletContext context = SpringUtil.getHttpServletReqeust().getSession().getServletContext();
	        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
			PropertyConfigurer property = (PropertyConfigurer) ctx.getBean("propertyConfigurer");
			YW_MYSQL_PORT_3306_TCP_ADDR= property.getProperty("url");
		}
		
		String YW_PROJECT_MODULE_PORT_8060_TCP_ADDR = System.getenv().get(SystemConfigEnum.IS_DOCKER.toString());//日志模块
		if(StringUtils.isBlank(YW_PROJECT_MODULE_PORT_8060_TCP_ADDR)){
			YW_PROJECT_MODULE_PORT_8060_TCP_ADDR = ConfigurationEnum.PROJECT_MODULE_URL.getValue();
		}else{
			YW_PROJECT_MODULE_PORT_8060_TCP_ADDR="http://yw_log:8060/api/v1/module/list";
		}
		
		String YW_DATA_LOG_PORT_8060_TCP_ADDR = System.getenv().get(SystemConfigEnum.IS_DOCKER.toString());//数据日志接口
		if(StringUtils.isBlank(YW_DATA_LOG_PORT_8060_TCP_ADDR)){
			YW_DATA_LOG_PORT_8060_TCP_ADDR = ConfigurationEnum.DATA_LOG_URL.getValue();
		}else{
			YW_DATA_LOG_PORT_8060_TCP_ADDR="http://yw_log:8060/api/v1/develop/list";
		}
		
		String YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR = System.getenv().get(SystemConfigEnum.IS_DOCKER.toString());//原始数据日志接口
		if(StringUtils.isBlank(YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR)){
			YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR = ConfigurationEnum.RAW_DATA_LOG_URL.getValue();
		}else{
			YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR="http://yw_log:8060/api/v1/device/raw/list";
		}
		
		
		
		map.put(SystemConfigEnum.YW_DEVMGR_PORT_9002_TCP_ADDR.toString(), YW_DEVMGR_PORT_9002_TCP_ADDR);//控制
		map.put(SystemConfigEnum.YW_LOG_PORT_8060_TCP_ADDR.toString(), YW_LOG_PORT_8060_TCP_ADDR);//日志
		map.put(SystemConfigEnum.YW_REDIS_PORT_6379_TCP_ADDR.toString(), YW_REDIS_PORT_6379_TCP_ADDR);//REDIS
		map.put(SystemConfigEnum.YW_MQTT_PORT_8060_TCP_ADDR.toString(), YW_MQTT_PORT_8060_TCP_ADDR);//MQTT
		map.put(SystemConfigEnum.YW_MYSQL_PORT_3306_TCP_ADDR.toString(), YW_MYSQL_PORT_3306_TCP_ADDR);//数据库
		map.put(SystemConfigEnum.YW_PROJECT_MODULE_PORT_8060_TCP_ADDR.toString(), YW_PROJECT_MODULE_PORT_8060_TCP_ADDR);//项目模块接口
		map.put(SystemConfigEnum.YW_DATA_LOG_PORT_8060_TCP_ADDR.toString(), YW_DATA_LOG_PORT_8060_TCP_ADDR);//数据日志接口
		map.put(SystemConfigEnum.YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR.toString(), YW_RAW_DATA_LOG_PORT_8060_TCP_ADDR);//数据日志接口
		return map;
	}
}
