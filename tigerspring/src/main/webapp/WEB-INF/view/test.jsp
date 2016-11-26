<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>json交互测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function requestJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/requestJson',
		contentType:'application/json;charset=utf-8',
		data:'{"name":"iphone","height":9,"age":1}',
		success:function(data){
			alert(data.name);
		},
		error:function(data){
			alert(data.name);
		}
		
	});
}
function reponseJson(){
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath}/responseJson',
		//contentType:'application/json;charset=utf-8',
		data:'name=iphone&age=9&height=100',
		success:function(data){
			alert(data);
		},
		error:function(data){
			alert(data);
		}
		
	});
}
</script>
</head>
<body>
<input type="button" onClick="requestJson()" value="请求Json">
<input type="button" onClick="reponseJson()" value="请求KV">

</body>
</html>