package com.yw.appapi.web.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.PlatformAppDto;
import com.yw.common.beansUtils.entity.ControlEntity;
import com.yw.common.beansUtils.entity.PlatformAppEntity;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.random.RandomTool;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
/**
 *<pre>
 * 功       能: 平台提供APP访问接口
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/appapi/web/applicationContext.xml" })
public class PlatformAppServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Before
	public void before() {
		try {
			this.configurationService.initData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void deviceGet() {
		try {
			
			AppResultUtil<List<PlatformAppDto>> resultUtil= this.platformAppService.deviceGet("8001051800000039");
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void historyDeviceDataGet() {
		try {
			
			PlatformAppEntity entity = new PlatformAppEntity();
			entity.setSn("8001011800010175");
			entity.setStart_time(DateUtils.getSysStringTime(null));
			entity.setEnd_time(DateUtils.getSysStringTime(null));
			AppResultUtil<List<PlatformAppDto>> resultUtil= this.platformAppService.historyDeviceDataGet(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void onofflineDeviceDataGet() {
		try {
			
			PlatformAppEntity entity = new PlatformAppEntity();
			entity.setSn("8001011800010175");
			entity.setStart_time(DateUtils.getSysStringTime(null));
			entity.setEnd_time(DateUtils.getSysStringTime(null));
			AppResultUtil<List<PlatformAppDto>> resultUtil= this.platformAppService.historyDeviceDataGet(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void historyDescendingOrderGet() {
		try {
			
			PlatformAppEntity entity = new PlatformAppEntity();
			entity.setApp_instance("test_app_instance");
			entity.setStart_time(DateUtils.getSysStringTime(null));
			entity.setEnd_time(DateUtils.getSysStringTime(null));
			AppResultUtil<List<PlatformAppDto>> resultUtil= this.platformAppService.historyDescendingOrderGet(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void appAuth() {
		try {
			
			PlatformAppEntity entity = new PlatformAppEntity();
			entity.setApp_instance("test");
			entity.setAppkey("948E0D9CC1B402F853AFB4C4AFC1F576");
			AppResultUtil resultUtil= this.authLogService.auth(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void devicebindSet() {
		try {
			
			String sn = "8001011800010175";
			String app_instance = "test";
			AppResultUtil resultUtil= this.platformAppService.devicebindSet(app_instance, sn, "fadb5ba0-8d3a-4ea2-a103-d8905fed55a6");
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void devicebindDel() {
		try {
			
			AppResultUtil resultUtil= this.platformAppService.devicebindDel("test", "8001011800010175", null);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test@Rollback(true)
	public void appstatusSet() {
		try {
			
			PlatformAppEntity entity = new PlatformAppEntity();
			entity.setStatus(0);
			entity.setApp_instance("test");
			entity.setSysload(23);
			entity.setMemory(RandomTool.getRandom(0, 100));
			entity.setDisk(RandomTool.getRandom(0, 100));
			entity.setCpu(RandomTool.getRandom(0, 100));
			
			AppResultUtil resultUtil= this.platformAppService.appstatusSet(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test@Rollback(true)
	public void appstatusPost() {
		try {
			
			PlatformAppEntity entity = new PlatformAppEntity();
			Map map = new HashMap();
			
			map.put("cel", 1);
			map.put("acc", 2);
			
			entity.setStatus(0);
			entity.setApp_instance("test");
			entity.setSysload(23);
			entity.setMemory(RandomTool.getRandom(0, 100));
			entity.setDisk(RandomTool.getRandom(0, 100));
			entity.setCpu(RandomTool.getRandom(0, 100));
			entity.setCustomdata(map);
			
			AppResultUtil resultUtil= this.platformAppService.appstatusPost(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void deviceRealTimeGet() {
		try {
			
			AppResultUtil resultUtil= this.platformAppService.deviceRealTimeGet("8001011800010175");
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void control() {
		try {
			
			ControlEntity control = new ControlEntity();
			Object obj = this.platformAppService.deviceControl(control);
			System.out.println(JavaBeanUtil.javaBeanToMap(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
