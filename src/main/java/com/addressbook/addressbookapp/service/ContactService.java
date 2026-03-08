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
}