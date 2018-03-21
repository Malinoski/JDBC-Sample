package malinoski;

//import static org.junit.Assert.assertNotNull;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import malinoski.ConnectionFactory;

public class TestConnectionFactory extends TestCase {
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public TestConnectionFactory(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TestConnectionFactory.class);
	}

	public void testApp() {
		assertNotNull(ConnectionFactory.getConnection());
	}
	

}
