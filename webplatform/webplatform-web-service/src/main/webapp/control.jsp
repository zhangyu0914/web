<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>TestJSONForSpringMVC</title>
</head>
<body>
    uuid:
    <input id="uuid" type="text" style="width:150px" value="5d3a9eef-1373-411e-8c78-a4d59c3cb0e6"/>
    <br /> 
    <br />
    	time: <input id="time" type="text" style="width:150px" value="2017-01-01 00:00:00" />
    <br />
    <br />
    	sn: <input id="sn" type="text" style="width:150px" value="8001040500000011" />
    <br />
    <br />
    	ep: <input id="ep" type="text" style="width:150px" value="0" />
    <br />
    <br />
    	cmdid: <input id="cmdid" type="text" style="width:150px" value="20100601" />
    <br />
    <br />
    	cmddata: <input id="cmddata" type="text" style="width:150px" value="" />//1:关闭  0：启动
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
    	
        $("#login").click(function() {
        	var info = JSON.stringify({
        		sn : $("#sn").val(),
        		ep : $("#ep").val()
        	});
        	
        	var json = JSON.stringify({
                uuid : $("#uuid").val(),
                time : $("#time").val(),
                info : {"sn":$("#sn").val(),"ep":parseInt($("#ep").val()),"cmdid":$("#cmdid").val(),"cmddata":parseInt($("#cmddata").val())}
        	});
        	console.info(json);
            $.ajax({   
                url : "platform/api/v100/device/control.action",
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