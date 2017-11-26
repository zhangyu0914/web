package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.OrganizationDto;
import com.yw.common.beansUtils.entity.OrganizationEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IOrganizationService;


/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:15
 * Q    Q: 308053847
 *</pre>
 */
@Service("organizationService")
public class OrganizationServiceImpl extends BaseMapperImpl  implements IOrganizationService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-15 15:29:15
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(OrganizationEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.organizationEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-03-15 15:29:15
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(OrganizationEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.organizationEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 15:29:15
	 *</pre>
	 */
	@Override
	public ResultUtil<List<OrganizationDto>> findPage(OrganizationEntity entity, InterfacePage<OrganizationEntity> page) throws Exception {
		ResultUtil<List<OrganizationDto>> resultUtil = new ResultUtil<List<OrganizationDto>>();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.organizationEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.organizationEntityMapper.findAll(entity));
		List<OrganizationDto> listDto = new ArrayList<OrganizationDto>();
		OrganizationDto dto = null;
		for (OrganizationEntity ce : page.getList()) {
			
			dto = new OrganizationDto(ce);
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
	 * 日       期: 2017-03-15 15:29:15
	 *</pre>
	 */
	@Override
	public OrganizationEntity findOne(OrganizationEntity entity)
			throws Exception {
		
		List<OrganizationEntity> list = this.organizationEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-15 15:29:15
	 *</pre>
	 */
	@Override
	public OrganizationEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<OrganizationEntity> list = this.organizationEntityMapper.findAll(
				new OrganizationEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
	
	/**
	 *<pre>
	 * 说       明: 删除
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: zhangyu
	 * 日       期: 2017-05-11 10:50:15
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil deleteOrganization(OrganizationEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		if(this.organizationEntityMapper.delete(entity.getTid())>0){
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
	}

	/**
	 *<pre>
	 * 说       明: 添加
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: zhangyu
	 * 日       期: 2017-05-11 10:50:15
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil insertOrganization(OrganizationEntity entity)
			throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				entity.getOrgName(),
				entity.getOrgCode())){
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("orgName,orgCode");
		}
		OrganizationEntity organizationEntity=new OrganizationEntity();
		organizationEntity.setOrgName(entity.getOrgName());
		if (!StringUtils.isBlank(this.findOne(organizationEntity))) {
			//机构名已存在
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_EXISTS);
		}
		entity.setTid(UUIDUtil.getUUID());
		if (this.organizationEntityMapper.insert(entity) > 0) {

			return resultUtil.setData(entity).setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}

	/**
	 *<pre>
	 * 说       明: 更新
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: zhangyu
	 * 日       期: 2017-05-11 10:50:15
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil updateOrganization(OrganizationEntity entity)
			throws Exception {
		ResultUtil resultUtil = new ResultUtil();	
		if (StringUtils.isBlank(entity)|| StringUtils.isBlankOr(entity.getTid())) {
			
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
		}
		if (StringUtils.isBlank(this.findById(entity.getTid()))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);//没找到相符的数据
		}
		if (this.organizationEntityMapper.updateById(entity) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
		
	}
}
