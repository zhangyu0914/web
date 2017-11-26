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

import com.yw.common.beansUtils.dto.UserLogDto;
import com.yw.common.beansUtils.entity.UserLogEntity;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.enums.UserLogVisitTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 用户注册登录日志表
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-08 16:48:18
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class UserLogServiceTest extends BaseControllerTest {
	
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
	public void insert(){
		try {
			UserLogEntity entity = new UserLogEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setFkUserTid(String.valueOf(1));
			entity.setLastLoginTime(Timestamp.valueOf("2015-05-20 00:00:00"));
			entity.setLastLoginToken(String.valueOf(1));
			entity.setLastLoginDeviceId(String.valueOf(1));
			entity.setVisitType(Integer.valueOf(2));


			Integer resultUtil = this.userLogService.insert(entity);
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

			ResultUtil<List<UserLogDto>> list = this.userLogService.
					findPage(null, new InterfacePage<UserLogEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				UserLogEntity entity = new UserLogEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setFkUserTid(String.valueOf(1));
			entity.setLastLoginTime(Timestamp.valueOf("2015-05-20 00:00:00"));
			entity.setLastLoginToken(String.valueOf(1));
			entity.setLastLoginDeviceId(String.valueOf(1));
			entity.setVisitType(Integer.valueOf(2));

				
				Integer resultUtil = this.userLogService.update(entity);
				
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
			
			UserLogEntity entity = new UserLogEntity();
			InterfacePage<UserLogEntity> ihygeiaPage = new InterfacePage<UserLogEntity>();
			
			ResultUtil<List<UserLogDto>> resultUtil= this.userLogService.findPage(entity, ihygeiaPage);
			
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
			
			UserLogEntity entity = new UserLogEntity();
			UserLogEntity resultUtil= this.userLogService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
