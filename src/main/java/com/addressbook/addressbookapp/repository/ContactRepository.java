package com.addressbook.addressbookapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addressbook.addressbookapp.model.Contact;
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);    
}