<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>TestJSONForSpringMVC</title>
</head>
<body>
    id:
    <input id="id" type="text" style="width:150px" value="LIC_1001"/>
    <br /> 
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $("#login").click(function() {
        	var json = JSON.stringify({
                id : $("#id").val()
        	});
        	if($("#id").val() != null){
	        	json = "{id:['"+$("#id").val()+"']}";
        	}else{
        		json = "{id}";
        	}
        	console.info(json);
            $.ajax({   
                url : "configapi/v100/license/get.action",
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