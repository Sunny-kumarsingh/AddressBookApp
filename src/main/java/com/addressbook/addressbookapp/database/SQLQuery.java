package com.addressbook.addressbookapp.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.addressbook.addressbookapp.model.Contact;

public class SQLQuery {
	SingletonConnection singleton = SingletonConnection.getInstance();
	Connection connection = singleton.getConnection();
	
	//add new contact to database 
	public void addContact(List<Contact> contactList) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(Contact contact : contactList) {
			executor.execute(()->{
				Connection conn = null;
				try {
					conn = SingletonConnection.getInstance().getConnection();
		            conn.setAutoCommit(false);   // Start transaction
					String sql = "INSERT INTO contact (first_name, last_name, address, city, state, zip, phone_number, email) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement = connection.prepareStatement(sql);
					
					statement.setString(1, contact.getFirstName());
					statement.setString(2, contact.getLastName());
					statement.setString(3, contact.getAddress());
					statement.setString(4, contact.getCity());
					statement.setString(5, contact.getState());
					statement.setString(6, contact.getZip());
					statement.setString(7, contact.getPhoneNumber());
					statement.setString(8, contact.getEmail());
					statement.executeUpdate();
					
					conn.commit();   // Success
	                System.out.println("Contact added by " + Thread.currentThread().getName() + " : " + contact.getFirstName());				}
				catch(SQLException e) {
					try {
	                    if (conn != null) {
	                        conn.rollback();
	                    }
	                } catch (SQLException ex) {
	                    System.out.println(ex.getMessage());
	                }
					System.out.println(e.getMessage());
				}
			});
		}
		 executor.shutdown();
		 System.out.println("====---All Contact tasks submitted---====");
	}
	
	//view all contact to database
	public void viewAllContact() {
		try {
			String sql = "SELECT * FROM contact";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				Contact c = new Contact(result.getString("first_name"), result.getString("last_name"), result.getString("address"), result.getString("city"), result.getString("state"), result.getString("zip"), result.getString("phone_number"),result.getString("email"));
				System.out.println(c);
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}