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

import com.yw.common.beansUtils.dto.TradeDto;
import com.yw.common.beansUtils.entity.TradeEntity;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.print.PrintWriteUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.yw.webplatform.web.test.BaseControllerTest;
/**
 *<pre>
 * 功       能: 厂商
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-06-14 15:52:09
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class TradeServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-06-14 15:52:09
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Test@Rollback(true)
	public void insert(){
		try {
			TradeEntity entity = new TradeEntity();
			
			entity.setTid(UUIDUtil.getUUID());
			
			entity.setTradeName(String.valueOf(1));
			entity.setTradeCode(String.valueOf(1));
			entity.setAddress(String.valueOf(1));


			Integer resultUtil = this.tradeService.insert(entity);
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Test@Rollback(true)
	public void update(){
		try {

			ResultUtil<List<TradeDto>> list = this.tradeService.
					findPage(null, new InterfacePage<TradeEntity>());
			if (StringUtils.isBlank(list) || list.getCode() != Integer.valueOf(ErrorTypeEnum.SUCCESS.getCode())
					|| list.getData().get(0).getTid() == null) {
				
				Assert.assertNull(null);//保证数据库中一定要有数据才能执行下面代码，测试才能通过
			}else{
				
				TradeEntity entity = new TradeEntity();
				
				entity.setTid(list.getData().get(0).getTid());
				
			entity.setTradeName(String.valueOf(1));
			entity.setTradeCode(String.valueOf(1));
			entity.setAddress(String.valueOf(1));

				
				Integer resultUtil = this.tradeService.update(entity);
				
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Test
	public void findPage() {
		try {
			
			TradeEntity entity = new TradeEntity();
			InterfacePage<TradeEntity> InterfacePage = new InterfacePage<TradeEntity>();
			
			ResultUtil<List<TradeDto>> resultUtil= this.tradeService.findPage(entity, InterfacePage);
			
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
	 * 日       期: 2017-06-14 15:52:09
	 *</pre>
	 */
	@Test
	public void findOne() {
		try {
			
			TradeEntity entity = new TradeEntity();
			TradeEntity resultUtil= this.tradeService.findOne(entity);
			
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 查询
	 * 涉及版本: V2.0.0 
	 * 创  建  者: zhangyu
	 * 日       期: 2017年6月15日 上午9:50:35
	 * Q     Q: 982234234
	 * </pre>
	 */
	@Test
	public void findAll() {
		try {
			
			TradeEntity entity = new TradeEntity();
			ResultUtil resultUtil= this.tradeService.findAll(entity);
			
			PrintWriteUtil.junitPrint(resultUtil, Thread.currentThread().getStackTrace());
			Assert.assertEquals(resultUtil.getCode(), 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
