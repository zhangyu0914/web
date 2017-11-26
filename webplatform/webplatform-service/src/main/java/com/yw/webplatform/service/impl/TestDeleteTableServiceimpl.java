package com.yw.webplatform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.webplatform.api.ITestDeleteTableService;
@Service("testDeleteTableService")
public class TestDeleteTableServiceimpl extends BaseMapperImpl implements ITestDeleteTableService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public ResultUtil deleteTable() throws Exception {
		ResultUtil resultUtil = new ResultUtil();
		List<String> list=new ArrayList<String>();
		list.add("t_interface_log");
		list.add("t_app_account_interface");
		list.add("t_platform_app");
		list.add("t_app_device");
		list.add("t_app_account");
		list.add("t_app");
		list.add("t_app_function");		
		list.add("t_auth_log");		
		list.add("t_device");
		list.add("t_io_data");
		list.add("t_attribute");
		list.add("t_model_att");
		list.add("t_model");
		list.add("t_model_config");
		list.add("t_model_ep");
		list.add("t_app_license");
		list.add("t_device_license");
		list.add("t_license");
		list.add("t_device_model_att");
		for (int i = 0; i < list.size(); i++) {
			String sql="delete from ";
			sql+=list.get(i);
			if(jdbcTemplate.update(sql)<0){
				return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
			}
		}
		if(jdbcTemplate.update("truncate table t_device_attribute")<0) {
			return resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_DEL);
		}
		return resultUtil.setCode(ErrorTypeEnum.SUCCESS);
	}
	
	

}
