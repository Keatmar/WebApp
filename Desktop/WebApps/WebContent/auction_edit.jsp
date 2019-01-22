<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@page
	import="java.util.*,java.sql.*,javax.servlet.http.HttpServletRequest"%>
<%@page
	import="model.Categories,ConnectionFactory.CategoriesDAO,ConnectionFactory.CategoriesDAOImpl"%>
<%@page
	import="java.util.*,java.util.Date,java.sql.*,java.io.*,javax.servlet.http.HttpServletRequest"%>
<%@page import="model.Auction,model.User"%>
<html>
<head>
<script src="http://maps.googleapis.com/maps/api/js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="./resources/bootstrap/css/bootstrap.min.css">

<link rel="stylesheet" href="./resources/css/auction_import.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.css">

<title>Auction Import</title>
<script src="./resources/js/location.js">
	
</script>
</head>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>

	<%
		Auction auction = (Auction) request.getAttribute("auction");
		OutputStream img;
		Date date = new Date();
	%>

	<!-- 	Main Page	 -->
	<div class="container">
		<div class="row centered-form">
			<div class="col-md-12  col-sm-12  column">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2 class="panel-title text-center">Edit Auction <strong> <%= auction.getId() %>></strong></h2>
					</div>
					<form role="form" action="./EditAuction"
						enctype="multipart/form-data" method="post">
						<div class="panel-body">
							<input type="hidden" value=<%= auction.getId() %> name ="auction_id">
							<input type="hidden" value="page1" name="createAuction">
							<div class="row">
								<div class="col-sm-5 col-md-5">
									<div class="form-group text-center">
										<label>Item Name</label> <input type="text" name="Name"
											class="form-control input-sm" placeholder="<%= auction.getName() %>">
									</div>
								</div>
								<div class="col-md-3 col-sm-3">
									<div class="form-group text-center">
										<label>Buy Price</label> <input type="text" name="Buy_Price"
											class="form-control input-sm" placeholder="<%= auction.getBuy_Price()%>">
									</div>

								</div>

								<div class="col-md-3 col-sm-3">
									<div class="form-group text-center">
										<label>First Bid</label> <input type="text" name="First_Bid"
											class="form-control input-sm" placeholder="<%= auction.getFirst_Bid()%>">
									</div>

								</div>
							</div>
							<div class="container">
							    <div class="row">
							        <div class='col-sm-5 col-sm-offset-3'>
							            <div class="form-group">
							                <div class='input-group date' id='datetimepicker1'>
							                    <input type='text' class="form-control" name="datetime_field" placeholder="<%= auction.getEnds()%>" />
							                    <span class="input-group-addon">
							                        <span class="glyphicon glyphicon-calendar"></span>
							                    </span>
							                </div>
							            </div>
							        </div>
							        
							    </div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group text-center">

										<input type="hidden" name="SumPhoto" id="number" value="0" />
										<div>
											<label>Click to Add Photo:</label>
										</div>
										<div>
											<input type="button" onclick="addPhoto()" value="Add Photo"
												class="btn btn-lg btn-info">
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4  col-md-offset-1 form-group text-center">
									<div id="add_photo_left"></div>
								</div>
								<div class="col-md-4 col-md-offset-2 form-group text-center">
									<div id="add_photo_right"></div>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-6 col-sm-offset-3 col-md-offset-3  col-md-6 ">
									<div class="form-group text-center">
										<label>Select a Category:</label> <input type="text"
											name="Categories" id="categories"
											class="form-control input-sm" value="" plcaceholder="">

									</div>
								</div>


							</div>

							<div class="row">
								<div class="col-md-1 col-sm-1">
									<div class="form-group text-center">
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
								</div>

								<div class="col-md-1 col-md-offset-2 col-sm-1 col-sm-offset-2">
									<div class="form-group text-center">
										<select style="display: none" id="category_2" name="fcat"
											size="13" onchange="nextGrade(this.value,2);">

										</select>

									</div>
								</div>


								<div class="col-md-1 col-md-offset-2 col-sm-1 col-sm-offset-2">
									<div class="form-group text-center">
										<select style="display: none" id="category_3" name="fcat"
											size="13" onchange="nextGrade(this.value,3);">

										</select>

									</div>
								</div>

								<div class="col-md-1 col-md-offset-2 col-sm-1 col-sm-offset-2">
									<div class="form-group text-center">
										<select style="display: none" id="category_4" name="fcat"
											size="13" onchange="nextGrade(this.value,4);">

										</select>
									</div>
								</div>

								<div class="col-md-1 col-md-offset-2 col-sm-1 col-sm-offset-2">
									<div class="form-group text-center">
										<select style="display: none" id="category_5" name="fcat"
											size="13" onchange="nextGrade(this.value,5);">

										</select>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-3">
									<div id="googleMap" style="width: 250px; height: 200px;"></div>
								</div>
								<div class="col-md-4 col-sm-4">
									<div class="form-group text-center">
										<label>Country</label> <input type="text" name="Country"
											class="form-control input-sm"  placeholder="<%= auction.getCountry() %>">
									</div>
								</div>
								<div class="col-md-4 col-sm-4">
									<div class="form-group text-center">
										<label>Location</label> <input type="text" id="Location"
											name="Location" class="form-control input-sm"
											 placeholder="<%= auction.getLocation() %>">
									</div>
								</div>


								<div class="col-sm-4 col-md-4">
									<div class="form-group text-center">
										<label>Latitude</label>
										<div>
											<input type="text" id="Latitude" name="Latitude"
												class="form-control input-sm" placeholder="<%= auction.getLatitude() %>">
										</div>
									</div>
								</div>
								<div class="col-sm-4 col-md-4">
									<div class="form-group text-center">
										<label>Longitude</label>
										<div>
											<input type="text" id="Longitude" name="Longitude"
												class="form-control input-sm"  placeholder="<%= auction.getLongitude() %>">
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-8 description">
									<div class="form-group text-center">
										<label>Description</label>
										<textarea name="Description" class="form-control" rows="5"  placeholder="<%= auction.getDescription() %>"></textarea>
									</div>

								</div>
							</div>


							<input type="submit" id="delete_button" value="Delete"
								name ="edit_form_button"
								class="btn col-md-offset-2 btn-danger">
							<input
								type="submit" value="Edit "
								name ="edit_form_button" 
								class="btn col-md-offset-6 btn-success">
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>




	<!-- jQuery 2.2.3 -->
	<script src="./resources/adminLte/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="./resources/js/dolars.js"></script>
	<script src="./resources/js/add_photo.js"></script>
	<script src="./resources/js/category.js"></script>
	<script src="./resources/adminLte/plugins/daterangepicker/moment.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
	
	<script type="text/javascript">
	    $(document).ready(function () {
	        $('#datetimepicker1').datetimepicker({format: 'MM-DD-YYYY HH:mm:ss'});
	        
	    });
	</script>
</body>
</html>