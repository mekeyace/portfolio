<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>api.do RestAPI 가져오기</title>
</head>
<body>

</body>

<script>
	var data;
	var html = new XMLHttpRequest();
	html.onreadystatechange = function(){
		if(html.readyState === XMLHttpRequest.DONE){
			if(html.status===200){
				data = JSON.parse(this.response);
				console.log(data);
			}
		}
	}
	html.open("GET","http://localhost:8080/web/api.do?keys=notice_oksign",true);
	html.send();
</script>

</html>