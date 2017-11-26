<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>TestJSONForSpringMVC</title>
</head>
<body>
  	  属性值ID(fkDeviceModelAttTid):
    <input id="fkDeviceModelAttTid" type="text" style="width:150px" value="1d92b208-e99f-404c-ac18-a7a7f95d3714"/>
    <br /> 
    <br />
    属性值(attValue):
    <input id="attValue" type="text" style="width:150px" value=2 />
    <br />
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $("#login").click(function() {
        	var json = JSON.stringify({
                fkDeviceModelAttTid : $("#fkDeviceModelAttTid").val(),
                attValue : $("#attValue").val()});
        	json = "["+json+"]";
            $.ajax({
                url : "device/changeSetting.action",
                type : "POST",
                dataType : "json",
                contentType : "application/json;charset=UTF-8",
                data : json,
                success : function(result) {
                    alert(JSON.stringify(result));
                },
                error:function(result){
                    alert("Sorry,you are make a error!");
                }
            });
        });
    </script>
</body>
</html>