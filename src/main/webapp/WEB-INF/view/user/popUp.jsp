<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@include file="../include/header.jsp" %>
<body>
	<div id="popData"></div>
	<table border="1">
		<tr>
			<th>선택</th>
			<th>아이디</th>
			<th>이름</th>
		</tr>
		<c:forEach items="${userList}" var="item">
		<tr>
			<td><input type="checkbox" id="chkPop" name="chkPop" value="${item.user_id }"></td>
			<td>${item.user_id }</td>
			<td>${item.user_name }</td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<button onclick="fnSelect()">선택</button>
	<script>
		$(function(){
			
		})
		
		function fnSelect(){
			
			var checkValue = new Array();
			
			$("input:checkbox[name=chkPop]:checked").each(function(i, o){
				checkValue.push($(this).val());
			})
			
			$("#popData").html("<span>" + checkValue +"</span>")
			
		}
	</script>
</body>
</html>