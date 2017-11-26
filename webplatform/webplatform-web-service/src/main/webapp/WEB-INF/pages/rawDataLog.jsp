<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>原始数据日志查询</title>
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
	
	if(<%= request.getAttribute("currentPage") %> == null){
		alert('数据日志服务出错，请检查!');
	}
	function myForm(currentPage){
		if(currentPage != null){
			$("#currentPage").attr("value",currentPage);
		}
		//对特殊字符进行替换
		URLencode($("#ip").val());
		$("form").submit();
	}
	function URLencode(sStr)   
    {  
      return escape(sStr).  
              replace(/\+/g, '%2B').  
              replace(/\"/g, '%22').  
              replace(/\'/g, '%27').  
              replace(/\//g, '%2F');  
    } 
</script>
</head>
	<body bgcolor="#FFFFFF" topmargin="6" leftmargin="6">
		<form name="form" action="<%=basePath%>log/findRawDataLog.action">
			<input type="hidden" name="currentPage" id="currentPage" value="<%= request.getAttribute("currentPage") %>">
		
			ip地址：<input type="text" name="ip" id="ip"  value="<%= request.getAttribute("ip") %>">
			开始时间：<input type="text" name="startTime" id="startTime"  value="<%= request.getAttribute("startTime") %>">
			结束时间：<input type="text" name="endTime"  id="endTime" value="<%= request.getAttribute("endTime") %>">
			<input type="button" onclick="myForm(1);" value="查询">
		
		<table cellspacing="0" cellpadding="4" border="1" bordercolor="#224466"
			width="100%">
			<tr>
				<th>序号</th>
				<th>内容</th>
				<th>ip地址</th>
				<th>返回结果</th>
				<th>时间</th>
			</tr>
			<c:if test="${list != null}">
				<c:forEach var="item" items="${list}" varStatus="status">
					<tr>
						<td>${(status.index + 1) + (currentPage - 1) * pageSize}</td>
						<td>${item.content}</td>
						<td>${item.ip}</td>
						<td>${item.result}</td>
						<td>${item.time}</td>
					</tr>
				</c:forEach>
			</c:if>		
		</table><br/>
		<c:if test="${list != null}">
		
			总共<%= request.getAttribute("totalPage") %>页  
			每页<input name="pageSize" value="<%= request.getAttribute("pageSize") %>" size="1" style="text-align: center"/>条  
			共<%= request.getAttribute("totalCount") %>条记录  
			当前第<%= request.getAttribute("currentPage") %>页
			
			<c:if test="${totalPage != 1}">
				<a onclick="myForm(1);" href="javascript:return false;">首页</a>
				<c:if test="${currentPage > 1}">
					<a onclick="myForm(<%= Integer.valueOf(request.getAttribute("currentPage").toString()) - 1 %>);" href="javascript:return false;">上一页</a>
				</c:if>
				<c:if test="${currentPage < totalPage}">
					<a onclick="myForm(<%= Integer.valueOf(request.getAttribute("currentPage").toString()) + 1 %>);"  href="javascript:return false;">下一页</a>
					<a onclick="myForm(<%= request.getAttribute("totalPage") %>);" href="javascript:return false;">尾页</a>
				</c:if>
			</c:if>
		</c:if>
		</form>
	</body>
	<script type="text/javascript">
		function getSysDate(){
			var today=new Date();
			var year = today.getYear() + 1900;
			var month = today.getMonth() + 1;
			var day = today.getDate();
			var h=today.getHours();
			var m=today.getMinutes();
		    var s = today.getSeconds();
		
			return year + '-' + month + '-' + day + ' 00:00:00';
		}
	</script>
</html>
		