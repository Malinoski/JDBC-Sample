package malinoski.db;

import java.sql.DriverManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.postgresql.util.PSQLException;

public class ConnectionFactory {

	private static Connection connection;
	private static Properties prop;
	private static String propertieFile = "config.properties";

	private static void setProperties(String fileName) {
		prop = new Properties();

		InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream(fileName);

		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				throw new FileNotFoundException("property file '" + prop + "' not found in the classpath");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException {

		if (connection == null) {
			
			// Load db properties
			setProperties(propertieFile);
			
			// Testando o driver
			try {
				Class.forName(prop.getProperty("db_driver"));
			} catch (ClassNotFoundException e) {
				// System.out.println("Nao foi encontrado o driver!");
				e.printStackTrace();
				return null;
			}
			// System.out.println("Driver encontrado");

			// Testando conexao
			connection = null;
			try {
				connection = DriverManager.getConnection(
						"jdbc:" + prop.getProperty("db_type") + "://" + prop.getProperty("db_host") + ":"
								+ prop.getProperty("db_port") + "/" + prop.getProperty("db_name"),
						prop.getProperty("db_user"), prop.getProperty("db_password"));

			} catch (PSQLException e) {
				e.printStackTrace();
				System.out.println("Falhou conexao! O banco esta acessivel?");
				// e.printStackTrace();
				return null;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Falhou conexao!");
				// e.printStackTrace();
				return null;

			}
			if (connection != null) {
				System.out.println("Conexao estasbelecida");
			} else {
				System.out.println("Falhou conexao nula!");
			}
		}
		
		return connection;
	}

	public static boolean closeConnection() {
		System.out.println("Releasing all open resources ...");
		try {
			if (connection != null) {
				connection.close();
				connection = null;
				return true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
}