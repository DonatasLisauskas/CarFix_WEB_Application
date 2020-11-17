package com.example.carfix.carfixspringboot;

import com.example.carfix.carfixspringboot.entities.Customer;
import com.example.carfix.carfixspringboot.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CarfixSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarfixSpringBootApplication.class, args);
    }

 /*   @Bean
    CommandLineRunner init(CustomerRepository customers) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                Customer customer = new Customer(name, name.toUpperCase(), name.toLowerCase() + "@domain.com", "+370-63800000");
                customers.save(customer);
            });
            customers.findAll().forEach(System.out::println);

        };
    }*/
}
