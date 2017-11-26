package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.InterfaceLogDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.entity.InterfaceLogEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IInterfaceLogService;


/**
 *<pre>
 * 功       能: 调用接口记录
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@Service("interfaceLogService")
public class InterfaceLogServiceImpl extends BaseMapperImpl  implements IInterfaceLogService{
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
	public Integer insert(InterfaceLogEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.interfaceLogEntityMapper.insert(entity);
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
	public Integer update(InterfaceLogEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.interfaceLogEntityMapper.updateById(entity);
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
	public ResultUtil findPage(InterfaceLogEntity entity, InterfacePage<InterfaceLogEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.interfaceLogEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.interfaceLogEntityMapper.findAll(entity));
		List<InterfaceLogDto> listDto = new ArrayList<InterfaceLogDto>();
		InterfaceLogDto dto = null;
		for (InterfaceLogEntity ce : page.getList()) {
			
			dto = new InterfaceLogDto(ce);
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
	public InterfaceLogEntity findOne(InterfaceLogEntity entity)
			throws Exception {
		
		List<InterfaceLogEntity> list = this.interfaceLogEntityMapper.findAll(entity);
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
	public InterfaceLogEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<InterfaceLogEntity> list = this.interfaceLogEntityMapper.findAll(
				new InterfaceLogEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 接口记录
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: zhangyu
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public AppResultUtil interfaceLog(InterfaceLogEntity entity, String token,
			String action) throws Exception {
		AppResultUtil resultUtil = new AppResultUtil();
		entity.setTid(UUID.randomUUID().toString());
		List<AppAccountEntity> appAccountList = this.appAccountEntityMapper
				.findOne(token);
		if (!StringUtils.isBlank(appAccountList)) {
			entity.setFkAppAccountTid(appAccountList.get(0).getTid());
		} else {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
		}
		InterfaceEntity interfaceEntity=new InterfaceEntity();
		interfaceEntity.setInterfaceUrl(action);
		List<InterfaceEntity> interfaceList = this.interfaceEntityMapper.findAll(interfaceEntity);
		AppAccountInterfaceEntity appAccountInterfaceEntity=new AppAccountInterfaceEntity();
		if (!StringUtils.isBlank(interfaceList)) {
			appAccountInterfaceEntity.setFkAppAccountTid(appAccountList.get(0).getTid());
			appAccountInterfaceEntity.setFkInterfaceTid(interfaceList.get(0).getTid());
			List<AppAccountInterfaceEntity> appAccountInterfaceList = this.appAccountInterfaceEntityMapper.findByFK(appAccountInterfaceEntity);
			if (!StringUtils.isBlank(appAccountInterfaceList)) {
				entity.setFkAppAccountInterfaceTid(appAccountInterfaceList.get(0)
						.getTid());
			} else {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
			}
		}else {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
		}
		if (this.interfaceLogEntityMapper.insert(entity) > 0) {
			resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}
}
