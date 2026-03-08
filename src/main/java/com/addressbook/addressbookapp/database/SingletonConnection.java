package com.addressbook.addressbookapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	private Connection connection;
	private static SingletonConnection instance;
	
	private final static String URL = "jdbc:mysql://localhost:3306/addressbook";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Ravi@ku123";
	
	private SingletonConnection() {
		try {
			this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static SingletonConnection getInstance() {
		if(instance==null) {
			instance = new SingletonConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}
}