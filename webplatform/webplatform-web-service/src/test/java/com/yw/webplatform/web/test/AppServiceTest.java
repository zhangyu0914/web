package com.yw.webplatform.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.AppDto;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.AppLicenseEntity;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.common.beansUtils.utils.validation.ValidationResult;
import com.yw.common.beansUtils.utils.validation.ValidationUtils;
/**
 *<pre>
 * 功       能: 应用表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:33
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class AppServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:33
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
	
	@Test
	public void findAppBindingDevice() {
		try {
			
			AppEntity entity = new AppEntity();
			ResultUtil resultUtil= this.appService.findAppBindingDevice(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findAppDevice() {
		try {
			InterfacePage<DeviceEntity> ihygeiaPage = new InterfacePage<DeviceEntity>();
			ResultUtil resultUtil= this.appService.findAppDevice("7081dee0-b455-4ee2-815a-79c46ebc686e",ihygeiaPage);
			
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
	 * 日       期: 2017年3月16日上午11:06:45
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findAppFunction() {
		try {
			
			InterfacePage<AppEntity> ihygeiaPage = new InterfacePage<AppEntity>();
			ResultUtil resultUtil= this.appService.findAppFunction("7f3d1ce9-8c88-4876-9fd1-f1ac7fa62929",ihygeiaPage);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void appIOReport() {
		try {
			
			AppEntity entity = new AppEntity();
			ResultUtil resultUtil= this.appService.appIOReport(entity);
			
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
	 * 日       期: 2017年3月21日上午10:50:55
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void appDetailReport() {
		try {
			
			AppLicenseEntity entity = new AppLicenseEntity();
			entity.setTid("e30760f7-c7e7-490b-84e1-3cb4e8c9b2c1");
			ResultUtil resultUtil= this.appService.appDetailReport(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void appReport() {
		try {
			
			AppLicenseEntity entity = new AppLicenseEntity();
			InterfacePage<AppLicenseEntity> page = new InterfacePage();
			ResultUtil resultUtil= this.appService.appReport(entity,page);
			
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
	 * 日       期: 2017-03-13 18:03:33
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			AppEntity entity = new AppEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setAppName(String.valueOf(1));
			entity.setIcon(String.valueOf(1));


			Integer resultUtil = this.appService.insert(entity);
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
	 * 日       期: 2017-03-13 18:03:33
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<AppDto>> list = this.appService.
					findPage(null, new InterfacePage<AppEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				AppEntity entity = new AppEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setAppName(String.valueOf(1));
			entity.setIcon(String.valueOf(1));

				
				Integer resultUtil = this.appService.update(entity);
				
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
	 * 日       期: 2017-03-13 18:03:33
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			AppEntity entity = new AppEntity();
			InterfacePage<AppEntity> ihygeiaPage = new InterfacePage<AppEntity>();
			
			ResultUtil<List<AppDto>> resultUtil= this.appService.findPage(entity, ihygeiaPage);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月7日下午3:12:12
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findVersionByAppId() {
		try {
			
			AppEntity entity = new AppEntity();
			entity.setAppId("8001.baby2");
			ResultUtil<List<AppDto>> resultUtil= this.appService.findVersionByAppId(entity);
			
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
	 * 日       期: 2017-03-13 18:03:33
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			AppEntity entity = new AppEntity();
			AppEntity resultUtil= this.appService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
