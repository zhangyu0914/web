<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>接口导航</title>
<style type="text/css">
<!--
body,table {
	font-family: arial, sans-serif;
	font-size: 15px;
}

th {
	background: #336699;
	color: #FFFFFF;
	text-align: center;
}
-->
</style>
</head>
<body bgcolor="#FFFFFF" topmargin="6" leftmargin="6">

	<table cellspacing="0" cellpadding="4" border="1" bordercolor="#224466"
		width="100%">
		<tr>
			<th>序号</th>
			<th>接口名称</th>
			<th>URL</th>
			<th>演示地址</th>
			<th>开发人员</th>
		</tr>
		<tr>
			<td>无</td>
			<td>调用REDIS</td>
			<td><a href="<%= basePath %>configapi/v100/initData.action" target="_blank"><%= basePath %>configapi/v100/initData.action</a></td>
			<td><a href="initData.jsp" target="_blank">initData.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1001</td>
			<td>获取型号信息接口</td>
			<td><a href="<%= basePath %>configapi/v100/model/get.action" target="_blank"><%= basePath %>configapi/v100/model/get.action</a></td>
			<td><a href="modelGet.jsp" target="_blank">modelGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1002</td>
			<td>获取应用信息接口</td>
			<td><a href="<%= basePath %>configapi/v100/app_instance/get.action" target="_blank"><%= basePath %>configapi/v100/app_instance/get.action</a></td>
			<td><a href="appInstanceGet.jsp" target="_blank">appInstanceGet.jsp</a></td>
			<td>zhangyu</td>
		</tr>
		<tr>
			<td>1003</td>
			<td>获取许可证信息接口</td>
			<td><a href="<%= basePath %>configapi/v100/license/get.action" target="_blank"><%= basePath %>configapi/v100/license/get.action</a></td>
			<td><a href="licenseGet.jsp" target="_blank">licenseGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1004</td>
			<td>获取属性信息接口</td>
			<td><a href="<%= basePath %>configapi/v100/profile/get.action" target="_blank"><%= basePath %>configapi/v100/profile/get.action</a></td>
			<td><a href="profileGet.jsp" target="_blank">profileGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1005</td>
			<td>获取应用-设备绑定信息接口</td>
			<td><a href="<%= basePath %>configapi/v100/app_instance_dev/get.action" target="_blank"><%= basePath %>configapi/v100/app_instance_dev/get.action</a></td>
			<td><a href="appInstanceDev.jsp" target="_blank">appInstanceDev.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1006</td>
			<td>导入PROFILE</td>
			<td><a href="<%= basePath %>importProfile.jsp" target="_blank"><%= basePath %>importProfile.jsp</a></td>
			<td><a href="importProfile.jsp" target="_blank">importProfile.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1007</td>
			<td>导入型号</td>
			<td><a href="<%= basePath %>importModel.jsp" target="_blank"><%= basePath %>importModel.jsp</a></td>
			<td><a href="importModel.jsp" target="_blank">importModel.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1009</td>
			<td>导入应用</td>
			<td><a href="<%= basePath %>importAppInstance.jsp" target="_blank"><%= basePath %>importAppInstance.jsp</a></td>
			<td><a href="importAppInstance.jsp" target="_blank">importAppInstance.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1010</td>
			<td>导入许可证</td>
			<td><a href="<%= basePath %>importLicense.jsp" target="_blank"><%= basePath %>importLicense.jsp</a></td>
			<td><a href="importLicense.jsp" target="_blank">importLicense.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1011</td>
			<td>获取应用密钥</td>
			<td><a href="<%= basePath %>appSecret.jsp" target="_blank"><%= basePath %>appSecret.jsp</a></td>
			<td><a href="appSecret.jsp" target="_blank">appSecret.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1012</td>
			<td>设备接入</td>
			<td><a href="<%= basePath %>devicebind.jsp" target="_blank"><%= basePath %>devicebind.jsp</a></td>
			<td><a href="devicebind.jsp" target="_blank">devicebind.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1013</td>
			<td>设备解绑</td>
			<td><a href="<%= basePath %>devicebinddel.jsp" target="_blank"><%= basePath %>devicebinddel.jsp</a></td>
			<td><a href="devicebinddel.jsp" target="_blank">devicebinddel.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1014</td>
			<td>设备控制</td>
			<td><a href="<%= basePath %>control.jsp" target="_blank"><%= basePath %>control.jsp</a></td>
			<td><a href="control.jsp" target="_blank">control.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1014</td>
			<td>获取配置信息</td>
			<td><a href="<%= basePath %>mwConfigGet.jsp" target="_blank"><%= basePath %>mwConfigGet.jsp</a></td>
			<td><a href="mwConfigGet.jsp" target="_blank">mwConfigGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1014</td>
			<td>更新配置信息</td>
			<td><a href="<%= basePath %>mwConfigUpdate.jsp" target="_blank"><%= basePath %>mwConfigUpdate.jsp</a></td>
			<td><a href="mwConfigUpdate.jsp" target="_blank">mwConfigUpdate.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1015</td>
			<td>推送IO、平台数据</td>
			<td><a href="<%= basePath %>ioAdd.jsp" target="_blank"><%= basePath %>ioAdd.jsp</a></td>
			<td><a href="ioAdd.jsp" target="_blank">ioAdd.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1016</td>
			<td>更新设备状态</td>
			<td><a href="<%= basePath %>devicePut.jsp" target="_blank"><%= basePath %>devicePut.jsp</a></td>
			<td><a href="devicePut.jsp" target="_blank">devicePut.jsp</a></td>
			<td>guyuegan</td>
		</tr>
		<tr>
			<td>1017</td>
			<td>添加警告信息</td>
			<td><a href="<%= basePath %>warningSet.jsp" target="_blank"><%= basePath %>warningSet.jsp</a></td>
			<td><a href="warningSet.jsp" target="_blank">warningSet.jsp</a></td>
			<td>guyuegan</td>
		</tr>
		<tr>
			<td>1018</td>
			<td>测试JAVA</td>
			<td><a href="<%= basePath %>configapi/v100/testJava.action" target="_blank"><%= basePath %>configapi/v100/testJava.action</a></td>
			<td><a href="<%= basePath %>configapi/v100/testJava.action" target="_blank">test</a></td>
			<td>vickey</td>
		</tr>
	</table>
	</body>
</html>
