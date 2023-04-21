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


public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
		//  pw.println("Remove form cart servlet");
		 response.setContentType("text/html;charset=UTF-8");
		  try {
			String cartId=request.getParameter("id");
		   if(cartId !=null) {
			   HttpSession hs=request.getSession();
			   ArrayList<Cart> cart_list= (ArrayList<Cart>) hs.getAttribute("cart-list");//Geting Session

			   for(Cart c:cart_list) {
				   if(c.getId()==Integer.parseInt(cartId)) {
					   cart_list.remove(cart_list.indexOf(c)); //removed from cart
					      break;
				   }
			   }
			   response.sendRedirect("cart.jsp");
		   }else {
			   response.sendRedirect("cart.js");
		   }


		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
