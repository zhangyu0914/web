<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件上传</title>
  </head>
  
  <body>
    <form action="<%= basePath %>/import/importModel.action" enctype="multipart/form-data" method="post">
        导入型号：<input type="file" name=uploadFile><br/>
        <br/><input type="submit" value="提交">
    </form>
  </body>
</html>