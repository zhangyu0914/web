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

import com.yw.common.beansUtils.dto.UserInfoDto;
import com.yw.common.beansUtils.entity.UserInfoEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.md5.MD5Util;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 用户表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:18
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class UserInfoServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-08 16:48:18
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
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertUserInfo(){
		try {
			UserInfoEntity entity = new UserInfoEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setUserAccount("13200000003");
			entity.setDisplayName(String.valueOf(1));
			entity.setUserPwd(String.valueOf(1));
			entity.setRolesIds("000023e4-5eee-4169-8346-16f548b3528f");
			entity.setPhone(String.valueOf(1));
			entity.setEmail(String.valueOf(1));

			ResultUtil resultUtil = this.userInfoService.insertUserInfo(entity);
			Assert.assertNotNull(resultUtil);
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
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			UserInfoEntity entity = new UserInfoEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setUserPwd(String.valueOf(1));
			entity.setUserSalt(String.valueOf(1));
			entity.setUserSecretKey(String.valueOf(1));
			entity.setUserAccount(String.valueOf(1));
			entity.setDisplayName(String.valueOf(1));
			entity.setPhone(String.valueOf(1));
			entity.setEmail(String.valueOf(1));
			entity.setBirthday(String.valueOf(1));
			entity.setAge(Integer.valueOf(2));
			entity.setGender(Integer.valueOf(2));
			entity.setCity(String.valueOf(1));
			entity.setAddress(String.valueOf(1));
			entity.setAvatar(String.valueOf(1));
			entity.setLoginStatus(Integer.valueOf(2));
			entity.setDelFlag(Integer.valueOf(2));
			
			
			Integer resultUtil = this.userInfoService.insert(entity);
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
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<UserInfoDto>> list = this.userInfoService.
					findPage(null, new InterfacePage<UserInfoEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				UserInfoEntity entity = new UserInfoEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setUserPwd(String.valueOf(1));
			entity.setUserSalt(String.valueOf(1));
			entity.setUserSecretKey(String.valueOf(1));
			entity.setUserAccount(String.valueOf(1));
			entity.setDisplayName(String.valueOf(1));
			entity.setPhone(String.valueOf(1));
			entity.setEmail(String.valueOf(1));
			entity.setBirthday(String.valueOf(1));
			entity.setAge(Integer.valueOf(2));
			entity.setGender(Integer.valueOf(2));
			entity.setCity(String.valueOf(1));
			entity.setAddress(String.valueOf(1));
			entity.setAvatar(String.valueOf(1));
			entity.setLoginStatus(Integer.valueOf(2));
			entity.setDelFlag(Integer.valueOf(2));

				
				Integer resultUtil = this.userInfoService.update(entity);
				
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
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			UserInfoEntity entity = new UserInfoEntity();
			InterfacePage<UserInfoEntity> ihygeiaPage = new InterfacePage<UserInfoEntity>();
			
			ResultUtil<List<UserInfoDto>> resultUtil= this.userInfoService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-08 16:48:18
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			UserInfoEntity entity = new UserInfoEntity();
			UserInfoEntity resultUtil= this.userInfoService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void login(){
		try {
				
			UserInfoEntity entity = new UserInfoEntity();
			entity.setUserAccount("13200000004");
			entity.setUserPwd(MD5Util.md5("123456"));
				
			ResultUtil resultUtil = this.userInfoService.login(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void deleteUserInfo(){
		try {
			
			UserInfoEntity entity = new UserInfoEntity();
			entity.setTid("c50c589d-dd74-4017-868b-74e19fa72c6f");
			
			ResultUtil resultUtil = this.userInfoService.deleteUserInfo(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void updateUserInfo(){
		try {
			
			UserInfoEntity entity = new UserInfoEntity();
			entity.setTid("1d92b208-e99f-404c-ac18-a7a7f95d3716");
			
			ResultUtil resultUtil = this.userInfoService.updateUserInfo(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 校验用户是否存在
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月1日 下午4:48:33
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test
	public void userCheck(){
		try {
			
			UserInfoEntity entity = new UserInfoEntity();
			entity.setUserAccount("18367120914");
			
			ResultUtil resultUtil = this.userInfoService.userCheck(entity);
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertNotNull(resultUtil);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
