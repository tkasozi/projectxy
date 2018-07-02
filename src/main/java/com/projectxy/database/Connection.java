package com.projectxy.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.projectxy.models.Account;

public class Connection {
	private java.sql.Connection conn;
	private Statement stmt;

	/**
	 * 
	 * @param URL
	 *            jdbc:mysql://localhost:3306/schema
	 */
	public void init(String URL, String user, String psswd) {
		Logger LOGGER = Logger.getLogger(Connection.class.getName());

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, user, psswd);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		LOGGER.info("DB connection --init (PASSED): " + LOGGER.getName());
	}

	public List<Account> getAllColumns(String sql) {
		List<Account> records = new ArrayList<Account>();
		try {
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
				// + rs.getString(3) + " " + rs.getString(4)
				// + " " + rs.getString(5));
				records.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3) + " " + rs.getString(4),
						rs.getString(5)));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return records;
	}
}
