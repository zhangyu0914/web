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
			<td>无</a></td>
			<td>调用REDIS</a></td>
			<td><a href="<%= basePath %>configapi/v100/initData.action" target="_blank"><%= basePath %>configapi/v100/initData.action</a></td>
			<td><a href="initData.jsp" target="_blank">initData.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1001</a></td>
			<td>获取型号信息接口</a></td>
			<td><a href="<%= basePath %>configapi/v100/model/get.action" target="_blank"><%= basePath %>configapi/v100/model/get.action</a></td>
			<td><a href="modelGet.jsp" target="_blank">modelGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1002</a></td>
			<td>获取应用信息接口</a></td>
			<td><a href="<%= basePath %>configapi/v100/app_instance/get.action" target="_blank"><%= basePath %>configapi/v100/app_instance/get.action</a></td>
			<td><a href="appInstanceGet.jsp" target="_blank">appInstanceGet.jsp</a></td>
			<td>zhangyu</td>
		</tr>
		<tr>
			<td>1003</a></td>
			<td>获取许可证信息接口</a></td>
			<td><a href="<%= basePath %>configapi/v100/license/get.action" target="_blank"><%= basePath %>configapi/v100/license/get.action</a></td>
			<td><a href="licenseGet.jsp" target="_blank">licenseGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1004</a></td>
			<td>获取属性信息接口</a></td>
			<td><a href="<%= basePath %>configapi/v100/profile/get.action" target="_blank"><%= basePath %>configapi/v100/profile/get.action</a></td>
			<td><a href="profileGet.jsp" target="_blank">profileGet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1005</a></td>
			<td>获取应用-设备绑定信息接口</a></td>
			<td><a href="<%= basePath %>configapi/v100/app_instance_dev/get.action" target="_blank"><%= basePath %>configapi/v100/app_instance_dev/get.action</a></td>
			<td><a href="appInstanceDev.jsp" target="_blank">appInstanceDev.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1016</a></td>
			<td>获取应用密钥</a></td>
			<td><a href="<%= basePath %>appSecret.jsp" target="_blank"><%= basePath %>appSecret.jsp</a></td>
			<td><a href="appSecret.jsp" target="_blank">appSecret.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1007</a></td>
			<td>推送IO、平台数据</a></td>
			<td><a href="<%= basePath %>ioAdd.jsp" target="_blank"><%= basePath %>ioAdd.jsp</a></td>
			<td><a href="ioAdd.jsp" target="_blank">ioAdd.jsp</a></td>
			<td>Vickey</td>
		</tr>
	</table>
	</body>
</html>
