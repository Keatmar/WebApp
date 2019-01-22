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
 * Servlet implementation class UsersSendedMessages
 */
@WebServlet("/UsersSendedMessages")
public class UsersSendedMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersSendedMessages() {
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
		
		UsersDAO userDAO = new UsersDAOImpl();
		User user = userDAO.searchUserById(user_id);
		
		MessageDAO messageDAO = new MessageDAOImpl();
		
		List<Message> sended_messages = user.getSendedMessages();
		List<Message> users_sended_messages = new ArrayList<Message>();
		if(!sended_messages.isEmpty()){
			for(Message  message : sended_messages){
				users_sended_messages.add(messageDAO.searchMessageById(message.getId()));
			}
		}
		request.setAttribute("users_sended_messages", users_sended_messages);
		request.setAttribute("type", "sended");
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
