package servlets.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ConnectionFactory.UsersDAO;
import ConnectionFactory.UsersDAOImpl;
import model.User;

@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Edit() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher disp;
		HttpSession session = request.getSession();

		int id = (Integer) session.getAttribute("id");
		String fname =request.getParameter("txtFName");
		if (fname.isEmpty()) {
			fname = (String) session.getAttribute("first_name");
		} else {
			fname = request.getParameter("txtFName");
		}
		
		String lname = request.getParameter("txtLName");
		if (lname.isEmpty()) {
			lname = (String) session.getAttribute("last_name");
		}else{
			lname = request.getParameter("txtLName");
		}
		
		String username = request.getParameter("txtUsername");
		if (username.isEmpty()) {
			username = (String) session.getAttribute("username");
		}else{
			username = request.getParameter("txtUsername");
		}
		
		String email = request.getParameter("txtEmail");
		if (email.isEmpty()) {
			email = (String) session.getAttribute("email");
		}else{
			email = request.getParameter("txtEmail");
		}
		
		String password = request.getParameter("txtPassword");
		if (password.isEmpty()) {
			password = (String) session.getAttribute("password");
		}else{
			password = request.getParameter("txtPassword");
		}
		

		String phone = request.getParameter("txtPhone");
		
		if (phone.isEmpty()) {
			phone = (String) session.getAttribute("phone");
		}
		
		String afm_ = request.getParameter("txtAfm");
		int afm;
		if (afm_.isEmpty()) {
			afm = (Integer) session.getAttribute("afm");
		} else {
			afm = Integer.parseInt(afm_);

		}

		String address = request.getParameter("txtAddress");
		if (address.isEmpty()) {
			address = (String) session.getAttribute("address");
		}else{
			address = request.getParameter("txtAddress");
		}
		
		String city = request.getParameter("txtCity");
		if (city.isEmpty()) {
			city = (String) session.getAttribute("city");
		}else{
			city = request.getParameter("txtCity");
		}
		
		String zip_ = request.getParameter("txtZip");
		int zip;
		if (zip_.isEmpty()) {
			zip = (Integer) session.getAttribute("zip");
		} else {
			zip = Integer.parseInt(zip_);
		}

		User user = new User();
		user.setId(id);
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

		UsersDAO users = new UsersDAOImpl();
		users.editUser(user);
		
		/*	Hold Session	*/
		session.setAttribute("id",user.getId());
		session.setAttribute("username", user.getUsername());
		session.setAttribute("password",user.getPassword());
		session.setAttribute("first_name",user.getFirstName());
		session.setAttribute("last_name",user.getLastName());
		session.setAttribute("email",user.getEmail());
		session.setAttribute("phone",user.getPhone());
		session.setAttribute("address",user.getAddress());
		session.setAttribute("city",user.getCity());
		session.setAttribute("afm",user.getAfm());
		session.setAttribute("zip",user.getZip());
		disp = getServletContext().getRequestDispatcher("profile.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
