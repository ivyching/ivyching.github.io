<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入</title>
<link rel="stylesheet" type="text/css" href="css/mystyle01.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>
<script type="text/javascript">
	function loading() {
		var xRemember = document.getElementById('check_cookie');
		var xUser = document.getElementById('user_Id');
		var xPassword = document.getElementById('user_Ps');
		if (getCookie('user') != null && getCookie('password') != null
				&& getCookie('user') != "" && getCookie('password') != "") {

			xUser.value = getCookie('user');
			xPassword.value = getCookie('password');
			xRemember.checked = true;

		}
	}

	//設置cookie
	function setCookie(name, value, day) {

		var date = new Date();
		date.setDate(date.getDate() + day);
		document.cookie = name + "=" + value + ';expires=' + date;

	}

	//刪除cookie

	function deleteCookie(name) {

		setCookie(name, null, -1);
	}

	//取得cookie

	function getCookie(name) {
		var reg = RegExp(name + '=([^;]+)');
		var arr = document.cookie.match(reg);
		if (arr) {
			console.log(arr);
			return arr[1];
		} else {
			return '';
		}
	}
</script>
<body>
	<form class="form01" action="login" method="post" onsubmit="return myFunction()">
		<img class="images00" src="images/RO01.jpg"
			style="display: block; margin: auto;"> <input type="text"
			placeholder="輸入帳號" class="control02" id="user_Id" name="userId"
			autofocus> <br> <input type="password"
			placeholder="輸入密碼" class="control02" id="user_Ps" name="userPs">
		<br> <input type="submit" class="btn01" id="btn" value="登入"
			style="cursor: pointer;"> <br> <br> <input
			type="checkbox" id="check_cookie" name="check_cookie"
			style="margin-left: 0.9cm"><b>記住我</b>
	</form>
	<nav>
		<a href="enroll.jsp" class="a01" style="color: black"><b>註冊</b></a> <a
			href="change.jsp" class="a02" style="color: black"><b>修改密碼</b></a> <a
			href="check.jsp" class="a03" style="color: black"><b>忘記密碼</b></a> <img
			class="images07" src="images/ro14.png">


	</nav>
	<script type="text/javascript">
		function myFunction() {
			var userId = $('input[name=userId]').val().toString().trim();
			var userPs = $('input[name=userPs]').val().toString().trim();
			if (userId.length == 0) {
				alert("你好，帳號不可為空");
				return false;
			} else if (userPs.length == 0) {
				alert("你好，密碼不可為空");
				return false;
			} else {
				if ($("#check_cookie").prop("checked")) {
					console.log(userId + "," + userPs)
					setCookie('user', userId, 7);
					setCookie('password', userPs, 7);
				} else {

					deleteCookie('user');
					deleteCookie('password');

				}
				console.log("!!")
				return true;
			}
		}

		
		$(document).ready(function() {
			console.log("ddd");
			loading();

		});
	</script>
</body>
</html>