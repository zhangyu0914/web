<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>TestJSONForSpringMVC</title>
</head>
<body>
  	  设备sn(sn):
    <input id="sn" type="text" style="width:150px" value="8001011500003527"/>
    <br /> 
    <br />
   	设备状态(eqStatus):
    <input id="eqStatus" type="text" style="width:150px" value=0 />
    <br />
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $("#login").click(function() {
        	var json = JSON.stringify({
        		sn : $("#sn").val(),
        		eqStatus : $("#eqStatus").val()});
        	//json = "["+json+"]";
        	console.log(json);
            $.ajax({
                url : "configapi/v100/device/put.action",
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