package com.addressbook.addressbookapp.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
	private List<Contact> contactList = new ArrayList<>();	

	public List<Contact> getContactList() {
		return contactList;
	}
	
	//add contact
	public void addContact(Contact contact) {
		if(duplicateCheck(contact)) {
	        System.out.println("Duplicate contact! Cannot add.");
	        return;
	    }
		contactList.add(contact);
		System.out.println("Contact Added");
	}
	
	//editContactByName
	public void editContactByName(String name, Contact contact) {
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				c.setFirstName(contact.getFirstName());
				c.setLastName(contact.getLastName());
				c.setAddress(contact.getAddress());
				c.setCity(contact.getCity());
				c.setState(contact.getState());
				c.setZip(contact.getZip());
				c.setPhoneNumber(contact.getPhoneNumber());
				c.setEmail(contact.getEmail());
				System.out.println("contact udpated.");
				return;
			}
		}
	}
	
	//find contact by name
	public boolean findByName(String name) {
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	
	//delete contact by name
	public void deleteContactByName(String name) {
		Contact removeContact = null;
		for(Contact c : contactList) {
			if(c.getFirstName().equalsIgnoreCase(name)) {
				removeContact = c;
				break;
			}
		}
		if(removeContact!=null) {
			contactList.remove(removeContact);
			System.out.println("contact deleted!");
			return;
		}else {
			System.out.println("contact not found by name!");
		}
	}
	
	//duplicate check method
	public boolean duplicateCheck(Contact contact) {
		for(Contact c : contactList) {
			if(c.equals(contact)) {
				return true;
			}
		}
		return false;
	}
	
	//get all contacts
	public void getAllContact() {
		if(contactList.isEmpty()) {
			System.out.println("contact list is empty!");
			return;
		}
		contactList.forEach(System.out::println);
	}
	
	//search person by city
	public void searchPerson(String name, String city) {
		contactList.stream().filter(x-> {
			if((x.getFirstName()+" "+x.getLastName()).equalsIgnoreCase(name) && (x.getCity().equalsIgnoreCase(city))){
				return true;
			}
			else {
				return false;
			}
		}).forEach(System.out::println);
	}
	
	//view contact by state
	public void viewByState(String state) {
		contactList
			.stream()
			.filter(x-> x.getState().equalsIgnoreCase(state)).forEach(System.out::println);
	}
	
	//count number in city
	public void countNumberByCity(String city) {
		if(contactList.isEmpty()) {
			System.out.println("contact list is empty!");
			return;
		}
		System.out.println(contactList.stream().filter(x-> x.getCity().equalsIgnoreCase(city)).collect(Collectors.counting()));
	}
	
	//sort by name
	public void sortByAlphabetically() {
		contactList.stream().sorted(Comparator.comparing(Contact::getFirstName)).toList().forEach(System.out::println);
	}
	
	//sort by ZIP
	public void sortByZIP() {
		contactList.stream().sorted(Comparator.comparing(Contact::getZip)).toList().forEach(System.out::println);
	}
}