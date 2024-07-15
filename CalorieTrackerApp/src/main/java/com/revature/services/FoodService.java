package com.revature.services;
import com.revature.dto.FoodDTO;
import java.util.List;

public interface FoodService {
//    public FoodDTO createFoodItem(FoodDTO foodDTO);
    public List<FoodDTO> getFoodItemsByNameContaining(String foodName);
    public FoodDTO getFoodItemById(int fId);
   // public FoodDTO getFoodItemByName(String foodName);
    public List<FoodDTO> getAllFoodItems();


}
