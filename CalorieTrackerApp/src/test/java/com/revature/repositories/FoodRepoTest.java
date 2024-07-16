package com.revature.repositories;

import com.revature.CalorieTrackerAppApplication;
import com.revature.models.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CalorieTrackerAppApplication.class)
//@Transactional
class FoodRepoTest {

    @Autowired
    FoodRepo repository;

    @Test
    void shouldFindFoodItems(){
        List<Food> actual = repository.findAll();
        assertNotNull(actual);
        assertEquals(actual.size(), 95);
    }

}