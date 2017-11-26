<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>日志查询</title>
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
	/* 首次加载或刷新时，获取项目的模块 */
	$(function () { 
			getModuleList();
	});
	if(<%= request.getAttribute("currentPage") %> == null){
		alert('NodeJS 日志服务出错，请检查!');
	}
	function myForm(currentPage){
		if(currentPage != null){
			$("#currentPage").attr("value",currentPage);
		}
		//对特殊字符进行替换
		URLencode($("#content").val());
		$("form").submit();
	}
	
	function URLencode(sStr)   
    {  
      return escape(sStr).  
               replace(/\+/g, '%2B').  
               replace(/\"/g, '%22').  
               replace(/\'/g, '%27').  
               replace(/\?/g, '%3F').
               replace(/\//g, '%2F'). 
               replace(/\%/g, '%25').  
               replace(/\&/g, '%26').  
               replace(/\=/g, '%3D').  
               replace(/\#/g, '%23');  
    } 
	//动态获取当前查询项目下的模块
	function getModuleList(){
		//获取查询的项目
		$("#moduleId").find("option").remove();
		var project = $('#project').val();
			//访问根据项目获取模块的接口
	 	  $.getJSON("<%= basePath %>log/getModuleList.action", 
	 			  {
	 		  		"project": project,
	 			  }, function(json) {
	 				  //获取查询的模块
	 				 var mod = "<%= request.getAttribute("module") %>";
		        	   
		        	  for(var index in json){
		        		  //option的值（传递给后台的值）
		        		var modFormJson = json[index];
		        		  //option的显示文本
		        		 var text = json[index];
		        		  //当模块是全部，option的值其实是All
		        		  if(text == 'All'){
		        			  text = '全部';
		        		  }
		        		  if(modFormJson == mod ){
		        			  $("#moduleId").append("<option value='" + modFormJson + "' selected='selected' >" + text + "</option>");
		        		  }else{
		        			  $("#moduleId").append("<option value='" + modFormJson + "'>" + text+ "</option>");
		        		  }
		        	  }
			}); 
		 
	}
	
</script>
</head>
	<body bgcolor="#FFFFFF" topmargin="6" leftmargin="6">
		<form name="form" action="<%=basePath%>log/findLog.action" >
			<input type="hidden" name="currentPage" id="currentPage" value="<%= request.getAttribute("currentPage") %>">
			内容：<input type="text" name="content" id="content" value="<%= request.getAttribute("content") %>">
			项目：<select name="project" id="project" onchange="getModuleList()">
					<c:forEach var="item" items="${projectList}" varStatus="status">
						<c:choose>  
						   <c:when test="${item == project}">
						   		<option value="${item}" selected="selected">${item}</option>
						   </c:when> 
						   <c:otherwise>  
						     	<option value="${item}">${item}</option>
						   </c:otherwise>  
						</c:choose>
					</c:forEach>
				</select>
			模块：<select name="module" id="moduleId" style="width:150px;">
					<%-- <c:forEach var="item" items="${moduleList}" varStatus="status">
						<c:choose>  
						   <c:when test="${item == module}">
						   		<option value="${item}" selected="selected">${item}</option>
						   </c:when> 
						   <c:otherwise>  
						     	<option value="${item}">${item}</option>
						   </c:otherwise>  
						</c:choose>
					</c:forEach> --%>
				</select>
			级别：<select name="level" id="levelId">
					<option value="">全部</option>
					<c:forEach var="item" items="${levelList}" varStatus="status">
						<c:choose>  
						   <c:when test="${item == level}">
						   		<option value="${item}" selected="selected">${item}</option>
						   </c:when> 
						   <c:otherwise>  
						     	<option value="${item}">${item}</option>
						   </c:otherwise>  
						</c:choose>
					</c:forEach>
				</select>
			开始时间：<input type="text" name="startTime" id="startTime"  value="<%= request.getAttribute("startTime") %>">
			结束时间：<input type="text" name="endTime"  id="endTime" value="<%= request.getAttribute("endTime") %>">
			<input type="button" onclick="myForm(1);" value="查询">
		
		<table cellspacing="0" cellpadding="4" border="1" bordercolor="#224466"
			width="100%">
			<tr>
				<th>序号</th>
				<th>内容</th>
				<th>日志级别</th>
				<th>模块</th>
				<th>项目</th>
				<th>时间</th>
			</tr>
			<c:if test="${list != null}">
				
				<c:forEach var="item" items="${list}" varStatus="status">
		
		         <tr>
		         	<td>${(status.index + 1) + (currentPage - 1) * pageSize}</td>
		         	<td>${item.content}</td>
		         	<td>${item.log_level}</td>
		         	<td>${item.module}</td>
		         	<td>${item.project}</td>
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
		