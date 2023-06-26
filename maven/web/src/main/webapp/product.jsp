<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품추가 페이지 (CORS 팝업창 외부 데이터 로드)</title>
</head>
<body>
<form id="frm">
상품코드 : <input type="text" name="product_code" id="product_code" readonly="readonly">
<input type="button" value="상품추가" onclick="pd_list()">
</form>
<script>
//자신의 팝업창으로 먼저 호출
function pd_list(){
	window.open("./load.jsp","","width=700 height=700");
}
</script>
</body>
</html>