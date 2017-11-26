package com.yw.webplatform.api;

import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UserLogEntity;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.service.IBaseService;



/**
 *<pre>
 * 功       能: 用户表
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:18
 * Q    Q: 308053847
 *</pre>
 */
public interface IUserInfoService extends IBaseService<UserInfoEntity, String> {

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月8日下午4:58:02
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil login(UserInfoEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月13日下午1:03:27
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil loginOut(UserInfoEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月25日下午4:18:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil insertUserInfo(UserInfoEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日上午10:40:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil deleteUserInfo(UserInfoEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年4月27日下午5:27:19
	 * Q    Q: 308053847
	 * </pre>
	 */
	public ResultUtil updateUserInfo(UserInfoEntity entity) throws Exception;

	/**
	 * <pre>
	 * 说       明: 校验用户是否存在
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月1日 下午4:38:34
	 * Q     Q: 982234234
	 * </pre>
	 */
	public ResultUtil userCheck(UserInfoEntity entity) throws Exception;
}
