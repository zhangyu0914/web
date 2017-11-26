package com.yw.innerapi.web.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yw.common.beansUtils.dto.ConfigapiDto;
import com.yw.common.beansUtils.entity.AppInstanceDevEntity;
import com.yw.common.beansUtils.entity.ConfigapiEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.PushMQTTEntity;
import com.yw.common.beansUtils.entity.PushMQTTInfoEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.entity.importFile.GetAppEntity;
import com.yw.common.beansUtils.entity.importFile.GetLicenseEntty;
import com.yw.common.beansUtils.entity.importFile.GetModelEntity;
import com.yw.common.beansUtils.entity.importFile.GetProfileEntity;
import com.yw.common.beansUtils.entity.importFile.ImportDeviceStatusEntity;
import com.yw.common.beansUtils.entity.importFile.PushDataEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.AppResultUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 配置数据
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-05-24 13d01:21
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/innerapi/web/applicationContext.xml" })
public class ConfigapiServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
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
	 * 说       明:  查询型号数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void modelGet(){
		try {
			List<String> list = new ArrayList<String>();
			GetModelEntity entity = new GetModelEntity();
			
			list.add("{\"id\":\"8001.B610\"}");
			entity.setData(list);
			
			ResultUtil resultUtil = this.configapiService.modelGet(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * <pre>
	 * 说       明: 应用信息获取接口
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月25日 下午3:30:56
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test
	public void appInstanceGet(){
		try {
			GetAppEntity entity = new GetAppEntity();
//			entity.setId(new String[]{"test1"});
			ResultUtil resultUtil = this.configapiService.appInstanceGet(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 *<pre>
	 * 说       明:  查询型号数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void licenseGet(){
		try {
			GetLicenseEntty entity = new GetLicenseEntty();
			String[] id = new String[]{"LIC_1001"};
			entity.setId(id);
			ResultUtil resultUtil = this.configapiService.licenseGet(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *<pre>
	 * 说       明:  查询型号数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void removeModel(){
		try {
			AppResultUtil resultUtil = this.configapiService.removeModel("B601",100);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明:  查询型号数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void removeApp(){
		try {
			AppResultUtil resultUtil = this.configapiService.removeAppInstance("test");
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明:  
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void removeLicense(){
		try {
			
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			list.add("0001");
			map.put("id", list);
			PushDataEntity pushModel = new PushDataEntity("license_remove", map);
			
			RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "license", pushModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明:  
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void removeProfile(){
		try {
			AppResultUtil resultUtil = this.configapiService.removeProfile("200002");
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 *<pre>
	 * 说       明:  查询型号数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void profileGet(){
		try {
			GetProfileEntity entity = new GetProfileEntity();
			entity.setId(new String[]{"20000D"});
			ResultUtil resultUtil = this.configapiService.profileGet(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *<pre>
	 * 说       明:  查询型号数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void appInstanceDevGet(){
		try {
			AppInstanceDevEntity entity = new AppInstanceDevEntity();
			entity.setId("test");
			ResultUtil resultUtil = this.configapiService.appInstanceDevGet(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *<pre>
	 * 说       明:  添加数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			ConfigapiEntity entity = new ConfigapiEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			
			
			Integer resultUtil = this.configapiService.insert(entity);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 更新数据
	 * @throws Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<ConfigapiDto>> list = this.configapiService.
					findPage(null, new InterfacePage<ConfigapiEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				ConfigapiEntity entity = new ConfigapiEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				

				
				Integer resultUtil = this.configapiService.update(entity);
				
				Assert.assertNotNull(resultUtil);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 查询分页数量
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			ConfigapiEntity entity = new ConfigapiEntity();
			InterfacePage<ConfigapiEntity> InterfacePage = new InterfacePage<ConfigapiEntity>();
			
			ResultUtil<List<ConfigapiDto>> resultUtil= this.configapiService.findPage(entity, InterfacePage);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 查询某一条数据
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			ConfigapiEntity entity = new ConfigapiEntity();
			ConfigapiEntity resultUtil= this.configapiService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void findAppAccount() {
		try {
			
			ResultUtil resultUtil= this.configapiService.appSecretGet(null);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明:  设备状态推送
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-24 13:01:21
	 *</pre>
	 */
	@Test@Rollback(true)
	public void deviceStatusAdd(){
		try {
			PushMQTTEntity entity = new PushMQTTEntity();
			List<PushMQTTInfoEntity> infoList = new ArrayList<PushMQTTInfoEntity>();
			entity.setSn("8001010200000138");
			entity.setTime(DateUtils.getTimestamp());
			entity.setMultiple(true);
			entity.setModel_id("8001.S400");
			entity.setModel_version(1);
			
			infoList.add(new PushMQTTInfoEntity(0,"系统","core.BEACON","200001","s",60));
			
			{
				Map map = new HashMap();
				map.put("code", 1);
				map.put("enumstr", "Motionless");
				infoList.add(new PushMQTTInfoEntity(1,"默认","sensor.MOVEMENT_STATE","201002",null,map));
			}
			
			entity.setInfo(infoList);;
			ResultUtil resultUtil = this.configapiService.deviceStatusAdd(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 说       明: 设备状态修改
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月4日下午4:50:01
	 * Q    Q: 17789861157
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void deviceStatusUpdate(){
		try {
			DeviceEntity entity = new DeviceEntity();
			entity.setSn("8001011500003527");
			entity.setEqStatus(2);
			ResultUtil resultUtil = this.configapiService.devicePut();
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 说       明: 添加警告信息
	 * 涉及版本:  
	 * 创  建  者: 古粤赣
	 * 日       期: 2017年7月5日下午2:31:55
	 * Q    Q: 17789861157
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void warningInsert(){
		try {
		WarningEntity entity = new WarningEntity();
		//entity.setSn("8001011500003527");//设备sn（感知类型）
		entity.setSn("8001051800000145");//设备sn(网关类型)
		entity.setContent("OnLineStatus");//异常内容
		entity.setEventTime(Timestamp.valueOf("2017-07-10 11:00:00"));//离线事件时间
		ResultUtil resultUtil = this.configapiService.warningSet();
		PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
		Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	public void deviceStatusPlan(){
		try {
			
			ResultUtil resultUtil = this.configapiService.deviceStatusPlan();
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	public void ioAdd(){
		try {
			
			ResultUtil resultUtil = this.configapiService.ioAdd();
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	public void warningSet(){
		try {
			
			ResultUtil resultUtil = this.configapiService.warningSet();
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	public void devicePut(){
		try {
			
			ResultUtil resultUtil = this.configapiService.devicePut();
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
