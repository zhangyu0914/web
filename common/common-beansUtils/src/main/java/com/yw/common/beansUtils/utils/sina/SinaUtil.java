package com.yw.common.beansUtils.utils.sina;

import java.net.URLEncoder;
import java.util.List;

import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.UrlUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: 新浪工具类
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-11-9上午11:31:09
 * Q    Q: 308053847
 *</pre>
 */
public class SinaUtil {
	
	public static final String source = "3213676317";
	public static final String SHORT_URL = "http://api.t.sina.com.cn/short_url/shorten.json";
	
	public static void main(String[] args) {
		try {
			String urlLong = "http://www.baidu.com";
			System.out.println(getShortUrl(urlLong).getUrl_short());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *<pre>
	 * 说       明: 获取短链接
	 * @param urlLong
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-9上午11:35:22
	 *</pre>
	 */
	public static ShortUrl getShortUrl(String urlLong) throws Exception{
		String resultUrl = UrlUtil.postNoSecret(SHORT_URL + "?source="+source+"&url_long=" + URLEncoder.encode(urlLong, "utf-8"));
		System.out.println("--------------:"+resultUrl);
		if (StringUtils.isBlankOr(resultUrl)) {
			return null;
		}
		List<ShortUrl> list = JavaBeanUtil.jsonToList(resultUrl, ShortUrl.class);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}
