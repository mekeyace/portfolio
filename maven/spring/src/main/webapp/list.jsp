<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL 사용법(표현식) 
EL : 표현식 코드를 반복문, 실제 java 변수를 표현식 받아서 처리를 쉽게 할 수 있도록
하는 JSTL 태그 및 라이브러리를 사용합니다.
-->
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 사용법</title>
<link rel="stylesheet" type="text/css" href="./css/notice.css?v=1">
</head>
<body>
	<ol class="olcss">
		<li>번호</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>등록일</li>
	</ol>
	<!-- JSTL 반복문 -->
	<app:forEach var="no" begin="1" end="5">
${no}
</app:forEach>
	<!-- JSTL 변수값을 받아서 표현식으로 출력 -->
	<% String user = "이순신"; %>
	<app:set var="username" value="<%=user%>"></app:set>
	${username}

	<!-- java -> jstl로 반복문 적용 -->
	<%
	String data[]={"a1","a2","a3","a4"};
	int ea = data.length;
%>
	<app:set var="ea" value="<%=ea%>"></app:set>
	<%
int w=0;
while(w < ea){
%>
	<app:set var="lists" value="<%=data[w]%>"></app:set>
	${lists}
	<%
w++;
}
%>
</body>
</html>