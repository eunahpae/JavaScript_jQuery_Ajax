<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Ice Cream Order</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
<style>
body {
	font-family: 'Apple SD Gothic Neo', 'Malgun Gothic', 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f5f5f5;
}

.container {
	background: #fff;
	padding: 20px 40px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	width: 100%;
	max-width: 600px;
	box-sizing: border-box;
}

h1, h2 {
	color: #ff6b6b;
	text-align: center;
	margin-bottom: 30px;
	font-weight: 900;
}

select, input[type="number"], input[type="submit"], input[type="text"] {
	margin-bottom: 20px;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

button {
	margin-bottom: 20px;
	padding: 10px;
	background-color: #ff6b6b;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
	cursor: pointer;
}

button:hover {
	background-color: #ff8e8e;
}

#responseMessage {
	text-align: center;
	margin-top: 20px;
	color: #20b2aa;
	font-size: 20px;
	font-weight: 700;
}
</style>
</head>

<body>
	<div class="container">
		<h1>🍦 Icecream Order System 🍦</h1>
		<div id="orderSection">
			<label for="icecreamName">제 품 :</label> <select id="icecreamName">
				<option value="" disabled selected>아이스크림 선택</option>
				<option value="초코">초코 (1000원/개당)</option>
				<option value="바나나">바나나 (1000원/개당)</option>
				<option value="망고">망고 (1000원/개당)</option>
			</select> <br> <label for="cnt">수 량 :</label> <input type="number"
				id="cnt" placeholder="수량 입력">
			<button id="submitOrder">아이스크림 주문</button>
			<br>
		</div>
		<div id="responseMessage"></div>
		<div id="totalPrice"></div>
		<br>

		<script>
		$(document).ready(function() {
		    $('#paymentSection').hide();	
		    $('#submitOrder').on('click', function() {	
		        const icecreamName = $('#icecreamName').val();
		        const cnt = $('#cnt').val();
	
		        if (!icecreamName || !cnt) {
		            alert('모든 필드를 입력하세요.');
		            return;
		        }
	
		        const data = {
		            icecreamName: icecreamName,
		            cnt: cnt
		        };
	
		        $.ajax({
		            url: '/practice/icecreamOrder',
		            type: 'post',
		            data: data,
		            success: function(data) {
		                const totalPrice = data.totalPrice;		                
		                const name = data.name;
		                const cnt = data.cnt;
		                const message = name + ` 아이스크림 ` + cnt + `개 주문으로 총 ` + totalPrice + `원 입니다.`;
		                $('#responseMessage').text(message);
		                $('#totalPrice').text(totalPrice).hide();
		                $('#paymentSection').show();
		            },
		            error: function(error) {
		                $('#responseMessage').text('잘못된 수량입니다.');
		            }
		        });
		    });
		});
		</script>

		<div id="paymentSection">
			<hr>
			<h2>Payment</h2>
			<label for="cardName">Card : </label> <select id="cardName"
				name="cardName">
				<option value="" disabled selected>카드 선택</option>
				<option value="lotte">롯데 (10% 할인)</option>
				<option value="hyundai">현대 (20% 할인)</option>
				<option value="samsung">삼성 (30% 할인)</option>
				<option value="kb">국민 (40% 할인)</option>
			</select> <br> <label for="cardNumber">Card No : </label> <input
				type="text" id="cardNumber" placeholder="Enter card number">
			<button id="payButton">결제하기</button>
			<br>
		</div>

		<script>
			$('#payButton').on('click', function() {
								
				const totalPrice = $('#totalPrice').text();
				const cardName = $('#cardName').val();
		        const cardNumber = $('#cardNumber').val();
		        
		        if (!cardName || !cardNumber) {
		            alert('모든 필드를 입력하세요.');
		            return;
		        }
		        
		        const data = {
		        		totalPrice: totalPrice,
		        		cardName: cardName,
		        		cardNumber : cardNumber
			        };
				
				$.ajax({
					url : '/practice/orderComplete',
				    type: 'post',
				    data: data,
				    success: function(data) {  
				    	$('#orderSection').hide();
						$('#paymentSection').hide();						
				    					
				        const cardName = data.cardName;
				        const cardNumber = data.cardNumber;
				        const totalPrice = data.totalPrice;
				        const message = cardName + ` 카드(` + cardNumber + `) 로 총 ` + totalPrice + `원 이 결제되었습니다.`;
						$('#responseMessage').text(message);		        
				    },
				    error: function(error) {
				    	console.log("error");
				    }
				});
			});
		</script>
	</div>
</body>
</html>
