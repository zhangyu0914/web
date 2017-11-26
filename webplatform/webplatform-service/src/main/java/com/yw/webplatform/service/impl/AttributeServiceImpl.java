package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.beansUtils.dto.AttributeDto;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.DeviceModelAttEntity;
import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAttributeService;

/**
 * <pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 * </pre>
 */
@Service("attributeService")
public class AttributeServiceImpl extends BaseMapperImpl implements
		IAttributeService {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	/**
	 * <pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:30
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(AttributeEntity entity) throws Exception {

		entity.setTid(UUID.randomUUID().toString());
		return this.attributeEntityMapper.insert(entity);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer delete(String tid) throws Exception {

		return this.attributeEntityMapper.delete(tid);
	}

	/**
	 * <pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:30
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer update(AttributeEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid())
				|| StringUtils.isBlankOr(entity.getTid())) {

			return null;
		}
		return this.attributeEntityMapper.updateById(entity);
	}

	/**
	 * <pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:30
	 * </pre>
	 */
	@Override
	public ResultUtil<List<AttributeDto>> findPage(AttributeEntity entity,
			InterfacePage<AttributeEntity> page) throws Exception {
		ResultUtil<List<AttributeDto>> resultUtil = new ResultUtil<List<AttributeDto>>();
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.attributeEntityMapper.getCount(entity));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.attributeEntityMapper.findAll(entity));
		List<AttributeDto> listDto = new ArrayList<AttributeDto>();
		AttributeDto dto = null;
		for (AttributeEntity ce : page.getList()) {

			dto = new AttributeDto(ce);
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
	 * 日       期: 2017-03-13 18:03:30
	 * </pre>
	 */
	@Override
	public AttributeEntity findOne(AttributeEntity entity) throws Exception {

		List<AttributeEntity> list = this.attributeEntityMapper.findAll(entity);
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
	 * 日       期: 2017-03-13 18:03:30
	 * </pre>
	 */
	@Override
	public AttributeEntity findById(String tid) throws Exception {

		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<AttributeEntity> list = this.attributeEntityMapper
				.findAll(new AttributeEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil deleteAttribute(AttributeEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity.getProfileId())
				|| StringUtils.isBlankOr(entity.getProfileId())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("profileId");
		}
		ModelAttEntity modelAttEntity = new ModelAttEntity();
		modelAttEntity.setProfileId(entity.getProfileId());
		List<ModelAttEntity> list = this.modelAttEntityMapper
				.findAll(modelAttEntity);
		DeviceModelAttEntity deviceModelAttEntity = new DeviceModelAttEntity();
		if (!StringUtils.isBlank(list)) {
			for (ModelAttEntity modelAttEntity2 : list) {
				deviceModelAttEntity.setFkModelAttTid(modelAttEntity2.getTid());
				if(!StringUtils.isBlank(deviceModelAttEntityMapper.findAll(deviceModelAttEntity))){
					if(this.deviceModelAttEntityMapper.deleteByFK(deviceModelAttEntity)<1){
						return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
					}
				}
			}
			if (this.modelAttEntityMapper.deleteByFK(modelAttEntity) < 1) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
			}
		}
		if (StringUtils.isBlank(this.findById(entity.getProfileId()))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
		}
		if (this.attributeEntityMapper.delete(entity.getProfileId()) < 1) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}

	/**
	 * <pre>
	 * 说       明: 添加
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil insertAttribute(AttributeEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getAttName(),
						entity.getAttKey(), entity.getAttType())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("attName,attKey,attType");
		}
		AttributeEntity attributeEntity = new AttributeEntity();
		attributeEntity.setAttName(entity.getAttName());
		if (!StringUtils.isBlank(this.findOne(attributeEntity))) {
			// 属性名称已存在
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_EXISTS);
		}
		entity.setTid(UUIDUtil.getUUID());
		if (this.attributeEntityMapper.insert(entity) > 0) {

			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_INSERT);
	}

	/**
	 * <pre>
	 * 说       明: 更新
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil updateAttribute(AttributeEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlankOr(entity.getTid())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		if (StringUtils.isBlank(this.findById(entity.getTid()))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);// 没找到相符的数据
		}
		if (this.attributeEntityMapper.updateById(entity) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月12日 下午2:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public ResultUtil findAll(AttributeEntity entity, String modelId)
			throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<AttributeEntity> list = this.attributeEntityMapper.findAll(entity);
		if (StringUtils.isBlank(list) || list.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		if (!StringUtils.isBlank(modelId)) {
			ModelEntity me = new ModelEntity();
			me.setTid(modelId);
			List<ModelEntity> listModel = this.modelEntityMapper.findAll(me);
			if (listModel != null) {
				List<AttributeEntity> attList = listModel.get(0).getAttList();
				if (!StringUtils.isBlankOr(attList)) {
					Map<String, AttributeEntity> data = new HashMap<String, AttributeEntity>();
					for (AttributeEntity ae : attList) {
						data.put(ae.getTid(), ae);
					}
					for (AttributeEntity ae : list) {
						if (data.containsKey(ae.getTid())) {
							ae.setChecked(true); // 默认选中
						}
					}
				}
			}
		}
		Collections.sort(list, new Comparator<AttributeEntity>() {
			@Override
			public int compare(AttributeEntity o1, AttributeEntity o2) {
				if (!o1.getChecked()) {
					return 1;
				}
				return 0;
			}
		});
		List<AttributeDto> dtoList = new ArrayList<AttributeDto>();
		for (AttributeEntity data : list) {
			dtoList.add(new AttributeDto(data));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS).setData(dtoList);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:23:47
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<AttributeEntity> findAll(AttributeEntity entity)
			throws Exception {
		return this.attributeEntityMapper.findAll(null);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月23日下午4:23:44
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public List<AttributeEntity> findByProfileId(AttributeEntity entity)
			throws Exception {
		return this.attributeEntityMapper.findByProfileId(entity);
	}

}
