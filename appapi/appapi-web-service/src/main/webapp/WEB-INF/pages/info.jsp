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
			<td>1009</a></td>
			<td>应用状态心跳提交</a></td>
			<td><a href="<%= basePath %>appStatusSet" target="_blank"><%= basePath %>appStatusSet</a></td>
			<td><a href="appStatusSet" target="_blank">appStatusSet.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1010</a></td>
			<td>应用错误提交</a></td>
			<td><a href="<%= basePath %>appStatusPost" target="_blank"><%= basePath %>appStatusPost</a></td>
			<td><a href="appStatusPost" target="_blank">appStatusPost.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1012</a></td>
			<td>设备接入</a></td>
			<td><a href="<%= basePath %>devicebind" target="_blank"><%= basePath %>devicebind</a></td>
			<td><a href="devicebind" target="_blank">devicebind.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1013</a></td>
			<td>设备解绑</a></td>
			<td><a href="<%= basePath %>devicebinddel" target="_blank"><%= basePath %>devicebinddel</a></td>
			<td><a href="devicebinddel" target="_blank">devicebinddel.jsp</a></td>
			<td>Vickey</td>
		</tr>
		<tr>
			<td>1014</a></td>
			<td>设备控制</a></td>
			<td><a href="<%= basePath %>control" target="_blank"><%= basePath %>control</a></td>
			<td><a href="control" target="_blank">control.jsp</a></td>
			<td>Vickey</td>
		</tr>
	</table>
	</body>
</html>
