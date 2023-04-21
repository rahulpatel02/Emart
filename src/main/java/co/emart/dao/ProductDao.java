package co.emart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import co.emart.model.Cart;
import co.emart.model.Product;

public class ProductDao {
	private String query;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ProductDao(Connection con) {
		this.con=con;
	}

	public List<Product> getAllProducts(){
		List<Product> products=new ArrayList<>();

		try {
			query="SELECT *FROM PRODUCTS LEFT JOIN PRODUCTCATEGORY ON PRODUCTS.CID=PRODUCTCATEGORY.CID";
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("ID"));
				row.setName(rs.getString("NAME"));
				row.setPrice(rs.getDouble("PRICE"));
				row.setCategory(rs.getString("CNAME"));
				row.setImage(rs.getString("IMAGE"));
				products.add(row);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		return products;
	}
	// Geting selected product in  cart
	public List<Cart> getCartProducts( ArrayList<Cart> cartList){
		List<Cart> cartProductsList= new ArrayList<>();

		try {
			if(cartList.size() >0) {
				for(Cart item: cartList) {
					query="SELECT *FROM PRODUCTS LEFT JOIN PRODUCTCATEGORY ON PRODUCTS.CID=PRODUCTCATEGORY.CID WHERE ID=?";
					ps=con.prepareStatement(query);
					ps.setInt(1, item.getId());
					rs=ps.executeQuery();
					while(rs.next()) {
						 Cart row= new Cart();
						 row.setId(rs.getInt("ID"));
							row.setName(rs.getString("NAME"));
							row.setPrice(rs.getDouble("PRICE")*item.getQuantity());
							row.setCategory(rs.getString("CNAME"));
							row.setQuantity(item.getQuantity());
							cartProductsList.add(row);

					}
				}
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return cartProductsList;

	}

	//Get Total price which is available in cart

	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum=0;
	try {
		if(cartList.size() >0) {
			for(Cart item:cartList) {
				 query="SELECT PRICE FROM PRODUCTS WHERE ID=?";
				 ps=con.prepareStatement(query);
				 ps.setInt(1, item.getId());
				 rs=ps.executeQuery();
				 while(rs.next()) {
					 sum +=rs.getDouble("PRICE")*item.getQuantity();
				 }
			}

		}

	} catch (Exception e) {
		// TODO: handle exception
	}
		return sum;
	}

	//Getting Ordered product in OrderDao.java

	public Product getSingleProduct(int id) {
		Product orderProduct=null;
		try {

			query="SELECT *FROM PRODUCTS LEFT JOIN PRODUCTCATEGORY ON PRODUCTS.CID=PRODUCTCATEGORY.CID WHERE ID=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				orderProduct=new Product();
				orderProduct.setId(rs.getInt("ID"));
				orderProduct.setName(rs.getString("NAME"));
				orderProduct.setCategory(rs.getString("CNAME"));
				orderProduct.setPrice(rs.getDouble("PRICE"));
				orderProduct.setImage(rs.getString("IMAGE"));
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return orderProduct;
	}
	public List<Product> getCategory(int catId) {
		 List<Product>getCateList=new ArrayList<>();
	
		try {
			query="SELECT *FROM PRODUCTS LEFT JOIN PRODUCTCATEGORY ON PRODUCTS.CID=PRODUCTCATEGORY.CID WHERE PRODUCTS.CID=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, catId);
			rs=ps.executeQuery();
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("ID"));
				row.setName(rs.getString("NAME"));
				row.setPrice(rs.getDouble("PRICE"));
				row.setCategory(rs.getString("CNAME"));
				row.setImage(rs.getString("IMAGE"));
				getCateList.add(row);
			
			
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return getCateList;
	}

}
