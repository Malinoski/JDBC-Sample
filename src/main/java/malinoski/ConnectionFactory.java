package malinoski;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

import malinoski.PropertyValue;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		PropertyValue property = new PropertyValue(); 

		String dbType = property.getPropValue("db_type");
		String dbHost = property.getPropValue("db_host");
		String dbPort = property.getPropValue("db_port"); 
		String dbName = property.getPropValue("db_name");
		String dbUser = property.getPropValue("db_user");
		String dbPassword = property.getPropValue("db_password");
		String dbDriver = property.getPropValue("db_driver");
		
		// Testando o driver
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			//System.out.println("Nao foi encontrado o driver!");
			e.printStackTrace();
			return null;
		}
		//System.out.println("Driver encontrado");

		// Testando conexao
		Connection connection = null;		
		try {
			connection = DriverManager.getConnection("jdbc:"+dbType+"://" + dbHost+ ":" + dbPort + "/" + dbName, dbUser, dbPassword);

		} catch (PSQLException e) {

			System.out.println("Falhou conexao! O banco esta acessivel?");
			// e.printStackTrace();
			return null;

		} catch (SQLException e) {

			System.out.println("Falhou conexao!");
			// e.printStackTrace();
			return null;

		}
		if (connection != null) {
			System.out.println("Conexao estasbelecida");
		} else {
			System.out.println("Falhou conexao! (null)");
		}
		
		return connection;
	}
}