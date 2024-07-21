package com.revature.services.impl;

import com.revature.dto.FoodDTO;
import com.revature.exceptions.FoodExceptions.FoodAlreadyExistsException;
import com.revature.exceptions.FoodExceptions.FoodNotFoundException;
import com.revature.exceptions.FoodExceptions.InvalidFoodDetailsException;
import com.revature.exceptions.userexceptions.UserNotFoundException;
import com.revature.models.Food;
import com.revature.models.User;
import com.revature.repositories.FoodRepo;
import com.revature.repositories.UserRepo;
import com.revature.services.FoodService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepo foodRepo;
    private final UserRepo userRepo;

    @Autowired
    public FoodServiceImpl(final FoodRepo foodRepo, final UserRepo userRepo){
        this.foodRepo = foodRepo;
        this.userRepo = userRepo;
    }


    @Override
    public FoodDTO createFoodItem(Integer uId, FoodDTO foodDTO) {
        User userEntity = userRepo.findById(uId)
                .orElseThrow(() -> new UserNotFoundException("Admin user not found"));

        validateFoodDetails(foodDTO);

        if (foodRepo.findByFoodName(foodDTO.getFoodName()).isPresent()){
            throw new FoodAlreadyExistsException("Food with name already exists.");
        }

        Food foodEntity = convertFoodDTOToFood(foodDTO);

        foodEntity.setUser(userEntity);

        Food savedFood = foodRepo.save(foodEntity);

        return convertFoodTOFoodDTO(savedFood);

    }

    @Override
    public FoodDTO updateFoodItem(Integer uId, int fId, FoodDTO foodDTO) {
        Food existingFoodEntity = foodRepo.findById(fId)
                .orElseThrow(() -> new FoodNotFoundException("food item to be updated not found."));

        validateFoodDetails(foodDTO);

        updateFoodEntityFromFoodDTO(existingFoodEntity, foodDTO);

        Food updatedFoodEntity = foodRepo.save(existingFoodEntity);

        return convertFoodTOFoodDTO(updatedFoodEntity);
    }

    @Override
    public void deleteFoodItem(Integer uId, int fId) {
        if (!foodRepo.existsById(fId)) {
            throw new FoodNotFoundException("food item not found");
        }

        foodRepo.deleteById(fId);
    }

    @Override
    public List<FoodDTO> getAllFoodItems() {
        System.out.println("service activated");
        List<Food> foodList = foodRepo.findAll();
        System.out.println("foodList:" + foodList);
        //Make a new list of FoodDTOs
        List<FoodDTO> foodDTOS = new ArrayList<>();
        //convert each element of food entity to foodDTO and add to the foodDTOS list.
        for(Food food: foodList){
            System.out.println(food);
            FoodDTO foodDTO = convertFoodTOFoodDTO(food);
            foodDTOS.add(foodDTO);
        }
        System.out.println(foodDTOS);
        return foodDTOS;
    }

    @Override
    public FoodDTO getFoodItemById(int fId) {
        Food food = foodRepo.findById(fId).orElseThrow(() -> new FoodNotFoundException("Food doesn't exist"));
        return convertFoodTOFoodDTO(food);
    }

//    @Override
//    public FoodDTO getFoodItemByName(String foodName) {
//        Food food = foodRepo.findByFoodName(foodName).orElse(null);
//        return convertFoodTOFoodDTO(food);
//    }

    @Override
    public List<FoodDTO> getFoodItemsByNameContaining(String foodName) {
        List<Food> foodList = foodRepo.findAllByFoodNameContainingIgnoreCase(foodName);
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
                .uId(food.getUser().getUId())
                .build();
    }

    private void validateFoodDetails(FoodDTO foodDTO) {
        if (foodDTO.getFoodName() == null || foodDTO.getCalorie() <=0) {
            throw new InvalidFoodDetailsException("Invalid food details provided");
        }
    }

    private void updateFoodEntityFromFoodDTO(Food foodEntity, FoodDTO foodDTO){
        foodEntity.setFoodName(foodDTO.getFoodName());
        foodEntity.setCalorie(foodDTO.getCalorie());
    }
}
