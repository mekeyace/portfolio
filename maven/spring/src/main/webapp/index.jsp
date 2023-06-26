<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- ${이름} : 표현문법을 사용합니다. (JSTL)--%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	스프링 메인 페이지 ${data}
	<br> ${userlist}
	<br> 전송된 아이디 :
	<%=request.getAttribute("muser")%>
</body>
<script>
var id = "${userlist}";	//jsp 표현식으로 자바스크립트 변수로 할당함
console.log(id);
</script>


</html>