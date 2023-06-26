<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 맵</title>
</head>
<body>

<div id="map" style="width:500px;height:400px;"></div>

</body>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=826617036f056391299eff60b5f4af15"></script>
<script>
var container = document.getElementById('map'); 
var options = { 
	center: new kakao.maps.LatLng(37.518185, 126.886102), 
	level: 3 
};
var map = new kakao.maps.Map(container, options);

//마커 생성

var makerPosition = new kakao.maps.LatLng(37.518185, 126.886102);

var marker = new kakao.maps.Marker({
	position:makerPosition
});
marker.setMap(map);

//마커 이름
var divpoint = "<div class='point' onclick='gopage()'>MY HOUSE!!</div>";
var divps = new kakao.maps.LatLng(37.518185, 126.886102);

var info = new kakao.maps.InfoWindow({
	position:divps,
	content:divpoint
});

info.open(map,marker);

function gopage(){
	location.href='http://leejongh.cafe24.com/spring/notice_list.do';
}
</script>
<style>
.point{
text-align: center;
width:150px;
height:25px;
line-height: 25px;
font-size: 13px;
color:orange;
cursor: pointer;
}
</style>
</html>