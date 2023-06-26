<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 API를 이용한 로그인</title>
</head>
<body>
<input type="button" value="카카오 로그인" onclick="join()">
<form id="kakao" method="post" action="./kakaojoin.do">
<input type="hidden" name="userid" value="">
<input type="hidden" name="username" value="">
<input type="hidden" name="useremail" value="">
</form>
</body>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
function join(){
	Kakao.Auth.login({
		success: function(response){
			Kakao.API.request({
		  		url: '/v2/user/me',
		  		success: function(response){
		  			kakao.userid.value = response["id"];
		  			kakao.username.value = response["kakao_account"]["email"];
		  			kakao.useremail.value = response["kakao_account"]["email"];
		  			
		  			kakao.submit();
		  		},
		  		fail:function(error){
	 				console.log("카카오 API 접속 오류");
		  		}
		});
	},
		fail:function(error){
 		console.log("카카오 Key 접속 오류");
		}
	});
}
Kakao.init('826617036f056391299eff60b5f4af15');
</script>
</html>