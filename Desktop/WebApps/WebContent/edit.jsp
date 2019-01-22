<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/css/edit.css">
<title>Edit Profile</title>

</head>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>
	<!-- 	Main Page	 -->

	<div class="container">
		<div class="row centered-form">
			<div
				class="col-xs-12 col-sm-12 col-md-16 col-lg-16 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
				<div class="panel panel-default" id="panel">
					<div class="panel-heading">

						<h2 class="panel-title">
							Edit:
							<%=session.getAttribute("username")%></h2>

					</div>
					<div class="panel-body">
						<form role="form" name="edit" action="./Edit" method="get">
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<label>First name</label> <input type="text" name="txtFName"
											id="first_name" class="form-control input-sm"
											placeholder="<%=session.getAttribute("first_name")%>">
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<label>Last name</label> <input type="text" name="txtLName"
											id="last_name" class="form-control input-sm"
											placeholder="<%=session.getAttribute("last_name")%>">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label>Username</label> <input type="text" name="txtUsername"
									id="username" class="form-control input-sm"
									placeholder="<%=session.getAttribute("username")%>">
							</div>

							<div class="form-group">
								<label>Email address</label> <input type="email" name="txtEmail"
									id="email" class="form-control input-sm"
									placeholder="<%=session.getAttribute("email")%>">
							</div>

							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<label>Password</label> <input type="password"
											name="txtPassword" id="password"
											class="form-control input-sm" placeholder="Password">
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<label>Confirm password</label> <input type="password"
											name="txtPasswordRepeat" id="password_confirmation"
											class="form-control input-sm" placeholder="Confirm Password">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label>Phone</label> <input type="number" name="txtPhone"
									id="phone" class="form-control input-sm"
									placeholder="<%=session.getAttribute("phone")%>">
							</div>

							<div class="form-group">
								<label>Α.Φ.Μ.</label> <input type="number" name="txtAfm"
									id="afm" class="form-control input-sm" placeholder="Α.Φ.Μ.">
							</div>

							<div class="form-group">
								<label>Address</label> <input type="text" name="txtAddress"
									id="address" class="form-control input-sm"
									placeholder="<%=session.getAttribute("address")%>">
							</div>

							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<label>City</label> <input type="text" name="txtCity"
											id="city" class="form-control input-sm"
											placeholder="<%=session.getAttribute("city")%>">
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<label>ZIP Code</label> <input type="number" name="txtZip"
											id="zip_code" class="form-control input-sm"
											placeholder="<%=session.getAttribute("zip")%>">
									</div>
								</div>
							</div>

							<input type="submit" value="Edit"
								class="btn btn-warning btn-block">

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>