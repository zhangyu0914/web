package com.yw.webplatform.web.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.DeviceDto;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.RouteEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 设备表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class DeviceServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
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
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:21:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findSetting() {
		try {
			
			DeviceEntity entity = new DeviceEntity();
			entity.setTid("1d92b208-e99f-404c-ac18-a7a7f95d3714");
			ResultUtil resultUtil= this.deviceService.findSetting(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void changeSetting() {
		try {
			List<DeviceEntity> list = new ArrayList<DeviceEntity>();
			
			DeviceEntity entity = new DeviceEntity();
			entity.setFkDeviceModelAttTid("1d92b208-e99f-404c-ac18-a7a7f95d3714");;
			entity.setAttValue("3");
			
			list.add(entity);
			ResultUtil resultUtil= this.deviceService.changeSetting(list);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:21:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void deviceViewReport() {
		try {
			
			AppEntity entity = new AppEntity();
			
			ResultUtil resultUtil= this.deviceService.deviceViewReport(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:21:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void gatewayReport() {
		try {
			
			AppEntity entity = new AppEntity();
			
			ResultUtil resultUtil= this.deviceService.gatewayReport(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月15日上午9:21:51
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findDeviceRoute() {
		try {
			
			RouteEntity entity = new RouteEntity();
			
			ResultUtil resultUtil= this.deviceService.findDeviceRoute(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *<pre>
	 * 说       明:  添加数据
	 * @throws  Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			DeviceEntity entity = new DeviceEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkModelTid(String.valueOf(1));
			entity.setSn(String.valueOf(1));
			entity.setEqName(String.valueOf(1));
			entity.setEqStatus(Integer.valueOf(2));


			Integer resultUtil = this.deviceService.insert(entity);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 更新数据
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<DeviceDto>> list = this.deviceService.
					findPage(null, new InterfacePage<DeviceEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				DeviceEntity entity = new DeviceEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setFkModelTid(String.valueOf(1));
			entity.setSn(String.valueOf(1));
			entity.setEqName(String.valueOf(1));
			entity.setEqStatus(Integer.valueOf(2));

				
				Integer resultUtil = this.deviceService.update(entity);
				
				Assert.assertNotNull(resultUtil);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 查询分页数量
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			DeviceEntity entity = new DeviceEntity();
//			entity.setModelCode("N600");
			InterfacePage<DeviceEntity> ihygeiaPage = new InterfacePage<DeviceEntity>();
			ResultUtil<List<DeviceDto>> resultUtil= this.deviceService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			DeviceEntity entity = new DeviceEntity();
			DeviceEntity resultUtil= this.deviceService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
