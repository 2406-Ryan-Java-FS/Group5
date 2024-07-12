package com.revature.services;

import com.revature.dto.CalorieTrackDTO;
import com.revature.dto.FoodDTO;

import java.time.LocalDate;
import java.util.List;

public interface FoodService {
//    public FoodDTO createFoodItem(FoodDTO foodDTO);
    public FoodDTO getFoodItemById(int fId);
    public FoodDTO getFoodItemByName(String foodName);
    public List<FoodDTO> getAllFoodItems();


}