<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" href="./resources/css/login.css">
<title>Login</title>
</head>

<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>


	<div class="container">

		<!-- content -->
		<section id="content">

			<!-- form -->
			<form action="./Login" method="post">
				<h1>Login Form</h1>
				<div>
					<input type="text" placeholder="Username" id="username"
						name="txtUserName" />
				</div>
				<div>
					<input type="password" placeholder="Password" id="password"
						name="txtPassword" />
				</div>
				<div>
					<input type="submit" value="Log in" /> <a href="#">Lost your
						password?</a> <a href="#">Register</a>
				</div>
			</form>

		</section>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="./resources/js/index.js"></script>
</body>
</html>