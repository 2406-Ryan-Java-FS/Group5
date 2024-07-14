package com.revature.services.impl;

import com.revature.dto.FoodDTO;
import com.revature.exceptions.FoodExceptions.FoodNotFoundException;
import com.revature.models.Food;
import com.revature.repositories.FoodRepo;
import com.revature.services.FoodService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    FoodRepo foodRepo;

    @Override
    @Transactional
    public List<FoodDTO> getAllFoodItems() {
        List<Food> foodList = foodRepo.findAll();
        //Make a new list of FoodDTOs
        List<FoodDTO> foodDTOS = new ArrayList<>();
        //convert each element of food entity to foodDTO and add to the foodDTOS list.
        for(Food food: foodList){
            foodDTOS.add(convertFoodTOFoodDTO(food));
        }
        return foodDTOS;
    }

    @Override
    public FoodDTO getFoodItemById(int fId) {
        Food food = foodRepo.findById(fId).orElseThrow(() -> new FoodNotFoundException("Food doesn't exist"));
        return convertFoodTOFoodDTO(food);
    }

    @Override
    public FoodDTO getFoodItemByName(String foodName) {
        Food food = foodRepo.findByFoodName(foodName).orElse(null);
        return convertFoodTOFoodDTO(food);
    }

    @Override
    public List<FoodDTO> getFoodItemsByNameContaining(String foodName) {
        List<Food> foodList = foodRepo.findAllByFoodNameContaining(foodName);
        List<FoodDTO> foodDTOS = new ArrayList<>();
        for(Food food: foodList){
            foodDTOS.add(convertFoodTOFoodDTO(food));
        }
        return foodDTOS;
    }

    private Food convertFoodDTOToFood(FoodDTO foodDTO){
        return Food.builder()
                .foodName(foodDTO.getFoodName())
                .calorie(foodDTO.getCalorie())
                .build();
    }

    private FoodDTO convertFoodTOFoodDTO(Food food){
        return FoodDTO.builder()
                .fId(food.getFId())
                .foodName(food.getFoodName())
                .calorie(food.getCalorie())
                .build();
    }
}
