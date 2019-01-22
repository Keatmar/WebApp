package servlets.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.User;


@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
		String fname = request.getParameter("txtFName");
		String lname = request.getParameter("txtLName");
		String username = request.getParameter("txtUsername");
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		String password_repeat = request.getParameter("txtPasswordRepeat");
		String phone;
		if (request.getParameter("txtPhone").equals("/0")) {
			phone = "";
		} else {
			phone = request.getParameter("txtPhone");

		}
		int afm;
		if (request.getParameter("txtAfm").equals("/0")) {
			afm = 0;
		} else {
			afm = Integer.parseInt(request.getParameter("txtAfm"));

		}
		String address = request.getParameter("txtAddress");
		if (address.equals("")) {
			address = "";
		}
		String city = request.getParameter("txtCity");
		if (city.equals("")) {
			city = "";
		}
		int zip = 0;
		if (request.getParameter("txtZip").equals("/0")) {
			zip = 0;
		} else {
			zip = Integer.parseInt(request.getParameter("txtZip"));
		}

		/* Check if passwords is equal */
		if (!password.equals(password_repeat)) {
			return;
		}

		User user = new User();

		/* Create new User */

		user.setFirstName(fname);
		user.setLastName(lname);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setPhone(phone);
		user.setAddress(address);
		user.setAfm(afm);
		user.setActive(false);
		user.setZip(zip);
		user.setCity(city);
		user.setRattingBidder(0);
		user.setRattingSeller(0);
		user.setRole("User");

		UsersDAO userDAO = new UsersDAOImpl();
		userDAO.createUser(user);
		disp = getServletContext().getRequestDispatcher("/need_approve.jsp");
		disp.forward(request, response);
	}



}
