package co.emart.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONObject;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import co.emart.model.Cart;
import co.emart.model.User;


public class OrderCreateForPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public OrderCreateForPay() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String ss= request.getParameter("totalPrice");
		   double totalPrice=Double.valueOf(ss);
		   
		   ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			System.out.println(auth);
			if (cart_list != null && auth != null) {
		try {
			var client=new RazorpayClient("rzp_test_KxAQukCVzeaCBz","9NpPoaZHQxHuwNLOLV2u0Opx");
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount",(int) totalPrice*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_1");
               
	// creating new order
			  com.razorpay.Order order1 = client.orders.create(orderRequest);
			  response.getWriter().append(order1.get("id"));
			  System.out.println(order1+"     ordercreatservlet run");
		} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println(e.getMessage());
			}
	}else {
		if (auth == null) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("cart.jsp");
		}
	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
