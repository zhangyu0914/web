package com.yw.webplatform.web.test;

import java.io.File;
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

import com.yw.common.beansUtils.entity.AttributeEntity;
import com.yw.common.beansUtils.entity.ModelEntity;
import com.yw.common.beansUtils.entity.importFile.AppsEntity;
import com.yw.common.beansUtils.entity.importFile.DevicesEntity;
import com.yw.common.beansUtils.entity.importFile.ImportAppEntity;
import com.yw.common.beansUtils.entity.importFile.ImportLicenseEntity;
import com.yw.common.beansUtils.entity.importFile.ImportModelEntity;
import com.yw.common.beansUtils.entity.importFile.ImportProfileEntity;
import com.yw.common.beansUtils.entity.importFile.MConfigEntity;
import com.yw.common.beansUtils.entity.importFile.ModelPropertyEntity;
import com.yw.common.beansUtils.entity.importFile.PermissionCmdEntity;
import com.yw.common.beansUtils.entity.importFile.PermissionEntity;
import com.yw.common.beansUtils.entity.importFile.PushModelDataEntity;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UUIDUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.AttributeAttTypeEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.log.LoggerUtil;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
/**
 *<pre>
 * 功       能: 导入
 * 涉及版本: V2.0.0  
 * 创  建  者: Vickey
 * 日       期: 2017-04-28 14:38:53
 * Q    Q: 308053847
 *</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/yw/webplatform/web/applicationContext.xml" })
public class ImportServiceTest extends BaseControllerTest {
	
	/**
	 *<pre>
	 * 说       明: 预加载
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Before
	public void before() {
		try {
			configurationService.initData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *<pre>
	 * 说       明: 型号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Rollback(true)
	@Test
	public void importModel() {
		try {
			
			String content = FileUtil.readFile("D:\\Company\\YW\\Item\\web\\3.program\\doc\\设计文档\\importModel.txt");
			String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
			ResultUtil resultUtil = this.importService.importModel(content, batNo);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 导入应用
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Rollback(true)
	@Test
	public void importApp() {
		try {
			
			String content = FileUtil.readFile("D:\\Company\\YW\\Item\\web\\3.program\\doc\\设计文档\\importApp.txt");
			String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
			ResultUtil resultUtil = this.importService.importApp(content, batNo);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 导入许可证
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Rollback(true)
	@Test
	public void importLicense() {
		try {
			
			String content = FileUtil.readFile("D:\\Company\\YW\\Item\\web\\3.program\\doc\\设计文档\\importLicense.txt");
			String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS);
			ResultUtil resultUtil = this.importService.importLicense(content, batNo, new HashMap<String, ModelEntity>());
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 导入PROFILE
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Rollback(true)
	@Test
	public void importProfile() {
		try {
			
			String content = FileUtil.readFile("D:\\Company\\YW\\Item\\web\\3.program\\doc\\设计文档\\importProfile.txt");
			ResultUtil resultUtil = this.importService.importProfile(content);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 *<pre>
	 * 说       明: 导入PROFILE
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Rollback(true)
	@Test
	public void importProfileInFile() {
		try {
			
			File file = new File("D:\\mnt\\webapp\\luaFile");
			for(File f : file.listFiles()){
				
				String content = FileUtil.readFile(f);
				int index = content.indexOf("return 0x");
				String id = content.substring(index + 9, index + 15);
				String value = f.getName().substring(0,f.getName().length() - 4);
				
				AttributeEntity attribute = null;
				{//PROFILE
					
					attribute = this.attributeService.findOne(new AttributeEntity(id, null, null));
					if (StringUtils.isBlank(attribute)) {//检查是否存在
						
						attribute = new AttributeEntity(
								UUIDUtil.getUUID(),
								id,
								value,
								value,
								AttributeAttTypeEnum.SETTING.getCode()
								);
						attribute.setRemark(content);
						if (this.attributeService.insert(attribute) == 0) {
							throw new Exception("新增属性失败");
						}
						
						List list = new ArrayList();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", id);
						list.add(map);
						PushModelDataEntity pushModel = new PushModelDataEntity("profile_add", list);
						
						RedisUtil.lpush(RedisTypeEnum.NOTIFY.getCode(), "profile", pushModel);
						
					}else{
						attribute.setRemark(content);
						if (this.attributeService.update(attribute) == 0) {
							throw new Exception("更新属性失败");
						}
					}
				}
			}
			Assert.assertNotNull("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 型号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void model() {
		try {
			List<ImportModelEntity> list = new ArrayList<ImportModelEntity>();
			{
				ImportModelEntity importModel = new ImportModelEntity();
				
				importModel.setId("B610");
				importModel.setName("婴儿防盗");
				importModel.setEnterprise_code("0001");
				
				{
					ModelPropertyEntity property = new ModelPropertyEntity();
					MConfigEntity config = new MConfigEntity();
					
					
					property.setProp_id("200001");
					property.setProp_name("core.BEACON");

					config.setConfig_id(0);
					config.setConfig_name("config_1");
					config.setConfig_type("mlink_config_unum8");
					
				}
				{
					ModelPropertyEntity property = new ModelPropertyEntity();
					MConfigEntity config = new MConfigEntity();
					
					property.setProp_id("200002");
					property.setProp_name("core.REGIST");

					config.setConfig_id(1);
					config.setConfig_name("config_2");
					config.setConfig_type("mlink_config_string");
					
				}
				System.out.println(JavaBeanUtil.javaBeanToString(importModel));
			}
			{
				ImportModelEntity importModel = new ImportModelEntity();
				
				importModel.setId("M610");
				importModel.setName("资产管理");
				importModel.setEnterprise_code("0001");
				
				{
					ModelPropertyEntity property = new ModelPropertyEntity();
					MConfigEntity config = new MConfigEntity();
					
					
					property.setProp_id("200001");
					property.setProp_name("power.VOLTAGE");

					config.setConfig_id(0);
					config.setConfig_name("config_1");
					config.setConfig_type("mlink_config_unum8");
					
				}
				{
					ModelPropertyEntity property = new ModelPropertyEntity();
					MConfigEntity config = new MConfigEntity();
					
					property.setProp_id("200002");
					property.setProp_name("power.BATTERY");

					config.setConfig_id(1);
					config.setConfig_name("config_2");
					config.setConfig_type("mlink_config_string");
					
				}
				System.out.println(JavaBeanUtil.javaBeanToString(importModel));
			}
			Assert.assertNotNull("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *<pre>
	 * 说       明: 应用
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test@Rollback(true)
	public void app() {
		try {
			
			ImportAppEntity importApp = new ImportAppEntity();
			
			importApp.setId("APPID_1005");
			importApp.setName("资产定位系统");
			importApp.setEnterprise_code("0801");
			
			{
				PermissionEntity permission = new PermissionEntity();
				
				
				permission.setModel_id("1234");
				permission.setCmdsObj(new PermissionCmdEntity(0,"20000100", new String[]{"write","read"}));
				
			}
			{
				PermissionEntity permission = new PermissionEntity();
				
				
				permission.setModel_id("1235");
				permission.setCmdsObj(new PermissionCmdEntity(0,"20000102", new String[]{"write","read"}));
				
			}
			{
				PermissionEntity permission = new PermissionEntity();
				
				
				permission.setModel_id("1236");
				permission.setCmdsObj(new PermissionCmdEntity(0,"20000103", new String[]{"read"}));
				
			}
			System.out.println(JavaBeanUtil.javaBeanToString(importApp));
			Assert.assertNotNull(importApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *<pre>
	 * 说       明: 许可证
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void license() {
		try {
			
			ImportLicenseEntity importLicense = new ImportLicenseEntity();
			
			importLicense.setId("LIC_1001");
			importLicense.setPid("PID_1001");
			importLicense.setDesc("资产定位系统");
			importLicense.setCreate_time(DateUtils.getSysStringTime(null));
			importLicense.setExp_time(DateUtils.getSysStringTime(null));
			
			{//DEVICES
				{
					DevicesEntity devices = new DevicesEntity();
					
					
					devices.setModel_id("1233");
					devices.setSn_list(new String[]{"800100100001","800100100002"});
					
					importLicense.setDevicesObj(devices);
				}
				{
					DevicesEntity devices = new DevicesEntity();
					
					
					devices.setModel_id("1234");
					devices.setSn_list(new String[]{"800100200001","800100200002"});
					
					importLicense.setDevicesObj(devices);
				}
			}
			{//APPS
				{
					AppsEntity apps = new AppsEntity();
					
					
					apps.setApp_id("asasa");
					apps.setLic_idsObj(new String[]{"12121","232323","44444"});
					
					importLicense.setAppsObj(apps);
				}
				{
					AppsEntity apps = new AppsEntity();
					
					
					apps.setApp_id("asasabbbbbbb");
					apps.setLic_idsObj(new String[]{"121dwew21","2323adada23","3232"});
					
					importLicense.setAppsObj(apps);
				}
			}
			System.out.println(JavaBeanUtil.javaBeanToString(importLicense));
			Assert.assertNotNull(importLicense);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: PROFILE
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Test
	public void profile() {
		try {
			
			ImportProfileEntity data = new ImportProfileEntity();
			
			data.setId("LIC_1001");
			data.setName("core.BEACON");
			String content = FileUtil.readFile("D:\\Company\\YW\\Item\\web\\3.program\\doc\\设计文档\\luaFile\\" + data.getName() + ".lua");
			
			data.setLua(content);
			System.out.println(JavaBeanUtil.javaBeanToString(data));
			Assert.assertNotNull(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 *<pre>
	 * 说       明: 型号
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017-04-28 14:38:53
	 *</pre>
	 */
	@Rollback(true)
	@Test
	public void importFile() {
		try {
			
			String path = "D:\\test\\test.lic";
			String batNo = "IMPORT_" + DateUtils.getSysStringTime(DateUtils.PATTERN_24_YYYYMMDDHHMMSS)+"[127.0.0.1]";
			String zipPassword = "123456";
			ResultUtil resultUtil = this.importService.importFile(path, batNo, zipPassword);
			Assert.assertNotNull(resultUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testLog() {
		try {
			
			String str1 = "[IMPORT_20170710113155]org.springframework.dao.DataIntegrityViolationException: "+
							"### Error updating database.  Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column 'model_no' at row 1"+
							"### The error may involve com.yw.webplatform.mapper.AppFunctionEntityMapper.insert-Inline"+
							"### The error occurred while setting parameters"+
							"### SQL: INSERT INTO T_APP_FUNCTION(           TID,    FK_APP_TID,    PROFILE_ID,    APP_ID,    APP_VERSION,    FLOW,    MODEL_NO,    EP,    CREATE_TIME,    UPDATE_TIME       )      VALUES(           ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    SYSDATE(),    DATE_FORMAT(SYSDATE(), '%Y%m%d%H%i%s')       )"+
							"### Cause: com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column 'model_no' at row 1"+
							"; SQL []; Data truncation: Data too long for column 'model_no' at row 1; nested exception is com.mysql.jdbc.MysqlDataTruncation: Data truncation: Data too long for column 'model_no' at row 1";

//			String str1 = "222222";				
			LoggerUtil.pushLog(str1, "webplatform", "error");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
