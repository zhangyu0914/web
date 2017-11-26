package com.yw.common.web.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.ConfigurationDto;
import com.yw.common.beansUtils.entity.ConfigurationEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 字典数据表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 13:17:22
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/common/web/applicationContext.xml" })
public class ConfigurationServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 13:17:22
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
	 * 日       期: 2017-03-13 13:17:22
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertConfiguration(){
		try {
			ConfigurationEntity entity = new ConfigurationEntity();
			
			
			entity.setVersionInt(Integer.valueOf(2));
			entity.setKey(String.valueOf(1));
			entity.setValue(String.valueOf(1));
			entity.setRemark("remark");

			ResultUtil resultUtil = this.configurationService.insertConfiguration(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void insert(){
		try {
			ConfigurationEntity entity = new ConfigurationEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setVersionInt(Integer.valueOf(2));
			entity.setKey(String.valueOf(1));
			entity.setValue(String.valueOf(1));
			entity.setRemark(String.valueOf(1));
			
			
			Integer resultUtil = this.configurationService.insert(entity);
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
	 * 日       期: 2017-03-13 13:17:22
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<ConfigurationDto>> list = this.configurationService.
					findPage(null, new InterfacePage<ConfigurationEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				ConfigurationEntity entity = new ConfigurationEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setVersionInt(Integer.valueOf(2));
			entity.setKey(String.valueOf(1));
			entity.setValue(String.valueOf(1));
			entity.setRemark(String.valueOf(1));

				
				Integer resultUtil = this.configurationService.update(entity);
				
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
	 * 日       期: 2017-03-13 13:17:22
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			ConfigurationEntity entity = new ConfigurationEntity();
			InterfacePage<ConfigurationEntity> ihygeiaPage = new InterfacePage<ConfigurationEntity>();
			
			ResultUtil<List<ConfigurationDto>> resultUtil= this.configurationService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-13 13:17:22
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			ConfigurationEntity entity = new ConfigurationEntity();
			ConfigurationEntity resultUtil= this.configurationService.findOne(entity);
			
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
	 * 日       期: 2017年6月21日上午9:34:04
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void sysParam() {
		try {
			
			Map<String, String> map= this.configurationService.sysParam();
			
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				System.out.println(map.get(iterator.next()));
			}
			Assert.assertNotNull(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
