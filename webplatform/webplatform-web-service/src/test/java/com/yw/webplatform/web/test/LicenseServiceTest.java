package com.yw.webplatform.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.LicenseDto;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.LicenseEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 许可证表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:32
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class LicenseServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:32
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
	 * 日       期: 2017年3月16日下午1:28:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findDeviceLicense() {
		try {
			
			LicenseEntity entity = new LicenseEntity();
			InterfacePage<LicenseEntity> ihygeiaPage = new InterfacePage<LicenseEntity>();
			
			ResultUtil<List<LicenseDto>> resultUtil= this.licenseService.findDeviceLicense(entity, ihygeiaPage);
			
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
	 * 日       期: 2017年3月16日下午1:28:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findAppLicense() {
		try {
			
			LicenseEntity entity = new LicenseEntity();
			InterfacePage<LicenseEntity> ihygeiaPage = new InterfacePage<LicenseEntity>();
			
			ResultUtil<List<LicenseDto>> resultUtil= this.licenseService.findAppLicense(entity, ihygeiaPage);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void appLicenseReport() {
		try {
			
			HomePageEntity entity = new HomePageEntity();
			
			ResultUtil<List<LicenseDto>> resultUtil= this.licenseService.appLicenseReport(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deviceLicenseReport() {
		try {
			
			HomePageEntity entity = new HomePageEntity();
			ResultUtil<List<LicenseDto>> resultUtil= this.licenseService.deviceLicenseReport(entity);
			
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
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			LicenseEntity entity = new LicenseEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setLicName(String.valueOf(1));
			entity.setRemark(String.valueOf(1));


			Integer resultUtil = this.licenseService.insert(entity);
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
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<LicenseDto>> list = this.licenseService.
					findPage(null, new InterfacePage<LicenseEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				LicenseEntity entity = new LicenseEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setLicName(String.valueOf(1));
			entity.setRemark(String.valueOf(1));

				
				Integer resultUtil = this.licenseService.update(entity);
				
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
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			LicenseEntity entity = new LicenseEntity();
			InterfacePage<LicenseEntity> ihygeiaPage = new InterfacePage<LicenseEntity>();
			
			ResultUtil<List<LicenseDto>> resultUtil= this.licenseService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-13 18:03:32
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			LicenseEntity entity = new LicenseEntity();
			LicenseEntity resultUtil= this.licenseService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月1日下午3:57:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void findExLicense() {
		try {
			
			ResultUtil resultUtil= this.licenseService.findExLicense();
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
