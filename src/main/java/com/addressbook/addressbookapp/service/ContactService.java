package com.addressbook.addressbookapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.addressbook.addressbookapp.exception.ContactNotFoundException;
import com.addressbook.addressbookapp.exception.DuplicateContactException;
import com.addressbook.addressbookapp.model.Contact;
import com.addressbook.addressbookapp.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository contactRepository;
	
	//Create contact
	public Contact createContact(Contact contact) {
		//validate email or phone number duplicate or not
		if(contactRepository.existsByEmail(contact.getEmail())) {
			throw new DuplicateContactException("Duplicate email found!");		
		}
		if(contactRepository.existsByPhoneNumber(contact.getPhoneNumber())) {
			throw new DuplicateContactException("Duplicate number found!");
		}		
		return contactRepository.save(contact);
	}
	
	//Get all contacts
	public List<Contact> getAllContacts(){
		return contactRepository.findAll();
	}
	
	//Get specific contact
	public Contact getById(Long id){
		return contactRepository.findById(id)
							.orElseThrow(()-> new ContactNotFoundException("Contact not found with id: "+id));
	}
	
	//update the Contact
	public Contact updateContact(Long id, Contact newContact) {
		Contact stored = getById(id);
		stored.setFirstName(newContact.getFirstName());
		stored.setLastName(newContact.getLastName());
		stored.setAddress(newContact.getAddress());
		stored.setCity(newContact.getCity());
		stored.setState(newContact.getState());
		stored.setZip(newContact.getZip());
		stored.setPhoneNumber(newContact.getPhoneNumber());
		stored.setEmail(newContact.getEmail());
		return contactRepository.save(stored);
	}
	
	//Ability to Retrieve number of Contacts in the Database by City
	public List<Contact> findAllContactByCity(String city) {
		return contactRepository.findByCityIgnoreCase(city);
	}
}