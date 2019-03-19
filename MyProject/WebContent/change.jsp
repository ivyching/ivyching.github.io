<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密碼</title>
<link rel="stylesheet" type="text/css" href="css/mystyle01.css">


</head>
<body class="text-center">
	<form class="form01" action="change" method="post">
		<img class="images09" src="images/ro09.png"
			style="display: block; margin: auto;">
			<input type="text"placeholder="輸入原有帳號" class="control" name="userId" autofocus>
			 <br>
			 <input type="password"placeholder="輸入原有密碼" class="control" name="userPs">
			 <br>
			<input type="password" placeholder="輸入欲修改密碼" class="control" name="userPs01">
		    <br>
		    <input type="email" placeholder="輸入註冊時email" class="control" name="useremail">
		    <br>
		<input type="submit" class="btn01" id="btn" value="修改" style="cursor:pointer;">
		<br> <br>

	</form>
	<nav>
		
				<img class="images01" src="images/ro08.png">
			
				<a  href="login.jsp" class="a04" style="color:black"><b>登入</b></a>
				<a  href="enroll.jsp" class="a05" style="color:black"><b>註冊</b></a>
				<a  href="check.jsp" class="a06" style="color:black"><b>忘記密碼</b></a>
			
	</nav>
	<script type="text/javascript">
	function myFunction() {
		var userId = $('input[name=userId]').val().toString().trim();
		var userPs = $('input[name=userPs]').val().toString().trim();
		var userPs01=$('input[name=userPs01]').val().toString().trim();
		var userEmail=$('input[name=useremail]').val().toString().trim();
		if (userId.length == 0) {
			alert("你好，帳號不可為空");
		} else if (userPs.length == 0) {
			alert("你好，密碼不可為空");
		}else if(userPs01.length == 0){
			alert("你好,欲修改密碼不可為空");
		}else if(userEmail.length == 0){
			alert("你好，信箱不可為空");
		}
	}

	$("#btn").click(function(e) {

		myFunction();

	});
	
	
	
	</script>
</body>
</html>