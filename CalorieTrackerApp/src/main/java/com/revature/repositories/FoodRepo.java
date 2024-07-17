package com.revature.repositories;

import com.revature.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {
    Optional<Food> findById(int fId);
    Optional<Food> findByFoodName(String foodName);
    List<Food> findAllByFoodNameContainingIgnoreCase(String foodName);
    List<Food> findAll();
}
