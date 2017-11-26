package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.OrganizationEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:15
 * Q    Q: 308053847
 *</pre>
 */
public interface IOrganizationService extends IBaseService<OrganizationEntity, String> {
	/**
	 *<pre>
	 * 说       明: 删除
	 * 涉及版本: V1.0.0
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月11日 上午10:47:25
	 * Q     Q: 982234234
	 *</pre>
	 */
	public ResultUtil deleteOrganization(OrganizationEntity entity) throws Exception;

	/**
	 *<pre>
	 * 说       明: 添加
	 * 涉及版本: V1.0.0
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月11日 上午10:59:27
	 * Q     Q: 982234234
	 *</pre>
	 */
	public ResultUtil insertOrganization(OrganizationEntity entity) throws Exception;

	/**
	 *<pre>
	 * 说       明: 更新
	 * 涉及版本: V1.0.0
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月11日 上午10:59:10
	 * Q     Q: 982234234
	 *</pre>
	 */
	public ResultUtil updateOrganization(OrganizationEntity entity) throws Exception;
}
