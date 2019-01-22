<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@page
	import="java.util.*,java.util.Date,java.sql.*,java.io.*,javax.servlet.http.HttpServletRequest"%>
<%@page import="model.Auction,model.User"%>
<html>
<head>
<script src="http://maps.googleapis.com/maps/api/js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap Core CSS -->
<link href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./resources/bootstrap-touchspin-master/dist/jquery.bootstrap-touchspin.css">
<link href="./resources/css/auction_profile.css" rel="stylesheet">
<title>Auction Import</title>
<script src="./resources/js/location.js">
	
</script>
</head>
<style>
	.bootstrap-touchspin{
		width: 60% !important;
	}

</style>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>


	<!-- Page Content -->

	<%
		Auction auction = (Auction) request.getAttribute("auction");
		OutputStream img;
		Date date = new Date();
	%>
	<div class="container-fluid">
		<div class="content-wrapper">
			<div class="item-container">
				<div class="container">
					<div class="row">
						<div class="col-md-12 col-sm-12 title">
							<h2><%=auction.getName()%></h2>
						</div>
					</div>

					<div class="row auction-ends">
						<div class="col-md-12 col-sm-12">
							<%
								if (auction.getEnds() == null) { //Auction importing from xml
							%>
							<h3>Auction Ended</h3>
							<%
								} else {
									if (auction.getEnds().after(date) == true) { //Check if Auction is active now
							%>
							<div class="auction-active">
								<h3>
									Auction ends at:<br> <strong><%=auction.getEnds()%></strong>
								</h3>
							</div>
							<%
								} else {
							%>
							<h3>This Auction has been ended</h3>
							<%
								}
								}
							%>
						</div>


					</div>

					<!-- Auction's Images -->
					<div class="row">
						<div class="col-md-6 col-sm-7">
							<div class="col-md-5 col-sm-5 image-right">
								<img id="item-display" class="img-responsive img-thumbnail"
									src="http://www.corsair.com/Media/catalog/product/g/s/gs600_psu_sideview_blue_2.png"
									alt=""></img>
							</div>

							<div class="col-sm-3 col-md-3 image-left">
								<a id="item-1"> <img class="img-responsive img-thumbnail"
									src="http://www.corsair.com/Media/catalog/product/g/s/gs600_psu_sideview_blue_2.png"
									alt=""></img>
								</a> <a id="item-2"> <img class="img-responsive img-thumbnail"
									src="http://www.corsair.com/Media/catalog/product/g/s/gs600_psu_sideview_blue_2.png"
									alt=""></img>
								</a> <a id="item-3"> <img class="img-responsive img-thumbnail"
									src="http://www.corsair.com/Media/catalog/product/g/s/gs600_psu_sideview_blue_2.png"
									alt=""></img>
								</a>
							</div>
						</div>

						<div class="col-md-5 bids">
							<div class="row col-md-offset-1 col-sm-offset-1 ">
								<div class="col-md-4">
									<%
										if (auction.getUser() != null) {
									%>

									<h3>
										Rating
										<%=auction.getUser().getRattingSeller()%></h3>
									<%
										String rating;
											String[] rat;
											if (auction.getUser().getRattingSeller() > 5) {
												rating = String.valueOf((auction.getUser().getRattingSeller() / 5));
											} else {
												rating = String.valueOf(auction.getUser().getRattingSeller());
												rat = rating.split(".");
												if (rat.length == 0) {
													rating = String.valueOf(auction.getUser().getRattingSeller());

												} else {
													rating = rat[1];
												}
											}
									%>
								</div>
								<div id="stars-existing " class="col-md-3 starrr"
									data-rating='<%=rating%>'>

									<%
										}
									%>
								</div>
							</div>

							<div class="row">
								<ul>
									<%
										Date now = new Date();
										if ((auction.getEnds().before(now)) || auction.getEnds() == null) {
											if (auction.getCurrently() != null) {
									%>
									<li><h3>
											Product sold at: <strong><%=auction.getCurrently()%></strong>
										</h3></li>
									<%
										} else {
									%>
									<li><h3>
											Product sold at: <strong><%=auction.getFirst_Bid()%></strong>
										</h3></li>
									<%
										}
									%>
									<%
										} else {
											if (auction.getCurrently() != null) {
									%>
									<li><h4>
											Current Price of product is: <strong><%=auction.getCurrently()%></strong>
										</h4></li>
									<%
										} else {
									%>
									<li><h4>
											Current Price of product is: <strong><%=auction.getFirst_Bid()%></strong>
										</h4></li>
									<%
										}
									%>
								</ul>
							</div>


									<%
										}
									%>

						</div>
					</div>
					<div class="container">
						<div class="row">
							<%
								if (session.getAttribute("username") != null) {
									if ( !auction.getStatus().contains("Purchased")) {
							%>
							<div class="col-md-5 col-md-offset-6">
					          <div class="panel box box-primar">
						            <div class="box-header with-border">
						            <h3 class="box-title " >
							            <a data-toggle="collapse"  href="#bid_form" aria-expanded="true" style="color: #333;">
							            	Make your Bid
										</a>
									</h3>
						           
						              <!-- /.box-tools -->
						            </div>
						            <!-- /.box-header -->
						            <div  id="bid_form" class="panel-collapse collapse " aria-expanded="true">
						            	<div class="box-body">
								           <form class="form-horizontal" role="form">
									            <div class="form-group">
									                <label for="bid_value" class="col-md-3 control-label">Ammount:</label> 
									                	<input id="bid_value" type="text"  value="<%= auction.getCurrently() %>" name="bid_value" class="col-md-4 form-control">
									            </div>
									            <input type="button"  value="Submit"
														data-toggle="modal" data-target="#myModal"
														class="btn col-md-offset-2 btn-active pull-right">
									       </form>
									      
						              	</div>
						            </div>
						            
						            <!-- Modal -->
									<div id="myModal" class="modal fade" role="dialog">
									  	<div class="modal-dialog">
									
									    	<!-- Modal content-->
									   		<div class="modal-content">
									      		<div class="modal-header">
									        		<button type="button" class="close" data-dismiss="modal">&times;</button>
									        		<h4 class="modal-title">Confirmation Ticket</h4>
										      	</div>
										      	<div class="modal-body">
										        	<p>You are about to create a new bid. Are you sure that you want to continue?</p>
										      	</div>
										      	<div class="modal-footer">
										        	<button type="button" id ="submit_button" 
										        			class="btn btn-default"  data-auction-id="<%=auction.getId()%>"  
										        			data-dismiss="modal">Confirm
										        	</button>
										      	</div>
									   		</div>
									
									  </div>
									</div>
						            
						            <!-- /.box-body -->
					          </div>
					          <!-- /.box -->
					        </div>
					        		<% } else if (session.getAttribute("id").equals(auction.getWinningBidderId())) { %>
					        				<form class="form-horizontal" role="form" method="get" action="./New_Message" >
						        				<div class="col-md-5 col-md-offset-6">
						        					<input type="button" name="receiver_id" 
						        					value="<%= auction.getUser().getId() %>" placeholder="Send Message to Seller">
												</div>
											</form>
					        		<% 
										} else {
									%>
											<div class="col-md-5 col-md-offset-6">
											
											</div>
									
									<% 
										} 
									%>
							<%
								} else {
							%>
							
							
								<div class="col-md-5 col-md-offset-6">
									<a href="./login.jsp"><button class="btn btn-block btn-warning btn-lg "> To Make a Bid<br> Please Login</button></a>
								</div>
								<% 
									} 
								%>
						</div>
					</div>
				</div>

				<div class="container-fluid">
					<div class="col-md-12 product-info">
						<ul id="myTab" class="nav nav-tabs nav_tabs">

							<li class="active"><a href="#service-one" data-toggle="tab">Price</a></li>
							<li><a href="#service-two" data-toggle="tab">Location</a></li>
							<li><a href="#service-three" data-toggle="tab">Description</a></li>
						</ul>
						<div id="myTabContent" class="tab-content">

							<div class="tab-pane fade in active price" id="service-one">

								<!-- 			Auctions Bids		 -->
								<div class="row">
									<div class="col-md-8 ">
										<ul>
											
											<li>
												<h4>
													You Can win This Auction by giving <br>
													the amount of <strong><%=auction.getBuy_Price()%></strong>€
													
												</h4>
											</li>
										
											<li><h4><%=auction.getNumberOfBids()%>
													bids have been commited for this product
												</h4></li>
											<!-- 			Appear owner of this auction 	-->
											<%
												if (auction.getUser() != null) {
											%>
											<li><h4>
													This auction created by <strong><%=auction.getUser().getUsername()%></strong>
												</h4></li>
											<%
												}
											%>
											<%
												/*		Someone win this Auction 	*/
												if (auction.getSellerUsersId() != 0) {
											%>
											<li><h4>
													<strong><%=auction.getSellerUsersId()%></strong> win this
													auction
												</h4></li>
											<%
												}
											%>
										</ul>
									</div>
								</div>
							</div>


							<!-- 				Second Tab					 -->
							<div class="tab-pane fade Location" id="service-two">
								<div class="col-md-12">
									<div class="col-md-8 location-detail">
										<h3>
											Country:
											<%=auction.getCountry()%>
										</h3>
										<h3>
											Location:
											<%=auction.getLocation()%></h3>
										<h4>
											Latitude:
											<%=auction.getLatitude()%></h4>
										<h4>
											Longitude:<%=auction.getLongitude()%></h4>
									</div>
									<div class="col-md-4 map">
										<div id="googleMap" style="width: 250px; height: 200px;">
										</div>
											<input type="hidden" id="Longitude" name="Longitude"
												value=<%=auction.getLongitude()%>> 
											<input type="hidden" id="Latitude" name="Latitude"
												value=<%=auction.getLatitude()%>>
									</div>
								</div>
							</div>

							<!-- 				Third Tab					 -->
							<div class="tab-pane fade description" id="service-three">
								<p><%= auction.getDescription() %></p>
							</div>
						</div>
					</div>
					<hr>
				</div>
			</div>
		</div>
	</div>
	<script src="./resources/adminLte/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="./resources/js/rating_star.js"></script>
	<script src="./resources/bootstrap-touchspin-master/dist/jquery.bootstrap-touchspin.js"></script>
	<script type="text/javascript">
	    $(document).ready(function () {
	    	
            $("#bid_value").TouchSpin({
                min: -1000000000,
                max: 1000000000,
                stepinterval: 50,
                maxboostedstep: 10000000,
                prefix: '€'
            });
            
            $("#submit_button").on("click", function(event){
            	var bid_value = $("#bid_value").val();
            	console.log(bid_value);
            	var auction_id = $(this).attr('data-auction-id');
            	console.log(auction_id);
            	$.post('Bid_Creation',{"bid_value": bid_value, "auction_id": auction_id},
         	           function() { // on success
         	               alert("Your Bid has been submited successfully!");
         	               location.href ='#';
         	           })
         	           .fail(function() { //on failure
         	               alert("Problem at bid submition.");
         	    	   });
            });
	    });
	</script>
</body>
</html>