package servlets.messaging;

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

import ConnectionFactory.MessageDAO;
import ConnectionFactory.MessageDAOImpl;
import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.Message;
import model.User;

/**
 * Servlet implementation class UsersInboxMessages
 */
@WebServlet("/UsersInboxMessages")
public class UsersInboxMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersInboxMessages() {
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
		int user_id = (Integer) session.getAttribute("id");
		System.out.println(user_id);
		UsersDAO userDAO = new UsersDAOImpl();
		User user = userDAO.searchUserById(user_id);
		MessageDAO messageDAO = new MessageDAOImpl();
		
		List<Message> inbox_messages = user.getReceivedMessages();
		List<Message> users_inbox_messages = new ArrayList<Message>();
		if(!inbox_messages.isEmpty()){
			for(Message  message : inbox_messages){
				users_inbox_messages.add(messageDAO.searchMessageById(message.getId()));
			}
		}
		request.setAttribute("users_inbox_messages", users_inbox_messages);
		request.setAttribute("type", "Inbox");
		disp = getServletContext().getRequestDispatcher("/users_messages.jsp");
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
