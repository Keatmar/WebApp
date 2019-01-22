<!DOCTYPE html>
<%@page import="java.sql.*"%>
<%@page import="java.util.*,model.User,ConnectionFactory.UsersDAOImpl"%>


<html lang="en-el">
<head>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/css/admin.css">
<title>Admin</title>
</head>
<body background="./resources/images/background.jpg">
	

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>



	<!-- 	Main Body	 -->
	<div class="container">
		<div class="row centered-form">
			<div class="col-md-8 column">
				<table class="table table-main" id="table">
					<thead>
						<tr>
							<th>id</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Username</th>
							<th>Password</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Address</th>
							<th>City</th>
							<th>ZIP</th>
							<th>ΑΦΜ</th>
							<th>Active</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<%
							int i = 0;
							UsersDAOImpl usersDAO = new UsersDAOImpl();
							List<User> ulist = usersDAO.list();
							for (User user : ulist) {
						%>
						<tr>
							<td><%=user.getId()%></td>
							<td><%=user.getFirstName()%></td>
							<td><%=user.getLastName()%></td>
							<td><%=user.getUsername()%></td>
							<td><%=user.getPassword()%></td>
							<td><%=user.getEmail()%></td>
							<td><%=user.getPhone()%></td>
							<td><%=user.getAddress()%></td>
							<td><%=user.getCity()%></td>
							<td><%=user.getZip()%></td>
							<td><%=user.getAfm()%></td>
							<td><%=user.getActive()%></td>
							<td>
								<form action="./ConfirmUser" method="get">
									<input type="submit" value="Accept" class="btn btn-success" /><input
										type="hidden" name="id" value=<%=user.getId()%> />
								</form>
								<form action="../DeleteUser" method="post">
									<input type="submit" value="Reject" class="btn btn-danger" />
									<input type="hidden" name="id" value=<%=user.getId()%> />
								</form>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
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