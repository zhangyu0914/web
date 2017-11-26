package com.yw.common.beansUtils.utils.resultUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.common.beansUtils.utils.InterfacePage;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-24上午9:39:51
 * Q  Q: 308053847
 * </pre>
 */
public class AskDrResultUtil<T> implements Serializable {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	private static final long serialVersionUID = 5984957786423092079L;
	
	private T data;
	private ErrorTypeEnum codeEnum;
	private InterfacePage page;
	private Timestamp systemTime = new Timestamp(System.currentTimeMillis());// 当前系统时间，作用：客户端计算多久前评论的

	public AskDrResultUtil() {
		this.codeEnum = ErrorTypeEnum.FAILURE;
	}

	public AskDrResultUtil(ErrorTypeEnum errorCode) {
		this.codeEnum = errorCode;
	}

	public T getData() {
		return data;
	}

	public AskDrResultUtil<T> setData(T data) {
		this.data = data;
		return this;
	}
	
	/**
	 * <pre>
	 * 说       明: 必填参数没赋值提醒
	 * 涉及版本: V3.3.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年8月25日下午6:08:25
	 * Q    Q: 308053847
	 * </pre>
	 */
	public AskDrResultUtil<T> setData(Object entity, T data) {
		if (StringUtils.isBlank(entity)) {
			this.data = data;
			return this; 
		}
		try {
			StringBuffer sb = new StringBuffer();
			String jsonStr = JSON.toJSONString(entity);
			if (jsonStr.indexOf("{") == -1) {
				
				this.data = data;
				return this;
			}
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			for (String str: data.toString().split(",")) {
				
				if (StringUtils.isBlank(jsonObject.get(str))) {
					sb.append(str+",");
				}
			}
			log.info("jsonStr:" + jsonStr);
			if (sb.length() > 0) {
				this.data = (T)(StringUtils.resplaceSb(sb.toString()) + "[参数需要赋值]");
			}
			log.info(this.data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AskDrResultUtil<T> setCodeEnum(ErrorTypeEnum errorCode) {
		this.codeEnum = errorCode;
		return this;
	}
	
	public AskDrResultUtil<T> setCodeEnum(ErrorTypeEnum errorCode, List list) {
		this.codeEnum = errorCode;
		return this;
	}

	public InterfacePage getPage() {
		return page;
	}

	public AskDrResultUtil<T> setPage(InterfacePage page) {
		page.setList(null);// 初始化，因为PAGE对象中不需要LIST
		this.page = page;
		return this;
	}

	public Timestamp getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Timestamp systemTime) {
		this.systemTime = systemTime;
	}

	public String getCodeEnum() {
		return codeEnum.getCode();
	}
}
