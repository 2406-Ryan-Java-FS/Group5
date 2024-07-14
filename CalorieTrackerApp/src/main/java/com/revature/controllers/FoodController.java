package com.revature.controllers;


import com.revature.dto.FoodDTO;
import com.revature.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/food")
public class FoodController {
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping
    public List<FoodDTO> getAllFoodItems(){
        return foodService.getAllFoodItems();
    }

    @GetMapping("/{fId}")
    public ResponseEntity<FoodDTO> getFoodItemById(@PathVariable int fId){
        FoodDTO foodDTO = foodService.getFoodItemById(fId);
        if(foodDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<FoodDTO> getFoodItemsByName(@RequestParam String foodName){
        return foodService.getFoodItemsByNameContaining(foodName);
    }


}
