package com.yw.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.api.IMwconfigService;
import com.yw.common.beansUtils.entity.MwconfigEntity;
import com.yw.common.beansUtils.entity.importFile.GetMwConfigEntity;
import com.yw.common.beansUtils.entity.importFile.PushDataEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;

/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:38
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("mwconfig")
@Controller
public class MwconfigController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IMwconfigService mwconfigService;//配置表
    
	
	/**
     * <pre>
     * 说       明: 获取配置数据
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月24日下午1:02:42
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("mw_config/get")
    @ResponseBody
    public ResultUtil mwConfigGetV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody GetMwConfigEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.mwconfigService.mwConfigGet(entity);
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
     * 日       期: 2017年6月27日下午5:39:59
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("updateMwConfig")
    @ResponseBody
	public ResultUtil updateMwConfigV200(HttpServletRequest request,HttpServletResponse response, MwconfigEntity entity) {
    	ResultUtil resultUtil = new ResultUtil();
		try {
			
			resultUtil = this.mwconfigService.updateMwConfig(entity);
			
			if (resultUtil.getCode() == Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())) {
				
				List data = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				data.add(entity.getTid());
				map.put("id",  data);
				PushDataEntity pushModel = new PushDataEntity("config_update", map);
				
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "config", pushModel);
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultUtil;
	}
    
    @RequestMapping(value = "mwConfigList")
    @ResponseBody
    public List<MwconfigEntity> mwConfigListV200(HttpServletRequest request,HttpServletResponse response, MwconfigEntity entity) throws Exception{
    	List<MwconfigEntity> mwConfigList=this.mwconfigService.mwConfigList(entity);
    	return mwConfigList;
    }
}
