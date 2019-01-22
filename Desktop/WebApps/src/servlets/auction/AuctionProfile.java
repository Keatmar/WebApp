package servlets.auction;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ConnectionFactory.AuctionDAO;
import ConnectionFactory.AuctionDAOImpl;
import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.Auction;
import model.User;

/**
 * Servlet implementation class AuctionProfile
 */
@WebServlet("/AuctionProfile")
public class AuctionProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuctionProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
		String auctionId = request.getParameter("Name");
		String clicked_button = request.getParameter("form_button");
		
		if (!auctionId.equals("")) {
			int id = Integer.parseInt(auctionId);

			AuctionDAO auctionDAO = new AuctionDAOImpl();
			Auction auction = auctionDAO.searchAuctionById(id);
			UsersDAO auctionUser = new UsersDAOImpl();

			response.setContentType("text/html;charset=UTF-8");
			if (auction.getSellerUsersId() != 00) {
				User userSeller = auctionUser.searchUserById(auction.getSellerUsersId());
				request.setAttribute("userSeller", userSeller);
			}
			
			request.setAttribute("auction", auction);
			
			disp = getServletContext().getRequestDispatcher("/auction_profile.jsp");
			disp.forward(request, response);
		} else {
			disp = getServletContext().getRequestDispatcher("/auction_search.jsp");
			disp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
