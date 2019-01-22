package servlets.messaging;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import model.Category;
import model.Message;
import model.User;

/**
 * Servlet implementation class DeleteMesage
 */
@WebServlet("/DeleteMessage")
public class DeleteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMessage() {
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
		RequestDispatcher disp;
		Map<String, String[]> MessageInfo = request.getParameterMap();

		HttpSession session = request.getSession();
		UsersDAO userDAO  = new UsersDAOImpl();
		
		String message_id = Arrays.asList(MessageInfo.get("message_id")).get(0);
		System.out.println(message_id);
		
		
		MessageDAO message_DAO = new MessageDAOImpl();
		Message to_delete = message_DAO.searchMessageById(Integer.valueOf(message_id));
		
		User sender = userDAO.getUserByEmailOrUsername(to_delete.getSender().getUsername());
		User receiver = userDAO.getUserByEmailOrUsername(to_delete.getReceiver().getUsername());
		
		receiver.removeReceivedMessage(to_delete);
		sender.removeSendedMessage(to_delete);
		userDAO.editUser(receiver);
		userDAO.editUser(sender);

		
		to_delete.setSender(null);
		to_delete.setReceiver(null);
		message_DAO.editMessage(to_delete);
		message_DAO.deleteMessage(to_delete.getId());
		response.setStatus(200);
	}

}
