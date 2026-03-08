package com.addressbook.addressbookapp.main;


import java.util.*;

import com.addressbook.addressbookapp.database.SQLQuery;
import com.addressbook.addressbookapp.model.Contact;

public class AddressBookMain {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		SQLQuery query = new SQLQuery();
		
		while(true) {
			System.out.println("\n--------------------Address Book System App--------------------");
			System.out.println("Add Contact -> Enter '1': ");
			System.out.println("View All Contact -> Enter '2': ");
			System.out.println("Exit -> Enter '0': ");
			System.out.println("--------------------------------------------------");
			
			int choise = sc.nextInt();
			sc.nextLine();
			
			if(choise==0) {
				System.out.println("Thanks for using our services!");
				break;
			}
			
			switch(choise) {
				case 1:
					System.out.println("How many number of contacts add : ");
					int n = sc.nextInt();
					sc.nextLine();
					if(n<=0) {
						System.out.println("Please! Enter postive number.");
						break;
					}
					query.addContact(takeInput(n));
					break;
				case 2:
					query.viewAllContact();
					break;
				default:
					System.out.println("Invalid choise!");
			}
		}
	}
	
	public static List<Contact> takeInput(int n ) {
		List<Contact> contactList = new ArrayList<>();
		while(n-->0) {
			System.out.println("--------------------------------");
			System.out.println("Enter first name: ");
			String firstName = sc.nextLine();
			
			System.out.println("Enter last name: ");
			String lastName = sc.nextLine();
			
			System.out.println("Enter address: ");
			String address = sc.nextLine();
			
			System.out.println("Enter city name: ");
			String city = sc.nextLine();
			
			System.out.println("Enter state name: ");
			String state = sc.nextLine();
			
			System.out.println("Enter zip: ");
			String zip = sc.next();
			sc.nextLine();
			
			System.out.println("Enter phone number: ");
			String phoneNumber = sc.nextLine();
			
			System.out.println("Enter email: ");
			String email = sc.nextLine();
			contactList.add(new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
			System.out.println("--------------------------------");
		}
		return contactList;
	}
}