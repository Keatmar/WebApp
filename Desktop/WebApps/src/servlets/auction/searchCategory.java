package servlets.auction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ConnectionFactory.CategoriesDAO;
import ConnectionFactory.CategoriesDAOImpl;
import model.Categories;

/**
 * Servlet implementation class searchCategory
 */
@WebServlet("/searchCategory")
public class searchCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public searchCategory() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parent = request.getParameter("parent");
		int grade = Integer.parseInt(request.getParameter("grade"));
		CategoriesDAO categoriesDAO = new CategoriesDAOImpl();
		List<Categories> clist = categoriesDAO.findCategoriesByGrade(grade+1,parent);
		List<String> obj = new ArrayList<String>();
		for(Categories categoryName : clist){
			obj.add(categoryName.getName());
		}
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	    response.getWriter().print(obj);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
