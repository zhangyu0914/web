package com.yw.common.beansUtils.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yw.common.beansUtils.utils.enums.DeviceEqSecondTypeGatewayEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqSecondTypeViewEnum;
import com.yw.common.beansUtils.utils.enums.DeviceEqTypeEnum;
import com.yw.common.beansUtils.utils.string.StringUtils;


public class ErrorType implements Serializable{
	
	private static final long serialVersionUID = 8339756426615891172L;
	
	private static Properties properties;
	private static Properties propertiesZH;
    private static Properties propertiesMethod;
    
	public static Integer CODE_SYS_INDEX = 0;
	public static Integer CODE_USERS_INDEX = 0;
	public static Integer CODE_APP_INDEX = 0;
	
	static{
        InputStream fis = null;
        InputStream fisMethod = null;
        try {
        	String packageName = StringUtils.getPackageName(ErrorType.class, "/",5);
        	
        	properties = new Properties();
    		fis = ErrorType.class.getClassLoader().getResourceAsStream(packageName + "/msg/errorTypeMsg.properties");
            properties.load(fis);
            
            propertiesMethod = new Properties();
            fisMethod = ErrorType.class.getClassLoader().getResourceAsStream(packageName + "/sign/sign.properties");
            propertiesMethod.load(fisMethod);
            
            propertiesZH = new Properties();
    		fis = ErrorType.class.getClassLoader().getResourceAsStream(packageName + "/i18N/lang-zh_CN.properties");
    		propertiesZH.load(fis);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
               if (fis != null) {
            	   fis.close();
               }
               if (fisMethod != null) {
            	   fisMethod.close();
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月30日上午9:53:14
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static String getEqName(String sn) throws Exception{
		if (StringUtils.isBlank(sn)
				|| sn.length() != 16) {
			return null;
		}
		String str = sn.substring(4,8);
		Map<String, String> dataMap = new HashMap<String, String>();
		
		dataMap.put("0101", "呼叫单元");//感知
		dataMap.put("0113", "卡片标签");//感知
		dataMap.put("0111", "资产标签");//感知
		dataMap.put("0118", "婴儿标签");//感知
		dataMap.put("0114", "护士标签");//感知
		dataMap.put("0115", "母亲标签");//感知
		dataMap.put("0102", "环境传感标签");//感知
		dataMap.put("0123", "病人标签");//感知
		dataMap.put("0612", "智能交互终端");//感知
		dataMap.put("0305", "智能床垫主板");//感知
		dataMap.put("0309", "智能床传感器");//感知
		dataMap.put("0310", "智能床网络适配器B2-M-WY");//感知
		dataMap.put("0311", "智能床网络适配器B2-M-GA");//感知
		dataMap.put("0131", "可充电婴儿防盗标签");//感知
		
		dataMap.put("0501", "物联网AP");//网关
		dataMap.put("0518", "阅读器");//网关
		dataMap.put("0403", "阅读型定位器");//网关
		dataMap.put("0516", "UHF阅读器");//网关
		dataMap.put("0517", "物联网AP模块");//网关
		dataMap.put("0404", "标准定位器");//网关
		dataMap.put("0410", "定位器（锐捷）");//网关
		dataMap.put("0521", "2.4G阅读器");//网关
		dataMap.put("0405", "出口监视器");//出口监视器
		dataMap.put("0519", "RFID中继器");//网关
		dataMap.put("0005", "通信网关");//网关
		dataMap.put("0526", "锐捷物联网接入模块");//网关
		dataMap.put("0523", "华三物联网接入模块");//网关
		dataMap.put("0525", "华三物联网接入模块");//网关
		dataMap.put("0524", "华三物联网接入模块");//网关
		dataMap.put("0522", "智能床头卡");//网关
		dataMap.put("0613", "运维网关");//网关
		dataMap.put("0506", "阅读器");//网关
		
		return dataMap.get(str);
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V1.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年3月30日上午9:56:07
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static Integer getEqType(String sn) throws Exception{
		if (StringUtils.isBlank(sn)
				|| sn.length() != 16) {
			return null;
		}
		String str = sn.substring(4,8);
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		dataMap.put("0101", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0113", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0111", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0118", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0114", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0115", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0102", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0123", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0612", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0305", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0309", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0310", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0311", DeviceEqTypeEnum.VIEW.getCode());//感知
		dataMap.put("0131", DeviceEqTypeEnum.VIEW.getCode());//感知
		
		dataMap.put("0501", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0518", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0403", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0516", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0517", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0404", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0410", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0521", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0405", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0519", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0005", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0526", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0523", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0525", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0524", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0522", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0613", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		dataMap.put("0506", DeviceEqTypeEnum.GATEWAY.getCode());//网关
		return dataMap.get(str);
	}
	
	public static Integer getEqSecondType(String sn) throws Exception{
		if (StringUtils.isBlank(sn)
				|| sn.length() != 16) {
			return null;
		}
		String str = sn.substring(4,8);
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		
		dataMap.put("0112", DeviceEqSecondTypeViewEnum.VIEW_BABY_PROTECTION_TAGS.getCode());//婴儿防盗标签
		dataMap.put("0131", DeviceEqSecondTypeViewEnum.VIEW_RECHARGEABLE_BABY_PROTECTION_TAGS.getCode());//可充电婴儿防盗标签
		dataMap.put("0101", DeviceEqSecondTypeViewEnum.VIEW_CALL_UNIT.getCode());//呼叫单元
		dataMap.put("0113", DeviceEqSecondTypeViewEnum.VIEW_CARD_TAG.getCode());//卡片标签
		dataMap.put("0111", DeviceEqSecondTypeViewEnum.VIEW_ASSET_TAG.getCode());//资产标签
		dataMap.put("0118", DeviceEqSecondTypeViewEnum.VIEW_BABY.getCode());//婴儿标签
		dataMap.put("0114", DeviceEqSecondTypeViewEnum.VIEW_THE_NURSE_LABEL.getCode());//护士标签
		dataMap.put("0115", DeviceEqSecondTypeViewEnum.VIEW_MOTHER.getCode());//母亲标签
		dataMap.put("0102", DeviceEqSecondTypeViewEnum.VIEW_ENVIRONMENT.getCode());//环境标签
		dataMap.put("0123", DeviceEqSecondTypeViewEnum.VIEW_THE_PATIENT_LABEL.getCode());//病人标签
		dataMap.put("0612", DeviceEqSecondTypeViewEnum.VIEW_SMART_INTERACTIVE_TERMINAL.getCode());//智能交互终端
		dataMap.put("0305", DeviceEqSecondTypeViewEnum.VIEW_SMART_MATTRESS_MOTHERBOARD.getCode());//智能床垫主板
		dataMap.put("0309", DeviceEqSecondTypeViewEnum.VIEW_SMART_BED_SENSOR.getCode());//智能床传感器
		dataMap.put("0310", DeviceEqSecondTypeViewEnum.VIEW_SMART_BED_NETWORK_ADAPTER_WY.getCode());//智能床网络适配器B2-M-WY
		dataMap.put("0311", DeviceEqSecondTypeViewEnum.VIEW_SMART_BED_NETWORK_ADAPTER_GA.getCode());//智能床网络适配器B2-M-GA
		dataMap.put("0131", DeviceEqSecondTypeViewEnum.VIEW_RECHARGEABLE_BABY_PROTECTION_TAGS.getCode());//可充电婴儿防盗标签
		dataMap.put("0518", DeviceEqSecondTypeViewEnum.VIW_READER.getCode());//阅读器
		
		dataMap.put("0506", DeviceEqSecondTypeGatewayEnum.GATEWAY_READ.getCode());//阅读器
		dataMap.put("0503", DeviceEqSecondTypeGatewayEnum.GATEWAY_AP.getCode());//物联网AP
		return dataMap.get(str);
	}
	public static final class Index implements Serializable{
    	
		public static  Integer CODE_BASS_INDEX = 0;					//基础
	 	public static  Integer CODE_UCENTER_INDEX = 0;				//用户模块
	 	public static  Integer CODE_COMMON_INDEX = 0;				//升级模块
	 	public static  Integer CODE_CPLUSPLUS_INDEX = 0;			//C++模块
	 	public static  Integer CODE_APP_INDEX = 0;					//应用模块
	 	public static  Integer CODE_FUNCTION_INDEX = 0;				//权限模块
	 	public static  Integer CODE_DEVICE_INDEX = 0;				//设备模块
		
        
    }
	public static final class Module implements Serializable{
		public static final String BASE = "20";						//基础模块
		public static final String UCENTER = "21";					//用户模块
		public static final String COMMON = "22";					//升级模块
		public static final String CPLUSPLUS = "23";				//C++模块
		public static final String APP = "24";						//APP模块
		public static final String FUNCTION = "25";					//权限模块
		public static final String DEVICE = "26";					//设备模块
		
	}
	
	public enum ErrorTypeEnum implements Serializable{

		/**
		 * 系统错误
		 */
		SUCCESS("00", 0000, "SUCCESS"), // 操作成功
		FAILURE(Module.BASE, ++Index.CODE_BASS_INDEX, "FAILURE"), // 操作失败
		
		FAILURE_BASE_TOKEN_PARAMS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_TOKEN_PARAMS"), //没获取到TOKEN参数
        FAILURE_BASE_LACK_PARAMS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_LACK_PARAMS"), //缺少参数
        FAILURE_BASE_NO_FUNCTION(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_NO_FUNCTION"), //此用户没有访问此资源的权限
        FAILURE_BASE_ILLEGAL_PARAMS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_ILLEGAL_PARAMS"), //参数值不合法
        FAILURE_BASE_SHIRO_TOKEN(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_SHIRO_TOKEN"), //权限中验证TOKEN失败
        FAILURE_BASE_PARAMS_MD5_ERROR(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_PARAMS_MD5_ERROR"), //参数加密不正确
        FAILURE_BASE_GET_REDIS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_GET_REDIS"), //太久没有操作了，为了安全，需要重新登录哦
        FAILURE_BASE_SELECT(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_SELECT"), //查询失败
        FAILURE_BASE_INSERT(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_INSERT"), //添加失败
        FAILURE_BASE_DEL(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_DEL"), //删除失败
        FAILURE_BASE_UPDATE(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_UPDATE"), //更新失败
        FAILURE_BASE_AUTH_CODE_PAST_TIME(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_AUTH_CODE_PAST_TIME"), //验证码已过期，请重新发送
        FAILURE_BASE_AUTH_CODE_FAILURE(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_AUTH_CODE_FAILURE"), //验证码错误，请重新输入
        FAILURE_BASE_NO_FUNCTIONS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_NO_FUNCTIONS"), //没有操作权限
        FAILURE_BASE_GET_REDIS_USERS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_GET_REDIS_USERS"), //在REDIS中没有获取到用户数据
        FAILURE_BASE_NO_FIND_DATA(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_NO_FIND_DATA"), //没找到相符的数据
        FAILURE_BASE_ILLEGALITY_OPERATION(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_ILLEGALITY_OPERATION"), //非法操作
        FAILURE_BASE_HEADER_LACK_PARAMS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_HEADER_LACK_PARAMS"), //Header中缺少参数
        FAILURE_BASE_EXISTS(Module.BASE,++ Index.CODE_BASS_INDEX,"FAILURE_BASE_EXISTS"), //数据已存在

        /**
		 * COMMON
		 */
		FAILURE_COMMON_FREQUENT_OPERATION(Module.COMMON, ++Index.CODE_COMMON_INDEX, "FAILURE_COMMON_FREQUENT_OPERATION"),//你操作过于频繁，请稍后重试
		
		/**
		 * USER
		 */
		FAILURE_USERS_CARDNO_ALREADY_EXISTS(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_USERS_CARDNO_ALREADY_EXISTS"), //此身份证号已经存在
		FAILURE_LOGINNAME_OR_PASSWORD_ERROR(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_LOGINNAME_OR_PASSWORD_ERROR"),//用户名或密码错误
		FAILURE_USERS_INPUT_PHONE(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_USERS_INPUT_PHONE"), //请输入正确的手机号
		FAILURE_USERS_REGIST_USER_EXISTS(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_REGIST_USER_EXISTS"), //用户名已存在
        FAILURE_USERS_DIFFERENT_PWD(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_DIFFERENT_PWD"), //两次输入的密码不一致
        FAILURE_USERS_REGIST_USER(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_REGIST_USER"), //注册失败
        FAILURE_USERS_INPUT_USER_NAME(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_INPUT_USER_NAME"), //请输入正确的登录名
        FAILURE_USERS_DISABLE(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_DISABLE"), //用户已禁用
        FAILURE_USERS_PASSWORD_ERROR(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_PASSWORD_ERROR"), //密码输入错误
        FAILURE_USERS_NOT_FOUND(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_NOT_FOUND"), //此用户不存在
        FAILURE_USERS_OLD_PWD(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_OLD_PWD"), //登录密码错误，请重新输入。如果你忘记了密码，请先到登录页面找回密码后再试。
        FAILURE_USERS_VALIDATE_CARD_NO(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_USERS_VALIDATE_CARD_NO"), //此身份证不合法
        FAILURE_USERS_INPUT_BUSI_CODE(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_USERS_INPUT_BUSI_CODE"), //请输入国际编码
        FAILURE_USERS_NOT_PASS(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_USERS_NOT_PASS"), //您当前未认证，认证通过后才能操作
        FAILURE_USERS_SEND_SMS(Module.UCENTER, ++Index.CODE_UCENTER_INDEX, "FAILURE_USERS_SEND_SMS"), //发送短信失败
        FAILURE_USERS_LOGIN_ERROR(Module.UCENTER,++ Index.CODE_UCENTER_INDEX,"FAILURE_USERS_LOGIN_ERROR"),//登录名或密码错误
        FAILURE_USERS_SERVICE_APPLY_APPLYING(Module.UCENTER,++ Index.CODE_UCENTER_INDEX,"FAILURE_USERS_SERVICE_APPLY_APPLYING"),//您已申请，请耐心等待审核
        FAILURE_USERS_SERVICE_APPLY_PASS(Module.UCENTER,++ Index.CODE_UCENTER_INDEX,"FAILURE_USERS_SERVICE_APPLY_PASS"),//您已在列表中，不需再重新申请
        FAILURE_USERS_PHONE_EXISTS(Module.UCENTER,++ Index.CODE_UCENTER_INDEX,"FAILURE_USERS_PHONE_EXISTS"),//此手机号已经存在
        FAILURE_USERS_USER_ACCOUNT_EXISTS(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_USER_ACCOUNT_EXISTS"), //账号已存在
        //您的账号于17:30分在其他设备登录。如非本人操作，则密码可能已经泄露，建议立即修改密码。
        FAILURE_USERS_ERROR_PHONE(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_ERROR_PHONE"),//手机号格式有误 
        FAILURE_USERS_AUTH_SEND_MSG_NO(Module.UCENTER,++ Index.CODE_UCENTER_INDEX,"FAILURE_USERS_AUTH_SEND_MSG_NO"),//您需要认证通过后，才可以发送消息
        FAILURE_USERS_USER_STATE_DISBLAED(Module.UCENTER, ++Index.CODE_UCENTER_INDEX,"FAILURE_USERS_USER_STATE_DISBLAED"), //此用户已被禁用
       
        /****C++******************************************************************************************/
        FAILURE_CPLUSPLUS_NOTFOUND_DATA(Module.CPLUSPLUS, ++Index.CODE_CPLUSPLUS_INDEX, "FAILURE_CPLUSPLUS_NOTFOUND_DATA"),//数据不存在
        FAILURE_CPLUSPLUS_MQTT_AUTH(Module.CPLUSPLUS, ++Index.CODE_CPLUSPLUS_INDEX, "FAILURE_CPLUSPLUS_MQTT_AUTH"),//应用ID或密钥错误
       
        /****应用******************************************************************************************/
        FAILURE_APP_LENGTH_ILLEGAL(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_LENGTH_ILLEGAL"),//密钥长度不合法
        FAILURE_APP_APP_ACCOUNT_EXISTS(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_APP_ACCOUNT_EXISTS"),//应用账号已存在
        FAILURE_APP_INTERFACE_EXISTS(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_INTERFACE_EXISTS"),//接口已存在，请勿重复添加
        FAILURE_APP_AC_FAILURE(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_AC_FAILURE"),//应用账号或密码错误
        FAILURE_APP_APP_ACCOUNT_IS_NOT_EXISTS(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_APP_ACCOUNT_IS_NOT_EXISTS"),//与此应用ID绑定的应用账号不存在
        FAILURE_APP_APPID_EXISTS(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_APPID_EXISTS"),//应用ID已经绑定了应用账号，请误重复创建
        FAILURE_APP_INSTANCE_IS_NO_EXISTS(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_INSTANCE_IS_NO_EXISTS"),//应用实例不存在
        FAILURE_APP_BIND_EXISTS(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_BIND_EXISTS"),//已经绑定，请误重复绑定
        FAILURE_APP_BIND_TOKEN_APP_INSTANCE_AUTH(Module.APP, ++Index.CODE_APP_INDEX, "FAILURE_APP_BIND_TOKEN_APP_INSTANCE_AUTH"),//此会话ID中的实例ID与被操作的实例ID不匹配
        
        /****设备******************************************************************************************/
        FAILURE_DEVICE_SN_NOT_EXISTS(Module.DEVICE, ++Index.CODE_DEVICE_INDEX, "FAILURE_DEVICE_SN_NOT_EXISTS"),//未在许可证中找到此SN号
        FAILURE_DEVICE_SN_NOT_BIND_APP(Module.DEVICE, ++Index.CODE_DEVICE_INDEX, "FAILURE_DEVICE_SN_NOT_BIND_APP"),//SN号未绑定到此应用下
        
        /****权限******************************************************************************************/
        FAILURE_FUNCTION_ROLES_EXISTS(Module.FUNCTION, ++Index.CODE_FUNCTION_INDEX, "FAILURE_FUNCTION_ROLES_EXISTS"),//角色已存在，请勿重复添加
        FAILURE_FUNCTION_EXISTS(Module.FUNCTION, ++Index.CODE_FUNCTION_INDEX, "FAILURE_FUNCTION_EXISTS"),//权限已存在，请勿重复添加
        FAILURE_FUNCTION_ERROR_EXISTS(Module.FUNCTION, ++Index.CODE_FUNCTION_INDEX, "FAILURE_FUNCTION_ERROR_EXISTS"),//报错信息
        FAILURE_FUNCTION_ERROR_BIND_MODEL_CHECK(Module.FUNCTION, ++Index.CODE_FUNCTION_INDEX, "FAILURE_FUNCTION_ERROR_BIND_MODEL_CHECK"),//此应用不支持此型号绑定
        
        ;
		private String model;
        private int code;
        private String name;
        
        ErrorTypeEnum(String model,int code, String name) {
            this.model = model;
            this.code = code;
            this.name = name;
        }

        public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCode() {
            return String.format("%s%02d", model, code);
        }

        public String getMessage() {
            return properties.getProperty(name);
        }

        public String getMessage(String defaultKey) {
            return properties.getProperty(name,defaultKey);
        }
        
        public static String getMethodParams(String key) {
    		return propertiesMethod.getProperty(key);
    	}
        
        public static String getZH(String key) {
    		return propertiesZH.getProperty(key);
    	}
	}
}
