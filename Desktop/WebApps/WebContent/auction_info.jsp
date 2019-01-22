<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@page
	import="java.util.*,java.util.Date,java.sql.*,java.io.*,javax.servlet.http.HttpServletRequest"%>
<%@page import="model.Auction,model.User,model.Bid"%>


<html>
<head>
<script src="http://maps.googleapis.com/maps/api/js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap Core CSS -->
<link href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title>Auction's Info</title>
</head>

<body background="./resources/images/background.jpg">
<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>
	
	<%
		Auction auction = (Auction) request.getAttribute("auction");
		OutputStream img;
		Date date = new Date();
	%>
	<div class= row>	
		<div class="col-md-6 col-md-offset-3">
	          <!-- Widget: user widget style 1 -->
          <div class="box box-widget widget-user-2">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-blue">
              <div class="widget-user-image">
               <%
					if (auction.getPhotos().isEmpty()) {
				%>
				<img class="group list-group-image"
					src="./resources/images/No_image_available.png" height="100"
					width="100" />
				<%
					} else {
							//InputStream in = new ByteArrayInputStream(auction.getPhotos().get(0).getPhoto());
							//BufferedImage bImageFromConvert = ImageIO.read(in);
							//File file = new File("c:/sas.jpg");
							//ImageIO.write(bImageFromConvert, "jpg",file);
				%>
	              </div>
	              <!-- /.widget-user-image -->
	              <img class="group list-group-image" id="image" src=<%=auction.getPhotos().get(0).getPhoto() %>
								height="100" width="100" />
				<%
					}
				%>
              <h3 class="widget-user-username">Auction No<strong><%= auction.getId() %></strong></h3>
              <h5 class="widget-user-desc">Name:<strong><%= auction.getName() %></strong></h5>
            </div>
            <div class="box-footer no-padding">
              <ul class="nav nav-stacked">
              	<li></li>
              	<li><a href="#"><strong>Status</strong><span class="pull-right badge bg-yellow"><%= auction.getStatus() %></span></a></li>
                <li><a href="#"><strong>Description </strong><span class="pull-right badge bg-yellow "><%= auction.getDescription() %></span></a></li>
                <li><a href="#"><strong>Highest bid </strong><span class="pull-right badge bg-green"><%= auction.getCurrently() %></span></a></li>
                <li><a href="#"><strong>Start Date </strong><span class="pull-right label label-success"><%= auction.getStarted() %></span></a></li>
                <li><a href="#"><strong>End Date</strong><span class="pull-right label label-danger"><%= auction.getEnds() %></span></a></li>
                <li><a href="#"><strong>Country</strong><span class="pull-right badge bg-yellowr"><%= auction.getCountry() %></span></a></li>
                <li><a href="#"><strong>Location</strong><span class="pull-right badge bg-yellow"><%= auction.getLocation() %></span></a></li>
                <li><a href="#"><strong>Number of Bids</strong><span class="pull-right badge bg-yellow"><%= auction.getNumberOfBids() %></span></a></li>
                <li><a href="#"><strong>Winning Bidder</strong>
                	<span class="pull-right label label-danger">
                	<% if(auction.getStatus().contains("Purchased")){%> 
                		Unknown
                	<% }else {%>
                		Auction has not been Purchased yet
                	<%} %>
               	 	</span></a>
               </li>
              </ul>
            </div>
          </div>
          <!-- /.widget-user -->
        </div>
	</div>
	<div class="row">	
		<div class="col-xs-6 col-xs-offset-3">
			<% List<Bid> auction_bid_list = (List<Bid>) request.getAttribute("auction_bid_list");
	           if (!auction_bid_list.isEmpty()) { 
	         %>
	          <div class="box ">
	            <div class="box-header">
	              <h3 class="box-title">Auctions Bid Table</h3>
	
	              <div class="box-tools">
	                <div class="input-group input-group-sm" style="width: 150px;">
	                  <input name="table_search" class="form-control pull-right" placeholder="Search" type="text">
	
	                  <div class="input-group-btn">
	                    <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
	                  </div>
	                </div>
	              </div>
	            </div>
	            <!-- /.box-header -->
	            <div class="box-body table-responsive no-padding">
	              <table class="table table-hover">
	                <tbody><tr>
	                  <th>ID</th>
	                  <th>Bidder</th>
	                  <th>Date_created</th>
	                  <th>Value</th>
	                 
	                </tr>
	                
	                  <%		
	                  		for(Bid bid : auction_bid_list){ 
	                  %>
	                  <tr>
	                  <td><%= bid.getId()%></td>
	                  <td><%= bid.getUser().getUsername()%></td>
	                  <td><%= bid.getTime()%></td>
	                  <td><span class="label label-success"><%= bid.getAmount()%></span></td>
	                  </tr>
	                  <% } %>
	                
	               
	              </tbody></table>
	            </div>
	            <!-- /.box-body -->
	          </div>
	          <!-- /.box -->
	        </div>
		
		<% } 
			else{ %>
				<div class="col-xs-6 col-xs-offset-3">
				<h3 style="text-align: center; color: #ffff;"><strong><em>Auction has no Bids yet!</em></strong></h3>
				</div>
		<%} %>
	</div>
	<% if(auction.getStatus().contains("Purchased")){ %>
		<div class="row">
		
			<div class="col-md-4 col-md-offset-4">
	     		<form id="send_message" class="form-horizontal " role="form" method="get" action="./New_Message" >
	      				<input type="hidden" name="receiver_id" 
	      						   value="<%= auction.getWinningBidderId() %>" >
	      				<button type="submit" class="btn btn-warning" form="send_message">Send Message to Winner Bidder</button>
				</form>
	       		
			</div>	
		</div>
	<% } %>
</body>
</html>