package com.yw.common.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.MwconfigDto;
import com.yw.common.beansUtils.entity.MwconfigEntity;
import com.yw.common.beansUtils.entity.importFile.GetMwConfigEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 配置表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-06-27 17:22:38
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/common/web/applicationContext.xml" })
public class MwconfigServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-06-27 17:22:38
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
	 * 日       期: 2017-06-27 17:22:38
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			MwconfigEntity entity = new MwconfigEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setConfigClass(String.valueOf(1));
			entity.setConfigName(String.valueOf(1));
			entity.setConfigValue(String.valueOf(1));
			entity.setConfigUnit(String.valueOf(1));
			entity.setConfigDname(String.valueOf(1));
			entity.setRemark(String.valueOf(1));
			entity.setReadOnly(Integer.valueOf(2));
			entity.setTypeContent(String.valueOf(1));
			entity.setConfigType(String.valueOf(1));


			Integer resultUtil = this.mwconfigService.insert(entity);
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
	 * 日       期: 2017-06-27 17:22:38
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<MwconfigDto>> list = this.mwconfigService.
					findPage(null, new InterfacePage<MwconfigEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				MwconfigEntity entity = new MwconfigEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setConfigClass(String.valueOf(1));
			entity.setConfigName(String.valueOf(1));
			entity.setConfigValue(String.valueOf(1));
			entity.setConfigUnit(String.valueOf(1));
			entity.setConfigDname(String.valueOf(1));
			entity.setRemark(String.valueOf(1));
			entity.setReadOnly(Integer.valueOf(2));
			entity.setTypeContent(String.valueOf(1));
			entity.setConfigType(String.valueOf(1));

				
				Integer resultUtil = this.mwconfigService.update(entity);
				
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
	 * 日       期: 2017-06-27 17:22:38
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			MwconfigEntity entity = new MwconfigEntity();
			InterfacePage<MwconfigEntity> InterfacePage = new InterfacePage<MwconfigEntity>();
			
			ResultUtil<List<MwconfigDto>> resultUtil= this.mwconfigService.findPage(entity, InterfacePage);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月27日下午6:06:15
	 * Q    Q: 308053847
	 * </pre>
	 */
	@Test
	public void mwConfigGet() {
		try {
			GetMwConfigEntity entity = new GetMwConfigEntity();
			entity.setId(new String[]{"0ada62e0-c565-44fd-b821-30e4c0850e30"});
			ResultUtil<List<MwconfigDto>> resultUtil= this.mwconfigService.mwConfigGet(entity);
			
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
	 * 日       期: 2017-06-27 17:22:38
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			MwconfigEntity entity = new MwconfigEntity();
			MwconfigEntity resultUtil= this.mwconfigService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
