package com.addressbook.addressbookapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addressbook.addressbookapp.model.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);    
    
    List<Contact> findByCityIgnoreCase(String city);
}