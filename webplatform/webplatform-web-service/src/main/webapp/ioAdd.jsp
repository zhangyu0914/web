<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>TestJSONForSpringMVC</title>
</head>
<body>
    dataCode:
    <input id="dataCode" type="text" style="width:150px" value="APP"/>
    <br /> 
    <input id="dataValue" type="text" style="width:150px" value="test1"/>
    <br /> 
    <br />
    	输入数据(inputData): <input id="inputData" type="text" style="width:150px" value=74 />
    <br />
    	输出数据(outputData): <input id="outputData" type="text" style="width:150px" value=956 />
    <br />
    	数据时间(dataTime): <input id="dataTime" type="text" style="width:150px"  />
    <br />
    <br />
    <input id="login" type="button" value="提交" />
    <!-- 注意了,此处jquery的路径记得替换成你自己jquery所在的路径 -->
    <script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $("#login").click(function() {
        	var json = JSON.stringify({
                dataCode : $("#dataCode").val(),
                dataValue : $("#dataValue").val(),
                dataTime : $("#dataTime").val(),
                outputData : $("#outputData").val(),
                inputData : $("#inputData").val()});
        	json = "["+json+"]";
            $.ajax({
                url : "configapi/v100/io/add.action",
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
        $(function () {
        	$("#dataTime").val(getSysDate());
    	});
        function getSysDate(){
			var today=new Date();
			var year = today.getYear() + 1900;
			var month = today.getMonth() + 1;
			var day = today.getDate();
			var h=today.getHours();
			var m=today.getMinutes();
		    var s = today.getSeconds();
		    var hh=h.toString().length==1?'0'+h:h;
		    var mm=m.toString().length==1?'0'+m:m;
		    var ss=s.toString().length==1?'0'+s:s;
			return year + '-' + month + '-' + day + ' ' + hh + ':' + mm + ':' + ss;
		}
    </script>
</body>
</html>