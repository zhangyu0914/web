package com.yw.appapi.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.yw.appapi.api.IPlatformAppService;
import com.yw.common.api.IConfigurationService;
import com.yw.webplatform.api.IAuthLogService;




/**
 *<pre>
 * 功   能: 功能说明 ：控制器共享类
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-9-13下午1:10:25
 * Q  Q: 308053847
 *</pre>
 */
public class BaseControllerTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	public IConfigurationService configurationService;//
	@Autowired
	public IPlatformAppService platformAppService;//
	@Autowired
	public IAuthLogService authLogService;//
	
	
}