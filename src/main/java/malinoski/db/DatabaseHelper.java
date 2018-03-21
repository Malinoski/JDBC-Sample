package malinoski.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
	
	public boolean createDatabase(String dbName) {
		
		Connection conn = ConnectionFactory.getConnection();		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE "+dbName+";";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			return false;
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		
		return true;
	}
	
	public boolean dropDatabase(String dbName) {
		
		Connection conn = ConnectionFactory.getConnection();		
		Statement stmt = null;
		
		try {
			stmt = conn.createStatement();
			String sql = "DROP DATABASE "+dbName+";";
			stmt.executeUpdate(sql);
			System.out.println("Database dropped successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			return false;
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
			return false;
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		
		return true;
	}
}
