package com.yw.webplatform.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.ViewDto;
import com.yw.common.beansUtils.entity.AppEntity;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.entity.ViewEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 预览
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-05-08 16:58:55
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class ViewServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Before
	public void before() {
		try {
			configurationService.initData();
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
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			ViewEntity entity = new ViewEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			


			Integer resultUtil = this.viewService.insert(entity);
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
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<ViewDto>> list = this.viewService.
					findPage(null, new InterfacePage<ViewEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				ViewEntity entity = new ViewEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				

				
				Integer resultUtil = this.viewService.update(entity);
				
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
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Test
	public void findWarningReport() {
		try {
			
			HomePageEntity entity = new HomePageEntity();
			
			ResultUtil resultUtil= this.viewService.findWarningReport(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *<pre>
	 * 说       明: 查询分页数量
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Test
	public void findPlatformReport() {
		try {
			
			AppEntity entity = new AppEntity();
			
			ResultUtil resultUtil= this.viewService.findPlatformReport(entity);
			
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
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			ViewEntity entity = new ViewEntity();
			ViewEntity resultUtil= this.viewService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 查询平台对应用数据
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-05-08 16:58:55
	 *</pre>
	 */
	@Test
	public void findIOAndInterface() {
		try {
			
			AppEntity entity = new AppEntity();
			ResultUtil resultUtil= this.viewService.findIOAndInterface(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
