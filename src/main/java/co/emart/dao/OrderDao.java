package co.emart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.emart.model.Order;
import co.emart.model.Product;

public class OrderDao {
	private Connection con;
    private String query;
    private PreparedStatement ps;
    private ResultSet rs;

    public OrderDao(Connection con) {
    	this.con=con;
    }

    public boolean insertOrder(Order model) {
    	boolean flag=false;
    	try {
			query="INSERT INTO USERORDER(ORDERID,PRODUCTID,USERID,QUANTITY,ORDERDATE)VALUES(seq_person.nextval,?,?,?,?)";
			ps=con.prepareStatement(query);
			ps.setInt(1, model.getId());
			ps.setInt(2, model.getUid());
			ps.setInt(3, model.getQunatity());
			ps.setString(4,model.getDate());
			ps.execute();
			flag=true;
		} catch (Exception e) {
			e.getStackTrace();
		}
    	return flag;
    }

    //Getting Ordered product on Order page
    public List<Order> userOrders(int id){
    	List<Order> list=new ArrayList<>();
    	try {
			query="SELECT * FROM USERORDER WHERE USERID=? ORDER BY ORDERID DESC";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			rs=ps.executeQuery();

			while(rs.next()) {
				Order order=new Order();
				ProductDao productDao= new ProductDao(this.con);
				int productId=rs.getInt("PRODUCTID");

				Product product = productDao.getSingleProduct(productId);
				order.setOrderId(rs.getInt("ORDERID"));
				order.setId(productId);
				order.setQunatity(rs.getInt("QUANTITY"));
				order.setDate(rs.getString("ORDERDATE"));
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice()*rs.getInt("QUANTITY"));
				list.add(order);



			}

		} catch (Exception e) {
			e.getStackTrace();
		}

    	return list;
    }

     //Cancel Order
    public void cancelOrder(int id) {

    	   try {
    		   query="DELETE FROM USERORDER WHERE ORDERID=?";
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {

			e.printStackTrace();
		}
    }
}
