<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>系统参数</title>
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
<script type="text/javascript" src="<%= basePath %>scripts/jquery-1.9.1.min.js"></script>

<script type="text/javascript">
</script>
</head>
	<body bgcolor="#FFFFFF" topmargin="6" leftmargin="6">
		<table cellspacing="0" cellpadding="4" border="1" bordercolor="#224466"
			width="100%">
			<tr>
				<th>序号</th>
				<th>KEY</th>
				<th>VALUE</th>
			</tr>
			<c:if test="${map != null }">
				<c:forEach var="item" items="${map.keySet()}" varStatus="status">
		         <tr>
		         	<td>${(status.index + 1)}</td>
		         	<td>${item}</td>
		         	<td>${map.get(item)}</td>
		         </tr>
		        </c:forEach>
		    </c:if>
		</table>
	</body>
</html>
		