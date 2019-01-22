package servlets.auction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class UsersAuctionView
 */
@WebServlet("/UsersAuctionView")
public class UsersAuctionView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersAuctionView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		String auctionId = request.getParameter("Name");
		String clicked_button = request.getParameter("form_button");
		
		if (!auctionId.equals("")) {
			int id = Integer.parseInt(auctionId);

			AuctionDAO auctionDAO = new AuctionDAOImpl();
			Auction auction = auctionDAO.searchAuctionById(id);
			UsersDAO auctionUser = new UsersDAOImpl();
			BidDAO auction_bidDAO = new BidDAOImpl();
			
			List<Bid> bid_list = auction.getBids();
			List<Bid> auction_bid_list  = new ArrayList<Bid>();
			System.out.println("rererere");
			System.out.println("The Bids are" + bid_list.size());
			if (!bid_list.isEmpty()){
				for (Bid bid : bid_list){
					if(auction.getCurrently() == null){
						auction.setCurrently(bid.getAmount());
					}
					else {
						auction.changeCurrently(bid.getAmount());
					}
					auction_bid_list.add(auction_bidDAO.getBidByID(bid.getId()));
				}
				if (bid_list.size() > auction.getNumberOfBids() -1){
					auction.setNumberOfBids(bid_list.size() +1);
					auctionDAO.editAuction(auction);
				}
				
			}
			request.setAttribute("auction_bid_list", auction_bid_list);
			response.setContentType("text/html;charset=UTF-8");
//			if (auction.getSellerUsersId() != 00) {
//				User userSeller = auctionUser.searchUserById(auction.getSellerUsersId());
//				request.setAttribute("userSeller", userSeller);
//			}
			
			request.setAttribute("auction", auction);
			if (clicked_button.matches("Edit")){
				disp = getServletContext().getRequestDispatcher("/auction_edit.jsp");
			}else{
				disp = getServletContext().getRequestDispatcher("/auction_info.jsp");
			}
			disp.forward(request, response);
		} else {
			disp = getServletContext().getRequestDispatcher("/auction_search.jsp");
			disp.forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
