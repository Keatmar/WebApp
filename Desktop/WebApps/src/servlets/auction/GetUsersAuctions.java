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
import javax.servlet.http.HttpSession;

import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.Auction;
import model.User;

/**
 * Servlet implementation class GetUsersAuctions
 */
@WebServlet("/GetUsersAuctions")
public class GetUsersAuctions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUsersAuctions() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp;
		
		HttpSession session = request.getSession();
		UsersDAO s_userDAO  = new UsersDAOImpl();
		String s_username = (String) session.getAttribute("username");
		User user 	    = s_userDAO.getUserByEmailOrUsername(s_username);
		
		List<Auction> auctions =  user.getAuctions();
		List<Auction> auctionList_with_bids = new ArrayList<Auction>();
		List<Auction> auctionList_without_bids = new ArrayList<Auction>();

		for(Auction auction : auctions){
			System.out.println(auction.getBids().size());
			if(auction.getBids().size() > 0){
				auctionList_with_bids.add(auction); 
			}
			else{
				auctionList_without_bids.add(auction);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("auctionList_without_bids",auctionList_without_bids);
		request.setAttribute("auctionList_with_bids",auctionList_with_bids);
		
		
		disp = getServletContext().getRequestDispatcher("/users_auctions.jsp");
		System.out.println(auctionList_without_bids.size());
		disp.forward(request, response);
//		response.setStatus(200); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
