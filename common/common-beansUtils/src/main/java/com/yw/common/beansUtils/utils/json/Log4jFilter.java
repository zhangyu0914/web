package com.yw.common.beansUtils.utils.json;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;

import com.yw.common.beansUtils.entity.InterfaceEntity;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.enums.LogEnum;
import com.yw.common.beansUtils.utils.enums.RedisTypeEnum;
import com.yw.common.beansUtils.utils.ip.IpUtil;
import com.yw.common.beansUtils.utils.redisUtil.RedisUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

public class Log4jFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		try {
			HttpServletRequest request = (HttpServletRequest) req;
			String ip = IpUtil.getLocalIp();
			String method = request.getParameter("method");// 获取用户名
			InterfaceEntity ie = JavaBeanUtil.jsonToJavaBean(RedisUtil.getHSet(RedisTypeEnum.INTERFACE.getCode(), method), InterfaceEntity.class);
			
			if (StringUtils.isBlankOr(ie)) {
				chain.doFilter(req, res);
				return;
			}
			
			chain.doFilter(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}