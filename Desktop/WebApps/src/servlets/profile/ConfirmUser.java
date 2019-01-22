package servlets.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ConnectionFactory.UsersDAOImpl;


@WebServlet("/ConfirmUser")
public class ConfirmUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConfirmUser() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		String id = request.getParameter("id");
		UsersDAOImpl user = new UsersDAOImpl();
		user.confirmUser(Integer.parseInt(id));
		disp = getServletContext().getRequestDispatcher("/admin.jsp");
		disp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
