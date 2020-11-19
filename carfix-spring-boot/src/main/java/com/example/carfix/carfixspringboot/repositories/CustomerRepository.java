package com.example.carfix.carfixspringboot.repositories;

import com.example.carfix.carfixspringboot.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

    List<Customer> getAllByFirstName(String firstName);

}
