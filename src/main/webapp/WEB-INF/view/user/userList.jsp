<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<%@include file="../include/header.jsp" %>
<body>
	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">유저 리스트</div>
		<div align="center">
			<form id="searchForm" name="searchForm">
				취미 : <label><input type="checkbox" id="userHobby" name="userHobby" value="">전체</label>
					  <label><input type="checkbox" id="userHobby" name="userHobby" value="game">게임</label>
					  <label><input type="checkbox" id="userHobby" name="userHobby" value="sleep">잠</label>
					  <label><input type="checkbox" id="userHobby" name="userHobby" value="study">공부</label>
				코드 : <select id="codeList" name="codeList" onchange="fnCodeChange()">
						<option value="">전체</option>
						<c:forEach items="${codeList}" var="code">
							<option value="${code.code_no}">${code.code_name}</option>
						</c:forEach>
					  </select>
				상세코드 : <select id="codeDtlList" name="codeDtlList">
							<option value="">전체</option>	
						</select>	  
				검색 : <input tpye="text" id="search" name="search">
				<input type="hidden" id="pageNum" name="pageNum" />
				<button type="button" onclick="fnSearch('1')">검색</button>
			</form>
		</div>
		<div id="dataList"></div>
	</div>
	
	<script>
		$(function(){
			fnSearch(1);
		});
		
		function fnCodeChange(){
			var selectValue = $("#codeList option:selected").val();
			console.log(selectValue);
			
			$.ajax({
				contentType: "application/json",
				url: "/user/getCodeDtlList",
				type: "POST",
				data: JSON.stringify({"code_no" : selectValue}),
				dataType: "JSON",
				success: function(data){
					$("#codeDtlList option").remove();
					$("select[name='codeDtlList']").append("<option value=''>선택</option>");
					$(data).each(function(i){
						$("select[name='codeDtlList']").append("<option value='"+ data[i].code_dtl_no +"'>" + data[i].code_dtl_name + "</option>");
					});
				},error: function(request, status, error){
					alert("실패");
				}
			});
		}
		
		
		
		function fnSearch(pageNum){
			if(!pageNum){
				$("#pageNum").val(1);
			}else{
				$("#pageNum").val(pageNum);
			}
			fnSearchAjax();
			
		}
		
		function fnSearchAjax(){
			
			var pageNum = $("#pageNum").val();
			
			
			var params = {
				pageNum : pageNum
			}
			
			$.ajax({
				url: "/user/getUserList",
				type: "GET",		
				data: params,
				dataType: "html",
				success: function(data){
					$("#dataList").html(data);
				},
				error: function(request, status, error){
					
				}
			});
			
		}	
	</script>
</body>
</html>