package com.yw.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yw.common.api.IMwconfigService;
import com.yw.common.beansUtils.dto.MwconfigDto;
import com.yw.common.beansUtils.entity.MwconfigEntity;
import com.yw.common.beansUtils.entity.importFile.GetMwConfigEntity;
import com.yw.common.beansUtils.entity.importFile.MwCfgEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;


/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:37
 * Q    Q: 308053847
 *</pre>
 */
@Service("mwconfigService")
public class MwconfigServiceImpl extends BaseMapperImpl  implements IMwconfigService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	
	/**
	 * <pre>
	 * 说       明: 添加配置
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月27日下午5:38:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public ResultUtil updateMwConfig(MwconfigEntity entity) throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(entity)
				|| StringUtils.isBlank(entity.getTid())) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("tid");
			
		}
		if (this.mwconfigEntityMapper.updateById(entity) > 0) {
			return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
		}
		return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_UPDATE);
	}
	
	/**
	 * <pre>
	 * 说       明: 获取型号配置数据
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:05:41
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Override
	public ResultUtil mwConfigGet(GetMwConfigEntity entity) throws Exception {

		ResultUtil resultUtil = new ResultUtil();		
		String[] ids = null;
		if (!StringUtils.isBlank(entity)
				&& !StringUtils.isBlank(entity.getId())
				&& entity.getId().length > 0) {
			
			List<String> list = new ArrayList<String>();;
			for(String str : entity.getId()){
				
				if(!StringUtils.isBlank(str)){
					list.add(str);
				}
			}
			ids =  (String[])list.toArray(new String[list.size()]);
		}
		List<MwCfgEntity> modelList = this.mwconfigEntityMapper.findMwConfig(ids);
		return resultUtil.setData(modelList).setCode(ErrorTypeEnum.SUCCESS);
	}
	
	/**
	 *<pre>
	 * 说      明:  添加数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-06-27 17:22:37
	 *</pre>
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insert(MwconfigEntity entity) throws Exception {
		
		entity.setTid(UUID.randomUUID().toString());
		return this.mwconfigEntityMapper.insert(entity);
	}
	
	/**
	 *<pre>
	 * 说      明:  修改数据
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017-06-27 17:22:37
	 *</pre>
	 */
	@Transactional(rollbackFor=Exception.class)
	@Override
	public Integer update(MwconfigEntity entity) throws Exception {
		if (StringUtils.isBlank(entity.getTid()) || StringUtils.isBlankOr(entity.getTid())) {
			
			return null;
		}
		return this.mwconfigEntityMapper.updateById(entity);
	}
	
	/**
	 *<pre>
	 * 说       明: 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-06-27 17:22:37
	 *</pre>
	 */
	@Override
	public ResultUtil findPage(MwconfigEntity entity, InterfacePage<MwconfigEntity> page) throws Exception {
		ResultUtil resultUtil = new ResultUtil();		
		//判断分页参数
		ResultUtil pageResult = InterfacePage.validatePage(page);
		if (pageResult != null) {
			return pageResult;
		}
		//获取总数量
		page.setTotalCount(this.mwconfigEntityMapper.getCount(entity));
		//获取分页后的数据
		PageHelper.startPage(page.getPageNo(), page.getPageSize());
		page.setList(this.mwconfigEntityMapper.findAll(entity));
		List<MwconfigDto> listDto = new ArrayList<MwconfigDto>();
		MwconfigDto dto = null;
		for (MwconfigEntity ce : page.getList()) {
			
			dto = new MwconfigDto(ce);
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
	 * 日       期: 2017-06-27 17:22:37
	 *</pre>
	 */
	@Override
	public MwconfigEntity findOne(MwconfigEntity entity)
			throws Exception {
		
		List<MwconfigEntity> list = this.mwconfigEntityMapper.findAll(entity);
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
	 * 日       期: 2017-06-27 17:22:37
	 *</pre>
	 */
	@Override
	public MwconfigEntity findById(String tid) throws Exception {
		
		if (StringUtils.isBlank(tid)) {
			return null;
		}
		List<MwconfigEntity> list = this.mwconfigEntityMapper.findAll(
				new MwconfigEntity(tid));
		if (StringUtils.isBlankOr(list) || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0  
	 * 创  建  者: zhangyu
	 * 日       期: 2017年7月19日 下午4:57:52
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Override
	public List<MwconfigEntity> mwConfigList(MwconfigEntity entity) throws Exception {
		List<MwconfigEntity> list=this.mwconfigEntityMapper.findAll(entity);
		return list;
	}
}
