<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp로 spring 페이지</title>
</head>
<body>
	<form id="f">
		<input type="text" name="userid"><br> <input type="text"
			name="username"><br> <input type="button" value="전송"
			onclick="abc()">
	</form>
</body>
<script>
function abc(){
	f.submit();
}
</script>
</body>
</html>