package com.revature.services;
import com.revature.dto.FoodDTO;
import java.util.List;

public interface FoodService {
    public FoodDTO createFoodItem(Integer uId, FoodDTO foodDTO);

    public FoodDTO updateFoodItem(Integer uId, int fId, FoodDTO foodDTO);

    public void deleteFoodItem(Integer uId, int fId);


    public List<FoodDTO> getFoodItemsByNameContaining(String foodName);
    public FoodDTO getFoodItemById(int fId);
    // public FoodDTO getFoodItemByName(String foodName);
    public List<FoodDTO> getAllFoodItems();


}
