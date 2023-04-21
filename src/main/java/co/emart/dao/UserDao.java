package co.emart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.emart.model.User;

public class UserDao {

	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDao(Connection con) {
		this.con = con;
	}

	public User userLogin(String email, String password) {
		User user = null;
		try {

			query = "SELECT* FROM USERREG WHERE EMAIL=? AND PASSWORD=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("USERID"));
				user.setName(rs.getString("FIRSTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setUserType(rs.getString("USERTYPE"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return user;

	}

	public boolean userReg(String fname, String lname, long phoneNo, String email, String password,String userType) {
		boolean flag = false;
		try {
			query = "INSERT INTO USERREG(USERID,FIRSTNAME,LASTNAME,PHONENO,EMAIL,PASSWORD,USERTYPE)VALUES(seq_person.nextval,?,?,?,?,?,?)";
			pst = con.prepareStatement(query);
			pst.setString(1, fname);
			pst.setString(2, lname);
			pst.setLong(3, phoneNo);
			pst.setString(4, email);
			pst.setString(5, password);
			pst.setString(6, userType);
			pst.execute();
			flag = true;

		} catch (Exception e) {
			e.getStackTrace();
		}
		return flag;

	}



}
