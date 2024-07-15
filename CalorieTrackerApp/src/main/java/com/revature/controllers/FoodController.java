package com.revature.controllers;


import com.revature.dto.FoodDTO;
import com.revature.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/food")
public class FoodController {
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService = foodService;
    }

    @GetMapping("/test")
    public String test(){
        return "Test endpoint working";
    }

    @GetMapping
    public List<FoodDTO> getAllFoodItems(){
        System.out.println("Controller activated");
        List<FoodDTO> list = foodService.getAllFoodItems();
        System.out.println("food list: " + list);
        return list;
    }

    @GetMapping("/{fId}")
    public ResponseEntity<FoodDTO> getFoodItem(@PathVariable int fId){
        FoodDTO foodDTO = foodService.getFoodItemById(fId);
        if(foodDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foodDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<FoodDTO> getFoodItemsByName(@RequestParam String foodName){
        return foodService.getFoodItemsByNameContaining(foodName);
    }


}
