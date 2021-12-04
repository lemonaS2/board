<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>
<body>
	<h1>회원가입</h1>
	<form id="joinForm" name="joinForm">
		아이디 : <input type="text" id="userId" name="userId">
		<button type="button" onclick="fnCheckId()">아이디 체크</button><span id="checkId"></span><br>
		비밀번호 : <input type="password" id="userPassword" name="userPassword">  <br>
		이름 : <input type="text" id="userName" name="userName"> <br>
		등급 : <select id="userGrade" name="userGrade">
				<option values="user">사용자</option>
				<option values="admin">관리자</option>
			 </select> <br>
		성별 : <label><input type="radio" id="userGender" name="userGender" value="남" checked>남</label>
			<label><input type="radio" id="userGender" name="userGender" value="여">여</label>
		 <br>
		취미 : <label><input type="checkbox" id="userHobby" name="userHobby" value="game">게임</label>
		<label><input type="checkbox" id="userHobby" name="userHobby" value="sleep">잠</label>
		<label><input type="checkbox" id="userHobby" name="userHobby" value="study">공부</label>
		<br>
<!-- 		일자: <input type="text" id="user_hiredate" name="user_hiredate"><br><br> -->
	
		<button type="button" onclick="fnJoin()">회원가입</button>
		<button type="reset">초기화</button>
		<a href="/user/loginForm"><button type="button">뒤로</button></a>
	</form>
	<script>
	$(function(){
	    //input을 datepicker로 선언
	    $("#user_hiredate").datepicker({
	        dateFormat: 'yy-mm-dd' //Input Display Format 변경
	        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
	        ,changeYear: true //콤보박스에서 년 선택 가능
	        ,changeMonth: true //콤보박스에서 월 선택 가능                
	        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
	        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
	        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
	        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
	        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
	        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
	        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
	        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
	        ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	        ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)                
	    });                    
	    
	    //초기값을 오늘 날짜로 설정
	    $('#user_hiredate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)       
	    
// 	    console.log($("#aUserHiredate").val());
	});
	
	
	function fnCheckId(){
		
		var userId = $("#userId").val();
		
		param = {
			userId : userId	
		}
		
		$.ajax({
			url: "/user/checkId",
			method: "GET",
			data: param,
			dataType: "html",
			success: function(data){
				if(data == '존재'){
					$("#checkId").text("존재하는 아이디 입니다.");
					$("#userId").val("");
				}else if (data == '미존재'){
					$("#checkId").text("사용가능 한 아이디 입니다.");
					$("#userId").attr("readonly", true);
				}
			},
			error: function(request, status, erroe){
				
			}
		});
		
	}
	
	function fnJoin(){
		
		
		
		var joinForm = {
			userId: $("#userId").val(),
			userPassword: $("#userPassword").val(),
			userName: $("#userName").val(),
			userGrade: $("#userGrade").val(),
			userGender: $('input[name="userGender"]:checked').val(),
			userHobby: $('input[name="userHobby"]:checked').val()
		}
		
		console.log("joinForm= " + joinForm);
		
		$.ajax({
			url: "/user/join",
			method: "POST",
			data: joinForm,
			dataTye : "",
			success: function(data){
				if(data == '성공'){
					location.href="/user/userList";
				}else{
					location.href="/user/joinForm";
				}
			},
			error: function(request, status, error){
				
			}
		});
		
	}
	
	</script>
</body>
</html>