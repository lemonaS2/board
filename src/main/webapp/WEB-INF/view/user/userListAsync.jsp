<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp" %>
<body>

	<div class="panel-body">
		<span>총 유저수: ${pagingInfo.totalCount}</span>
		<button type="button" class="btn btn-info" style="float:right" onclick="fnDelete()">선택삭제</button>
		<button type="button" class="btn btn-default" style="float:right"><a href="/user/joinForm" style="text-decoration:none">회원가입</a></button>
	</div>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th>선택</th>
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
					<td><input type="checkbox" id="chk" name="chk" value="${userList.user_id}"></td>
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
	
	<script>
		$(function(){
			
		});
		
		function fnDelete(){
			
			var deleteArr = new Array();
			
			$("input:checkbox[name=chk]:checked").each(function(){
				deleteArr.push($(this).val());
			});
			
			if(deleteArr != ''){
				console.log(deleteArr);
			}
			
			$.ajax({
				url: "/user/deleteUser",
				type: "POST",		
				data: {deleteArr : deleteArr},
				dataType: "",
				success: function(data){
					if(data.resultCode == 'SUCCESS'){
						alert("성공");
						location.reload();
					}else if(data.resultCode == 'FAIL'){
						alert("실패");
						location.reload();
					}
				},
				error: function(request, status, error){
					
				}
			});
			
		}	
	</script>
</body>
</html>