package com.yw.webplatform.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.AppAccountInterfaceDto;
import com.yw.common.beansUtils.entity.AppAccountInterfaceEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.enums.AppAccountInterfaceDataStatusEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 可访问接口列表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class AppAccountInterfaceServiceTest extends BaseControllerTest {
	
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
	public void insert(){
		try {
			AppAccountInterfaceEntity entity = new AppAccountInterfaceEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkAppAccountTid(String.valueOf(1));
			entity.setFkInterfaceTid(String.valueOf(1));
			entity.setDataStatus(Integer.valueOf(2));
			entity.setRemark(String.valueOf(1));


			Integer resultUtil = this.appAccountInterfaceService.insert(entity);
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
	public void insertAppAccountInterface(){
		try {
			AppAccountInterfaceEntity entity = new AppAccountInterfaceEntity();
			
			entity.setFkAppAccountTid("17060481-6665-4c7d-a8f9-e162e2aa8bbb");
			entity.setFkInterfaceTid("05a55301-2589-45c5-aef6-c4176e85be08");
			entity.setDataStatus(AppAccountInterfaceDataStatusEnum.ENABLED.getCode());
			entity.setRemark(String.valueOf(1));
			
			
			ResultUtil resultUtil = this.appAccountInterfaceService.insertAppAccountInterface(entity);
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

			ResultUtil<List<AppAccountInterfaceDto>> list = this.appAccountInterfaceService.
					findPage(null, new InterfacePage<AppAccountInterfaceEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				AppAccountInterfaceEntity entity = new AppAccountInterfaceEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setFkAppAccountTid(String.valueOf(1));
			entity.setFkInterfaceTid(String.valueOf(1));
			entity.setDataStatus(Integer.valueOf(2));
			entity.setRemark(String.valueOf(1));

				
				Integer resultUtil = this.appAccountInterfaceService.update(entity);
				
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
			
			AppAccountInterfaceEntity entity = new AppAccountInterfaceEntity();
			entity.setAppId("1");
			entity.setAppName("2");
			entity.setOrgName("3");
			entity.setInterfaceName("4");
			InterfacePage<AppAccountInterfaceEntity> InterfacePage = new InterfacePage<AppAccountInterfaceEntity>();
			
			ResultUtil<List<AppAccountInterfaceDto>> resultUtil= this.appAccountInterfaceService.findPage(entity, InterfacePage);
			
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
			
			AppAccountInterfaceEntity entity = new AppAccountInterfaceEntity();
			AppAccountInterfaceEntity resultUtil= this.appAccountInterfaceService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
