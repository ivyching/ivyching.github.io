<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bean.Bean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢密碼</title>
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
<body>
	<form class="form01" action="check" method="post">
		<img class="images09" src="images/ro10.png"
			style="display: block; margin: auto;">
			 <input type="text"placeholder="輸入帳號" class="control02" name="userId" autofocus>
			 <br>
			
			<input type="email" placeholder="輸入e_mail" class="control02" name="useremail">
		
		    <br>
		<input type="submit" class="btn01" id="btn" value="查詢" style="cursor:pointer;">
		<br> <br>

	</form>
	<nav>
		
				<img class="images11" src="images/ro11.png">
			
				<a  href="login.jsp" class="a07" style="color:black"><b>登入</b></a>
				<a  href="enroll.jsp" class="a08" style="color:black"><b>註冊</b></a>
				<a  href="change.jsp" class="a09" style="color:black"><b>修改密碼</b></a>
			
	</nav>
	
	<script type="text/javascript">
	function myFunction() {
		var userId = $('input[name=userId]').val().toString().trim();
		var userEmail=$('input[name=useremail]').val().toString().trim();
		if (userId.length == 0) {
			alert("你好，帳號不可為空");
		} else if(userEmail.length ==0){
			alert("你好，信箱不可為空");
		}
	}
	$("#btn").click(function(e) {

		myFunction();

	});
	
	
	</script>
</body>
</html>