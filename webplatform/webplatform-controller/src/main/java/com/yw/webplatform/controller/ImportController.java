package com.yw.webplatform.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.entity.importFile.PushDataEntity;
import com.yw.common.beansUtils.entity.importFile.PushModelDataEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.ip.IpUtil;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.IAttributeService;
import com.yw.webplatform.api.IImportService;
import com.yw.webplatform.api.ILicenseService;
import com.yw.webplatform.api.IModelService;

/**
 *<pre>
 * 功       能: 导入基础数据
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 21:44:07
 * Q    Q: 308053847
 *</pre>
 */
@RequestMapping("import")
@Controller
public class ImportController extends BaseController{
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	@Autowired
    public IImportService importService;//导入
	@Autowired
	public IAttributeService attributeService;//
	@Autowired
	public IModelService modelService;//
	@Autowired
	public ILicenseService licenseService;//
    

    /**
     * <pre>
     * 说       明: 导入型号
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("importModel")
    @ResponseBody
	public ResultUtil importModelV200(HttpServletRequest request,HttpServletResponse response) {
    	ResultUtil resultUtil = new ResultUtil();
		try {
			
			MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mul.getFiles("uploadFile");
			List<AppLicenseEntity> importPatientList = new ArrayList<AppLicenseEntity>();
			if (StringUtils.isBlank(files)
					|| files.isEmpty()) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
			}
			MultipartFile fileItem = files.iterator().next();
			String content = FileUtil.readFile(fileItem.getInputStream());
			String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
			resultUtil = this.importService.importModel(content, batNo);
			
			List<ModelEntity> modelList = this.modelService.findAll(null);
			for(ModelEntity str : modelList){
				
				List data = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", str.getModelNo());
				map.put("version", str.getModelVersion());
				data.add(map);
				PushModelDataEntity pushModel = new PushModelDataEntity("model_add", data);
				
				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "model", pushModel);
				
			}

			List<AttributeEntity> profileList = this.attributeService.findAll(null);
			for(AttributeEntity str : profileList){
    			
    			List data = new ArrayList();
    			Map<String, Object> map = new HashMap<String, Object>();
    			data.add(str.getProfileId());
    			map.put("id", data);
    			PushDataEntity pushModel = new PushDataEntity("profile_add", map);
    			
    			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
    			
    		}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultUtil;
	}
    
    
    /**
     * <pre>
     * 说       明: 导入应用
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("importApp")
    @ResponseBody
    public ResultUtil importAppV200(HttpServletRequest request,HttpServletResponse response) {
    	ResultUtil resultUtil = new ResultUtil();
    	String batNo = "";
    	try {
    		MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mul.getFiles("uploadFile");
			List<AppLicenseEntity> importPatientList = new ArrayList<AppLicenseEntity>();
			if (StringUtils.isBlank(files)
					|| files.isEmpty()) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
			}
			MultipartFile fileItem = files.iterator().next();
			String content = FileUtil.readFile(fileItem.getInputStream());
			batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
    		resultUtil = this.importService.importApp(content, batNo);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		resultUtil.setData(batNo);
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 导入许可证
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("importLicense")
    @ResponseBody
    public ResultUtil importLicenseV200(HttpServletRequest request,HttpServletResponse response) {
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mul.getFiles("uploadFile");
			List<AppLicenseEntity> importPatientList = new ArrayList<AppLicenseEntity>();
			if (StringUtils.isBlank(files)
					|| files.isEmpty()) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
			}
			MultipartFile fileItem = files.iterator().next();
			String content = FileUtil.readFile(fileItem.getInputStream());
			String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
    		resultUtil = this.importService.importLicense(content, batNo, new HashMap<String, ModelEntity>());
    		List<LicenseEntity> licenseList = this.licenseService.findAll(null);

    		for(LicenseEntity licenseId : licenseList){
    			
    			List data = new ArrayList();
    			Map<String, Object> map = new HashMap<String, Object>();
    			data.add(licenseId.getLicNo());
    			map.put("id", data);
    			PushDataEntity pushModel = new PushDataEntity("license_add", map);
    			
    			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushModel);
    			
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 导入PROFILE
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("importProfile")
    @ResponseBody
    public ResultUtil importProfileV200(HttpServletRequest request,HttpServletResponse response) {
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
    		List<MultipartFile> files = mul.getFiles("uploadFile");
    		List<AppLicenseEntity> importPatientList = new ArrayList<AppLicenseEntity>();
    		if (StringUtils.isBlank(files)
    				|| files.isEmpty()) {
    			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
    		}
    		MultipartFile fileItem = files.iterator().next();
    		String content = FileUtil.readFile(fileItem.getInputStream());
    		resultUtil = this.importService.importProfile(content);
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * <pre>
     * 说       明: 导入PROFILE
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年5月10日下午5:33:32
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("importProfileFile")
    @ResponseBody
    public ResultUtil importProfileFileV200(HttpServletRequest request,HttpServletResponse response, String path) {
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
    		resultUtil = this.importService.importProfileFile(path, batNo);
    		
    		List<AttributeEntity> profileList = this.attributeService.findAll(null);
    		for(AttributeEntity str : profileList){
    			
    			List data = new ArrayList();
    			Map<String, Object> map = new HashMap<String, Object>();
    			data.add(str.getProfileId());
    			map.put("id", data);
    			PushDataEntity pushModel = new PushDataEntity("profile_add", map);
    			
    			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
    			
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	return resultUtil;
    }
    
    /**
     * 
     * <pre>
     * 说       明: 
     * 涉及版本: V2.0.0  
     * 创  建  者: Vickey
     * 日       期: 2017年6月8日上午11:18:39
     * Q    Q: 308053847
     * </pre>
     */
    @RequestMapping("importFile")
    @ResponseBody
    public ResultUtil importFileV200(HttpServletRequest request,HttpServletResponse response) {
    	ResultUtil resultUtil = new ResultUtil();
    	String batNo = "";
    	try {
    		String zipPassword = "mlink123";
    		batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS)+"["+IpUtil.getIp(request)+"]";
    		MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = mul.getFiles("uploadFile");
			List<AppLicenseEntity> importPatientList = new ArrayList<AppLicenseEntity>();
			if (StringUtils.isBlank(files)
					|| files.isEmpty()) {
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS).setData("uploadFile");
			}
			MultipartFile fileItem = files.iterator().next();
			String path = ConfigurationEnum.FILE_PATH.getValue();
			new File(path).mkdirs();
			File file = new File(path+UUIDUtil.getUUID()+"_"+fileItem.getOriginalFilename());
			fileItem.transferTo(file);
			
