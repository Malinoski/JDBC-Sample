package malinoski.test;

import junit.framework.TestCase;
import malinoski.db.ConnectionFactory;

public class TestConnectionFactory extends TestCase {

	public void testGetConnection() {

		// If return null, means that has no connection
		assertNotNull(ConnectionFactory.getConnection());
		
	}

}
