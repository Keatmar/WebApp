<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@page
	import="java.util.*,java.sql.*,javax.servlet.http.HttpServletRequest"%>
<%@page
	import="model.Categories,ConnectionFactory.CategoriesDAO,ConnectionFactory.CategoriesDAOImpl"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap Core CSS -->
<link href="./resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="./resources/css/auction_search.css" rel="stylesheet">

<title>Auction Search</title>

</head>
<body background="./resources/images/background.jpg">
	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>

	<!-- Slide bar  -->
	<div class="container">
		<div class="row centered-form">
			<div class="col-md-9 col-md-offset-1 column">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2 class="panel-title text-center">Search Auction</h2>
					</div>


					<form role="form" action="./AuctionResults" method="get">
						<div class="grid">
							<div class="row">
								<div id="custom-search-input">
									<div class="input-group col-md-offset-2 col-md-8">
										<input type="text" class="search-query form-control"
											placeholder="Search" name="SearchName" /> <span class="input-group-btn">
											<button class="btn btn-danger" type="button">
												<span class=" glyphicon glyphicon-search"></span>
											</button>
										</span>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4 col-md-offset-4">
									<label>Browse A Category</label>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<input type="text" name="Categories" id="categories"
										class="form-control input-sm" value="">
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<select size="13" onchange="nextGrade(this.value,1);">
										<%
											CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
											List<Categories> clist = categoriesDAO.getFirstGrade();
											for (Categories categories : clist) {
										%>
										<option id="option1" value="<%=categories.getName()%>"
											role="option"><%=categories.getName()%></option>
										<%
											}
										%>
									</select>
								</div>

								<div class="col-md-2 col-md-offset-3">
									<select style="display: none" id="category_2" name="fcat"
										size="13" onchange="nextGrade(this.value,2);">

									</select>
								</div>


								<div class="col-md-2 col-md-offset-2">
									<select style="display: none" id="category_3" name="fcat"
										size="13" onchange="nextGrade(this.value,3);">

									</select>
								</div>
							</div>

							<div class="row">
								<div class="col-md-2">
									<select style="display: none" id="category_4" name="fcat"
										size="13" onchange="nextGrade(this.value,4);">

									</select>
								</div>

								<div class="col-md-2 col-md-offset-3">
									<select style="display: none" id="category_5" name="fcat"
										size="13" onchange="nextGrade(this.value,5);">

									</select>
								</div>
							</div>

							<div class="row">
								<div class="button">
									<input type="button" value="Cancel"
										onClick="window.location='http://localhost:8080/WebApps';"
										class="btn col-md-offset-2 btn-danger"><input
										type="submit" value="Next "
										class="btn col-md-offset-6 btn-success">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="./resources/js/category.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>