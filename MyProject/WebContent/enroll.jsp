<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊</title>
<link rel="stylesheet" type="text/css" href="css/mystyle01.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

</head>
<body class="text-center">
	<form class="form01" action="enroll" method="post">
		<img class="mb-4" src="images/ro04.jpg"
			style="display: block; margin: auto;"> <input type="text"
			placeholder="輸入註冊帳號" class="control" id="enuserId" name="userId"
			autofocus> <br> <input type="password"
			placeholder="輸入註冊密碼" class="control" id="enuserPs" name="userPs">
		<br> <input type="email" placeholder="輸入註冊email" class="control"
			name="useremail"> <br> <input type="submit"
			class="btn01" id="btn" value="註冊" style="cursor: pointer;"> <br>
		<br>

	</form>
	<nav>

		<img class="images01" src="images/ro05.jpg"> <a href="login.jsp"
			class="a01" style="color: black"><b>登入</b></a> <a href="change.jsp"
			class="a02" style="color: black"><b>修改密碼</b></a> <a href="check.jsp"
			class="a03" style="color: black"><b>忘記密碼</b></a>

	</nav>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		function myFunction() {
			var userId = $('input[name=userId]').val().toString().trim();
			var userPs = $('input[name=userPs]').val().toString().trim();
			var userEmail = $('input[name=useremail]').val().toString().trim();
			if (userId.length == 0) {
				alert("你好，帳號不可為空");
			} else if (userPs.length == 0) {
				alert("你好，密碼不可為空");
			} else if (userEmail.length == 0) {
				alert("你好，信箱不可為空");
			}
		}

		$("#btn").click(function(e) {

			myFunction();

		});

		var reId = "";

		$("#enuserId").keydown(function() {
			$.ajax({
				type : "POST",
				url : "reuser",
				data : {
					"userId" : $("#enuserId").val().trim().toString()
				},
				success : function(result) {
					if (result == 1) {

						alert("此帳號已存在");
						reId = result;
					} else {

						$("enuserId").html("Ok")
					}
				}
			});
		});
	</script>
</body>
</html>