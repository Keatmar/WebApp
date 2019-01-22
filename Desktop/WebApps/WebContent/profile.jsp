<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/css/profile.css">
<title>Profile</title>

</head>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>


	<div class="container">
		<div class="row">
			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title"><%=session.getAttribute("username")%></h3>
						
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center">
								<img alt="User Pic" src="./resources/images//no-user.jpg"
									class="img-circle img-responsive">
							</div>

							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>First Name:</td>
											<td><%=session.getAttribute("first_name")%></td>
										</tr>
										<tr>
											<td>Last Name:</td>
											<td><%=session.getAttribute("last_name")%></td>
										</tr>
										<tr>
											<td>Username:</td>
											<td><%=session.getAttribute("username")%></td>
										</tr>
										<tr>
											<td>Email:</td>
											<td><a href="mailto:<%=session.getAttribute("email")%>"><%=session.getAttribute("email")%></a></td>
										</tr>
										<tr>
											<td>Phone</td>
											<td><%=session.getAttribute("phone")%></td>
										</tr>

										<tr>
										<tr>
											<td>Address</td>
											<td><%=session.getAttribute("address")%></td>
										</tr>
										<tr>
											<td>City</td>
											<td><%=session.getAttribute("city")%></td>
										</tr>
										<tr>
											<td>Zip code</td>
											<td><%=session.getAttribute("zip")%></td>
										</tr>
										<tr>
											<td>ΑΦΜ</td>
											<td><%=session.getAttribute("afm")%></td>
										</tr>
										<tr>
											<td>Role</td>
											<td><%=session.getAttribute("role")%></td>
										</tr>

									</tbody>
								</table>


							</div>
						</div>
					</div>

					<form class="panel-footer" action="./Logout" method="get">
					<a  href="./edit.jsp">
					<button type="button" class="btn btn-warning">Edit</button></a>
					

						<div class="pull-right">
							<input type="submit" name="method" value="Logout"
								class="btn btn-danger">

						</div>
					</form>

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