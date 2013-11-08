package model.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static String server = "localhost";
    private static String port = "3306";              
    private static String schema = "cellar_schema";
    private static String username = "root";
    private static String password = "mysql";
    
    public static Connection getConnection(){		
		
		Connection connection = null;
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }
		
		String url = "jdbc:mysql://" + server + ":" + port + "/" + schema;
		
		try {
			connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return connection;
	}
	
	
	/*Getters e Setters para página de configuração*/
	
	public static void setPort(String port) {
		ConnectionFactory.port = port;
	}

	public static void setServer(String server) {
		ConnectionFactory.server = server;
	}

	public static String getSchema() {
		return schema;
	}
	
	public static void setSchema(String schema) {
		ConnectionFactory.schema = schema;
	}

	public static void setUsername(String username) {
		ConnectionFactory.username = username;
	}

	public static void setPassword(String password) {
		ConnectionFactory.password = password;
	}	
	
}
