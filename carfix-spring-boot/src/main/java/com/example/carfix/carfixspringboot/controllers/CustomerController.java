package com.example.carfix.carfixspringboot.controllers;

import com.example.carfix.carfixspringboot.entities.Customer;
import com.example.carfix.carfixspringboot.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/customer")
    public List<Customer> customerResponseEntity() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }


    @PostMapping(value = "/customer/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(new Customer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber()));
    }
}
