package com.yw.common.beansUtils.utils.controller;
import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.yw.common.beansUtils.utils.string.StringUtils;


/**
 *<pre>
 * 功   能: 控制器基类
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-22下午4:09:02
 * Q  Q: 308053847
 *</pre>
 */
public class CommonBaseController {

	/**
	 *<pre>
	 * 说   明:  初始化数据绑定器(时间类型入参转换)
	 * @param request
	 * @param binder
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-30下午3:24:12
	 *</pre>
	 */
	@InitBinder
    public void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
		final String PATTERN_24_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; // 24小时制
		final String PATTERN_24_YYYY_MM_DD = "yyyy-MM-dd"; // 24小时制
		
		// 数字越界问题
        binder.setAutoGrowCollectionLimit(1024);
        // 处理DATE类型数据
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(PATTERN_24_YYYY_MM_DD_HH_MM_SS), true));
        // 处理TIMESTAMP类型数据
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                	Date parsedDate = null;
                	if (!StringUtils.isBlank(value)) {
                		
                		value = value.toUpperCase().replace("  ", " ");//2016-12-01  00:00:00
                		value = value.toUpperCase().replace("T", " ");//2016-12-01T00:00:00
                		if(value.length() == 10){
                			parsedDate = new SimpleDateFormat(PATTERN_24_YYYY_MM_DD).parse(value);
                		}else if(value.length() == 19){
                			parsedDate = new SimpleDateFormat(PATTERN_24_YYYY_MM_DD_HH_MM_SS).parse(value);
                		}
					}else{
						parsedDate = new SimpleDateFormat(PATTERN_24_YYYY_MM_DD_HH_MM_SS).parse(value);
					}
                    setValue(new Timestamp(parsedDate.getTime()));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        }); 
    }
}