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

import com.yw.common.beansUtils.dto.OrganizationDto;
import com.yw.common.beansUtils.entity.OrganizationEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 机构
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-15 15:29:15
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class OrganizationServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 15:29:15
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
	 * <pre>
	 * 说       明:  添加数据
	 * @throws  Exception
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 15:29:15
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void insert() {
		try {
			OrganizationEntity entity = new OrganizationEntity();
			entity.setTid(UUIDUtil.getUUID());
			entity.setOrgName("浙医1");
			entity.setAddress("浙江");
			ResultUtil resultUtil = this.organizationService.insertOrganization(entity);
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
	 * 日       期: 2017-03-15 15:29:15
	 * </pre>
	 */
	@Test
	@Rollback(true)
	public void update() {
		try {
			OrganizationEntity entity = new OrganizationEntity();

			entity.setTid("17060481-6665-4c7d-a8f9-e162e2aa8bbb");
			entity.setOrgName("浙医1");
			ResultUtil resultUtil = this.organizationService.updateOrganization(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread()
					.getStackTrace());
			Assert.assertEquals(resultUtil.getCode(),0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *<pre>
	 * 说       明: 删除
	 * 涉及版本: V1.0.0
	 * 创  建  者: zhangyu
	 * 日       期: 2017年5月11日 上午11:48:29
	 * Q     Q: 982234234
	 *</pre>
	 */
	@Test
	@Rollback(true)
	public void delete() {
		try {
			OrganizationEntity entity = new OrganizationEntity();

			entity.setTid("17060481-6665-4c7d-a8f9-e162e2aa8bbb");
			ResultUtil resultUtil = this.organizationService.deleteOrganization(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread()
					.getStackTrace());
			Assert.assertEquals(resultUtil.getCode(),0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 查询分页数量
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-15 15:29:15
	 * </pre>
	 */
	@Test
	public void findPage() {
		try {

			OrganizationEntity entity = new OrganizationEntity();
			InterfacePage<OrganizationEntity> ihygeiaPage = new InterfacePage<OrganizationEntity>();

			ResultUtil<List<OrganizationDto>> resultUtil = this.organizationService
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
	 * 日       期: 2017-03-15 15:29:15
	 * </pre>
	 */
	@Test
	public void findOne() {
		try {

			OrganizationEntity entity = new OrganizationEntity();
			OrganizationEntity resultUtil = this.organizationService
					.findOne(entity);

			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
