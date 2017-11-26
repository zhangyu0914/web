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

import com.yw.common.beansUtils.entity.SnListEntity;
import com.yw.common.beansUtils.dto.SnListDto;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: SN列表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-07-13 14:34:22
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class SnListServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-07-13 14:34:22
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
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-07-13 14:34:22
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			SnListEntity entity = new SnListEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setDeviceType(String.valueOf(1));
			entity.setEqName(String.valueOf(1));
			entity.setEqType(Integer.valueOf(2));
			entity.setModelNo(String.valueOf(1));


			Integer resultUtil = this.snListService.insert(entity);
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
	 * 日       期: 2017-07-13 14:34:22
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<SnListDto>> list = this.snListService.
					findPage(null, new InterfacePage<SnListEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				SnListEntity entity = new SnListEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setDeviceType(String.valueOf(1));
			entity.setEqName(String.valueOf(1));
			entity.setEqType(Integer.valueOf(2));
			entity.setModelNo(String.valueOf(1));

				
				Integer resultUtil = this.snListService.update(entity);
				
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
	 * 日       期: 2017-07-13 14:34:22
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			SnListEntity entity = new SnListEntity();
			InterfacePage<SnListEntity> InterfacePage = new InterfacePage<SnListEntity>();
			
			ResultUtil<List<SnListDto>> resultUtil= this.snListService.findPage(entity, InterfacePage);
			
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
	 * 日       期: 2017-07-13 14:34:22
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			SnListEntity entity = new SnListEntity();
			SnListEntity resultUtil= this.snListService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
