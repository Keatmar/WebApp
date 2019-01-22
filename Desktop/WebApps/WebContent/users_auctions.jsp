<!DOCTYPE html>
<%@page
	import="java.sql.*,java.io.InputStream,java.io.ByteArrayInputStream,javax.imageio.ImageIO,java.awt.image.BufferedImage,java.io.File"%>
<%@page import="java.util.*,model.Auction,model.Photo"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap Core CSS -->
<link href="./resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="./resources/css/auction_results.css" rel="stylesheet">
<link href="./resources/css/users_auctions.css" rel="stylesheet">
<title>My Auctions</title>
</head>
<body>
<body background="./resources/images/background.jpg">
<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>
		
	<div class="container">
		<div id="products" class="row">
		<h1 class="display-3"><em><strong>My Latest Auctions</strong></em></h1>
			<%
				List<Auction> auctionList = (List<Auction>) request.getAttribute("auctionList_without_bids");
				if(auctionList != null){
					for (Auction auction : auctionList) {
			%>
			
			<div class="item col-md-4 col-xs-4 col-lg-4">
				<form  action="./UsersAuctionView" method="get">
					<div class="thumbnail" id="item">
						<div class="caption">
							<div class="row">
								<div class="col-xs-7">
									<h4 class="group inner list-group-item-heading"><%=auction.getName()%></h4>
									<input type="hidden" name="Name" value=<%=auction.getId()%>>
								</div>
								<div class="col-xs-4 col-xs-offset-1">
									<span><%= auction.getEnds() %></span>
								</div>
							</div>
						</div>
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
					
						<img class="group list-group-image" id="image" src=<%=auction.getPhotos().get(0).getPhoto() %>
							height="100" width="100" />
						<%
							}
						%>
						<div class="row">
							<div class="col-xs-3 text-left">
								<input type="submit" class="btn btn-warning" name="form_button"value="Edit">
							</div>
							<div class="col-xs-3 col-xs-offset-6 text-right">
								<input type="submit" class="btn btn-success"  name="form_button"value="View">
							</div>
						</div>
					</div>
				</form>
			</div>
			<%
				}
			%>
			
		<%
			}else{
				%>
				<h3><strong><%= session.getAttribute("username") %> you have not committed any new Auctions</strong></h3>
		<% 	
			}
		%>
		</div>
	</div>
	
	<div class="container">
		<div id="products" class="row">
		<h1 class="display-3"><em><strong>My Purchased and Active Auctions</strong></em></h1>
			<%
				List<Auction> auctionList2 = (List<Auction>) request.getAttribute("auctionList_with_bids");
				if(auctionList2 != null){
					for (Auction auction : auctionList2) {
			%>
			
			<div class="item col-md-4 col-xs-4 col-lg-4">
				<form  action="./UsersAuctionView" method="get">
					<div class="thumbnail" id="item">
						<div class="caption">
							<div class="row">
								<div class="col-xs-7">
									<h4 class="group inner list-group-item-heading"><%=auction.getName()%></h4>
									<input type="hidden" name="Name" value=<%=auction.getId()%>>
								</div>
								<div class="col-xs-4 col-xs-offset-1">
									<span><%= auction.getEnds() %></span>
								</div>
							</div>
							
						</div>
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
					
						<img class="group list-group-image" id="image" src=<%=auction.getPhotos().get(0).getPhoto() %>
							height="100" width="100" />
						<%
							}
						%>
						
						<div class="row">
							<div class="col-xs-3 col-xs-offset-4 text-left">
								<input type="submit" class="btn btn-success"  name="form_button"value="View Purchased">
							</div>
						</div>
					</div>
				</form>
			</div>
			<%
				}
			%>
			
		<%
			}else{
				%>
				<h3><strong><%= session.getAttribute("username") %> you have no purchased or active auctions</strong></h3>
		<% 	
			}
		%>
		</div>
	</div>
	
</body>
</html>