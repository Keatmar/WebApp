package servlets.auction;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ConnectionFactory.AuctionDAO;
import ConnectionFactory.AuctionDAOImpl;
import model.Auction;

@WebServlet("/AuctionResults")
public class AuctionResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuctionResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categories = request.getParameter("Categories");
		String name = request.getParameter("SearchName");
		
		RequestDispatcher disp;
		
		if(!categories.equals("")){
			AuctionDAO auctionDAO = new AuctionDAOImpl();
			List<Auction> auctionList = auctionDAO.searchAuctionByCategory(categories);
			System.out.println("a");
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("auctionList",auctionList);
		}
		if(!name.equals("")){
			AuctionDAO auctionDAO = new AuctionDAOImpl();
			List<Auction> auctionList = auctionDAO.searchAuctionByName(name);
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("auctionList",auctionList);
		}
		disp = getServletContext().getRequestDispatcher("/auction_results.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
