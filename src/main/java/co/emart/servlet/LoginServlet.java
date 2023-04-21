package co.emart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import co.emart.connection.DbCon;
import co.emart.dao.AdminDao;
import co.emart.dao.UserDao;
import co.emart.model.Admin;
import co.emart.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   response.sendRedirect("login.jsp");
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		  String s1=request.getParameter("email");
		  String s2=request.getParameter("password");

		  try {
			UserDao udao=new UserDao(DbCon.getConnetion());
			   User user=udao.userLogin(s1, s2);
			   HttpSession hs=request.getSession();

			   if(user != null) {
				  hs.setAttribute("auth", user);

				  if(user.getUserType().equals("admin")) {
					    int[] arr;
					      Admin admin=new Admin();
					      AdminDao adminDao=new AdminDao(DbCon.getConnetion());
					      //Getting Numbers of users & orders
					     arr=adminDao.countUserAndOrder();
					     admin.setUserCounts(arr[0]);
					     admin.setOrederCount(arr[1]);

					       hs.setAttribute("user-count", admin);
					  response.sendRedirect("admin.jsp");

				  }else if(user.getUserType().equals("user")) {
					response.sendRedirect("index.jsp");
				}else {
					pw.println("We have not identify the user");
				}


			   }else {
				   hs.setAttribute("message", "notlogin");
				   response.sendRedirect("login.jsp");
				//pw.println("incorret username /password");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}


	}

}
