<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL 표현식으로 리스트 출력 -->
<%@ taglib prefix="app" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 배열 갯수를 확인하는 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<% int z = (int)(request.getAttribute("totals")); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<link rel="stylesheet" type="text/css" href="./css/notice.css?v=3">
<style>
.btn { width:100px; height:30px; background-color: black;
color:white;
}

</style>
</head>
<body>
	<ol class="olcss">
		<li>번호</li>
		<li>제목</li>
		<li>글쓴이</li>
		<li>등록일</li>
	</ol>
	<!-- 찾을 내용이 없을 경우 또는 게시글이 하나도 없을 경우 -->
	<app:set var="ea" value="${fn:length(noticelist)}"></app:set>
	<app:if test="${ea==0}">
		<!-- jstl 조건문 -->
		<ol class="olcss css2">
			<li style="width: 100%;">찾고자 하는 게시물 또는 등록된 게시물이 없습니다.</li>
		</ol>
	</app:if>
	<%
	int now_page = (int)(request.getAttribute("now_page"));
%>
	<!-- 반복문 적용 JSTL : forEach -->
	<!-- 게시물에 표시되는 넘버 번호 공식 : 전체데이터 갯수 - (현재페이지번호 - 1) * 한페이지당 출력 수 -->
	<app:set var="w" value="<%=z-(now_page-1)*2%>"></app:set>
	<app:forEach var="item" items="${noticelist}">
		<ol class="olcss css2">
			<li>${w}</li>
			<!-- 게시판 제목 -->
			<li style="text-align: left; text-indent: 10px;"
				onclick="notice_view('${item[0]}')">${item[1]}</li>
			<li>${item[2]}</li>
			<!-- fn을 이용해서 글자를 자르는 파트 -->
			<app:set var="date" value="${fn:substring(item[3],0,10)}"></app:set>
			<li>${date}</li>
		</ol>
		<app:set var="w" value="${w-1}"></app:set>
	</app:forEach>
	<!-- 반복문 적용 -->

	<!-- 페이징 번호 -->
	<div class="pageing">
		<ul>
			<%
//페이징 계산 수식구
int pg = (int)Math.ceil(z / 2f);

int w = 1;
while(w <= pg){
%>
			<li onclick="pageing(<%=w%>)"><%=w%></li>
			<%
w++;
}
%>
		</ul>
	</div>
	<!-- 페이징 번호 -->

	<!-- 게시물 검색 -->
	<form id="frm" onsubmit="return sh()" method="get"
		action="./notice_list.do">
		<div class="div_search">
			제목 검색 : <input type="text" name="search"> <input
				type="submit" value="게시물 검색"> <input type="button"
				value="전체목록" onclick="gopage()">
		</div>
	</form>
	<div class="div_search">
		<input type="button" value="글쓰기" class="btn"
			onclick="location.href='./notice_write.html';">
	</div>
</body>
<script>

	//게시판 제목 클릭시 해당 페이지로 자동증가값이 전달 되도록 합니다.
	function notice_view(idx){
		location.href='./notice_view.do?nidx='+idx;
	}


	function pageing(no){
		location.href='./notice_list.do?page='+no;		
	}

	function gopage(){
		location.href='./notice_list.do';
	}
	function sh(){
		if(frm.search.value==""){
			alert("검색한 단어를 입력해 주세요");
			return false;
		}	
		else{
			return;
		}
	}
</script>
</html>