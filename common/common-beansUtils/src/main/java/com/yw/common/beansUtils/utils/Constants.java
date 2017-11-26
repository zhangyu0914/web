package com.yw.common.beansUtils.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功   能:   
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-23上午11:22:59
 * Q  Q: 308053847
 *</pre>
 */
public class Constants{

    /**
     * 错误代码
     */
    private static Properties properties;
    private static Properties propertiesMethod;
    public static String methodParams;	//方法参数加密

	public static final String SIGN = "sign";
	public static final String CHAR_ACTION = ".action";
	public static final String CURRENTUSER = "currentUser";
	public static final String CURRENTUSER_NAME = "currentUserName";
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String MODULE_LOGIN = "login";
    public static Map<String, String> allSimplePasswordMap = new HashMap<String, String>();//保存了所有简单密码
	public static final String AES_KEY = "";
	public static final String LOG_MODEL = "production";
    
	public static  Integer CODE_BASS_INDEX = 0;					//基础
	public static String MSG_NAME = "MsgName";

    public static final class Module{
    	
        public static final String BASE = "10";						//基础
        
    }
    
    static{
        InputStream fis = null;
        InputStream fisMethod = null;
        try {
        	String packageName = StringUtils.getPackageName(ErrorType.class, "/",5);
        	properties = new Properties();
    		fis = ErrorType.class.getClassLoader().getResourceAsStream(packageName + "/msg/lang-zh_CN.properties");
            properties.load(fis);
            
            propertiesMethod = new Properties();
            fisMethod = ErrorType.class.getClassLoader().getResourceAsStream(packageName + "/sign/sign.properties");
            propertiesMethod.load(fisMethod);
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

    public enum ErrorCode{
        /**
         * 系统错误
         */
        SUCCESS(Module.BASE,++ Constants.CODE_BASS_INDEX ,"SUCCESS" ), //操作成功
        FAILURE(Module.BASE,++ Constants.CODE_BASS_INDEX,"FAILURE"), //操作失败
        ;
        private int errCode;
        private String key;
        private String model;
        
        ErrorCode(String model,int errCode, String key) {
            this.model = model;
            this.errCode = errCode;
            this.key = key;
        }

        public int getErrCode() {
            return Integer.valueOf(String.format("%s%02d", model, errCode));
        }

        public String getMessage() {
            return properties.getProperty(key);
        }

        public String getMessage(String defaultKey) {
            return properties.getProperty(key,defaultKey);
        }
    }

	public static String getMethodParams(String key) {
		return propertiesMethod.getProperty(key);
	}
	
	public static String getValues(String key) {
		return properties.getProperty(key);
	}
}
