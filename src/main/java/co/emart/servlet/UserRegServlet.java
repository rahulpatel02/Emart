package co.emart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import co.emart.connection.DbCon;
import co.emart.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();
		String userType = request.getParameter("userType");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		long phoneNo = Long.parseLong(request.getParameter("phoneno"));
		String email = request.getParameter("email");
		String password = request.getParameter("pswrd");

		try {
			UserDao regDao = new UserDao(DbCon.getConnetion());
			boolean flag = regDao.userReg(fname, lname, phoneNo, email, password, userType);
			if (flag) {
				// pw.println("Registration successufully");
				HttpSession hs = request.getSession();
				hs.setAttribute("message", hs.getId());
				response.sendRedirect("registration.jsp");
				return;
				// pw.println(hs.getAttribute("message"));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
