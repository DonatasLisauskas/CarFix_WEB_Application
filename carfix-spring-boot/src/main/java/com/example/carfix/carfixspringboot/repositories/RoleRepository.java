package com.example.carfix.carfixspringboot.repositories;

import com.example.carfix.carfixspringboot.entities.ERole;
import com.example.carfix.carfixspringboot.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(ERole role);
}
