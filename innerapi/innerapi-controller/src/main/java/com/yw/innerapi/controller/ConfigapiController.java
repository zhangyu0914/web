package com.yw.innerapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.entity.AppInstanceDevEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.IoDataEntity;
import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppSecretEntity;
import com.yw.common.beansUtils.entity.importFile.GetLicenseEntty;
import com.yw.common.beansUtils.entity.importFile.GetModelEntity;
import com.yw.common.beansUtils.entity.importFile.GetProfileEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.innerapi.api.IConfigapiService;

/**
 *<pre>
 * 功       能: 配置数据
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-05-24 13:01:21
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("configapi/v100")
@Controller
public class ConfigapiController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IConfigapiService configapiService;//配置数据
    
	/**
	 * <pre>
	 * 说       明: 获取型号配置数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午1:02:42
	 * Q    Q: 308053847
	 * </pre>
	 */
    @RequestMapping("model/get")
    @ResponseBody
    public ResultUtil modelGetV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody GetModelEntity data) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.modelGet(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 获取应用实例配置数据
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月24日下午1:02:42
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("app_instance/get")
    @ResponseBody
    public ResultUtil appInstanceGetV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody GetAppEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.appInstanceGet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 获取许可证配置数据
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月24日下午1:02:42
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("license/get")
    @ResponseBody
    public ResultUtil licenseGetV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody GetLicenseEntty entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.licenseGet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 获取PROFILE
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("profile/get")
    @ResponseBody
    public ResultUtil profileGetV200(HttpServletRequest request,HttpServletResponse response,@RequestBody GetProfileEntity entity) {
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.profileGet(entity);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 获取应用绑定设备列表
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("app_instance_dev/get")
    @ResponseBody
    public ResultUtil appInstanceDevGetV200(HttpServletRequest request,HttpServletResponse response,@RequestBody AppInstanceDevEntity entity) {
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.appInstanceDevGet(entity);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除型号
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("removeModel")
    @ResponseBody
    public AppResultUtil removeModelV200(HttpServletRequest request,HttpServletResponse response, @RequestBody String id,@RequestBody Integer version) {
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.configapiService.removeModel(id, version);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    
    /**
     * <pre>
     * 说       明: 删除应用
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("removeApp")
    @ResponseBody
    public AppResultUtil removeAppV200(HttpServletRequest request,HttpServletResponse response, @RequestBody String id) {
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.configapiService.removeAppInstance(id);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除许可证
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("removeLicense")
    @ResponseBody
    public AppResultUtil removeLicenseV200(HttpServletRequest request,HttpServletResponse response, @RequestBody String id) {
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.configapiService.removeLicense(id);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 删除PROFILE
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("removeProfile")
    @ResponseBody
    public AppResultUtil removeProfileV200(HttpServletRequest request,HttpServletResponse response, @RequestBody String profileId) {
    	AppResultUtil resultUtil = new AppResultUtil();
    	try {
    		resultUtil = this.configapiService.removeProfile(profileId);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 获取应用绑定设备列表
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("initData")
    @ResponseBody
    public ResultUtil initDataV200(HttpServletRequest request,HttpServletResponse response,String str) {
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.initData(str);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年6月13日上午11:53:18
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("app_secret/get")
    @ResponseBody
    public ResultUtil appSecretGetV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody GetAppSecretEntity entity) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.appSecretGet(entity);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
	 * <pre>
	 * 说       明: 推送设备状态信息
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月14日下午4:46:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("device_status/add")
    @ResponseBody
    public ResultUtil deviceStatusAddV200(HttpServletRequest request,
    		HttpServletResponse response, @RequestBody PushMQTTEntity entity ) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil = this.configapiService.deviceStatusAdd(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
	
	/**
	 * <pre>
	 * 说       明: NODEJS推送I/O、平台数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月3日下午4:29:31
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("io/add")
	@ResponseBody
	public ResultUtil ioAddV200(HttpServletRequest request,
			HttpServletResponse response, @RequestBody List<IoDataEntity> data) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		try {
			if (StringUtils.isBlank(data)) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_NO_FIND_DATA);
			}
    		List<IoDataEntity> list = JavaBeanUtil.jsonToList(JavaBeanUtil.javaBeanToString(data), IoDataEntity.class);
    		resultUtil = this.configapiService.ioAdd();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUtil;
	}
	
	/**
	 * 
	 * <pre>
	 * 说       明: 根据sn更新设备状态（t_device）
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月4日下午2:09:22
	 * Q    Q: 17789861157
	 * </pre>
	 */
	@RequestMapping("device/put")
	@ResponseBody
	public ResultUtil devicePutV200(HttpServletRequest request,
			HttpServletResponse response, @RequestBody DeviceEntity entity){
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.configapiService.devicePut();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUtil;
	}
	
	/**
	 *
	 * <pre>
	 * 说       明: 新增警告接口
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月5日下午1:52:43
	 * Q    Q: 17789861157
	 * </pre>
	 */
	@RequestMapping("warning/set")
	@ResponseBody
	public ResultUtil warningSetV200(HttpServletRequest request,
			HttpServletResponse response, @RequestBody WarningEntity entity){
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.configapiService.warningSet();
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
	 * 日       期: 2017年8月22日上午9:25:40
	 * Q    Q: 308053847
	 * </pre>
	 */
	@RequestMapping("testJava")
	@ResponseBody
	public ResultUtil testJavaV200(HttpServletRequest request,
			HttpServletResponse response){
		ResultUtil resultUtil = new ResultUtil();
		try {
			resultUtil = this.configapiService.testJava(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUtil;
	}
}
