<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>设备接入</title>
</head>
<body>
    app_instance:
    <input id="app_instance" type="text" style="width:150px" value="test1"/>
    <br /> 
    sn:<input id="sn" type="text" style="width:150px" value="8001011400001600"/>
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
      
		$("#login").click(function() {
			$.ajax({
				url : "platform/api/v100/devicebind/set.action?app_instance=" + $("#app_instance").val() + "&sn=" + $("#sn").val(),
				contentType : "application/text;charset=UTF-8",
				success : function(result) {
					alert(JSON.stringify(result));
				},
				error : function(result) {
					alert("Sorry,you are make a error!");
				}
			});
		});
	</script>
</body>
</html>