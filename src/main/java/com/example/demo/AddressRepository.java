package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<AddressBook, Long> {
    List<AddressBook> findByLastname(String ln);


}
