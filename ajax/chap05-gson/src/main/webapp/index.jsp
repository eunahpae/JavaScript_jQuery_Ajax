<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GSON</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
</head>
<body>

	<h1>AJAX 요청 후 stream을 이용한 JSON 응답 (gson library)</h1>
	
	<h3>1. 데이터 전송 없이 json 반환</h3>
	<button id="send-get-1">SEND</button>
	<script>
		$("#send-get-1").click(function(){
			$.ajax({
				url: "/chap05/gson/test1",
				type: "get",
				success: function(data) {
					console.table(data);
				},
				error: function(error) {
					console.log(error);
				}
			});
		});
	</script>
	
	<hr>
	
	memberCode: <input type="text" id="memberCode"><br>
	memberName: <input type="text" id="name"><br>
	gender: <input type="text" id="gender"><br>
	age: <input type="number" id="age"><br>
	enrollDate: <input type="date" id="enrollDate"><br>
	
	<h3>2. 기본 자료형 데이터 전송 후 json 반환</h3>
	<button id="send-get-2">기본 자료형 데이터 SEND</button>
	<script>
		$("#send-get-2").click(function() {
			const memberCode = $("#memberCode").val();
			const name = document.getElementById("name").value;
			const gender = $("#gender").val();
			const age = document.getElementById("age").value;
			const enrollDate = $("#enrollDate").val();			
			
			$.ajax({
				url: "/chap05/gson/test2",
				type: "get",
				data: {
					memberCode: memberCode,
					name: name,
					gender: gender,
					age: age,
					enrollDate: enrollDate
				},
				success: function(data){
					console.table(data);
				},
				error: function(error){
					console.log(error);
				}
			});
		});
	</script>
	
	<h3>3. Object 타입의 데이터 전송 후 json 반환</h3>
	<button id="send-get-3">객체 타입의 데이터 SEND</button>
	<script>
		$("#send-get-3").click(function(){
			const memberCode = $("#memberCode").val();
			const name = document.getElementById("name").value;
			const gender = $("#gender").val();
			const age = document.getElementById("age").value;
			const enrollDate = $("#enrollDate").val();		
			
			const member = {
					memberCode: memberCode,
					name: name,
					gender: gender,
					age: age,
					enrollDate: enrollDate
			};
			
			$.ajax({
				url: "/chap05/gson/test2",
				type: "get",
				data: member,
				success: function(data) {
					console.table(data);
				},
				error: function(error) {
					console.log(error);
				}
			});
			
			
		});
	</script>
	
	<h3>4. json 문자열 전송 후 json 반환</h3>
	<button id="send-get-4">JSON 문자열 SEND</button>
	<script>
		$("#send-get-4").click(function(){
			const memberCode = $("#memberCode").val();
			const name = document.getElementById("name").value;
			const gender = $("#gender").val();
			const age = document.getElementById("age").value;
			const enrollDate = $("#enrollDate").val();		
			
			const member = {
					memberCode: memberCode,
					name: name,
					gender: gender,
					age: age,
					enrollDate: enrollDate
			};
			
			const jsonString = JSON.stringify(member);
			
			$.ajax({
				url: "/chap05/gson/test3",
				type: "get",
				data: { jsonString },
				success: function(data) {
					console.table(data);
				},
				error: function(error) {
					console.log(error);
				}
			});
			
			
		});
	</script>
	
	<hr>
	
	<h3>5. GsonBuilder 테스트</h3>
	<button id="send-get-5">SEND</button>
	<script>
		$("#send-get-5").click(function(){
			$.ajax({
				url: "/chap05/gson/test4",
				type: "get",
				success: function(data) {
					console.table(data);
				},
				error: function(error) {
					console.log(error);
				}
			});
		});
	</script>
</body>
</html>