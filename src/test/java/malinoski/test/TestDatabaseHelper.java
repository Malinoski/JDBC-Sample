package malinoski.test;

import junit.framework.TestCase;
import malinoski.db.DatabaseHelper;

public class TestDatabaseHelper extends TestCase {

	public void testCreateAndDropDatabase() {
		assertTrue(new DatabaseHelper().createDatabase("DBTEST"));
		assertTrue(new DatabaseHelper().dropDatabase("DBTEST"));
	}
	
	

}
