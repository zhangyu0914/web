package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AppAccountInterfaceDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.AppAccountStatusEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAppAccountInterfaceService;


/**
 *<pre>
 * 功       能: 可访问接口列表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@Service("appAccountInterfaceService")
public class AppAccountInterfaceServiceImpl extends BaseMapperImpl  implements IAppAccountInterfaceService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
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
	public ResultUtil insertAppAccountInterface(AppAccountInterfaceEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(
						entity.getFkAppAccountTid(),
						entity.getFkInterfaceTid())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("fkAppAccountTid,fkInterfaceTid");
		}
		
		String[] interfaceTidArray = entity.getFkInterfaceTid().split(",");
		for (String interfaceTid : interfaceTidArray) {
			
			if (!StringUtils.isBlank(this.findOne(new AppAccountInterfaceEntity(entity.getFkAppAccountTid(), interfaceTid)))) {
				
				log.info("已经绑定，不重新绑定!");
				continue;
			}
			
			entity.setTid(UUID.randomUUID().toString());
			entity.setFkInterfaceTid(interfaceTid);
			if (this.appAccountInterfaceEntityMapper.insert(entity) < 0) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
			}
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
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
	public Integer insert(AppAccountInterfaceEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.appAccountInterfaceEntityMapper.insert(entity);
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
	public Integer update(AppAccountInterfaceEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.appAccountInterfaceEntityMapper.updateById(entity);
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
	public ResultUtil findPage(AppAccountInterfaceEntity entity, InterfacePage<AppAccountInterfaceEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.appAccountInterfaceEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.appAccountInterfaceEntityMapper.findAll(entity));
		List<AppAccountInterfaceDto> listDto = new ArrayList<AppAccountInterfaceDto>();
		AppAccountInterfaceDto dto = null;
		for (AppAccountInterfaceEntity ce : page.getList()) {
			
			dto = new AppAccountInterfaceDto(ce);
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Override
	public AppAccountInterfaceEntity findOne(AppAccountInterfaceEntity entity)
			throws Exception {
		
		List<AppAccountInterfaceEntity> list = this.appAccountInterfaceEntityMapper.findAll(entity);
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
	public AppAccountInterfaceEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AppAccountInterfaceEntity> list = this.appAccountInterfaceEntityMapper.findAll(
				new AppAccountInterfaceEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
