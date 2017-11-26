<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>测试导航</title>
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
			<th>类型</th>
			<th>地址</th>
		</tr>
		<tr>
			<td>1</td>
			<td>测试调试</a></td>
			<td><a href="test.jsp" target="_blank">test.jsp</a></td>
		</tr>
		<tr>
			<td>2</td>
			<td>开发调试</a></td>
			<td><a href="info.jsp" target="_blank">info.jsp</a></td>
		</tr>
		<tr>
			<td>3</td>
			<td>日志查询</a></td>
			<td><a href="log/findLog.action" target="_blank">log</a></td>
		</tr>
		<tr>
			<td>4</td>
			<td>系统参数</a></td>
			<td><a href="configuration/sysParam.action" target="_blank">系统参数</a></td>
		</tr>
		<tr>
			<td>5</td>
			<td>数据日志查询</a></td>
			<td><a href="log/findDataLog.action" target="_blank">dataLog</a></td>
		</tr>
		<tr>
			<td>6</td>
			<td>原始数据日志查询</a></td>
			<td><a href="log/findRawDataLog.action" target="_blank">rawDataLog</a></td>
		</tr>
	</table>
	</body>
</html>
