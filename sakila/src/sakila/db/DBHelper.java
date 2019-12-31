package sakila.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHelper {
	public static Connection getConnection() throws Exception{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/sakila","root","java1234");
		//Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/e2garden","e2garden","dlwjddnjs12!@");
		return conn;
	}
	
	public static void close(ResultSet rs,Statement stmt, Connection conn) {
		if(rs !=null) {
			try {
				rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(stmt !=null) {
			try {
				stmt.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(conn !=null) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
