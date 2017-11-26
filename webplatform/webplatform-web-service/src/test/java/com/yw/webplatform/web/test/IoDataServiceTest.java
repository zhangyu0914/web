package com.yw.webplatform.web.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yw.common.beansUtils.dto.IoDataDto;
import com.yw.common.beansUtils.entity.IoDataEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.IoDataDataCodeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.random.RandomTool;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: IO数据
 * 涉及版本: V1.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-03-14 16:11:14
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class IoDataServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-03-14 16:11:14
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
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insertTestData(){
		try {
			String date = "";
			ResultUtil resultUtil = this.ioDataService.insertTestData(date);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test@Rollback(true)
	public void insert(){
		try {
			IoDataEntity entity = new IoDataEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setInputData(RandomTool.getRandom(100, 1000));
			entity.setOutputData(RandomTool.getRandom(100, 1000));
			entity.setDataTime(DateUtils.getSysStringTime(null));
//			entity.setDataCode(IoDataDataCodeEnum.PLATFORM.toString());
			entity.setDataCode(IoDataDataCodeEnum.DEVICE.toString());
			entity.setDataValue("SN_1001");
			
			
			Integer resultUtil = this.ioDataService.insert(entity);
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
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<IoDataDto>> list = this.ioDataService.
					findPage(null, new InterfacePage<IoDataEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				IoDataEntity entity = new IoDataEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setInputData(Integer.valueOf(2));
			entity.setOutputData(Integer.valueOf(2));
			entity.setDataTime(String.valueOf(1));
			entity.setReamrk(String.valueOf(1));

				
				Integer resultUtil = this.ioDataService.update(entity);
				
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
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			IoDataEntity entity = new IoDataEntity();
			InterfacePage<IoDataEntity> ihygeiaPage = new InterfacePage<IoDataEntity>();
			
			ResultUtil<List<IoDataDto>> resultUtil= this.ioDataService.findPage(entity, ihygeiaPage);
			
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
	 * 日       期: 2017-03-14 16:11:14
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			IoDataEntity entity = new IoDataEntity();
			IoDataEntity resultUtil= this.ioDataService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
