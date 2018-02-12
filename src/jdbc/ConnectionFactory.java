package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	
	private static final String DRIVE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/lavacao";
	private static final String USU = "root";
	private static final String PASS = "root";
	
	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
			Class.forName(DRIVE);
			
			conn = DriverManager.getConnection(URL, USU, PASS);
			
			if(conn != null) {
				System.out.println("Conectado!");
			}else {
				System.out.println("Erro ao conectar-se ao banco...");
			}
			
			return conn;
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Drive não encontrado, ou erro ao conectar-se ao banco!\n" + e);
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection conn, Statement stmt) {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}