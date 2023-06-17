package co.emart.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import co.emart.connection.DbCon;
import co.emart.dao.AdminDao;
import co.emart.model.AddProduct;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AdminOprationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminOprationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();

		String op = request.getParameter("operation");
		if (op.trim().equals("addcategory")) {
			try {
				String cName = request.getParameter("cName");
				String desc = request.getParameter("description");
				AdminDao adminDao = new AdminDao(DbCon.getConnetion());
				boolean categoryStatus = adminDao.addCategory(cName, desc);
				if (categoryStatus) {
					HttpSession hs = request.getSession();
					hs.setAttribute("categoryStatus", "true");
					response.sendRedirect("admin.jsp");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (op.trim().equals("addproduct")) {
			pw.println("enter in add Products");
			try {
				String name = request.getParameter("name");
				int price = Integer.parseInt(request.getParameter("price"));
				String desc = request.getParameter("description");
				int cId = Integer.parseInt(request.getParameter("cId"));
				Part part = request.getPart("imageName");

				AddProduct addProduct = new AddProduct();
				addProduct.setpName(name);
				addProduct.setpPrice(price);
				addProduct.setpDesc(desc);
				addProduct.setpCategory(cId);
				addProduct.setpPic(part.getSubmittedFileName());

				AdminDao aDo = new AdminDao(DbCon.getConnetion());
				Boolean status = aDo.addProducts(addProduct);
				String path = getServletContext().getRealPath("images") + File.separator + "product-image"
						+ File.separator + part.getSubmittedFileName();
				// reading data
				InputStream is = part.getInputStream();
				byte[] data = new byte[is.available()];

				is.read(data);
				// writing data
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(data);
				fos.close();
				is.close();

				System.out.println(path);
				HttpSession hs = request.getSession();
				hs.setAttribute("categoryStatus", "true");
				response.sendRedirect("admin.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
