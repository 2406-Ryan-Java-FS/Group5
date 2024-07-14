package com.revature.repositories;

import com.revature.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    // boolean existsByUsername(String username);

    // You can add more custom query methods here as needed

}
