<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    	function commit(){
    		$("form").submit();
    	}
    	function del(){
			$.ajax({   
                url : "testDeleteTable/deleteTable.action",
                type : "POST",
                dataType : "json",
                contentType : "application/json;charset=UTF-8",
                success : function(result) {
                	if(result.code=="0"){
                		alert("删除成功");
                	}
                },
                error:function(result){
                    alert("Sorry,you are make a error!");
                }
            });
		}
	</script>
</head>
  
  <body>
	  <table cellspacing="0" cellpadding="4" border="1" bordercolor="#224466"
			width="100%">
	  		<tr>
				<td>1、导入文件</td>
				<td>
				<form name="form" action="<%=basePath%>/import/importFile.action" enctype="multipart/form-data" method="post">
					导入文件：<input type="file" name=uploadFile>
					<input type="button" onclick="commit();" value="提交">
				</form>
			</td>
			</tr>
	  		<tr>
				<td>2、绑定应用实例</td>
				<td>
				    物联网共性平台
				</td>
			</tr>
	  		<tr>
				<td>3、删除表数据</td>
				<td>
					<input type="button" onclick="del()" value="删除" >
				</td>
			</tr>
			<tr>
				<td>t_interface_log</td>
				<td>调用接口记录</td>
			</tr>
	  		<tr>
				<td>t_app_account_interface</td>
				<td>可访问接口列表</td>
			</tr>
	  		<tr>
				<td>t_app_account</td>
				<td>APP账户</td>
			</tr>
	  		<tr>
				<td>t_app</td>
				<td>应用表</td>
			</tr>
	  		<tr>
				<td>t_app_function</td>
				<td>应用权限</td>
			</tr>
	  		<tr>
				<td>t_app_device</td>
				<td>应用与设备</td>
			</tr>
	  		<tr>
				<td>t_device_license</td>
				<td>认证记录</td>
			</tr>
	  		<tr>
				<td>t_device</td>
				<td>设备表</td>
			</tr>

	  		<tr>
				<td>t_device_attribute</td>
				<td>设备属性</td>
			</tr>
	  		<tr>
				<td>t_io_data</td>
				<td>IO数据</td>
			</tr>
	  		<tr>
				<td>t_attribute</td>
				<td>属性表</td>
			</tr>
	  		<tr>
				<td>t_model</td>
				<td>设备型号表</td>
			</tr>
	  		<tr>
				<td>t_model_att</td>
				<td>设备型号与属性表</td>
			</tr>
	  		<tr>
				<td>t_model_config</td>
				<td>型号配置信息</td>
			</tr>
	  		<tr>
				<td>t_model_ep</td>
				<td>型号流程</td>
			</tr>
	  		<tr>
				<td>t_app_license</td>
				<td>型号许可证表</td>
			</tr>
	  		<tr>
				<td>t_device_license</td>
				<td>SN许可证表</td>
			</tr>
	  		<tr>
				<td>t_license</td>
				<td>许可证表</td>
			</tr>
	  		<tr>
				<td>t_device_model_att</td>
				<td>设备与型号属性表</td>
			</tr>
		</table>		
  </body>
</html>