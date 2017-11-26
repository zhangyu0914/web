package com.yw.webplatform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.entity.IoDataEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.entity.PlatformDataEntity;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.api.ICPlusPlusService;
import com.yw.webplatform.api.IDeviceService;
import com.yw.webplatform.api.IDeviceLicenseService;


/**
 *<pre>
 * 功       能: C++服务接口
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
@Service("cplusPlusService")
public class CPlusPlusServiceImpl extends BaseMapperImpl  implements ICPlusPlusService{
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
	@Autowired
	private IDeviceService deviceService;//
	@Autowired
	private IDeviceLicenseService deviceLicenseService;//
	
	
}
