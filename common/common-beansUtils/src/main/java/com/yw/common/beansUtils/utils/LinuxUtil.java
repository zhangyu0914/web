package com.yw.common.beansUtils.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.trilead.ssh2.Connection;
import com.trilead.ssh2.SCPClient;
import com.trilead.ssh2.Session;
import com.trilead.ssh2.StreamGobbler;

/**
 *<pre>
 * 功       能: Linux工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:26:55
 * Q    Q: 308053847
 *</pre>
 */
public class LinuxUtil {
	
	private static Logger log = Logger.getLogger(LinuxUtil.class);
	/**
	 * <pre>
	 * 说       明: 使用SSH连接LINUX服务器 TODO LINUXUTIL
	 * @param serverIp
	 * @param userName
	 * @param password
	 * @param command
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-19下午1:17:21
	 * </pre>
	 */
	public static String[] sshLinux(String serverIp, String userName,
			String password, String command) throws Exception {

		if (serverIp == null || userName == null || password == null
				|| command == null) {

			return null;
		}
		Connection conn = new Connection(serverIp);
		conn.connect();

		boolean isAuthenticated = conn.authenticateWithPassword(userName,
				password);
		if (isAuthenticated == false)
			throw new IOException("连接服务器失败");

		Session sess = conn.openSession();
		sess.requestDumbPTY();
		// ----------------------------------------执行命令-------------------------
		sess.execCommand(command);
		InputStream stdout = new StreamGobbler(sess.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
		String line = null;
		List<String> list = new ArrayList<String>();
		while ((line = br.readLine()) != null) {
			list.add(line);
			System.out.println(line);
		}
		sess.close();
		conn.close();
		if (list.size() == 0) {
			return null;
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * <pre>
	 * 说       明: 使用SCP复制文件或目录 TODO LINUXUTIL
	 * @param serverIp
	 * @param userName
	 * @param password
	 * @param sourceFileOrDir
	 * @param targetDir
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-21上午10:10:37
	 * </pre>
	 */
	public static boolean scpLinux(String serverIp, String userName,
			String password, String sourceFileOrDir, String targetDir)
			throws Exception {

		if (serverIp == null || userName == null || password == null) {

			return false;
		}
		Connection conn = new Connection(serverIp);
		conn.connect();

		boolean isAuthenticated = conn.authenticateWithPassword(userName,
				password);
		if (isAuthenticated == false)
			throw new IOException("连接服务器失败");

		SCPClient scpclient = new SCPClient(conn);
		scpclient.put(sourceFileOrDir, targetDir);
		conn.close();
		return true;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * @param order : "ps -ef|grep java" TODO LINUXUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-17下午4:51:21
	 * </pre>
	 */
	public static String processOrder(String order) throws Exception {
		String[] cmds = { "/bin/sh", "-c", order };
		Process pro = Runtime.getRuntime().exec(cmds);
		pro.waitFor();
		InputStream in = pro.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = read.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
