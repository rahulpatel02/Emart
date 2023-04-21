<%@page import="co.emart.connection.DbCon"%>
<%@page import="co.emart.dao.ProductDao"%>
<%@page import="jakarta.websocket.Session"%>
<%@page import="java.util.*"%>
<%@page import="co.emart.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%
          HttpSession hs=request.getSession();
      User auth=(User)  hs.getAttribute("auth");
        if(auth !=null){
        	request.setAttribute("auth", auth);
        }
        
        //cart product list
        ArrayList<Cart> cart_list =(ArrayList<Cart>)session.getAttribute("cart-list");
        
        List<Cart> cartProduct =null;
        if(cart_list != null){
        	ProductDao pDao= new ProductDao(DbCon.getConnetion());
        	//Get Product in cart page(Show product in cart page)
        	cartProduct= pDao.getCartProducts(cart_list);
        	request.setAttribute("cart_list", cart_list);
        	//Geting Total Price
        	double total= pDao.getTotalCartPrice(cart_list);
        	request.setAttribute("total",total);
        	
        }
    %>
<!DOCTYPE html>
<html>

<style type="text/css">
.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>
<head>

<title>Cart Page</title>
<%@ include file="include/header.jsp" %> 
</head>
<body>
<%@ include file="include/navbar.jsp" %>
<div class="container">
<div class="d-flex py-3"><h3>Total Price: $ ${(total>0)?total:0} </h3>
<a class="mx-3 btn btn-primary" href="check-out">Check Out</a>
</div>
<table class="table table-loght">
<thead>
<tr>
<th scope="col">Name</th>
<th scope="col">Category</th>
<th scope="col">Price</th>
<th scope="col">Buy Now</th>
<th scope="col">Cancel</th>
</tr>
</thead>
<tbody>
<%

if(cart_list != null){
	for( Cart c:cartProduct){ %>
		
		<tr>
<td><%= c.getName() %></td>
<td><%= c.getCategory() %></td>
<td>$<%= c.getPrice() %></td>
<td>
  <form action="order-now" method "POST" class="form-inline">
  <input type="hidden" name="id" value="<%= c.getId() %>" class="form-inpute">
  <div class="from-group d-flex justify-content-between w-50">
  <a class="btn btn-lm btn-decre" href="quantity-inc-dec?action=dec&id=<%= c.getId() %>"><i class="fas fa-minus-square"></i></a>
  <input type="text" name="quantity" class="form-control w-50 " value="<%= c.getQuantity() %>" readonly>
    <a class="btn btn-lm btn-incre" href="quantity-inc-dec?action=inc&id=<%= c.getId() %>"><i class="fas fa-plus-square"></i></a>
  
  
  <button type="submit" class="btn btn-primary bnt-sm w-25 "> Buy Now</button>
  </div>
  </form>	
</td>
<td><a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%= c.getId() %>">Remove</a></td>
</tr>
		
	<% }
}
%>

</tbody>

</table>
</div>
<%@ include file="include/footer.jsp" %>
</body>
</html>