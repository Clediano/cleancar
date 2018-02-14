package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectionFactory {

	private static final String DRIVE = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://";
	public static String USU = "";
	public static String PASS = "";

	public Connection getConnection() {

		Connection conn = null;

		try {
			Class.forName(DRIVE);

			conn = DriverManager.getConnection(URL, USU, PASS);

			if (conn != null) {
				System.out.println("Conectado!");
			} else {
				System.out.println("Erro ao conectar-se ao banco...");
			}

			return conn;

		} catch (ClassNotFoundException | SQLException e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("Caminho do banco não informado ou inválido!");
				alert.setContentText("Conexão não efetuada!");
				alert.setTitle("Verifique os dados informados.");
				alert.show();	
		}
		return null;
	}

public static boolean testConnection(String drive, String url, String usu, String pass) {
		
		Connection conn = null;
		
		try {
			Class.forName(drive);
			
			conn = DriverManager.getConnection(url, usu, pass);
			
			if(conn != null) {
				System.out.println("Conectado!");
				return true;
			}else {
				System.out.println("Erro ao conectar-se ao banco...");
				return false;
			}
		}catch (Exception e) {
			System.out.println(e);
			return false;
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