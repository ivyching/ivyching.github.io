package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private final String url = "jdbc:mysql://localhost:3306/myproject";
	private final String DBAccount = "root";
	private final String DBPassword = "850610";
	private Connection conn = null;

	private DBConnection() {

	}

	public static DBConnection getDB() {
		return new DBConnection();
	}

	public Connection ConnectionDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, DBAccount, DBPassword);

		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		return conn;
	}

	public void close(Connection cn) {
		if (cn != null) {

			try {
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(PreparedStatement ps) {
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Statement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rs, Statement st,Connection cn) {
		close(rs);
		close(st);
		close(cn);
	}
}
