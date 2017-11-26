package com.yw.common.beansUtils.utils.image;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.GetPolicy;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.URLUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 *<pre>
 * 功   能: 七牛图片上传下载处理
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-8-26下午8:48:27
 * Q  Q: 308053847
 *</pre>
 */
public class QiNiuUtil {

	//七牛
	public static String QNACCESSKEY="F7l1VRvbTbnMcV3YKE10LCNYjUSS3oXbevZ5cfD8";
	public static String QNSECRETKEY="w1T6mdxF0GZq3m5eXbcJSVZhX4Qi4Lo2IITCx0bH";
	public static final Integer REDIS_UPLOAD_EXPIRES=7200;			//上传资源时的有效时间(秒)
	
	public static final String BUCKET_ASKDR = "askdr";//包名=文件夹
	public static final String BUCKET_ANDOID_CRASH_LOG="askdrandroidcrashlog";
	public static final String BUCKET_IOS_CRASH_LOG="askdrioscrashlog";
	public static final String BUCKET_ANDOID_CRASH_DOWNLOAD="http://oi1s4e5fl.bkt.clouddn.com/";
	public static final String BUCKET_IOS_CRASH_DOWNLOAD="http://oi1shmgvy.bkt.clouddn.com/";
	public static final String BUCKET_MP3 = "mp3";//包名=文件夹
	public static final String URL_ASKDR = "askdrportrait.qiniudn.com";//七牛请求域名链接

	// 头像的空间，存储为公有
	public static final String BUCKET_ASKDR_PORTRAIT = "askdrportrait";//头象,包名=文件
	public static final String URL_ASKDR_PORTRAIT = BUCKET_ASKDR_PORTRAIT + ".qiniudn.com";//域名
	
	public static final String BUCKET_ASKDRZJ = "askdrzj";//头象,包名=文件
	public static final String URL_ASKDRZJ = BUCKET_ASKDRZJ + ".qiniudn.com";//域名
	
	public static final String BUCKET_ASKDRARTICLE = "askdrarticle";//头象,包名=文件
	public static final String URL_ASKDRARTICLE = BUCKET_ASKDRARTICLE + ".qiniudn.com";//域名

	public static final String BUCKET_ASKDRHISTORY = "askdrhistory";//头象,包名=文件
	public static final String URL_ASKDRHISTORY = BUCKET_ASKDRHISTORY + ".qiniudn.com";//域名

	public static final String CALLBACK_URL = "http://115.28.10.142/bbs1/test.php";
	public static final String CALLBACK_BODY = "key=$(key)&hash=$(etag)&fname=$(fname)&bucket=$(bucket)&moudle=$(x:moudle)&domain=$(x:domain)";

	public static final String RETURNBACK_BODY = "{\"key\": $(key), \"hash\": $(etag), \"bucket\": $(bucket),\"moudle\":$(x:moudle),\"domain\":$(x:domain),\"fname\":$(fname)}";

	/**
	 * <pre>
	 * 说 明: 获取上传的TOKEN，并回调指定URL
	 * @param bucketName	包名
	 * @param callbackUrl	回调地址	
	 * @param callbackBody	回调的数据格式
	 * @return
	 * @throws Exception
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-30下午6:13:47
	 * </pre>
	 */
	public static String uploadTokenCallback(String bucketName,
			String callbackUrl, String callbackBody) throws Exception {

		String redisKey = "uploadCallback" + bucketName;
		
		Mac mac = new Mac(QNACCESSKEY, QNSECRETKEY);

		PutPolicy putPolicy = new PutPolicy(bucketName);
		putPolicy.expires = REDIS_UPLOAD_EXPIRES;

		if (!StringUtils.isBlank(callbackUrl)) {
			putPolicy.callbackUrl = callbackUrl;
		}
		if (!StringUtils.isBlank(callbackBody)) {
			putPolicy.callbackBody = callbackBody;
		}
		return putPolicy.token(mac);
	}

	/**
	 * <pre>
	 * 说 明: 获取上传的Token,并返回数据
	 * @param bucketName	包名
	 * @param returnbackBody	返回的数据格式
	 * @return
	 * @throws Exception
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-30下午6:15:15
	 * </pre>
	 */
	public static String uploadTokenRetrunback(String bucketName,
			String returnbackBody) throws Exception {

		String redisKey = "uploadReturnback" + bucketName;
		Mac mac = new Mac(QNACCESSKEY, QNSECRETKEY);

		PutPolicy putPolicy = new PutPolicy(bucketName);
		putPolicy.expires = REDIS_UPLOAD_EXPIRES;

		if (!StringUtils.isBlank(returnbackBody)) {
			putPolicy.returnBody = returnbackBody;
		}
		return putPolicy.token(mac);
	}

