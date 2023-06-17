package co.emart.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {

	private static Connection connection = null;

	public static Connection getConnetion() throws ClassNotFoundException, SQLException {
		if (connection == null) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1539:orcl", "EMART", "1234");
		}
		return connection;
	}

}
