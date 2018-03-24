package malinoski.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	public boolean createDatabase(Connection conn, String dbName) {

		Statement stmt = null;
		boolean result = false;

		try {
			stmt = conn.createStatement();
			String sql = "CREATE DATABASE " + dbName + ";";
			stmt.executeUpdate(sql);
			result = true;
			System.out.println("Database created successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return result;
	}

	public boolean dropDatabase(Connection conn, String dbName) {

		Statement stmt = null;
		boolean result = false;
		
		try {
			stmt = conn.createStatement();
			String sql = "DROP DATABASE " + dbName + ";";
			stmt.executeUpdate(sql);
			result = true;
			System.out.println("Database dropped successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

		return result;
	}
}