	/**
	 * <pre>
	 * 说 明: 上传资源并回调指定URL
	 * @param key	上传为指定的KEY 建议使用UUID
	 * @param localFile	本地文件路径
	 * @param bucketName	包名
	 * @param params		此参数必需和指定的格式对应
	 * @return
	 * @throws Exception
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-30下午6:16:16
	 * </pre>
	 */
	public static PutRet uploadFileCallBack(String key, String localFile,
			String bucketName, Map<String, String> params) throws Exception {

		String uptoken = QiNiuUtil.uploadTokenCallback(bucketName,
				QiNiuUtil.CALLBACK_URL, QiNiuUtil.CALLBACK_BODY);
		PutExtra extra = new PutExtra();
		extra.params = params;
		PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
		System.out.println("upload callback:" + key + "=" + ret);
		return ret;
	}

    public static PutRet uploadStreamCallBack(String key, InputStream localFile,
                                            String bucketName, Map<String, String> params) throws Exception {

        String uptoken = QiNiuUtil.uploadTokenCallback(bucketName,
                QiNiuUtil.CALLBACK_URL, QiNiuUtil.CALLBACK_BODY);
        PutExtra extra = new PutExtra();
        extra.params = params;
        PutRet ret = IoApi.Put(uptoken, key, localFile, extra);
        System.out.println("upload callback:" + ret);
        return ret;
    }
	/**
	 * <pre>
	 * 说 明: 
	 * @param key	上传为指定的KEY 建议使用UUID
	 * @param localFile	本地文件路径
	 * @param bucketName	包名
	 * @param params	必需格式对应的参数
	 * @return
	 * @throws Exception
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-30下午6:20:39
	 * </pre>
	 */
	public static PutRetResponse uploadFileReturnBack(String key, String localFile,
			String bucketName, Map<String, String> params) throws Exception {

		String uptoken = QiNiuUtil.uploadTokenRetrunback(bucketName, QiNiuUtil.RETURNBACK_BODY);
		PutExtra extra = new PutExtra();
		extra.params = params;
		PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
		System.out.println("upload return:" + ret);
		return JavaBeanUtil.jsonToJavaBean(ret.getResponse(), PutRetResponse.class);
	}

	/**
	 * <pre>
	 * 说 明: 下载私有资源
	 * @param domain	包对的域名
	 * @param key	资源KEY
	 * @param param	格式化参数
	 * @return
	 * @throws Exception
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-29下午3:50:46
	 * </pre>
	 */
	public static String privateDownUrl(String domain, String key, String param)
			throws Exception {
		String downUrl = "";
		downUrl = URLUtils.makeBaseUrl(domain, key);
		if (StringUtils.isBlank(param)) {
			downUrl = downUrl + "?" + param;
		}

		GetPolicy getPolicy = new GetPolicy();
		getPolicy.expires = 7200;
		Mac mac = new Mac(QNACCESSKEY, QNSECRETKEY);
		downUrl = getPolicy.makeRequest(downUrl, mac);

		return downUrl;
	}

	/**
	 * <pre>
	 * 说 明: 下载公有资源
	 * @param domain	包对的域名
	 * @param key	资源KEY
	 * @param param	格式化参数
	 * @return
	 * @throws Exception
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-30上午10:15:31
	 * </pre>
	 */
	public static String publicDownUrl(String domain, String key, String param)
			throws Exception {
		String downUrl = "";
		downUrl = URLUtils.makeBaseUrl(domain, key);
		if (param != null) {
			downUrl = downUrl + "?" + param;
		}else{
			return downUrl;
		}
		return downUrl;
	}
	
