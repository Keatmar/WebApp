package servlets.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
//		String method = request.getParameter("method");
//		if (method.equals("Logout")){
			HttpSession session = request.getSession();
			session.setAttribute("username", null);
			session.setAttribute("first_name",null);
			session.setAttribute("last_name",null);
			session.setAttribute("email",null);
			session.setAttribute("phone",null);
			session.setAttribute("address",null);
			session.setAttribute("city",null);
			session.setAttribute("afm",null);
			session.setAttribute("zip",null);
			disp = getServletContext().getRequestDispatcher("/index.jsp");
			disp.forward(request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
