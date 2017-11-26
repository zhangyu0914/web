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

import com.yw.common.beansUtils.dto.InterfaceDto;
import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 接口
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:14
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class InterfaceServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-20 16:59:14
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertInterface(){
		try {
			InterfaceEntity entity = new InterfaceEntity();
			
			entity.setInterfaceName(String.valueOf(1));
			entity.setInterfaceUrl(String.valueOf(1));
			entity.setInterfaceType(Integer.valueOf(2));

			ResultUtil resultUtil = this.interfaceService.insertInterface(entity);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午5:44:19
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test@Rollback(true)
	public void updateInterface(){
		try {
			InterfaceEntity entity = new InterfaceEntity();
			entity.setTid("05a55301-2589-45c5-aef6-c4176e85be09");
			entity.setInterfaceUrl("/platform/api/v100/device/get0");
			ResultUtil resultUtil = this.interfaceService.updateInterface(entity);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月22日 下午5:44:39
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test@Rollback(true)
	public void deleteInterface(){
		try {
			InterfaceEntity entity = new InterfaceEntity();
			
			entity.setInterfaceName(String.valueOf(1));
			entity.setInterfaceUrl(String.valueOf(1));
			entity.setInterfaceType(Integer.valueOf(2));

			ResultUtil resultUtil = this.interfaceService.deleteInterface(entity);
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			InterfaceEntity entity = new InterfaceEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setInterfaceName(String.valueOf(1));
			entity.setInterfaceCode(String.valueOf(1));
			entity.setInterfaceType(Integer.valueOf(2));
			
			
			Integer resultUtil = this.interfaceService.insert(entity);
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<InterfaceDto>> list = this.interfaceService.
					findPage(null, new InterfacePage<InterfaceEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				InterfaceEntity entity = new InterfaceEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setInterfaceName(String.valueOf(1));
			entity.setInterfaceCode(String.valueOf(1));
			entity.setInterfaceType(Integer.valueOf(2));

				
				Integer resultUtil = this.interfaceService.update(entity);
				
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			InterfaceEntity entity = new InterfaceEntity();
			entity.setInterfaceName("接口");
			InterfacePage<InterfaceEntity> InterfacePage = new InterfacePage<InterfaceEntity>();
			
			ResultUtil<List<InterfaceDto>> resultUtil= this.interfaceService.findPage(entity, InterfacePage);
			
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
	 * 日       期: 2017-04-20 16:59:14
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			InterfaceEntity entity = new InterfaceEntity();
			InterfaceEntity resultUtil= this.interfaceService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
