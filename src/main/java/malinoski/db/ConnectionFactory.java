package malinoski.db;

import java.sql.DriverManager;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.postgresql.util.PSQLException;

public class ConnectionFactory {

	private Properties prop;

	public ConnectionFactory(String fileName) throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		super();
		this.setProperties(fileName);
	}

	private void setProperties(String fileName) throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		this.prop = new Properties();

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

		if (inputStream != null) {
			this.prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + this.prop + "' not found in the classpath");
		}
	}

	public Connection getConnection() throws SQLException {		

		// Testando o driver
		try {
			Class.forName(this.prop.getProperty("db_driver"));
		} catch (ClassNotFoundException e) {
			// System.out.println("Nao foi encontrado o driver!");
			e.printStackTrace();
			return null;
		}
		// System.out.println("Driver encontrado");

		// Testando conexao
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:" + this.prop.getProperty("db_type") + "://" + this.prop.getProperty("db_host") + ":" + this.prop.getProperty("db_port") + "/" + this.prop.getProperty("db_name"), 
					this.prop.getProperty("db_user"), this.prop.getProperty("db_password"));

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

	public static void closeConnection(Connection conn) {
		System.out.println("Releasing all open resources ...");
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}