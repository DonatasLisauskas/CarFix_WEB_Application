package com.example.carfix.carfixspringboot.repositories;

import com.example.carfix.carfixspringboot.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
