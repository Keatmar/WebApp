package servlets.messaging;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;

//import model.User;

/**
 * Servlet implementation class New_Message
 */
@WebServlet("/New_Message")
public class New_Message extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public New_Message() {
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
		String receiver_id = request.getParameter("receiver_id");
		System.out.println(receiver_id);
		int receiverId = Integer.parseInt(receiver_id);
	
		model.User receiver = s_userDAO.searchUserById(receiverId);
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("receiver",receiver);
		
		disp = getServletContext().getRequestDispatcher("/new_message.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
