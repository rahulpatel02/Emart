
<%@page import="org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse"%>
<%@page import="netscape.javascript.JSObject"%>
<%@page import="co.emart.connection.DbCon"%>
<%@page import="co.emart.dao.ProductDao"%>
<%@page import="jakarta.websocket.Session"%>
<%@page import="java.util.*"%>
<%@page import="co.emart.model.*"%>
<%@page import="com.razorpay.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
HttpSession hs = request.getSession();
User auth = (User) hs.getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
   
//cart product list
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
double total=0.0;

List<Cart> cartProduct = null;
if (cart_list != null) {
	ProductDao pDao = new ProductDao(DbCon.getConnetion());
	//Get Product in cart page(Show product in cart page)
	cartProduct = pDao.getCartProducts(cart_list);
	request.setAttribute("cart_list", cart_list);
	//Getting Total Price
	 total = pDao.getTotalCartPrice(cart_list);
	request.setAttribute("total", total);
	//System.out.println(total);
	 int value1=100;

}
      String  payOrder= (String)request.getAttribute("orderdata");
      if(payOrder !=null){
    	  System.out.println(payOrder +"order1111111111111" );
      }
     //total price in integer
      int total1=(int)total;
     
    // sening payment status on server
   
   
      
%>
<!DOCTYPE html>
<html>

<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<title>Cart Page</title>
<%@ include file="include/header.jsp"%>
<script>
 var xhttp= new XMLHttpRequest();
 var amount = '<%= total1*100 %>';
 var orderId;
 function createOrderId(){
	 xhttp.open("GET","http://localhost:3030/Emart/ordercreateforpay",false);
	 orderId= xhttp.responseText;
	
	 openCheckOut(); 
 }
</script>

<script src="https://checkout.razorpay.com/v1/checkout.js"></script> 
<script>
function openCheckOut(){
	
var options = {
    "key": "rzp_test_KxAQukCVzeaCBz", // Enter the Key ID generated from the Dashboard
    "amount": amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
    "currency": "INR",
    "name": "Emart",
    "description": "Test Transaction",
    "image": "https://media.glassdoor.com/sqll/513776/emart-solutions-squarelogo-1450950971993.png",
    "order_id": orderId, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
    "handler":  function submitForm() {
        document.getElementById("myForm").submit();
    },
    "prefill": {
        "name": "Rahul Patel",
        "email": "rahulpatel02030@gmail.com.com",
        "contact": "7054293711"
    },
    "notes": {
        "address": "Emart "
    },
    "theme": {
        "color": "#3399cc"
    }
};
var rzp1 = new Razorpay(options);
rzp1.on('payment.failed', function (response){
        alert(response.error.code);
        alert(response.error.description);
        alert(response.error.source);
        alert(response.error.step);
        alert(response.error.reason);
        alert(response.error.metadata.order_id);
        alert(response.error.metadata.payment_id);
});
    rzp1.open();
   // e.preventDefault();
};
</script>

</head>
<body>



	<%@ include file="include/navbar.jsp"%>
	
	
	<div class="container">
	
	 <form id="myForm" action="check-out" method="GET" >
	  <div class="form-group">
        	<input type="hidden" class="form-control"  name="totalPrice" value="${total}" placeholder="${total}">
        </div>
        <div class="d-flex py-3">
			<h3>Total Price: $ ${(total>0)?total:0}</h3>
			 <button class="invisible" class="mx-3 btn btn-primary" onClick="submitForm()" type="submit"></button>  
			 
		</div>
      
        
	 </form>
	 <button class="mx-3 btn btn-primary" onClick="createOrderId()">Pay with Razorpay</button>
	<!--	<div class="d-flex py-3">
			<h3>Total Price: $ ${(total>0)?total:0}</h3>
			<a class="mx-3 btn btn-primary" href="check-out">Check Out</a>
		</div>  -->
		
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
				if (cart_list != null) {
					for (Cart c : cartProduct) {
				%>

				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td>$<%=c.getPrice()%></td>
					<td>
						<form action="order-now" method "POST" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId()%>"
								class="form-inpute">
							<div class="from-group d-flex justify-content-between ">
								<a class="btn btn-lm btn-decre"
									href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50 "
									value="<%=c.getQuantity()%>" readonly> <a
									class="btn btn-lm btn-incre"
									href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>


								<button type="submit" class="btn btn-sm btn-success">Buy Now </button>
							</div>
						</form>
					</td>
					<td><a class="btn btn-m btn-danger"
						href="remove-from-cart?id=<%=c.getId()%>">Remove</a></td>
				</tr>

				<%
				}
				}
				%>

			</tbody>

		</table>
	</div>
	<%@ include file="include/footer.jsp"%>
</body>
</html>