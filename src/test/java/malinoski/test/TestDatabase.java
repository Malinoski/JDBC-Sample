package malinoski.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import junit.framework.TestCase;
import malinoski.db.ConnectionFactory;
import malinoski.db.DatabaseHelper;

public class TestDatabase extends TestCase {

	public void testCreateAndDropDatabase() {
		
		ConnectionFactory connectionFactory;
		
		try {
			connectionFactory = new ConnectionFactory("config.properties");
			assertNotNull(connectionFactory);
			
			Connection conn = connectionFactory.getConnection();			
			assertNotNull(conn);
			assertTrue(new DatabaseHelper().createDatabase(conn, "DBTEST"));
			assertTrue(new DatabaseHelper().dropDatabase(conn, "DBTEST"));
			
			conn.close();			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
	
	

}
