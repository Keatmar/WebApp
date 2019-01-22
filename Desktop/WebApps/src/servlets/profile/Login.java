package servlets.profile;

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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher disp;
		String username = request.getParameter("txtUserName");
		String password = request.getParameter("txtPassword");

		UsersDAO userDAO = new UsersDAOImpl();
		User user = userDAO.getUserByEmailOrUsername(username);
		/* user Login */

		if (user != null) {
			/* Hold session */
			HttpSession session = request.getSession();
			session.setAttribute("id", user.getId());
			session.setAttribute("username", user.getUsername());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("first_name", user.getFirstName());
			session.setAttribute("last_name", user.getLastName());
			session.setAttribute("email", user.getEmail());
			session.setAttribute("phone", user.getPhone());
			session.setAttribute("address", user.getAddress());
			session.setAttribute("role", user.getRole());
			session.setAttribute("city", user.getCity());
			session.setAttribute("afm", user.getAfm());
			session.setAttribute("zip", user.getZip());
			session.setAttribute("password", user.getPassword());
			session.setAttribute("new_inbox", user.getNewInboxMessages());

			if (user.getActive() == false) {
				disp = getServletContext().getRequestDispatcher("/need_approve.jsp");
				disp.forward(request, response);
				// response.sendRedirect("http://localhost:8080/WebApps/need_approve.jsp");
			} else {
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
				disp.forward(request, response);
				// response.sendRedirect("http://localhost:8080/WebApps");
			}
			/* user do not login */
		} else {
			disp = getServletContext().getRequestDispatcher("/login.jsp");
			disp.forward(request, response);
		}

	}

}
