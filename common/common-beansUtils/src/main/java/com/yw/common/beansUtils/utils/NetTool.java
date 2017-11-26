package com.yw.common.beansUtils.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;


/**
 * @author JeffreyShi
 * @Mail : shijunfan@gmail.com
 */

public class NetTool {

	public static byte[] GET(String url) throws Exception {
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl
				.openConnection();
		urlConnection.setRequestMethod("GET");
		urlConnection.setConnectTimeout(45 * 1000);
		if (urlConnection.getResponseCode() == 200) {
			byte[] bytes = NetTool.read(urlConnection.getInputStream());
			urlConnection.disconnect();
			return bytes;
		}
		return null;
	}

    public static byte[] read(InputStream inputStream) throws IOException {
        ByteArrayOutputStream arrayBuffer = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            arrayBuffer.write(b, 0, len);
        }
        inputStream.close();
        arrayBuffer.close();
        return arrayBuffer.toByteArray();
    }
}
