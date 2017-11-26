package com.yw.webplatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.dto.AppSecretDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.entity.importFile.PushAppDataEntity;
import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.IAppAccountService;

/**
 *<pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("appAccount")
@Controller
public class AppAccountController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IAppAccountService appAccountService;//APP账户
    
	/**
	 *<pre>
	 * 说       明: 查询数据
	 * @param request
	 * @param response
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 * 涉及版本: V2.0.0 
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
    @RequestMapping("findPage")
    @ResponseBody
    public ResultUtil findPageV200(HttpServletRequest request,
    		HttpServletResponse response, AppAccountEntity entity, InterfacePage page) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appAccountService.findPage(entity, page);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月21日下午3:53:05
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("insertAppAccount")
    @ResponseBody
    public ResultUtil insertAppAccountV200(HttpServletRequest request,
    		HttpServletResponse response, AppAccountEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appAccountService.insertAppAccount(entity);
    		{
    			if (resultUtil.getCode() == Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())) {
					
    				List list = new ArrayList();
    				Map<String, Object> map = new HashMap<String, Object>();
    				list.add(new AppSecretDto(entity.getAppId(), entity.getAppKey()));
    				map.put("id", list);
    				PushAppDataEntity pushApp = new PushAppDataEntity("user_add", map);
    				
    				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "user", pushApp);
				}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年4月21日下午3:53:05
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("removeAppAccount")
    @ResponseBody
    public ResultUtil removeAppAccountV200(HttpServletRequest request,
    		HttpServletResponse response, AppAccountEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.appAccountService.removeAppAccount(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
