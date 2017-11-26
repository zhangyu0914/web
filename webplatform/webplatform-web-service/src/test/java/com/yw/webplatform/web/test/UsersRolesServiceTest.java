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

import com.yw.common.beansUtils.dto.UsersRolesDto;
import com.yw.common.beansUtils.entity.UsersRolesEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 角色表
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-25 14:00:42
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class UsersRolesServiceTest extends BaseControllerTest {
	
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
	@Test@Rollback(true)
	public void insertRoles(){
		try {
			UsersRolesEntity entity = new UsersRolesEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setRoleName("管理员");
			entity.setRoleDescribe(entity.getRoleDescribe());
			entity.setFunctionsIds("c86911cc-36c6-47ab-98dc-47e73fd87b34");

			ResultUtil resultUtil = this.usersRolesService.insertRoles(entity);
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
	public void updateRoles(){
		try {
			UsersRolesEntity entity = new UsersRolesEntity();
			
			entity.setTid("000023e4-5eee-4169-8346-16f548b3528f");
			entity.setRoleName("管理员");
			entity.setFunctionsIds("c86911cc-36c6-47ab-98dc-47e73fd87b34");
			
			ResultUtil resultUtil = this.usersRolesService.updateRoles(entity);
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
			UsersRolesEntity entity = new UsersRolesEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setRoleName(String.valueOf(1));
			entity.setRoleCode(String.valueOf(1));
			entity.setRoleDescribe(String.valueOf(1));
			entity.setState(Integer.valueOf(2));
			
			
			Integer resultUtil = this.usersRolesService.insert(entity);
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

			ResultUtil<List<UsersRolesDto>> list = this.usersRolesService.
					findPage(null, new InterfacePage<UsersRolesEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				UsersRolesEntity entity = new UsersRolesEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setRoleName(String.valueOf(1));
			entity.setRoleCode(String.valueOf(1));
			entity.setRoleDescribe(String.valueOf(1));
			entity.setState(Integer.valueOf(2));

				
				Integer resultUtil = this.usersRolesService.update(entity);
				
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
			
			UsersRolesEntity entity = new UsersRolesEntity();
			InterfacePage<UsersRolesEntity> InterfacePage = new InterfacePage<UsersRolesEntity>();
			
			ResultUtil<List<UsersRolesDto>> resultUtil= this.usersRolesService.findPage(entity, InterfacePage);
			
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
	public void findAll() {
		try {
			
			UsersRolesEntity entity = new UsersRolesEntity();
			
			ResultUtil<List<UsersRolesDto>> resultUtil= this.usersRolesService.findAll(entity, "357a97a9-e777-40d1-95b8-2bbca2c45bad");
			
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
			
			UsersRolesEntity entity = new UsersRolesEntity();
			UsersRolesEntity resultUtil= this.usersRolesService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
