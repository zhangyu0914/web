package com.yw.common.beansUtils.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *<pre>
 * 功       能: WINDOWS工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午3:06:23
 * Q    Q: 308053847
 *</pre>
 */
public class WindowsUtil {
	public static void startStopTomcat(String command) throws Exception{
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		process.waitFor();
		bufferedReader.close();
	}
	
	public static void main(String[] args){
		try{
			//startStopTomcat("C:/ProgramFiles/Apache/tomcat6_2/bin/startup.bat");
			startStopTomcat("C:/ProgramFiles/Apache/tomcat6_2/bin/shutdown.bat");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
