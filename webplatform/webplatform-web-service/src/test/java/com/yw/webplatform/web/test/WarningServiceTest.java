package com.yw.webplatform.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.WarningDto;
import com.yw.common.beansUtils.entity.DeviceEntity;
import com.yw.common.beansUtils.entity.WarningEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 报警表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:30
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class WarningServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:30
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
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			WarningEntity entity = new WarningEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setSn(String.valueOf(1));
			entity.setEqType(Integer.valueOf(2));
			entity.setWaStatus(Integer.valueOf(2));
			entity.setRemark(String.valueOf(1));


			Integer resultUtil = this.warningService.insert(entity);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getWarningCount(){
		try {
			WarningEntity entity = new WarningEntity();
			ResultUtil resultUtil = this.warningService.getWarningCount(entity);
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
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<WarningDto>> list = this.warningService.
					findPage(null, new InterfacePage<WarningEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				WarningEntity entity = new WarningEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setSn(String.valueOf(1));
			entity.setEqType(Integer.valueOf(2));
			entity.setWaStatus(Integer.valueOf(2));
			entity.setRemark(String.valueOf(1));

				
				Integer resultUtil = this.warningService.update(entity);
				
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
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			WarningEntity entity = new WarningEntity();
			InterfacePage<WarningEntity> ihygeiaPage = new InterfacePage<WarningEntity>();
			
			ResultUtil<List<WarningDto>> resultUtil= this.warningService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			WarningEntity entity = new WarningEntity();
			WarningEntity resultUtil= this.warningService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
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
	 * 日       期: 2017-03-13 18:03:30
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertTestData(){
		try {
			
			for(DeviceEntity de : this.deviceService.findAll(null)){
				
				WarningEntity entity = new WarningEntity();
				
				entity.setTid(UUIDUtil.getUUID());
				
				entity.setSn(de.getSn());
				entity.setContent(de.getEqName()+"["+de.getSn()+"]" + "异常");
				entity.setEqType(de.getEqType());
				entity.setWaStatus(0);
				
				Integer resultUtil = this.warningService.insert(entity);
				Assert.assertNotNull(resultUtil);
			}
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
}
