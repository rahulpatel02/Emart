<%@page import="co.emart.connection.DbCon"%>
<%@page import="co.emart.dao.AdminDao"%>
<%@page import="co.emart.model.*"%>
<%@page import="java.util.*"%>
<%@page import="co.emart.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// Admin authandication
User user1 = (User) session.getAttribute("auth");
if (user1 == null) {
	out.print("you arenot login");
	return;
} else if (user1.getUserType().equals("user")) {
	out.print("you are not Admin");
	return;

}
//CountUser
Admin userCount = (Admin) session.getAttribute("user-count");

//For Showing Status of Category Add or Not 

String cStatus = (String) session.getAttribute("categoryStatus");
String visibility = "invisible";
if (cStatus != null) {
	visibility = "visible";
	session.removeAttribute("categoryStatus");
}
//For Getting Category Id & Nmae
AdminDao adminDao = new AdminDao(DbCon.getConnetion());
List<Admin> category_list = adminDao.getCategory();

//For show cart item number in this page
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<%@ include file="include/header.jsp"%>
<link href="css/admin.css" rel="stylesheet" type="text/css">


</head>
<body>
	<%@ include file="include/navbar.jsp"%>
	<div class="container " calss="admin">
		<h1>Admin Dashboard</h1>

		<%--Status --%>

		<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    	 
    	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    	  <symbol id="check-circle-fill" fill="currentColor"
					viewBox="0 0 16 16">
    	    <path
					d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z" />
    	  </symbol>
    	</svg>

    	<div
				class="alert alert-success d-flex align-items-center <%=visibility%>"
				style="height: 2px;" role="alert">
    	  <svg class="bi flex-shrink-0 me-2" width="18" height="18"
					role="img" aria-label="Success:">
					<use xlink:href="#check-circle-fill" /></svg>
    	  <div>
    	      Successfully Add
    	  </div>
    	</div>
      <%--Status togle close --%>
	
		<%--First row --%>
		<div class="row mt-3 ">

			<div class="col-md-4 h-50 ">
				<div class="card">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid"
									src="images/admin/profile.png">
						</div>
						<h1><%=userCount.getUserCounts()%></h1>
						<h1>Users</h1>

					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid"
									src="images/admin/new-product.png">
						</div>

						<h1>31654654</h1>
						<h1>Category</h1>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="card">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 100px" class="img-fluid"
									src="images/admin/menu.png">
						</div>

						<h1><%=userCount.getOrederCount()%></h1>
						<h1>Orders</h1>
					</div>
				</div>
			</div>
		</div>
		<%--Second Row --%>

		<div class="row mt-3 ">

			<div class="col-md-6 h-50 ">
				<div class="card" data-toggle="modal" data-target="#add">
					<div class="card-body text-center">

						<div class="container">
							<img style="max-width: 100px" class="img-fluid"
									src="images/admin/add-product.png">
						</div>

						<h1>31654654</h1>
						<h1>Add Product</h1>

					</div>
				</div>
			</div>

			<div class="col-md-6">
			    <%--Model add here --%>
				<div class="card" data-toggle="modal" data-target="#e">
		
					<div class="card-body text-center">

						<div class="container">
							<img style="max-width: 100px" class="img-fluid"
									src="images/admin/category.png">
						</div>
						<h1>31654654</h1>
						<h1>Add Category</h1>
					</div>
				</div>
			</div>


		</div>

		<%-- model for Add Category (start) --%>
		


<!-- Modal -->
<div class="modal fade" id="e" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Fill Category details</h5>
        <button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      
      
      <div class="modal-body">
   
       
        <form action="admin-opration" method="POST">
<input type="hidden" name="operation" value="addcategory">
		
        <div class="form-group mt-3">
        	<input type="text" class="form-control" name="cName"
										placeholder="Category Name" required="required">
        </div>
        
		<div class="form-group mt-3">
            <textarea type="text" style="height: 200px"
										class="form-control" name="description"
										placeholder="Description" required="required"></textarea>
        </div>
		      
       
		<div class="container text-center mt-3">
            <button type="submit" class="btn btn-primary btn-sm ">Add Category</button>
        </div>
       
     

    </form>
    </div>
  </div>
</div>
</div>

		<%--Add Category Model (End) --%>
		
		
		<%--Add Product Mode Start --%>
		
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Add Products</h5>
        <button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      
      <div class="modal-body">
   
       
        <form action="admin-opration" method="POST"
								enctype="multipart/form-data">

		<input type="hidden" name="operation" value="addproduct">
        <div class="form-group mt-3 ">
        	<input type="text" class="form-control" name="name"
										placeholder="Product Name" required="required">
        </div>
        
         <div class="form-group mt-3">
        	<input type="text" class="form-control" name="price"
										placeholder=" price" required="required">
        </div>
        
        
        
		<div class="form-group mt-3">
            <textarea type="text" style="height: 200px"
										class="form-control" name="description"
										placeholder="Description" required="required"></textarea>
        </div>
        
        	  <div class="form-group mt-3">
        <select name="cId" class="form-select">
        <option>Category</option>
        <%
        for (Admin a : category_list) {
        %>
        
        
        <option value=<%=a.getcId()%>><%=a.getCategoryName()%></option>
        
       
        
        
       <%
                                      }
                                      %>
         </select>
        </div>
       
        
         <div class="form-group mt-3">
        	<input type="file" class="form-control" name="imageName"
										placeholder="image" required="required">
        </div>
		      
       
		<div class="container text-center mt-3">
            <button type="submit" class="btn btn-primary btn-sm ">Add Product</button>
        </div>
       
     

    </form>
    </div>
    </div>
  </div>
</div>
		
		<%--Add Product Model End --%>
		

	<%

			%>
<%@ include file="include/footer.jsp"%>
		









</body>
</html>