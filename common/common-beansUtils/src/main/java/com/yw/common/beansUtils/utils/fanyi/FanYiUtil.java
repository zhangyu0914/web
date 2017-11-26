package com.yw.common.beansUtils.utils.fanyi;

import java.net.URLEncoder;

import com.google.gson.Gson;
import com.yw.common.beansUtils.utils.UrlUtil;

/**
 *<pre>
 * 功       能: 中英文翻译，中->英
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-10-29下午3:19:39
 * Q    Q: 308053847
 *</pre>
 */
public class FanYiUtil {
	public static void main(String[] args) {
		try {
			System.out.println(FanYiUtil.chineseToEnglish("医路同行"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *<pre>
	 * 说       明: 中文翻译成英文
	 * @param source
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-29下午3:20:03
	 *</pre>
	 */
	public static String chineseToEnglish(String source) throws Exception{
		String api_url = new StringBuilder("http://fanyi.baidu.com/transapi?from=zh&to=en&query=")
					.append(URLEncoder.encode(source, "utf-8")).toString();
		String json = UrlUtil.postNoSecret(api_url);
		Gson gson = new Gson();
		TranslateMode translateMode = gson.fromJson(json,
				TranslateMode.class);

		if (translateMode != null && translateMode.getData() != null
				&& translateMode.getData().size() == 1) {
			return translateMode.getData().get(0).getDst();
		}
		return null;
	}
}