	/**
	 *<pre>
	 * 说   明: 上传七牛图片
	 * @param filePath 
	 * @param bucket 
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-10-13下午4:20:19
	 *</pre>
	 */
	public static void upload(String filePath, String bucket) throws Exception{

		Map<String, String> params = new HashMap<String, String>();
		params.put("x:moudle", "test"); // 设置一个自定义变量
		params.put("x:domain", QiNiuUtil.URL_ASKDR_PORTRAIT);

		File file = new File(filePath);
		
		if (file.isDirectory()) {
			
			for (File f: file.listFiles()) {
				uploadFileReturnBack(f.getName(), f.getPath(), bucket, params);
			}
		}else {
			uploadFileReturnBack(file.getName(), file.getPath(), bucket, params);
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 上传文件
	 * @param filePath
	 * @param bucket
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-23下午3:09:12
	 *</pre>
	 */
	public static String uploadFile(String filePath, String bucket) throws Exception{

		Map<String, String> params = new HashMap<String, String>();
		params.put("x:moudle", "test"); // 设置一个自定义变量
		params.put("x:domain", QiNiuUtil.URL_ASKDR_PORTRAIT);

		
		File file = new File(filePath);
		
		if (!file.isDirectory()) {
			
			PutRetResponse response = uploadFileReturnBack(file.getName(), file.getPath(), bucket, params);
			return "http://" + response.getBucket() + ".qiniudn.com/" + response.getFname();
		}
		return bucket + file.getName();
	}
	
	public static String uploadStream(String key, String bucket, InputStream inputSteam) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("x:moudle", "test"); // 设置一个自定义变量
		params.put("x:domain", QiNiuUtil.URL_ASKDR_PORTRAIT);

		String uptoken = QiNiuUtil.uploadTokenCallback(bucket, QiNiuUtil.CALLBACK_URL, QiNiuUtil.CALLBACK_BODY);
		PutExtra extra = new PutExtra();
		extra.params = params;
		IoApi.Put(uptoken, key, inputSteam, extra);
		
		return "http://" + bucket + ".qiniudn.com/" + key;
	}

	public static void main(String[] args) {
		try {
			//上传图片
//			String filePath = "I://xampp//htdocs//medmeeting//images//hudong_zhoujingmin.png";
//			String bucket = BUCKET_ASKDRZJ;
//			String key="27ED7320-6F5A-4860-BA37-9514AF0D459A";
//			delete(bucket,key);
//			String uptoken = getUploadToken(BUCKET_ASKDRZJ,key);
//			Map<String, String> params = new HashMap<String, String>();
//			params.put("x:moudle", BUCKET_ASKDRZJ); // 设置一个自定义变量
//			params.put("x:domain", URL_ASKDRZJ);
//			PutExtra extra = new PutExtra();
//			extra.params = params;
//			PutRet ret = IoApi.putFile(uptoken, key, filePath, extra);
//			System.out.println("upload return:" + getDownloadToken("",""));
//			System.out.println(JavaBeanUtil.jsonToJavaBean(ret.getResponse(), PutRetResponse.class));
			
//			uploadFile("D:/test/doctor_card_backgroud.png", QiNiuUtil.BUCKET_ASKDR_PORTRAIT);
//			uploadFile("D:/test/doctor_card_contact.png", QiNiuUtil.BUCKET_ASKDR_PORTRAIT);
//			uploadFile("D:/test/doctor_card_doctor001.jpg", QiNiuUtil.BUCKET_ASKDR_PORTRAIT);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getBucketAskdr() {
		return BUCKET_ASKDR;
	}

	public static String getUrlAskdr() {
		return URL_ASKDR;
	}

	public static String getBucketAskdrPortrait() {
		return BUCKET_ASKDR_PORTRAIT;
	}

	public static String getUrlAskdrPortrait() {
		return URL_ASKDR_PORTRAIT;
	}
	
	/**
	 * <pre>
	 * 说 明: 获取上传的TOKEN
	 * @param bucketName	包名
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String getUploadToken(String bucketName,String key) throws Exception {
		Mac mac = new Mac(QNACCESSKEY, QNSECRETKEY);
		PutPolicy putPolicy = new PutPolicy(bucketName);
		putPolicy.expires = REDIS_UPLOAD_EXPIRES;
		if(key!=null&&key!=""){
			putPolicy.scope=bucketName+":"+key;
		}
		
		return putPolicy.token(mac);
	}

	
	public static void delete(String bucket,String key){
		 try { //设置需要操作的账号的AK和SK
		    Auth auth = Auth.create(QNACCESSKEY, QNSECRETKEY);
	
		    Zone z = Zone.zone0();
		    Configuration c = new Configuration(z);
	
		    //实例化一个BucketManager对象
		    BucketManager bucketManager = new BucketManager(auth,c);
		    //要测试的空间和key，并且这个key在你空间中存在
		   
		      //调用delete方法移动文件
		      bucketManager.delete(bucket, key);
		    } catch (QiniuException e) {
		      //捕获异常信息
		      Response r = e.response;
		      System.out.println(r.toString());
		    }catch (Exception e) {
	    	
	    }
	}
	
	/**
	 * <pre>
	 * 说 明: 获取上传的TOKEN
	 * @param bucketName	包名
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String getDownloadToken(String bucketdomain,String key) throws Exception {
		 Auth auth = Auth.create(QNACCESSKEY, QNSECRETKEY);
		
		return auth.privateDownloadUrl(bucketdomain+key, 7200);
	}
	
	
	public static String uploadFile(byte[] data, String key, String token){
		 try {
			Zone zone = Zone.autoZone();
			 
			 Configuration config = new Configuration(zone);
			 UploadManager uploadManager = new UploadManager(config);
			 return JavaBeanUtil.javaBeanToString(uploadManager.put(data, key, token));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
}