    		resultUtil = this.importService.importFile(file.getPath(), batNo, zipPassword);
    		{
    			List<ModelEntity> modelList = this.modelService.findAll(null);
    			for(ModelEntity str : modelList){
    				
    				List data = new ArrayList();
    				Map<String, Object> map = new HashMap<String, Object>();
    				map.put("id", str.getModelNo());
    				map.put("version", str.getModelVersion());
    				data.add(map);
    				PushModelDataEntity pushModel = new PushModelDataEntity("model_add", data);
    				
    				RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "model", pushModel);
    				
    			}
    		}
    		{
    			Map<String, Object> map = new HashMap<String, Object>();
    			PushDataEntity pushModel = new PushDataEntity("profile_refresh", map);
    			
    			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
    		}
    		{
    			List<LicenseEntity> licenseList = this.licenseService.findAll(null);

        		for(LicenseEntity licenseId : licenseList){
        			
        			List data = new ArrayList();
        			Map<String, Object> map = new HashMap<String, Object>();
        			data.add(licenseId.getLicNo());
        			map.put("id", data);
        			PushDataEntity pushModel = new PushDataEntity("license_add", map);
        			
        			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushModel);
        			
        		}
    		}
    	} catch (Exception ex) {
    		ex.printStackTrace();
    		log.error("["+batNo+"]"+ex.toString());
    		resultUtil.setData(batNo);
    	}
    	return resultUtil;
    }
}
