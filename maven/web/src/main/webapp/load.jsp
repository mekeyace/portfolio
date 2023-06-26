<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<style>
body{margin: 0; padding: 0;}
iframe{
width:100%;
height:700px;
border:0;
}
</style>
<!-- 외부 사이트에 있는 팝업 데이터를 출력 -->
<iframe frameborder="0" src="http://192.168.10.144:8080/web/popup.jsp"></iframe>
<script>
/* 
window.addEventListener : ES5 지속적으로 응답을 기다리는 함수 입니다. 
"message" : postMessage 받는 역활 합니다.
false : 휘발성
*/
window.addEventListener("message",products,false);

//event : postMessage 여러개의 옵션값을 말함
function products(a){	 
	//msg : 키값 (외부 사이트 담당자와 협업이 필요함)
	window.opener.frm.product_code.value = a.data.msg;
	self.close();
}
</script>
