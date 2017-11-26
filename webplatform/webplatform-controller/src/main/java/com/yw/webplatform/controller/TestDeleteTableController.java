package com.yw.webplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.ITestDeleteTableService;

@Controller
@RequestMapping("testDeleteTable")
public class TestDeleteTableController extends BaseController{
	@Autowired
	private ITestDeleteTableService testDeleteTableService;
	
	@RequestMapping("deleteTable")
    @ResponseBody
    public ResultUtil deleteTableV200(HttpServletRequest request,
    		HttpServletResponse response) throws Exception{
    	ResultUtil resultUtil = new ResultUtil();
    	try {
    		resultUtil=this.testDeleteTableService.deleteTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resultUtil;
    }
}
