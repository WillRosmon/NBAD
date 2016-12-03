

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String purchaseId = request.getParameter("id");
		
		//look up purchase in Database
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Purchase purchase = new Purchase();
		double amount = 0;
		String companyId = request.getSession().getAttribute("user_id").toString();
		try {
			Double.parseDouble(request.getParameter("amount").toString());
			
		} catch (NumberFormatException e) {
			PrintWriter out = response.getWriter();
			out.println("Amount was not a valid number");
			getServletContext().getRequestDispatcher("/purchase.jsp").forward(request, response);
		}
	}

}
