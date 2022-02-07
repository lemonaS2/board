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
	<h1>업로드</h1>
	<form name="uploadForm" id="uploadForm" enctype="multipart/form-data">
		파일 : <input type="file" id="files" name="files">
		<br>
		<button type="button" onclick="fnUpload()">업로드</button>
	</form>
	<script>
		
		function fnUpload(){
			
			var form = $("#uploadForm")[0];
			var uploadForm = new FormData(form);
			
			$.ajax({
				url: "/upload/uploadFile",
				method: "POST",
				processData: false,
				contentType: false,
				data: uploadForm,
				success: function(data){
					
				},
				error: function(error, status, request){
					
				}
			});
			
		}
	</script>
</body>
</html>