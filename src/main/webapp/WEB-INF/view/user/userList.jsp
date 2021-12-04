<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html> 
<html>
<!-- <head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.js"></script>
합쳐지고 최소화된 최신 CSS
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
부가적인 테마
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
합쳐지고 최소화된 최신 자바스크립트
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head> -->
<%-- <jsp:include page="../include/header.jsp" /> --%>
<%@include file="../include/header.jsp" %>
<body>
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">유저 리스트</div>
		<div class="panel-body">
			<span>총 유저수: ${totalCount } 건</span>
			<button type="button" class="btn btn-default" style="float:right"><a href="/user/joinForm" style="text-decoration:none">회원가입</a></button>
		</div>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>No</th>
					<th>아이디</th>
					<th>이름</th>
					<th>등급</th>
					<th>성별</th>
					<th>취미</th>
					<th>가입일</th>
				<tr>
			</thead>
			<tbody>
				<c:forEach var="userList" items="${userList}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${userList.user_id}</td>
						<td>${userList.user_name}</td>
						<td>${userList.user_grade}</td>
						<td>${userList.user_gender}</td>
						<td>${userList.user_hobby}</td>
						<td>${userList.user_hiredate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div align="center">
		<nav>
		  <ul class="pagination">
		    <li>
		      <a href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <c:forEach var="item" items="${pagingInfo.list }">
		   		<li><a href="#">${item }</a></li>
			</c:forEach>
		    <li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>		
</body>
</html>