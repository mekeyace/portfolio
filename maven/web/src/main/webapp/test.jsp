<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="aa" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출력 리스트</title>
</head>
<body>
<ul>
    <aa:forEach var="num" items="${noticedata}">
        <li>자동증가값 : ${num["nidx"]}</li>
        <li>자동증가값 : ${num["nsubject"]}</li>
        <li>자동증가값 : ${num["nwriter"]}</li>
        <li>자동증가값 : ${num["ndate"]}</li>
        <li>--------------------------</li>
    </aa:forEach>
</ul>
</body>
</html>