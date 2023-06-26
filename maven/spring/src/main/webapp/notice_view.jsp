<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 내용 출력</title>
</head>
<script>
var msg = "${nidx}";
if(msg=="error"){
	alert("잘못된 접근 방식 입니다.");
	location.href='./notice_list.do';
}
</script>
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	position: relative;
}

.text_div {
	width: 800px;
	height: 400px;
	border: 1px solid black;
	overflow-y: auto;
}

.blacks {
	box-shadow: rgba(0, 0, 0, 0.5) 0 0 0 999999px;
	position: absolute;
	z-index: 10;
	display: none;
}

.blacks>.passwd {
	display: block;
	width: 300px;
	height: 300px;
	background: white;
	position: fixed;
	top: 50%;
	left: 50%;
	margin: -150px 0 0 -150px;
}
</style>
<body>
	<form id="frm" method="post" action="./notice_delete.do"
		enctype="application/x-www-form-urlencoded">
		<div class="blacks" id="blacks">
			<!-- 게시물 패스워드 -->
			<span class="passwd"> <input type="hidden" name="nidx"
				value="${notice_data[0]}">
				<p>[게시물 삭제]</p> 패스워드 입력 : <input type="password" name="npass"><br>
				<input type="button" value="확인" onclick="delete_notice()"> <input
				type="button" value="닫기" onclick="close2()">
			</span>
		</div>
	</form>

	제목 :
	<span>${notice_data[1]}</span>
	<br> 글쓴이 :
	<span>${notice_data[2]}</span>
	<br> 내용 :
	<div class="text_div">${notice_data[4]}</div>
	<br>
	<input type="button" value="글목록"
		onclick="location.href='./notice_list.do';">
	<input type="button" value="글삭제" onclick="delete_view()">
</body>
<script>
function delete_view(){
	if(confirm("해당 글을 삭제 하시겠습니까?")){
		var ids = document.getElementById("blacks");
		ids.style.display="block";
	}
}

function delete_notice(){
	if(frm.npass.value==""){
		alert("패스워드를 입력해 주시길 바랍니다.");
		frm.npass.focus();
	}
	else{
		frm.submit();
	}
}

function close2(){
	var ids = document.getElementById("blacks");
	ids.style.display="none";
}
</script>
</html>