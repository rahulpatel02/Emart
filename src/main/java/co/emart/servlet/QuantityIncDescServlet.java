package co.emart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import co.emart.model.Cart;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QuantityIncDescServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		try {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			// Geting session
			HttpSession hs = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) hs.getAttribute("cart-list");
			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (Cart c : cart_list) {

						if (c.getId() == id) {
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}

				if (action.equals("dec")) {
					for (Cart c : cart_list) {

						if (c.getId() == id && c.getQuantity() > 1) {
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
					response.sendRedirect("cart.jsp");
				}

			} else {
				response.sendRedirect("cart.jsp");
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
