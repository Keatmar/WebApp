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
<title>Auction Results</title>
</head>
<body background="./resources/images/background.jpg">

	<!-- 	Menu Bar	 -->
	<%@ include file="./menu_bar.jsp"%>


	<div class="container">
		<div id="products" class="row">
			<%
				List<Auction> auctionList = (List<Auction>) request.getAttribute("auctionList");
				for (Auction auction : auctionList) {
			%>
			<div class="item col-md-4 col-xs-4 col-lg-4">
				<form role="form" action="./AuctionProfile" method="get">
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
						<div class="text-right">
							<input type="submit" class="btn btn-success" name="form_button" value="View">
						</div>
					</div>
				</form>
			</div>
			<%
				}
			%>
		</div>
	</div>

</body>
</html>