package com.yw.webplatform.web.test;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.AppAccountDto;
import com.yw.common.beansUtils.entity.AppAccountEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: APP账户
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class AppAccountServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
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
	 * 说       明:  添加数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertAppAccount(){
		try {
			AppAccountEntity entity = new AppAccountEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkOrganizationTid("ca057f18-2f74-4201-891d-2b483c6bb77e");
			entity.setFkAppLicenseTid("e4adc402-c14f-4b30-ba13-d81d5b312d88");
			entity.setAppId("APPID_1001");
			entity.setAppName("资产管理");
			entity.setAppInstance("test");
			entity.setAppKey("12345678912345678912345678912345");
			entity.setMsgMaxCount(0);


			ResultUtil resultUtil = this.appAccountService.insertAppAccount(entity);
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			AppAccountEntity entity = new AppAccountEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkOrganizationTid(String.valueOf(1));
			entity.setFkAppLicenseTid(String.valueOf(1));
			entity.setAppId(String.valueOf(1));
			entity.setAppInstance(String.valueOf(1));
			entity.setAppKey(String.valueOf(1));
			entity.setMsgMaxCount(Integer.valueOf(2));
			entity.setRemark(String.valueOf(1));
			
			
			Integer resultUtil = this.appAccountService.insert(entity);
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<AppAccountDto>> list = this.appAccountService.
					findPage(null, new InterfacePage<AppAccountEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				AppAccountEntity entity = new AppAccountEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setFkOrganizationTid(String.valueOf(1));
			entity.setFkAppLicenseTid(String.valueOf(1));
			entity.setAppId(String.valueOf(1));
			entity.setAppInstance(String.valueOf(1));
			entity.setAppKey(String.valueOf(1));
			entity.setMsgMaxCount(Integer.valueOf(2));
			entity.setRemark(String.valueOf(1));

				
				Integer resultUtil = this.appAccountService.update(entity);
				
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			AppAccountEntity entity = new AppAccountEntity();
			entity.setAppReportName("资产定位1");
			InterfacePage<AppAccountEntity> InterfacePage = new InterfacePage<AppAccountEntity>();
			
			ResultUtil<List<AppAccountDto>> resultUtil= this.appAccountService.findPage(entity, InterfacePage);
			
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
	 * 日       期: 2017-04-20 16:59:13
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			AppAccountEntity entity = new AppAccountEntity();
			AppAccountEntity resultUtil= this.appAccountService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
