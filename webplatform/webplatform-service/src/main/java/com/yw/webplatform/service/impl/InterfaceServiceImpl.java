package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.InterfaceDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppAccountStatusEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IInterfaceService;


/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:14
 * Q    Q: 308053847
 *</pre>
 */
@Service("interfaceService")
public class InterfaceServiceImpl extends BaseMapperImpl  implements IInterfaceService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil insertInterface(InterfaceEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getInterfaceName(),
						entity.getInterfaceUrl(),
						entity.getInterfaceType())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("interfaceName,interfaceName,interfaceUrl");
		}
		if (!StringUtils.isBlank(this.findOne(new InterfaceEntity(null, entity.getInterfaceUrl())))) {
			//接口已存在，请勿重复添加
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_APP_INTERFACE_EXISTS);
		}
		entity.setTid(UUID.randomUUID().toString());
		entity.setInterfaceCode(this.interfaceEntityMapper.getMaxCode() + "");
		if (this.interfaceEntityMapper.insert(entity) >0) {
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(InterfaceEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.interfaceEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(InterfaceEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.interfaceEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(InterfaceEntity entity, InterfacePage<InterfaceEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.interfaceEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.interfaceEntityMapper.findAll(entity));
		List<InterfaceDto> listDto = new ArrayList<InterfaceDto>();
		InterfaceDto dto = null;
		for (InterfaceEntity ce : page.getList()) {
			
			dto = new InterfaceDto(ce);
			listDto.add(dto);	//封装成DTO数据
		}
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Override
	public InterfaceEntity findOne(InterfaceEntity entity)
			throws Exception {
		
		List<InterfaceEntity> list = this.interfaceEntityMapper.findAll(entity);
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Override
	public InterfaceEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<InterfaceEntity> list = this.interfaceEntityMapper.findAll(
				new InterfaceEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 更新
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午4:29:36
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil updateInterface(InterfaceEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getTid())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
		}
		
		if(!StringUtils.isBlank(entity.getInterfaceUrl())){
			InterfaceEntity interfaceEntity=this.interfaceEntityMapper.findByURL(entity);
			if(!StringUtils.isBlank(interfaceEntity)){
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_EXISTS);
			}
		}
		
		if (this.interfaceEntityMapper.updateById(entity) >0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}

	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午4:29:55
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil deleteInterface(InterfaceEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getTid())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
		}
		AppAccountInterfaceEntity appAccountInterfaceEntity=new AppAccountInterfaceEntity();
		appAccountInterfaceEntity.setFkInterfaceTid(entity.getTid());
		List<AppAccountInterfaceEntity> list=this.appAccountInterfaceEntityMapper.findAll(appAccountInterfaceEntity);
		if(!StringUtils.isBlank(list)){
			for (AppAccountInterfaceEntity appAccountInterfaceEntity2 : list) {
				if (this.appAccountInterfaceEntityMapper.delete(entity.getTid()) <1) {
					return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
				}
			}
			if (this.interfaceEntityMapper.delete(entity.getTid()) <1) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
			}
		}
		
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
}
