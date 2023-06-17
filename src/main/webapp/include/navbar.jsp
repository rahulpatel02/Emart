<%@page import="co.emart.model.*"%>
<%
User user = (User) session.getAttribute("auth");
%>
<html>
<head>


</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light  " style="background: #3598dc;" id="maincontainer" >
	<div class="container  ">
		<a class="navbar-brand text-white" href="index.jsp">EMart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse float-right" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto ">
			 
				<li class="nav-item"><a class="nav-link text-white " href="index.jsp">Home</a></li>
				
				   <div class="d-flex align-items-center">
    
   
				<li class="nav-item"><a class="nav-link text-white" href="cart.jsp"><i class="fas fa-shopping-cart position-relative "></i>
			<span class= "position-absolute  translate-middle  bg-danger  badge rounded-pill"><span>${cart_list.size()}</span></span>
			<a></li>
				
				
				
				
				
				
				<%--End --%>
				<%
                 if(session.getAttribute("auth") !=null){ %>
                	<% 	if (user.getUserType().equals("admin")){%>
                	 <li class="nav-item"><a class="nav-link text-white" href="admin.jsp">Admin</a></li>
                	 <%} %>
                	 <li class="nav-item"><a class="nav-link text-white" href="order.jsp">Orders</a></li>
     				<li class="nav-item"><a class="nav-link text-white" href="log-out">Logout</a></li>
     				  <li class="nav-item"><a class="nav-link text-white "><%= user.getName() %></a></li>
                <% }else{ %>
                	 <li class="nav-item"><a class="nav-link text-white" href="login.jsp">Login</a></li>
                	  <li class="nav-item"><a class="nav-link text-white" href="registration.jsp">SignUp</a></li>
                	 
                <% }
				%>			
				
			</ul>
		</div>
	</div>
</nav>


</body>
</html>
