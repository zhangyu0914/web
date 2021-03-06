<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>TestJSONForSpringMVC</title>
</head>
<body>
    app_instance:
    <input id="app_instance" type="text" style="width:150px" value="test"/>
    <br /> 
    <br />
    	status: <input id="status" type="text" style="width:150px" value="1" />
    <br />
    <br />
    	sysload: <input id="sysload" type="text" style="width:150px" value="23" />
    <br />
    <br />
    	cpu: <input id="cpu" type="text" style="width:150px" value="3" />
    <br />
    <br />
    	memory: <input id="memory" type="text" style="width:150px" value="44" />
    <br />
    <br />
    	disk: <input id="disk" type="text" style="width:150px" value="25" />
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="<%=basePath%>scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    
        $("#login").click(function() {
        	var json = JSON.stringify({
        		app_instance : $("#app_instance").val(),
        		status : $("#status").val(),
        		sysload : $("#sysload").val(),
        		cpu : $("#cpu").val(),
        		memory : $("#memory").val(),
        		disk : $("#disk").val(),
        		customdata : {"err_count":"11","rfid_msgcount":"2091","tag_count":"124","ap_count":"23"}
        	});
        	console.info(json);
            $.ajax({   
                url : "<%=basePath%>platform/api/v100/appstatus/set",
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