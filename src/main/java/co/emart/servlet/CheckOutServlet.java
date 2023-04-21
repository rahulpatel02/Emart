package co.emart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.emart.connection.DbCon;
import co.emart.dao.OrderDao;
import co.emart.model.Cart;
import co.emart.model.Order;
import co.emart.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckOutServlet
 */
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		PrintWriter pw=response.getWriter();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
		ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		User auth = (User) request.getSession().getAttribute("auth");
		if(cart_list != null && auth!=null) {
			for(Cart c:cart_list) {
				Order order = new Order();
				order.setId(c.getId());
				order.setUid(auth.getId());
				order.setQunatity(c.getQuantity());
				order.setDate(formatter.format(date));

				OrderDao oDao = new OrderDao(DbCon.getConnetion());
				boolean result = oDao.insertOrder(order);
				if(!result) break;
			}
			cart_list.clear();
			response.sendRedirect("order.jsp");
		}else {
			if(auth==null) {
				response.sendRedirect("login.jsp");
			}else {
			response.sendRedirect("cart.jsp");
			}
		}
		}
	    catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
