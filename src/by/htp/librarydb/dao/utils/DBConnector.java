package by.htp.librarydb.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DBConnector {

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	public static Connection getConnection() {

		ResourceBundle resource = ResourceBundle.getBundle("database", Locale.ENGLISH);

		String url = resource.getString("db.url");
		String user = resource.getString("db.user");
		String pass = resource.getString("db.password");

		Connection connection = null;

		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(url, user, pass);
			return connection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
