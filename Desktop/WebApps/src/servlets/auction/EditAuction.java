package servlets.auction;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ConnectionFactory.AuctionDAO;
import ConnectionFactory.AuctionDAOImpl;
import ConnectionFactory.CategoriesDAO;
import ConnectionFactory.CategoriesDAOImpl;
import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.Auction;
import model.Bid;
import model.Category;
import model.Photo;
import model.User;

@WebServlet("/EditAuction")
@MultipartConfig
public class EditAuction extends HttpServlet {

	public EditAuction() {
		// TODO Auto-generated constructor stub
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;

		HttpSession session = request.getSession();
		UsersDAO userDAO = new UsersDAOImpl();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		User user = userDAO.loginUser(username, password);
		String auction_id =  request.getParameter("auction_id");
		System.out.println(auction_id);
		
		AuctionDAO auction_DAO = new AuctionDAOImpl();
		Auction auction = auction_DAO.searchAuctionById(Integer.parseInt(auction_id));
		String submit_type = request.getParameter("edit_form_button");
		System.out.println(submit_type);
		
		if (submit_type.contains("Edit")) {
			System.out.println("PSOLA");
			String name = request.getParameter("Name");
			if ( name.isEmpty()){
				name = auction.getName();
			}
			String categoryPath = request.getParameter("Categories");
			
			String[] category = categoryPath.split("->");
			String location = request.getParameter("Location");
			if ( location.isEmpty()){
				location = auction.getLocation();
			}
			String country = request.getParameter("Country");
			if ( country.isEmpty()){
				country = auction.getCountry();
			}
			String description = request.getParameter("Description");
			if ( description.isEmpty()){
				description = auction.getDescription();
			}
			String latitude = request.getParameter("Latitude");
			if ( latitude.isEmpty()){
				latitude = auction.getLatitude();
			}
			String longitude = request.getParameter("Longitude");
			if ( longitude.isEmpty()){
				longitude = auction.getLongitude();
			}
			String dateStr = request.getParameter("datetime_field");
			String buy_price_param = request.getParameter("Buy_Price");
			String first_bid_param = request.getParameter("First_Bid");
			
			Date started = new Date();
			System.out.println(dateStr);
			if ( categoryPath.isEmpty()){
				List<Category> listCategory = new ArrayList<Category>();
				listCategory = auction.getCategories();
				auction.setCategories(listCategory);
			}else{		
				Category[] categories = new Category[category.length];
				List<Category> listCategory = new ArrayList<Category>();
				for (int i = 0; i < category.length; i++) {
					categories[i] = new Category();
					categories[i].setGrade(i + 1);
					categories[i].setName(category[i]);
					categories[i].setAuction(auction);
					listCategory.add(categories[i]);
				}
				auction.setCategories(listCategory);
			}
			
			auction.setUser(user);
			auction.setStarted(started);
			
			/* 		Set Auction Ends		*/
			
			
			
			if ( dateStr.isEmpty()){
				Date ends = auction.getEnds();
				auction.setEnds(ends);
			}
			else{
			try {
					SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH);
					Date ends = formatter.parse(dateStr);
					System.out.println(ends);
					auction.setEnds(ends);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			
			auction.setName(name);
			auction.setLocation(location);
			auction.setCountry(country);
			auction.setDescription(description);
			if (latitude != "") {
				auction.setLatitude(latitude);
			}
			if (longitude != "") {
				auction.setLongitude(longitude);
			}
	
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator('.');
			String pattern = "##0.0#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);
	
			BigDecimal buy_price = BigDecimal.ZERO;
			BigDecimal first_bid = BigDecimal.ZERO;
			try {
				if (buy_price_param.isEmpty()){
					buy_price = auction.getBuy_Price();
				}
				else {
					buy_price = (BigDecimal) decimalFormat.parse(request.getParameter("Buy_Price"));
				}
				if(first_bid_param.isEmpty()){
					first_bid = auction.getFirst_Bid();
				}
				else{
					first_bid = (BigDecimal) decimalFormat.parse(request.getParameter("First_Bid"));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
	
			auction.setBuy_Price(buy_price);
			auction.setFirst_Bid(first_bid);
			auction.setCurrently(first_bid);
			System.out.println(String.valueOf(auction.getBuy_Price()) + String.valueOf(auction.getCurrently()) + String.valueOf(auction.getFirst_Bid()));
			/* Photo */
			Part pht = request.getPart("Photo0");
			InputStream inputStream = null;
			if (pht != null) {
	
				byte[] img = new byte[(int) pht.getSize()];
				inputStream = pht.getInputStream();
				inputStream.read(img);
	
				int num_photo = Integer.parseInt(request.getParameter("SumPhoto"));
				if (num_photo != -1) {
					if (!pht.equals("")) {
						Photo[] photo = new Photo[num_photo + 1];
						photo[0] = new Photo();
						photo[0].setPhoto(img);
						inputStream.close();
						photo[0].setAuction(auction);
						List<Photo> listPhoto = new ArrayList<Photo>();
						listPhoto.add(photo[0]);
						auction.setPhotos(listPhoto);
						
						for (int i = 1; i < num_photo ; i++) {
							inputStream = null;
							pht = request.getPart("Photo" + (i));
							img = new byte[(int) pht.getSize()];
							inputStream = pht.getInputStream();
							inputStream.read(img);
							
							photo[i] = new Photo();
							photo[i].setPhoto(img);
							photo[i].setAuction(auction);
							auction.addPhoto(photo[i]);
							inputStream.close();
						}
					
					}
				}
				
			} else {
				System.out.println("Auction doesn't have photo...");
			}
	
			AuctionDAO auctionDAO = new AuctionDAOImpl();
			auctionDAO.editAuction(auction);
		}
		else{
			
			List<Category> auction_categories= auction.getCategories();
			if (auction_categories.isEmpty() == false){
				for (Category category : auction_categories){
					auction.removeCategory(category);
					
				}
			}
			List<Photo> auction_photos= auction.getPhotos();
			if (!auction_photos.isEmpty()){
				for ( Photo photo : auction_photos){
					auction.removePhoto(photo);
				}
			}
			List<Bid> auction_bids= auction.getBids();
			if (!auction_bids.isEmpty()){
				for ( Bid bid : auction_bids){
					auction.removeBid(bid);
				}
			}
			
			user.removeAuction(auction);
			userDAO.editUser(user);
			
			auction.setUser(null);
			auction_DAO.editAuction(auction);
			System.out.println("lalalala");
			auction_DAO.deleteAuction(Long.valueOf(auction.getId()));
		}
		disp=getServletContext().getRequestDispatcher("/profile.jsp");
		disp.forward(request, response);

	}
//	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher disp;
//		
//		HttpSession session = request.getSession();
//		UsersDAO userDAO = new UsersDAOImpl();
//		String username = (String) session.getAttribute("username");
//		String password = (String) session.getAttribute("password");
//		User user = userDAO.loginUser(username, password);
//		
//		String auction_id =  request.getParameter("auction_id");
//		AuctionDAO auction_DAO = new AuctionDAOImpl();
//		Auction auction = auction_DAO.searchAuctionById(Integer.parseInt(auction_id));
//		
//		CategoriesDAO auction_categories = new CategoriesDAOImpl();
//		for (Category category : auction.getCategories()){
//			category.setAuction(null);
//			
//		}
//		for ( Photo photo : auction.getPhotos()){
//			photo.setAuction(null);
//		}
//		
//		user.removeAuction(auction);
//		userDAO.editUser(user);
//		
//		auction_DAO.deleteAuction(auction.getId());
//		
//		disp =getServletContext().getRequestDispatcher("/index.jsp");
//		disp.forward(request, response);
//	}
}
