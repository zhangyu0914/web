package com.yw.webplatform.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 参数特殊字符过滤
 *
 * @author   单红宇(365384722)
 * @myblog  http://blog.csdn.net/catoop/
 * @create    2015年9月18日
 */
public class MHttpServletRequest extends HttpServletRequestWrapper {

    public MHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        // 返回值之前 先进行过滤
        return XssShieldUtil.stripXss(super.getParameter(XssShieldUtil.stripXss(name)));
    }

    @Override
    public String[] getParameterValues(String name) {
        // 返回值之前 先进行过滤
        String[] values = super.getParameterValues(XssShieldUtil.stripXss(name));
        if(values != null){
            for (int i = 0; i < values.length; i++) {
                values[i] = filterDangerString(values[i]);
            }
        }
        return values;
    }
    
    public String filterDangerString(String value) {
        if (value == null) {
            return null;
        }
        value = value.replaceAll("'", "").trim();
        value = value.replaceAll("&", "").trim();
        return value;
    }
   
    public String[] filterDangerString(String[] value) {
        if (value == null) {
            return null;
        }
        for (int i = 0; i < value.length; i++) {
            String val = filterDangerString(value[i]);
            value[i] = val;
        }

        return value;
    }

}