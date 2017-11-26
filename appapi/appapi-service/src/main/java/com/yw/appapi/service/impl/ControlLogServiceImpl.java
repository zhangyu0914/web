package com.yw.appapi.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.appapi.api.IControlLogService;
import com.yw.appapi.mapper.BaseMapperImpl;
import com.yw.common.beansUtils.entity.ControlEntity;
import com.yw.common.beansUtils.entity.ControlLogEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: 记录参数和返回值功能
 * 涉及版本: 
 * 创  建  者: 古粤赣
 * 日       期: 2017年6月30日下午3:34:24
 * Q    Q: 1784286916
 *</pre>
 */
@Service("controlLogService")
public class ControlLogServiceImpl extends BaseMapperImpl implements IControlLogService {


	/**
	 * 
	*<pre>
	* 说      明:  对参数进行检验，通过参数创建ControlLogEntity实体，添加到t_control_log
	* @param args,result,token
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年6月30日下午4:39:28
	*</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public AppResultUtil insert(Object[] args, Object result, String token)
			throws Exception {
		AppResultUtil appResultUtil = new AppResultUtil();
		//检测参数是否为空
		if(StringUtils.isBlank(args)){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS);
		}
		//检查token是否为空
		if(StringUtils.isBlank(token)){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("token");
		}
		ControlEntity arg = (ControlEntity)args[0];
		String tid = UUID.randomUUID().toString();
		//获取t_auth_log表中app_instance,从表中查找的数据要判断是否为空
		String appInstance = this.controlLogEntityMapper.getAppInstance(token);
		if(StringUtils.isBlank(appInstance)){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_APP_INSTANCE_IS_NO_EXISTS);
		}
		String controlUuid = arg.getUuid();
		//javabean --> json
		String reqParam = JavaBeanUtil.javaBeanToString(arg);
		String rspData = JavaBeanUtil.javaBeanToString(result);
		
		ControlLogEntity entity = new ControlLogEntity(tid, appInstance, controlUuid, reqParam, rspData);
		if(this.controlLogEntityMapper.insert(entity) == 1){
			return appResultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		
		return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}

	
	/**
	 * 
	*<pre>
	* 说      明:  
	* @param entity
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月1日下午8:25:49
	*</pre>
	 */
	@Override
	public List<ControlLogEntity> findAll(ControlLogEntity entity) throws Exception {
		return this.controlLogEntityMapper.findAll(entity);
	}

	/**
	 * 
	*<pre>
	* 说      明:  根据条件查询表中记录数
	* @param entity
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月1日下午11:19:40
	*</pre>
	 */
	@Override
	public AppResultUtil getCount(ControlLogEntity entity) throws Exception {
		AppResultUtil appResultUtil = new AppResultUtil();
		//参数可以都为空，不需要检测参数
		int count =  this.controlLogEntityMapper.getCount(entity);
		//对数据库中查询出来的结果进行检查
		if(count == 0){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_SELECT); 
		}
		return appResultUtil.setCode(ErrorTypeEnum.SUCCESS).setData(count);
	}

	/**
	 * 
	*<pre>
	* 说      明:  根据主键删除记录
	* @param 
	* @return 
	* @throws 
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月1日下午11:20:56
	*</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public AppResultUtil delete(String tid) throws Exception {
		AppResultUtil appResultUtil = new AppResultUtil();
		//对参数进行检查
		if(StringUtils.isBlank(tid)){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
		}
		int result = this.controlLogEntityMapper.delete(tid);
		if(result == 1){
			return appResultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}

	/**
	 * 
	*<pre>
	* 说      明: 根据主键更新信息 
	* @param tid
	* @return AppResultUtil
	* @throws Exception
	* 涉及版本:  
	* 创  建  者: 古粤赣
	* 日       期: 2017年7月2日下午10:13:19
	*</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public AppResultUtil updateById(ControlLogEntity entity) throws Exception {
		AppResultUtil appResultUtil = new AppResultUtil();
		//判断tid是否为空
		if(StringUtils.isBlank(entity.getTid())){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
		}
		//根据tid查询出将要更新的记录
		ControlLogEntity entityForUpdate = this.controlLogEntityMapper.findById(entity.getTid());
		//对数据库中查询出来的结果进行检查
		if(StringUtils.isBlank(entityForUpdate)){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
		}
		//判断entity是否为空
		if(StringUtils.isBlank(entity)){
			return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS);
		}
		//判断appInstance在数据表中是否已经存在
		if(!StringUtils.isBlank(entity.getAppInstance())){
			if(this.controlLogEntityMapper.getCountOfAppInstance(entity.getAppInstance()) != 0){
				return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
			}
			//appInstance不为空，才更新
			entityForUpdate.setAppInstance(entity.getAppInstance());
		}
		//判断controlUuid在数据表中是否已经存在
		if(!StringUtils.isBlank(entity.getControlUuid())){
			if(this.controlLogEntityMapper.getCountOfControlUuid(entity.getControlUuid()) != 0){
				return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
			}
			//controlUuid不为空，才更新
			entityForUpdate.setControlUuid(entity.getControlUuid());
		}
		//reqParam不为空，才更新
		if(!StringUtils.isBlank(entity.getReqParam())){
			entityForUpdate.setReqParam(entity.getReqParam());
		}
		//rspData不为空，才更新
		if(!StringUtils.isBlank(entity.getRspData())){
			entityForUpdate.setRspData(entity.getRspData());
		}
		//受影响行数为1时，才返回成功
		if(this.controlLogEntityMapper.updateById(entityForUpdate) == 1){
			return appResultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		
		return appResultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
	}

}
