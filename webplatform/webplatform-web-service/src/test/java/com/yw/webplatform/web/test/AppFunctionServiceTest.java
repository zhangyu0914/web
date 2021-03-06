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

import com.yw.common.beansUtils.dto.AppFunctionDto;
import com.yw.common.beansUtils.entity.AppFunctionEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 应用权限
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 14:57:31
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class AppFunctionServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 14:57:31
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
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			AppFunctionEntity entity = new AppFunctionEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkAppTid(String.valueOf(1));
			entity.setFkModelTid(String.valueOf(1));


			Integer resultUtil = this.appFunctionService.insert(entity);
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
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<AppFunctionDto>> list = this.appFunctionService.
					findPage(null, new InterfacePage<AppFunctionEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				AppFunctionEntity entity = new AppFunctionEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
				entity.setFkAppTid(String.valueOf(1));
				entity.setFkModelTid(String.valueOf(1));

				
				Integer resultUtil = this.appFunctionService.update(entity);
				
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
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			AppFunctionEntity entity = new AppFunctionEntity(null, "1","2","3","4", 5, "6", 1);
			InterfacePage<AppFunctionEntity> ihygeiaPage = new InterfacePage<AppFunctionEntity>();
			
			ResultUtil<List<AppFunctionDto>> resultUtil= this.appFunctionService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-15 14:57:31
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			AppFunctionEntity entity = new AppFunctionEntity();
			AppFunctionEntity resultUtil= this.appFunctionService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
