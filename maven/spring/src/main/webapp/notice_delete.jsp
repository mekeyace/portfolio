<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<script>
var msg = "${result}";

if(msg=="ok"){
	alert("정상적으로 게시물이 삭제 되었습니다.");
	location.href="./notice_list.do";
}
else{
	alert("잘못된 접근 방식 입니다.");
	location.href="./notice_list.do";
}
</script>
