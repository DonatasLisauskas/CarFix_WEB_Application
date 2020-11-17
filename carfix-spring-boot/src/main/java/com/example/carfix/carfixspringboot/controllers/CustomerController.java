package com.example.carfix.carfixspringboot.controllers;

import com.example.carfix.carfixspringboot.entities.Customer;
import com.example.carfix.carfixspringboot.error.ResourceNotFoundException;
import com.example.carfix.carfixspringboot.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/carfix")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    private static final String ERROR = "User not found by : ";

    @GetMapping(value = "/customers")
    public ResponseEntity<Customer> getAllCustomers() throws ResourceNotFoundException {
        LOGGER.info("Getting all Customers...");
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        if (customers.isEmpty()) {
            LOGGER.error("ERROR: Customer table is empty");
            throw new ResourceNotFoundException("This table is empty");
        } else {
            LOGGER.info("All Customers was returned successfully");
            return new ResponseEntity(customers, HttpStatus.OK);

        }
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("id") long id) throws ResourceNotFoundException {
        LOGGER.info("Searching by id: " + id + "...");
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ERROR + id));
        return ResponseEntity.ok().body(customer);
    }


    @PostMapping(value = "/customers/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        LOGGER.info("Creating new entry...");
        return customerRepository.save(new Customer(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber()));
    }

    @DeleteMapping(value = "/customers/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) throws ResourceNotFoundException {
        Optional<Customer> customerData = customerRepository.findById(id);
        if (!customerData.isPresent()) {
            LOGGER.error(ERROR + id + ". The deletion process was canceled.");
            throw new ResourceNotFoundException(ERROR + id + ". The deletion process was canceled.");
        } else {
            customerRepository.deleteById(id);
        }
        return new ResponseEntity<>("The deletion process was processed successfully.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/customers/delete/all")
    public ResponseEntity<String> deleteAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        if (!customers.isEmpty()) {
            LOGGER.info("Deleting content of Customer table...");
            customerRepository.deleteAll();
            return ResponseEntity.ok("The content of Customer table was deleted successfully.");
        } else {
            LOGGER.info("Customer table is already empty.");
            return ResponseEntity.ok("Customer table is already empty.");
        }
    }

    @RequestMapping(value = "/customers/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) throws ResourceNotFoundException {
        Optional<Customer> checkCustomer = customerRepository.findById(id);
        if (checkCustomer.isPresent()) {
            LOGGER.info("Updating customer by id: " + id + ".");
            Customer updateCustomer = checkCustomer.get();
            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setPhoneNumber(customer.getPhoneNumber());
            customerRepository.save(updateCustomer);
            return ResponseEntity.ok(updateCustomer);
        } else {
            LOGGER.error(ERROR);
            throw new ResourceNotFoundException(ERROR + id);
        }
    }

    @GetMapping(value = "/customers/findby/{firstName}")
    public ResponseEntity<Customer> findCustomerByName(@PathVariable("firstName") String firstName) throws ResourceNotFoundException {
        List<Customer> customers = customerRepository.getAllByFirstName(firstName);
        LOGGER.info("Searching customer by name: " + firstName);
        if (!customers.isEmpty()) {
            return new ResponseEntity(customers, HttpStatus.OK);
        } else {
            LOGGER.error(ERROR + firstName);
            throw new ResourceNotFoundException(ERROR + firstName);
        }
    }
}
