<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<form name="loginForm" id="loginForm">
		아이디 : <input type="text" id="userId" name="userId"><br>
		비밀번호 : <input type="password" id="userPassword" name="userPassword"><br>
		<span id="loginMsg"></span><br>
		<button type="button" onclick="fnLogin()">로그인</button>
		<button type="reset">초기화</button>
		<a href="/user/joinForm"><button type="button">회원가입</button></a>
	</form>
	<script>
		$(function(){
			$("#startMsg").text("준비완료");
		});
		
		
		function fnLogin(){
			
			var userId = $("#userId").val();
			var userPassword = $("#userPassword").val();
			
			var param = {
				userId : userId,
				userPassword : userPassword
			}
			
			$.ajax({
				url: "/user/login",
				method: "GET",
				contentType: "application/json",
				data: param,
				dataType: 'html',
				success: function(data){
					alert(data);
					if(data == '로그인 성공'){
						location.href="/user/userList";
					}else if(data == '실패'){
						$("#loginMsg").text('존재하지 않는 계정이거나 아이디나 비밀번호가 일치하지 않습니다.');
						$("#userId").val("");
						$("#userPassword").val("");
					}
				},
				error: function(error, status, request){
// 					alert(data);
				}
			});
			
		}
	</script>
</body>
</html>