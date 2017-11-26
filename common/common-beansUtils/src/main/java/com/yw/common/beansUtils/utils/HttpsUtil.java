package com.yw.common.beansUtils.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * <pre>
 * 功       能: Https请求工具类
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年6月30日下午3:21:42
 * Q    Q: 308053847
 * </pre>
 */
public class HttpsUtil {
	public static void main(String[] args) throws IOException,
			KeyManagementException, NoSuchAlgorithmException {
		String uri = "https://kaolacode.com:6060/webplatform/app/findPage.action";
//		System.out.println(httpsNoEncrypt(uri));
		System.out.println(httpsLoadCA(uri, "D:/1certs"));
	}
	
	/**
	 * <pre>
	 * 说       明: 加载指定证书访问
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年7月4日上午10:39:06
	 * Q    Q: 308053847
	 * </pre>
	 * @param caPath TODO
	 */
	public static String httpsLoadCA(String uri, String caPath) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		 //serverkeys是通过keytool生成的自己的证书 
		  System.setProperty("javax.NET.ssl.trustStore",   caPath); //导入证书库的别名，而不是证书文件名
		  System.setProperty("javax.net.ssl.trustStorePassword",   "changeit");//证书库口令，非证书口令[密钥库口令]
		  URL   url   =   new   URL(uri); 
		  HttpURLConnection   connection   =   (HttpURLConnection)   url.openConnection(); 
		  connection.setRequestMethod( "POST"); 
		  connection.setDoOutput(true);
		  connection.setDoInput(true);
		     
		  StringBuffer   outbuff   =   new   StringBuffer(); 
		  BufferedReader   in   =   new   BufferedReader(new   InputStreamReader(connection.getInputStream())); 
		  String   line; 
		  while   ((line   =   in.readLine())   !=   null)   { 
		            outbuff.append(line); 
		  } 
		  in.close(); 
		return outbuff.toString();
	}

	/**
	 * <pre>
	 * 说       明: 不加载证书访问接口
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月30日下午3:20:31
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static String httpsNoEncrypt(String uri) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		HttpsURLConnection.setDefaultHostnameVerifier(new HttpsUtil().new NullHostNameVerifier());
		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		URL url = new URL(uri);
		// 打开restful链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");// POST GET PUT DELETE
		// 设置访问提交模式，表单提交
		conn.setRequestProperty("Content-Type","application/json;charset=utf-8");
		conn.setConnectTimeout(130000);// 连接超时 单位毫秒
		conn.setReadTimeout(130000);// 读取超时 单位毫秒
		// 读取请求返回值
		InputStream inStream = conn.getInputStream();
		byte[] bytes = NetTool.read(inStream);
		return new String(bytes, "utf-8");
	}

	static TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	} };

	public class NullHostNameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}
}