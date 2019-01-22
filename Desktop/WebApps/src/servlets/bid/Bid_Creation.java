package servlets.bid;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ConnectionFactory.AuctionDAO;
import ConnectionFactory.AuctionDAOImpl;
import ConnectionFactory.BidDAO;
import ConnectionFactory.BidDAOImpl;
import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.Auction;
import model.Bid;
import model.User;

/**
 * Servlet implementation class Bid_Creation
 */
@WebServlet("/Bid_Creation")
public class Bid_Creation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bid_Creation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Map<String, String[]> Bid_Values = request.getParameterMap();
		
		
		UsersDAO userDAO  = new UsersDAOImpl();
		String username = (String) session.getAttribute("username");	
		
		User user	= userDAO.getUserByEmailOrUsername(username);
		
		int ammount =  Integer.parseInt(Arrays.asList(Bid_Values.get("bid_value")).get(0));
		int auction_id = Integer.parseInt(Arrays.asList(Bid_Values.get("auction_id")).get(0));
		System.out.println("blaaa");
		BigDecimal ammount_dec = new BigDecimal(ammount);
		
		AuctionDAO auction_DAO = new AuctionDAOImpl();
		Auction auction = auction_DAO.searchAuctionById(auction_id);
		if(auction.getCurrently() == null){
			auction.setCurrently(ammount_dec);
		}
		else{
			if (auction.getCurrently().compareTo(ammount_dec) == -1){
				auction.changeCurrently(ammount_dec);
				if(!auction.getBuy_Price().equals(null)){
					if (auction.getBuy_Price().compareTo(ammount_dec) == -1){
						Date now = new Date();
						auction.setEnds(now);
					}
				}
			}
			else{
				response.setStatus(404);
			}
		}
		System.out.println(ammount_dec);
		System.out.println("THe currently is" + auction.getCurrently());
		auction.setNumberOfBids(auction.getNumberOfBids() + 1);
		
		auction_DAO.editAuction(auction);
		

		Date date_created = new Date();
		
		Bid new_bid = new Bid();
		new_bid.setAuction(auction);
		new_bid.setTime(date_created);
		new_bid.setAmount(ammount_dec);
		new_bid.setUser(user);
		
		
		BidDAO  bid = new BidDAOImpl();
		bid.createBid(new_bid);
		
		// return success
		response.setStatus(200); 
	}

}
