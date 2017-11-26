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
import com.yw.common.beansUtils.dto.ModelDto;
import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.DeviceModelAttEntity;
import com.yw.common.beansUtils.entity.ModelAttEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IModelService;

/**
 * <pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 * </pre>
 */
@Service("modelService")
public class ModelServiceImpl extends BaseMapperImpl implements IModelService {
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	/**
	 * <pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(ModelEntity entity) throws Exception {

		entity.setTid(UUID.randomUUID().toString());
		return this.modelEntityMapper.insert(entity);
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer delete(String tid) throws Exception {

		return this.modelEntityMapper.delete(tid);
	}

	/**
	 * <pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Integer update(ModelEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid())
				|| StringUtils.isBlankOr(entity.getTid())) {

			return null;
		}
		return this.modelEntityMapper.updateById(entity);
	}

	/**
	 * <pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Override
	public ResultUtil<List<ModelDto>> findPage(ModelEntity entity,
			InterfacePage<ModelEntity> page) throws Exception {
		ResultUtil<List<ModelDto>> resultUtil = new ResultUtil<List<ModelDto>>();
		// 判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		// 获取总数量
		page.setTotalCount(this.modelEntityMapper.getCount(entity));
		// 获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.modelEntityMapper.findAll(entity));
		List<ModelDto> listDto = new ArrayList<ModelDto>();
		ModelDto dto = null;
		for (ModelEntity ce : page.getList()) {

			dto = new ModelDto(ce);
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
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Override
	public ModelEntity findOne(ModelEntity entity) throws Exception {

		List<ModelEntity> list = this.modelEntityMapper.findAll(entity);
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月27日下午2:13:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil findAll(ModelEntity entity, String attId)
			throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<ModelEntity> list = this.modelEntityMapper.findAll(entity);
		if (StringUtils.isBlank(list) || list.isEmpty()) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		if (!StringUtils.isBlank(attId)) {
			AttributeEntity ae = new AttributeEntity();
			ae.setTid(attId);
			List<AttributeEntity> listAtt = this.attributeEntityMapper
					.findAll(ae);
			if (listAtt != null) {
				List<ModelEntity> modelList = listAtt.get(0).getModelList();
				if (!StringUtils.isBlankOr(modelList)) {
					Map<String, ModelEntity> data = new HashMap<String, ModelEntity>();
					for (ModelEntity me : modelList) {
						data.put(me.getTid(), me);
					}
					for (ModelEntity me : list) {
						if (data.containsKey(me.getTid())) {
							me.setChecked(true); // 默认选中
						}
					}
				}
			}
		}
		Collections.sort(list, new Comparator<ModelEntity>() {
			@Override
			public int compare(ModelEntity o1, ModelEntity o2) {
				if (!o1.getChecked()) {
					return 1;
				}
				return 0;
			}
		});
		List<ModelDto> dtoList = new ArrayList<ModelDto>();
		for (ModelEntity data : list) {
			dtoList.add(new ModelDto(data));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS).setData(dtoList);
	}

	/**
	 * <pre>
	 * 说       明: 根据ID查询数据
	 * @param tid
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Override
	public ModelEntity findById(String tid) throws Exception {

		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<ModelEntity> list = this.modelEntityMapper
				.findAll(new ModelEntity(tid));
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
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResultUtil deleteModel(ModelEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity.getTid())
				|| StringUtils.isBlankOr(entity.getTid())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS)
					.setData("tid");
		}
		AppFunctionEntity ae=new AppFunctionEntity();
		ae.setFkModelTid(entity.getTid());
		List<AppFunctionEntity> appFunctionEntityList=appFunctionEntityMapper.findAll(ae);
		if(!StringUtils.isBlank(appFunctionEntityList)){
			if(this.appFunctionEntityMapper.deleteByFK(ae)<1){
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
			}
		}
		
		ModelAttEntity me=new ModelAttEntity();
		me.setFkModelTid(entity.getTid());
		List<ModelAttEntity> modelAttEntityList=modelAttEntityMapper.findAll(me);
		DeviceModelAttEntity da=new DeviceModelAttEntity();
		if(!StringUtils.isBlank(modelAttEntityList)){
			for (ModelAttEntity modelAttEntity : modelAttEntityList) {
				da.setFkModelAttTid(modelAttEntity.getTid());
				if(!StringUtils.isBlank(deviceModelAttEntityMapper.findAll(da))){
					if(this.deviceModelAttEntityMapper.deleteByFK(da)<1){
						return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
					}
				}
			}
			if(this.modelAttEntityMapper.deleteByFK(me)<1){
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
			}
		}
		if (this.modelEntityMapper.delete(entity.getTid()) < 1) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
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
	@Transactional(rollbackFor = Exception.class)
	@Override
	public ResultUtil updateModel(ModelEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)|| StringUtils.isBlankOr(entity.getTid())) {

			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS);
		}
		if (StringUtils.isBlank(this.findById(entity.getTid()))) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);// 没找到相符的数据
		}
		if (this.modelEntityMapper.updateById(entity) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
	}
	
	@Override
	public List<ModelEntity> findAll(ModelEntity entity) throws Exception {

		return this.modelEntityMapper.findAll(entity);
	}
	
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 下午10:10:06
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public ResultUtil findAllModel(ModelEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<ModelEntity> list=this.modelEntityMapper.findAllModel(entity);
		List<ModelDto> dtoList = new ArrayList<ModelDto>();
		for (ModelEntity data : list) {
			dtoList.add(new ModelDto(data));
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS).setData(dtoList);
	}
}
