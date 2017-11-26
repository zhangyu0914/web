package com.yw.appapi.web;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

/**
 * <pre>
 * 功       能: 解决跨域
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年5月23日上午10:50:47
 * Q    Q: 308053847
 * </pre>
 */
public class CorsFilter implements Filter {

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (StringUtil.isNotEmpty(allowOrigin)) {
        	
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            if (allowOriginList != null && !allowOriginList.isEmpty()) {
            	
                String currentOrigin = request.getHeader("Origin");
                if (!StringUtil.isBlank(currentOrigin)) {
					
                	for (String str : allowOriginList) {
                		
                		if (str.equals("*")) {//允许所有
                			response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                			break;
                		}
                		if (str.equals(currentOrigin)) {
                			response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                		}
                	}
				}else{
					
					for (String str : allowOriginList) {
                		
                		if (str.equals("*")) {//允许所有
                			response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                			break;
                		}
                	}
				}
            }
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}
