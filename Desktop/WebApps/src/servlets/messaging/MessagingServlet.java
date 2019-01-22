package servlets.messaging;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
 * Servlet implementation class Messaging
 */
@WebServlet("/MessagingServlet")
public class MessagingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessagingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher disp;
		Map<String, String[]> MessageInfo = request.getParameterMap();

		HttpSession session = request.getSession();
		UsersDAO s_userDAO  = new UsersDAOImpl();
		String s_username = (String) session.getAttribute("username");	
		
		User sender 	    = s_userDAO.getUserByEmailOrUsername(s_username);
		
		UsersDAO r_userDAO 	= new UsersDAOImpl();
		String r_user_email = Arrays.asList(MessageInfo.get("receiver_input")).get(0);
		System.out.println(r_user_email);
		User receiver 		= r_userDAO.getUserByEmailOrUsername(r_user_email);
		
		
		String message_text = Arrays.asList(MessageInfo.get("message_text")).get(0);
		String subject 		= Arrays.asList(MessageInfo.get("subject")).get(0);
		Date created		= new Date(); 
		Date updated 		= new Date();
		
		
		Message new_message = new Message();
		new_message.setDateCreated(created);
		new_message.setDateUpdated(updated);
		new_message.setText(message_text);
		new_message.setSubject(subject);
		new_message.setIsRead(false);
		new_message.setSender(sender);
		new_message.setReceiver(receiver);
		
		
		sender.addSendedMessage(new_message);
		sender.setNewSendedMessages();
		
		receiver.addReceivedMessage(new_message);
		receiver.setNewInboxMessages();
		
		
//		TODO creation of MessagesDAO in connection factory
		MessageDAO message = new MessageDAOImpl();
		message.createMessage(new_message);
		
		UsersDAO sender_DAO = new UsersDAOImpl();
		sender_DAO.editUser(sender);

		UsersDAO receiver_DAO = new UsersDAOImpl();
		receiver_DAO.editUser(receiver);
		
//		disp = getServletContext().getRequestDispatcher("/inboxmessages.jsp");
//		disp.forward(request, response);
		
		// return success
		response.setStatus(200); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
//		RequestDispatcher disp;
//		
//		disp = getServletContext().getRequestDispatcher("/messaging.jsp");
//		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
