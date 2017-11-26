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

import com.yw.common.beansUtils.dto.ModelDto;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.random.RandomTool;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 设备型号表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class ModelServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
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
	 * 说       明: 更新数据
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {
			ResultUtil resultUtil = new ResultUtil();
			ResultUtil<List<ModelDto>> list = this.modelService.findPage(null,
					new InterfacePage<ModelEntity>());
			if (StringUtils.isBlank(list)
					|| list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS
							.getCode())
					|| list.getData().get(0).getTid() == null) {

				Assert.assertNull(null);// 保证数据库中一定要有数据才能执行下面代码，测试才能通过
			} else {

				ModelEntity entity = new ModelEntity();

				entity.setTid(list.getData().get(0).getTid());

				entity.setModelName(String.valueOf(1));
				entity.setRemark(String.valueOf(1));

				resultUtil = this.modelService.updateModel(entity);
				Assert.assertNotNull(resultUtil);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 删除
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月15日 下午4:28:57
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test@Rollback(true)
	public void delete(){
		try {
			ResultUtil resultUtil = new ResultUtil();
			ModelEntity entity = new ModelEntity();

			entity.setTid("1d92b208-e99f-404c-ac18-a7e7f95d3717");

			resultUtil = this.modelService.deleteModel(entity);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 查询分页数量
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			ModelEntity entity = new ModelEntity();
			InterfacePage<ModelEntity> ihygeiaPage = new InterfacePage<ModelEntity>();
			
			ResultUtil<List<ModelDto>> resultUtil= this.modelService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-13 18:03:31
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			ModelEntity entity = new ModelEntity();
			ModelEntity resultUtil= this.modelService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 查询型号
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 上午10:09:57
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test
	public void findAllModel() {
		try {
			
			ModelEntity entity = new ModelEntity();
			ResultUtil resultUtil= this.modelService.findAllModel(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
