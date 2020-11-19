package com.example.carfix.carfixspringboot.repositories;

import com.example.carfix.carfixspringboot.entities.CarService;
import org.springframework.data.repository.CrudRepository;

public interface CarServiceRepository extends CrudRepository<CarService, Long> {
}
