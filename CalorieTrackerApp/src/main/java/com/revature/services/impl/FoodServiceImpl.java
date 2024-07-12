package com.revature.services.impl;

import com.revature.dto.FoodDTO;
import com.revature.services.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    @Override
    public FoodDTO getFoodItemById(int fId) {
        return null;
    }

    @Override
    public FoodDTO getFoodItemByName(String foodName) {
        return null;
    }

    @Override
    public List<FoodDTO> getAllFoodItems() {
        return null;
    }
}
