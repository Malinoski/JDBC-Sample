package malinoski.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

import malinoski.properties.PropertyValue;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		Database database = getDatabase();
		
		// Testando o driver
		try {
			Class.forName(database.getDbDriver());
		} catch (ClassNotFoundException e) {
			//System.out.println("Nao foi encontrado o driver!");
			e.printStackTrace();
			return null;
		}
		//System.out.println("Driver encontrado");

		// Testando conexao
		Connection connection = null;		
		try {
			connection = DriverManager.getConnection("jdbc:"+database.getDbType()+"://" + database.getDbHost()+ ":" + database.getDbPort() + "/" + database.getDbName(), database.getDbUser(), database.getDbPassword());

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
			System.out.println("Falhou conexao nula!");
		}
		
		return connection;
	}
	
	private static Database getDatabase() {
		
		Database database = new Database();
		
		PropertyValue property = new PropertyValue(); 
		database.setDbType(property.getPropValue("db_type"));
		database.setDbHost(property.getPropValue("db_host"));
		database.setDbPort(property.getPropValue("db_port")); 
		database.setDbName(property.getPropValue("db_name"));
		database.setDbUser(property.getPropValue("db_user"));
		database.setDbPassword(property.getPropValue("db_password"));
		database.setDbDriver(property.getPropValue("db_driver"));
		
		return database;
	}
}