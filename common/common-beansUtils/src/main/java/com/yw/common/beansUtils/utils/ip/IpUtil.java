package com.yw.common.beansUtils.utils.ip;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: IP工具类
 * 涉及版本: V1.0.0
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-7-15下午2:57:06
 * Q    Q: 308053847
 *</pre>
 */
public class IpUtil {
	
	private static Logger log = Logger.getLogger(IpUtil.class);
	
	public static void main(String[] args) {
		try {
			System.out.println(IpUtil.isIp("192.168.1.158"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 获取登录用户的IP地址
	 * @param request
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-15下午2:57:18
	 *</pre>
	 */
	public static String getIp(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = getLocalIp();
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	/**
	 *<pre>
	 * 说       明: 根据IP进行解析
	 * @param request
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-15下午2:57:28
	 *</pre>
	 */
	public static SinaIp getSinaIp(HttpServletRequest request) throws Exception {
		return getSinaIp(getIp(request));
	}

	/**
	 * <pre>
	 * 说       明: 根据IP进行解析
	 * @param ip
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2014-12-23下午1:49:06
	 * </pre>
	 */
	public static SinaIp getSinaIp(String ip) {
		SinaIp s = new SinaIp();
		try {
			if (ip == null || ip.equals("")
					|| !IpUtil.isIp(ip)) {
				return null;
			}
			String json = UrlUtil.postNoSecret(
					"http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=" + ip + "&format=json");
			if (json == null || json.equals("")) {
				return null;
			}
			s = UrlUtil.parse(json, SinaIp.class);
			s.setIp(ip);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 *<pre>
	 * 说       明: 获取本机IP
	 * @return
	 * 涉及版本: V1.0.0
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-15下午2:57:45
	 *</pre>
	 */
	public static String getLocalIp() {
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString();// 获得本机IP
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param request TODO IPUTIL
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23上午11:54:41
	 *</pre>
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	public static String getLocalAddress(String filter) {
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) netInterfaces
						.nextElement();
				Enumeration nii = ni.getInetAddresses();
				while (nii.hasMoreElements()) {
					InetAddress ip = (InetAddress) nii.nextElement();
					if (!ip.getHostAddress().contains(":")) {
						String[] h = ip.getHostAddress().split("\\.");
						String[] b = filter.split("\\.");
						int i = 0;
						for(i=0;i<b.length;i++){
							String s = b[i];
							if(s.equals("*"))continue;
							if(!h[i].equals(s))break;
						}
						if(i == 4){
							return ip.getHostAddress();
						}
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "127.0.0.1";
	}
	
	public static boolean isIp(String ipAddress) {
		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}
}
