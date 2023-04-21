<%@page import="co.emart.connection.DbCon"%>
<%@page import="co.emart.dao.*"%>
<%@page import="co.emart.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
      <%
          HttpSession hs=request.getSession();
      User auth=(User)hs.getAttribute("auth");
      List<Order>ordered_list=null;
    
        if(auth !=null){
        	request.setAttribute("auth", auth);
        	// Getting ordered item by specific user
        	OrderDao orderedDao=new OrderDao(DbCon.getConnetion());
        	ordered_list=orderedDao.userOrders(auth.getId());
        	
        }else{
        	response.sendRedirect("login.jsp");
        }
       
      
        //For show cart item number in this page
        ArrayList<Cart> cart_list =(ArrayList<Cart>)session.getAttribute("cart-list");
                if(cart_list != null){
               	request.setAttribute("cart_list", cart_list);
                }
    %>
<!DOCTYPE html>
<html>
<head>

<title>Orders Page</title>
<%@ include file="include/header.jsp" %> 
</head>
<body>
<%@ include file="include/navbar.jsp" %>

<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(ordered_list !=null){
				
				for(Order o:ordered_list){%>
					<tr>
				

						<td><%=o.getDate() %></td>
						<td><%=o.getName() %></td>
						<td><%=o.getCategory() %></td>
						<td><%=o.getQunatity() %></td>
						<td><%=o.getPrice() %></td>
						<td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel Order</a></td>
					</tr>
				<%}
			}
			%>
			
			</tbody>
		</table>
	</div>

<%@ include file="include/footer.jsp" %>
<%--<% out.print(ordered_list.toString()); %> --%>

</body>
</html>