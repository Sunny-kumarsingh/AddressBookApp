package com.addressbook.addressbookapp.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.addressbook.addressbookapp.model.Contact;
import com.addressbook.addressbookapp.service.ContactService;
@RequestMapping("/v1/api")
@RestController
public class ContactController {
	
	@Autowired
	ContactService addressBookService;
	
	//POST: Create contact
	@PostMapping("/contacts")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
		return ResponseEntity.ok(addressBookService.createContact(contact));
	}
	
	//GET: Get all contacts
	@GetMapping("/contacts")
	public List<Contact> getAllContacts(){
		return addressBookService.getAllContacts();
	}
	
	//GET by ID: Get specific contact
	@GetMapping("/contacts/{id}")
	public ResponseEntity<Contact> getById(@PathVariable Long id){
		Contact contactData = addressBookService.getById(id);
		return ResponseEntity.ok(contactData);
	}
	
	//update the Contact
	@PutMapping("/contacts/{id}")
	public ResponseEntity<Contact> updateById(@PathVariable Long id, @RequestBody Contact contact){
		Contact contactData = addressBookService.updateContact(id, contact);
		return ResponseEntity.ok(contactData);
	}
	
	
	//Ability to Retrieve number of Contacts in the Database by City
	@GetMapping("/contacts/city/{city}")
	public List<Contact> getAllContactsByCity(@PathVariable String city){
		return addressBookService.findAllContactByCity(city);
	}
}