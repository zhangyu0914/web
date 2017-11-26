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

import com.yw.common.beansUtils.dto.AttributeDto;
import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;

/**
 * <pre>
 * 功       能: 属性表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 18:03:31
 * Q    Q: 308053847
 * </pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class AttributeServiceTest extends BaseControllerTest {

	/**
	 * <pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Before
	public void before() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 说       明:  添加数据
	 * @throws  Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void insert() {
		try {
			AttributeEntity entity = new AttributeEntity();

			entity.setTid(UUIDUtil.getUUID());

			entity.setProfileId(String.valueOf(1));
			entity.setAttName(String.valueOf(1));
			entity.setAttType(String.valueOf(1));
			entity.setAttKey("www");

			ResultUtil resultUtil = this.attributeService
					.insertAttribute(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread()
					.getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 说       明: 更新数据
	 * @throws Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void update() {
		try {

			ResultUtil<List<AttributeDto>> list = this.attributeService
					.findPage(null, new InterfacePage<AttributeEntity>());
			if (StringUtils.isBlank(list)
					|| list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS
							.getCode())
					|| list.getData().get(0).getTid() == null) {

				Assert.assertNull(null);// 保证数据库中一定要有数据才能执行下面代码，测试才能通过
			} else {

				AttributeEntity entity = new AttributeEntity();

				entity.setTid(list.getData().get(0).getTid());

				entity.setProfileId(String.valueOf(1));
				entity.setAttName(String.valueOf(1));
				entity.setAttType(String.valueOf(1));

				ResultUtil resultUtil = this.attributeService
						.updateAttribute(entity);
				PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread()
						.getStackTrace());
				Assert.assertEquals(resultUtil.getCode(), 0);
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
	 * 日       期: 2017年5月12日 下午5:37:51
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void delete(){
		try {
			AttributeEntity entity = new AttributeEntity();
			entity.setTid("2971a79c-137f-11e7-932f-00163e004ae0");
			ResultUtil resultUtil = this.attributeService
					.deleteAttribute(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread()
					.getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <pre>
	 * 说       明: 查询分页数量
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Test
	public void findPage() {
		try {

			AttributeEntity entity = new AttributeEntity();
			InterfacePage<AttributeEntity> ihygeiaPage = new InterfacePage<AttributeEntity>();

			ResultUtil<List<AttributeDto>> resultUtil = this.attributeService
					.findPage(entity, ihygeiaPage);

			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread()
					.getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 说       明: 查询某一条数据
	 * 涉及版本: 
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-13 18:03:31
	 * </pre>
	 */
	@Test
	public void findOne() {
		try {

			AttributeEntity entity = new AttributeEntity();
			AttributeEntity resultUtil = this.attributeService.findOne(entity);

			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
