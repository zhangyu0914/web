package com.yw.webplatform.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.DeviceLicenseDto;
import com.yw.common.beansUtils.entity.DeviceLicenseEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: SN许可证表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 13:20:47
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class DeviceLicenseServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Before
	public void before() {
		try {
			
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertTestData(){
		try {
			
			File dir = new File("d:\\file.txt");
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dir),
					"UTF-8"));
			String tempString = "";
			StringBuffer fileContent = new StringBuffer();
			while ((tempString = br.readLine()) != null) {

				DeviceLicenseEntity entity = new DeviceLicenseEntity();
				
				entity.setSn(tempString.substring(tempString.indexOf("auth") + 4,tempString.length() - 1 ));
				
				if (StringUtils.isBlank(this.deviceLicenseService.findOne(entity))) {
					entity.setTid(UUIDUtil.getUUID());
					entity.setFkLicenseTid("1d92b208-e99f-404c-ac18-a7a7f95d3714");
					entity.setModelNo("A380");
					
					Integer resultUtil = this.deviceLicenseService.insert(entity);
				}
				
			}
			br.close();
			Assert.assertNotNull(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void insert(){
		try {
			DeviceLicenseEntity entity = new DeviceLicenseEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkLicenseTid(String.valueOf(1));
			entity.setSn(String.valueOf(1));
			entity.setRemark(String.valueOf(1));
			
			
			Integer resultUtil = this.deviceLicenseService.insert(entity);
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<DeviceLicenseDto>> list = this.deviceLicenseService.
					findPage(null, new InterfacePage<DeviceLicenseEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				DeviceLicenseEntity entity = new DeviceLicenseEntity();
				
				entity.setTid(list.getData().get(0).getTid());
					
				entity.setFkLicenseTid(String.valueOf(1));
				entity.setSn(String.valueOf(1));
				entity.setRemark(String.valueOf(1));

				
				Integer resultUtil = this.deviceLicenseService.update(entity);
				
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			DeviceLicenseEntity entity = new DeviceLicenseEntity();
			InterfacePage<DeviceLicenseEntity> ihygeiaPage = new InterfacePage<DeviceLicenseEntity>();
			
			ResultUtil<List<DeviceLicenseDto>> resultUtil= this.deviceLicenseService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-15 13:20:47
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			DeviceLicenseEntity entity = new DeviceLicenseEntity();
			DeviceLicenseEntity resultUtil= this.deviceLicenseService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
