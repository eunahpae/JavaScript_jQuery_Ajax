<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax file upload</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
</head>
<body>

	<h1>Ajax file upload (formdata 이용)</h1>


	<input type="file" id="file"><br>
	<input type="text" id="description"><br>
	<input type="button" value="파일 전송" id="send-file">
	
	<script>
		$("#send-file").click(function(){
			
			const formData = new FormData();
			formData.append("file", $("#file")[0].files[0]);
			formData.append("description",$("#description").val());
			
			$.ajax({
				url : "/chap06/commons",
				type : "post",
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {
					alert(data);
				},
				error : function(error) {
					console.log(error);
				}	
			})
		})
	</script>

</body>
</html>