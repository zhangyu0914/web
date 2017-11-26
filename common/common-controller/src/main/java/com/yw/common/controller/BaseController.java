package com.yw.common.controller;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *<pre>
 * 功   能: 功能说明 ：控制器共享类
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-9-13下午1:10:25
 * Q  Q: 308053847
 *</pre>
 */
public class BaseController {
	
	/**
	 *<pre>
	 * 说   明:  时间类型入参转换
	 * @param request
	 * @param binder
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-30下午3:24:12
	 *</pre>
	 */
	@InitBinder
    protected void initBinder(HttpServletRequest request, 
            ServletRequestDataBinder binder) throws Exception {
		final String PATTERN_24_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; // 24小时制
		
		// 数字越界问题
        binder.setAutoGrowCollectionLimit(1024);
        // 处理DATE类型数据
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(PATTERN_24_YYYY_MM_DD_HH_MM_SS), true));
        // 处理TIMESTAMP类型数据
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                	if (value != null && value.length() != 19) {
//                		value += DateUtils.PARAM_TIME_STAR;
					}
                    Date parsedDate = new SimpleDateFormat(PATTERN_24_YYYY_MM_DD_HH_MM_SS).parse(value);
                    setValue(new Timestamp(parsedDate.getTime()));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        }); 
    }
}