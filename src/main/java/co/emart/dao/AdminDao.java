package co.emart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.emart.model.AddProduct;
import co.emart.model.Admin;

public class AdminDao {

	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public AdminDao (Connection con) {
		this.con = con;
	}


	public int[] countUserAndOrder() {
		int []countUserArray= new int[2];

		try {
			query = "SELECT COUNT(*) FROM USERREG WHERE USERTYPE='user'";
			pst = con.prepareStatement(query);

			rs=pst.executeQuery();
			while(rs.next()) {
			   countUserArray[0]=rs.getInt(1);
			}
			String query2="select count(*) from userorder";
			pst=con.prepareStatement(query2);
			rs=pst.executeQuery();
			while(rs.next()) {
				countUserArray[1]=rs.getInt(1);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return countUserArray;
	}

	public boolean addCategory(String cName,String desc) {
		 boolean flag=false;

		try {
			query="INSERT INTO PRODUCTCATEGORY(CID,CNAME,DESCRIPTION)VALUES(seq_person.nextval,?,?)";
			pst=con.prepareStatement(query);
			pst.setString(1, cName);
			pst.setString(2, desc);
			pst.executeUpdate();
			flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	//Getting Category for Addproduct form
	public List<Admin> getCategory(){
		List<Admin> category_list=new ArrayList<>();
		try {
			query=" SELECT *FROM PRODUCTCATEGORY";
			pst=con.prepareStatement(query);
			rs=pst.executeQuery();
			while(rs.next()) {
				Admin admin=new Admin();
				admin.setcId(rs.getInt(1));
				admin.setCategoryName(rs.getString(2));
				category_list.add(admin);
			}
		} catch (Exception e) {
		e.printStackTrace();
		}

		return category_list;
	}

	public boolean addProducts(AddProduct addProduct) {
		 boolean addProductStatus=false;

		 try {
			     query="INSERT INTO PRODUCTS(ID,NAME,PRICE,IMAGE,CID,DESCRIPTION) VALUES(seq_person.nextval,?,?,?,?,?)";
			     pst=con.prepareStatement(query);
			     pst.setString(1,addProduct.getpName());
			     pst.setInt(2, addProduct.getpPrice());
			     pst.setString(3, addProduct.getpPic());
			     pst.setInt(4, addProduct.getpCategory());
			     pst.setString(5, addProduct.getpDesc());
			     pst.executeUpdate();
			     addProductStatus=true;
		} catch (Exception e) {
		     e.printStackTrace();
		}


		 return addProductStatus;

	}



}
