<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page
	import="java.util.*,java.sql.*,javax.servlet.http.HttpServletRequest"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="./resources/css/menu_bar.css">

 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
 <!-- Ionicons -->
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" href="./resources/js/jquery.js">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
 folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="./resources/adminLte/dist/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="./resources/adminLte/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet"
	href="./resources/adminLte/plugins/iCheck/flat/blue.css">
<!-- Morris chart -->
<link rel="stylesheet"
	href="./resources/adminLte/plugins/morris/morris.css">
<!-- jvectormap -->
<link rel="stylesheet"
	href="./resources/adminLte/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<!-- Date Picker -->
<link rel="stylesheet"
	href="./resources/adminLte/plugins/datepicker/datepicker3.css">
<!-- Daterange picker -->

 <link rel="stylesheet" href="./resources/adminLte/plugins/datatables/dataTables.bootstrap.css">
 
<link rel="stylesheet"
	href="./resources/adminLte/plugins/daterangepicker/daterangepicker.css">
<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet"
	href="./resources/adminLte/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

</head>

<body>

	<p class="text-center">
		<%
			if (session.getAttribute("username") != null) {
				out.print(session.getAttribute("username"));
			}
		%>

	</p>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" id="nav-item">WebApp</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="http://localhost:8080/WebApps">Home</a></li>
					<li class="dropdown"><a id="nav-item" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Profile <span class="caret"></span></a> <!--  <ul class="dropdown-menu">-->
						<ul class="dropdown-menu ">
							<%
									if (session.getAttribute("username") != null) {
							%>
							<li>
								 <a href="./profile.jsp">Profile</a>
							</li>
							<li><a href="GetUsersAuctions"  id="user_auctions">My Auctions</a></li>
							<%
								}
							%>

							<%
								if (session.getAttribute("username") != null) {
							%>

							<li class="treeview">
								<a href="#"> <span>Messages</span>
									<span class="pull-right-container"> <i
										  class="fa fa-angle-left pull-right"></i>
									</span>
								</a>
								<ul class="treeview-menu ">
									<li><a href="./new_message.jsp"> New_Messages</a></li>
									<li><a href="./UsersInboxMessages">Inbox
										<% 
											if (session.getAttribute("new_inbox") != null) {
												int new_inbox = (int) session.getAttribute("new_inbox");
												if (new_inbox > 0){ 
										%>
											<span class="pull-right-container">
								              <small class="label t bg-red"><%=new_inbox %></small>
								           </span>
											<% } %>
										<% } %>
											 </a></li>
									<li><a href="./UsersSendedMessages">Sended</a></li>
								</ul>
							</li>
							<li><a href="Logout"  id="logout">Logout</a></li>

							<%
								}
							%>

							<%
								if (session.getAttribute("username") == null) {
							%>
							<li><a href="./register.jsp">Register</a></li>
							<li><a href="./login.jsp">Login</a></li>
							<%
								}
							%>
							<li>
								<%
									if (session.getAttribute("username") != null) {
										if (session.getAttribute("role").equals("ADMIN")) {
								%><a href="./admin.jsp">Administrator</a> <a
								href="./Import-Export.jsp">Import-Export</a>
							</li>
							<%
								}
								}
							%>

						</ul></li>
					<li class="dropdown"><a id="nav-item" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Shop <span class="caret"></span></a>
						<ul class="dropdown-menu">

							<%
								if (session.getAttribute("username") != null) {
							%>
							<li><a href="./auction_import.jsp">Import</a></li>

							<%
								}
							%>
							<li><a href="./auction_search.jsp">Search</a></li>

						</ul></li>

				</ul>



				<ul class="nav navbar-nav ">
					<li><a href="#">Help</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- 	<script -->
<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="./resources/adminLte/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>


	<script type="text/javascript">
		$(document).ready(function() {
			$('.dropdown-toggle').dropdown();
// 			$('#user_auctions').on('click',function(event){
// 				event.preventDefault();
// 				console.log("rrrr");
// 				$.get('GetUsersAuctions',{},
// 					function() { // on success
// 		               console.log("success");
// 		               location.href ='./users_auctions.jsp';
// 		           })
// 		           .fail(function() { //on failure
// 		               alert("Wrong email or userame submited.");
// 		    	   });
				
// 			});
		});
	</script>
</body>
</html>