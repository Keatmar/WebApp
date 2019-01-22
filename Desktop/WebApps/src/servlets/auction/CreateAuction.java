package servlets.auction;

import java.util.List;
import java.util.Locale;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.Auction;
import model.Category;
import model.Photo;
import model.User;

@WebServlet("/CreateAuction")
@MultipartConfig
public class CreateAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Auction auction;

	public CreateAuction() {
		auction = new Auction();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;

		HttpSession session = request.getSession();
		UsersDAO userDAO = new UsersDAOImpl();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		User user = userDAO.loginUser(username, password);

		String name = request.getParameter("Name");
		String categoryPath = request.getParameter("Categories");
		String[] category = categoryPath.split("->");
		String location = request.getParameter("Location");
		String country = request.getParameter("Country");
		String description = request.getParameter("Description");
		String latitude = request.getParameter("Latitude");
		String longitude = request.getParameter("Longitude");
		String dateStr = request.getParameter("datetime_field");
		
		Date started = new Date();
		System.out.println(dateStr);
				
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
		auction.setUser(user);
		auction.setStarted(started);
		
		/* 		Set Auction Ends		*/
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH);
		
		/* Check if time Zone is Am or Pm 	*/
		

		try {
			
			Date ends = formatter.parse(dateStr);
			System.out.println(ends);
			auction.setEnds(ends);
		} catch (ParseException e1) {
			e1.printStackTrace();
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
			buy_price = (BigDecimal) decimalFormat.parse(request.getParameter("Buy_Price"));
			first_bid = (BigDecimal) decimalFormat.parse(request.getParameter("First_Bid"));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		auction.setBuy_Price(buy_price);
		auction.setFirst_Bid(first_bid);
		auction.setCurrently(first_bid);
		auction.setNumberOfBids(1);
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
		auctionDAO.createAuction(auction);

		disp =getServletContext().getRequestDispatcher("/index.jsp");
		disp.forward(request, response);

	}

}
