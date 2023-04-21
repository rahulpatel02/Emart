package co.emart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import jakarta.servlet.http.HttpSession;


public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OrderNowServlet() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     PrintWriter pw=response.getWriter();


		     try {
		    	 SimpleDateFormat dateFormate=new SimpleDateFormat("yyyy-MM-dd");
		    	 Date date= new Date();
				     User auth=(User) request.getSession().getAttribute("auth");  //geting user session
				     if(auth !=null) {
				    	 String productId=request.getParameter("id");
				    	 int quantity= Integer.parseInt(request.getParameter("quantity"));
				    	 if(quantity <=0) {
				    		 quantity =1;
				    	 }
				    	 Order productOrder=new Order();
				    	  productOrder.setId(Integer.parseInt(productId));
				    	  productOrder.setQunatity(quantity);
				    	  productOrder.setUid(auth.getId());
				    	  productOrder.setDate(dateFormate.format(date));

				    	  OrderDao orderDao =new OrderDao(DbCon.getConnetion());
				    	   boolean result =orderDao.insertOrder(productOrder);
				    	   if(result) {
				    		   HttpSession hs=request.getSession();
				    		         ArrayList<Cart> cart_list=(ArrayList<Cart>) hs.getAttribute("cart-list");
				    		         if(cart_list !=null) {
				    		         for(Cart c:cart_list) {
				    					   if(c.getId()==Integer.parseInt(productId)) {
				    						   cart_list.remove(cart_list.indexOf(c)); //removed from cart
				    						      break;
				    					   }
				    				   }
				    		         }
				    		   response.sendRedirect("order.jsp");
				    	   }else {
							pw.println("order Failed");
						}

				     }else {
				    	 response.sendRedirect("login.jsp");
				     }
			} catch (Exception e) {
				e.getStackTrace();
			}
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
