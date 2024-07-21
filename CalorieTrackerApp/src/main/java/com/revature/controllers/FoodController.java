package com.revature.controllers;


import com.revature.dto.FoodDTO;
import com.revature.dto.UserDTO;
import com.revature.services.FoodService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users/{uId}/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

//    @Autowired
//    public FoodController(final FoodService foodService, final UserService userService){
//        this.foodService = foodService;
//        this.userService = userService;
//    }

    @GetMapping("/test")
    public String test(){
        return "Test endpoint working";
    }

    @PostMapping
    public ResponseEntity<FoodDTO> createFoodItem(@PathVariable Integer uId, @RequestBody FoodDTO foodDTO){

        UserDTO userDTO = userService.getUserById(uId);

        if (!userDTO.getRole().equals("ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        FoodDTO foodItem = foodService.createFoodItem(uId, foodDTO);

        return new ResponseEntity<>(foodItem, HttpStatus.CREATED);

    }

    @PutMapping("/{fId}")
    public ResponseEntity<FoodDTO> updateFoodItem(@PathVariable Integer uId, @PathVariable int fId, @RequestBody FoodDTO foodDTO){
        UserDTO userDTO = userService.getUserById(uId);

        // Check if the user's role is equal to "USER", if not, return a HTTPStatus Forbidden.
        if (!userDTO.getRole().equals("ADMIN")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        FoodDTO updateFoodItem = foodService.updateFoodItem(uId, fId, foodDTO);

        return ResponseEntity.ok(updateFoodItem);
    }

    @DeleteMapping("/{fId}")
    public  ResponseEntity<Void> deleteFoodItem(@PathVariable Integer uId, @PathVariable int fId){
        foodService.deleteFoodItem(uId, fId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<FoodDTO>> getAllFood(){
//        System.out.println("Controller activated");
        List<FoodDTO> allFoods = foodService.getAllFoodItems();
//        System.out.println("food list: " + list);
        return ResponseEntity.ok(allFoods);
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
