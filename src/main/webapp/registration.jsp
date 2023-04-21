<%@page import="java.util.*"%>
<%@page import="co.emart.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String message=(String)session.getAttribute("message");
    String visibility = "invisible";
    if(message !=null){
     visibility = "visible";
    	session.removeAttribute("message");
    
    }
    
  //For show cart item number in this page
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
    	request.setAttribute("cart_list", cart_list);
    }
    %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
     <%@ include file="include/header.jsp" %> 
    <link href="css/regstyle.css" rel="stylesheet" type="text/css">
    
</head>
<body 	style="background: #f2eeed;">
  
<%@ include file="include/navbar.jsp" %>

    	
 

    
    
    
    <div class="signup-form">
    <form  action="user-reg" method="POST" >
		<h2>Sign Up</h2>
		<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    	 
    	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    	  <symbol id="check-circle-fill" fill="currentColor" viewBox="0 0 16 16">
    	    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z"/>
    	  </symbol>
    	</svg>

    	<div class="alert alert-success d-flex align-items-center <%= visibility %>"   style="height: 2px;" role="alert">
    	  <svg class="bi flex-shrink-0 me-2" width="18" height="18" role="img" aria-label="Success:"><use xlink:href="#check-circle-fill"/></svg>
    	  <div>
    	    Registration Successfully
    	  </div>
    	</div>
		<hr>
		<input type="hidden" name="userType" value="user">
        <div class="form-group">
			<div class="row">
				<div class="col-sm"><input type="text" class="form-control" name="fname" placeholder="First Name" required="required"></div>
				
				<div class="col-sm"><input type="text" class="form-control" name="lname" placeholder="Last Name" required="required"></div>
			</div>        	
        </div>
        
        
          <div class="form-group">
        	<input type="number" class="form-control" name="phoneno" placeholder="Mobile No" required="required">
        </div>
        
        <div class="form-group">
        	<input type="email" class="form-control" name="email" placeholder="Email" required="required">
        </div>
        
		<div class="form-group">
            <input type="password" class="form-control" name="pswrd" placeholder="Password" required="required">
        </div>
		      
       
		<div class="form-group ">
            <button type="submit" class="btn btn-primary btn-lg ">Sign Up</button>
        </div>
        <div class="hint-text">Already have an account? <a href="login.jsp">Login here</a></div>
        <div class="d-flex justify-content-around">
<i class="fab fa-facebook-f"></i>
<i class="fab fa-twitter"></i>
<i class="fab fa-google"></i>
<i class="fab fa-linkedin-in"></i>
<i class="fab fa-github"></i>
</div>
    </form>
	
</div>
    
    

<%@ include file="include/footer.jsp" %>
</body>
</html>