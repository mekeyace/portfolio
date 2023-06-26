<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팝업 상품 리스트</title>
</head>
<body>
<p>[상품코드 리스트]</p>
<ul>
<li onclick="code('123456')">123456</li>
<li onclick="code('045612')">045612</li>
<li onclick="code('565122')">565122</li>
<li onclick="code('998851')">998851</li>
</ul>
</body>
<script>
	function code(ncode){
		//[name값으로 전달]
		//window.opener.frm.product_code.value = ncode;\
		//[id값으로 전달]
		//window.opener.document.getElementById("product_code").value=ncode;
		
		//postMessage : 서로 다른 도메인에 한하여 배열 형태로 데이터를 보내는 함수임.
		//postMessage(데이터배열키:데이터값,"*") : * 모든 사이트 모두 오픈
		//postMessage(데이터배열키:데이터값,"http://naver.com") : * 모든 사이트 모두 오픈
		parent.window.postMessage({msg:ncode},"*");
		
	}
</script>
</html>