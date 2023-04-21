<%@page import="co.emart.dao.AdminDao"%>
<%@page import="co.emart.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page import="co.emart.connection.DbCon"%>
<%@page import="co.emart.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
          HttpSession hs=request.getSession();
      User auth=(User)hs.getAttribute("auth");
        if(auth !=null){
        	request.setAttribute("auth", auth);
        }
        ProductDao pd=new ProductDao(DbCon.getConnetion());
        List<Product> products=null;
                     products=pd.getAllProducts();
        
        //Filter product category wise
      
        String categoryId=  request.getParameter("category");
      
       
        try{
        
        if(categoryId==null||categoryId.equals("all")){
        	  products= pd.getAllProducts();
        	 
        }else{
        	int catId=Integer.parseInt(categoryId);
        	
        	products=pd.getCategory(catId);
        	
        }
        }catch(NullPointerException n){
        	n.printStackTrace();
        }
       

        
       //For showing cart item number in this page(nav Bar)
 ArrayList<Cart> cart_list =(ArrayList<Cart>)session.getAttribute("cart-list");
         if(cart_list != null){
        	request.setAttribute("cart_list", cart_list);
         }
         
         //Getting Category
         AdminDao adminDao=new AdminDao(DbCon.getConnetion());
         List<Admin> categoryList=adminDao.getCategory();
    %>
<!DOCTYPE html>
<html>
<head>

<title>Welcome to EMart</title>
<%@ include file="include/header.jsp" %> 
<style>

</style>
</head>
<body>
<%@ include file="include/navbar.jsp" %>
 <div class="container-fluid " >
 <div class="row">
  
 <%--Categroy wise filter bar --%>
 

 
  <div class="col-2">
 <div class="row mx-auto" style="margin-top:200px;">
 
  <a href="index.jsp?category=all" class="list-group-item list-group-item-action active">All Products</a>
  
 
  <% for(Admin a:categoryList){%>
 
  <a href="index.jsp?category=<%=a.getcId() %>" class="list-group-item ">
    <%=a.getCategoryName() %>
  </a>
  
   <% } %>

 
 </div>
 </div>
 <div class="col-10">
 <%--All Products --%>
 
 <div class="card-header my-3">Products</div>

 
 <div class="row">
 <%
   if(!products.isEmpty()){
	   for(Product p:products){
		   %>
		   <div class="col-md-3">
		   <div class="card my-3" style="width: 18rem;height:auto">
		    <img src="images/product-image/<%= p.getImage() %>" class="card-img-top" style="max-height:400px; width:auto;"  alt="...">
		    <div class="card-body">
		      <h5 class="card-title"><%= p.getName() %></h5>
		      <h6 class="price">Price: $<%= p.getPrice() %></h6>
		      <h6>Category:<%= p.getCategory() %></h6>
		      <div class="mt-3 d-flex justify-content-between">
		      <a href="add-to-cart?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
		       <a class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
		    </div>
		    </div>
		  </div>
		   </div>
	<% }
   }
 %>
</div>
 </div>
 </div>
 </div>

 </div>
<%-- <% out.print(products.toString());//for Dibuging --%>
<%--<% out.print(DbCon.getConnetion()); --%>
		 
<%@ include file="include/footer.jsp" %>
</body>
</html>