package co.emart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserCountServlet() {
        super();

    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   PrintWriter pw=response.getWriter();
		   pw.println("Usercount servlet");
	}

}
