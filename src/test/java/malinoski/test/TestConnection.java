package malinoski.test;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

import malinoski.db.ConnectionFactory;

public class TestConnection {

	@Test
	public void testConnection() {
		
		// Open conection
		Connection connection = null;
		try {
			connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		assertNotNull(connection);
		
		// Close conection
		boolean result = ConnectionFactory.closeConnection();
		assertTrue(result);

	}

}
