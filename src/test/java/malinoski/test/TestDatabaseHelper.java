package malinoski.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.Test;

import malinoski.db.ConnectionFactory;
import malinoski.db.DatabaseHelper;

public class TestDatabaseHelper {

	@Test
	public void testCreateAndDropDatabase() throws FileNotFoundException, InvalidPropertiesFormatException, IOException, SQLException {
		
		// Open conection
		Connection conn = ConnectionFactory.getConnection();
		assertNotNull(conn);
		
		// Invoke the database helper
		DatabaseHelper helper = new DatabaseHelper();
		
		// Create the database
		boolean result = false;
		result = helper.createDatabase(conn,"testdb");
		assertTrue(result);
		
		// Drop the database 
		result = false;
		result = helper.dropDatabase(conn,"testdb");
		assertTrue(result);
		
		// Close connection
		result = false;
		result = ConnectionFactory.closeConnection();
		assertTrue(result);
		
		
	}


}
