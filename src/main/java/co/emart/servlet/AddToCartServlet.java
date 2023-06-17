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

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter pw = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<>();
			int id = Integer.parseInt(request.getParameter("id"));

			Cart cm = new Cart();
			cm.setId(id);
			cm.setQuantity(1);
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

			if (cart_list == null) {
				cartList.add(cm);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			} else {

				cartList = cart_list;
				boolean exist = false; // for checking product is already exist or not
				for (Cart c : cart_list) {
					if (c.getId() == id) {
						exist = true;
						pw.println(
								"<html><body><center><h2>Item is already exist in Cart.<a href=cart.jsp>Go To Cart Page</a></h2></center></body></html>");
					}
				}
				if (!exist) { // if product id not exist add into the CartList
					cartList.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
//			   for(Cart c: cart_list) { //for cheking how many product added
//				   pw.println(c.getId());
//			   }

		}
	}

}
