package com.yw.webplatform.web.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.FunctionDto;
import com.yw.common.beansUtils.dto.UsersFunctionsDto;
import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.entity.UsersFunctionsEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 权限表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class UsersFunctionsServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-25 14:00:42
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test
	public void findByUserId(){
		try {
			UserInfoEntity entity = new UserInfoEntity();
			entity.setTid("29e4cb3e-7b07-42c9-8e67-dc28d10af5f9");
			Map<String, List<FunctionDto>> map = this.usersFunctionsService.findByUserId(entity);
			Assert.assertNotNull(map);
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test@Rollback(true)
	public void findAll(){
		try {
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			ResultUtil resultUtil = this.usersFunctionsService.findAll(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test@Rollback(true)
	public void deleteFunctions(){
		try {
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			
			entity.setTid("c0832c31-7a77-4db9-a39e-5e2b64597877");
			ResultUtil resultUtil = this.usersFunctionsService.deleteFunctions(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明:  修改数据
	 * @throws  Exception
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test@Rollback(true)
	public void updateFunctions(){
		try {
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			
			entity.setTid("c0832c31-7a77-4db9-a39e-5e2b64597877");
			entity.setFunctionName("abc");
			ResultUtil resultUtil = this.usersFunctionsService.updateFunctions(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFunctionCode(String.valueOf(1));
			entity.setFunctionName(String.valueOf(1));
			entity.setUri(String.valueOf(1));
			entity.setFunctionLevel(Integer.valueOf(2));
			entity.setParentCode(String.valueOf(1));
			entity.setDisplayOrder(Integer.valueOf(2));
			entity.setFunctionType(Integer.valueOf(2));
			entity.setFunctionSeq(Integer.valueOf(2));
			entity.setFunctionDesc(String.valueOf(1));
			
			
			Integer resultUtil = this.usersFunctionsService.insert(entity);
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertFunction(){
		try {
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFunctionCode(String.valueOf(1));
			entity.setFunctionName(String.valueOf(1));
			entity.setUri(String.valueOf(1));
			entity.setFunctionLevel(Integer.valueOf(2));
			entity.setParentCode(String.valueOf(1));
			entity.setDisplayOrder(Integer.valueOf(2));
			entity.setFunctionType(Integer.valueOf(2));
			entity.setFunctionSeq(Integer.valueOf(2));
			entity.setFunctionDesc(String.valueOf(1));
			
			ResultUtil resultUtil = this.usersFunctionsService.insertFunction(entity);
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<UsersFunctionsDto>> list = this.usersFunctionsService.
					findPage(null, new InterfacePage<UsersFunctionsEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				UsersFunctionsEntity entity = new UsersFunctionsEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setFunctionCode(String.valueOf(1));
			entity.setFunctionName(String.valueOf(1));
			entity.setUri(String.valueOf(1));
			entity.setFunctionLevel(Integer.valueOf(2));
			entity.setParentCode(String.valueOf(1));
			entity.setDisplayOrder(Integer.valueOf(2));
			entity.setFunctionType(Integer.valueOf(2));
			entity.setFunctionSeq(Integer.valueOf(2));
			entity.setFunctionDesc(String.valueOf(1));

				
				Integer resultUtil = this.usersFunctionsService.update(entity);
				
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			InterfacePage<UsersFunctionsEntity> InterfacePage = new InterfacePage<UsersFunctionsEntity>();
			
			ResultUtil<List<UsersFunctionsDto>> resultUtil= this.usersFunctionsService.findPage(entity, InterfacePage);
			
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test
	public void findParent() {
		try {
			
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			entity.setFunctionCode("10016");
			String rolesId = null;
			
			ResultUtil<List<UsersFunctionsDto>> resultUtil= this.usersFunctionsService.findParent(entity, rolesId);
			
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
	 * 日       期: 2017-04-25 14:00:42
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			UsersFunctionsEntity entity = new UsersFunctionsEntity();
			UsersFunctionsEntity resultUtil= this.usersFunctionsService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
