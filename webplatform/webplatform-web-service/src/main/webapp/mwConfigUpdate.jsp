<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>更新配置信息</title>
<style type="text/css">
body, table {
	font-family: arial, sans-serif;
	font-size: 15px;
}

th {
	background: #336699;
	color: #FFFFFF;
	text-align: center;
}
</style>
</head>
<!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	function update(i) {
		var json = {
			"tid" : $("tr").eq(1 + i).find("input").eq(0).val(),
			"config_project" : $("tr").eq(1 + i).find("input").eq(1).val(),
			"configClass" : $("tr").eq(1 + i).find("input").eq(2).val(),
			"configName" : $("tr").eq(1 + i).find("input").eq(3).val(),
			"configValue" : $("tr").eq(1 + i).find("input").eq(4).val(),
			"configUnit" : $("tr").eq(1 + i).find("input").eq(5).val(),
			"configDname" : $("tr").eq(1 + i).find("input").eq(6).val(),
			"remark" : $("tr").eq(1 + i).find("input").eq(7).val(),
			"readOnly" : $("tr").eq(1 + i).find("input").eq(8).val(),
			"typeContent" : $("tr").eq(1 + i).find("input").eq(9).val(),
			"configType" : $("tr").eq(1 + i).find("input").eq(10).val(),
		};
		$.ajax({
			url : "mwconfig/updateMwConfig.action",
			data : json,
			type : "POST",
			success : function(result) {
				alert(result.msg);
				$("#tb tr:gt(0)").remove();
				mwConfigList();
			},
			error : function(result) {
				alert("Sorry,you are make a error!");
			}
		});
	}
	$(function() {
		mwConfigList();
	});
	function mwConfigList(){
		$.ajax({
			url : "mwconfig/mwConfigList.action",
			type : "POST",
			success : function(result) {
				for (var i = 0; i < result.length; i++) {
					$("#tb").append("<tr><td><input type='text' readonly='readonly' value='"+result[i].tid+"' /></td>"
					+ "<td><input type='text' value='"+result[i].config_project+"' /></td>"
					+ "<td><input type='text' value='"+result[i].configClass+"' /></td>"
					+ "<td><input type='text' value='"+result[i].configName+"' /></td>"
					+ "<td><input type='text' value='"+result[i].configValue+"' /></td>"
					+ "<td><input type='text' value='"+result[i].configUnit+"' /></td>"
					+ "<td><input type='text' value='"+result[i].configDname+"' /></td>"
					+ "<td><input type='text' value='"+result[i].remark+"' /></td>"
					+ "<td><input type='text' value='"+result[i].readOnly+"' /></td>"
					+ "<td><input type='text' value='"+result[i].typeContent+"' /></td>"
					+ "<td><input type='text' value='"+result[i].configType+"' /></td>"
					+ "<td><input id='login' type='button' value='修改' onclick='update("
					+ i + ")'/></td>" + "</tr>");
				}

			},
			error : function(result) {
				alert("Sorry,you are make a error!");
			}
		});
	}
	
</script>
<body>
	<form>
		<table id="tb" cellspacing="0" cellpadding="4" border="1"
			bordercolor="#224466" width="100%">
			<tr>
				<th>主键</th>
				<th>项目名称</th>
				<th>配置类别</th>
				<th>配置名称</th>
				<th>配置值</th>
				<th>单位</th>
				<th>配置项中文名称</th>
				<th>备注</th>
				<th>读写</th>
				<th>类型内容</th>
				<th>配置类型</th>
				<th>操作</th>
			</tr>
		</table>
	</form>

</body>
</html>