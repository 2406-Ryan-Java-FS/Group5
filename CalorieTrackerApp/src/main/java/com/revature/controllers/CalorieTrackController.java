package com.revature.controllers;

import com.revature.dto.CalorieTrackDTO;
import com.revature.services.CalorieTrackService;
import com.revature.services.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/calorietrack")
public class CalorieTrackController {
    private final CalorieTrackService calorieTrackService;

    @Autowired
    public CalorieTrackController(CalorieTrackService calorieTrackService){
        this.calorieTrackService = calorieTrackService;
    }

    @GetMapping("/test")
    public String test(){
        return "Test endpoint working";
    }

    @GetMapping("/{cId}")
    public ResponseEntity<CalorieTrackDTO> getCalorieTrack(@PathVariable int cId){
        log.info("I'm in the getCalorieTrack Controller");
        CalorieTrackDTO calorieTrackDTO = calorieTrackService.getCalories(cId);
        log.info(calorieTrackDTO.toString());
        if(calorieTrackDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(calorieTrackDTO, HttpStatus.OK);
    }

    @GetMapping("/users/{uId}")
    public List<CalorieTrackDTO> getCalorieTrackByUser(@PathVariable int uId){
        return calorieTrackService.getCaloriesByUser(uId);
    }

    @GetMapping("/users/{uId}/date")
    public List<CalorieTrackDTO> getCaloriesByUserAndDate(@PathVariable int uId,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDate){
        System.out.println("logDate: " + logDate);
        System.out.println("userId: " + uId);
        List<CalorieTrackDTO> calorieTrackDTOS = calorieTrackService.getCaloriesByUserAndDate(uId, logDate);
        System.out.println("list at controller" + calorieTrackDTOS);

        return calorieTrackDTOS;
    }

    @GetMapping("/users/{uId}/date-between")
    public List<CalorieTrackDTO> getCaloriesByUserAndDateBetween(@PathVariable int uId,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDateStart,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDateEnd){
        System.out.println("Controller - getCaloriesByUserAndDateBetween");
        return calorieTrackService.getCaloriesByUserAndDateBetween(uId, logDateStart, logDateEnd);
    }

    @PostMapping
    public ResponseEntity<?> createCalorieTrack(@RequestBody CalorieTrackDTO calorieTrackDTO){
        // Get the Result of CalorieTrack
        System.out.println("Post controller");
        System.out.println("CalorieTrackDTO: " + calorieTrackDTO);
        System.out.println(calorieTrackDTO.toString());
        System.out.println("userId: " + calorieTrackDTO.getUId());
        System.out.println("foodId: " +calorieTrackDTO.getFood().getFId());
        Result<CalorieTrackDTO> result = calorieTrackService.createCalorieTrack(calorieTrackDTO);
        System.out.println(result);
        //If creation went successful, it will return payload of result which has a CalorieTrackDTO object.
        if(result.isSuccess()){
            System.out.println("post succeed");
            return new ResponseEntity<>(result.getPayload(),HttpStatus.CREATED);
        }
        //Else return BAD_REQUEST
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{cId}")
    public ResponseEntity<CalorieTrackDTO> updateCalorieTrack(@PathVariable int cId,
                                                              @RequestBody CalorieTrackDTO calorieTrackDTO){
        //Validate if requesting CId and requestBody CId are the same, else return CONFLICT
        if(cId != calorieTrackDTO.getCId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<CalorieTrackDTO> result = calorieTrackService.updateCalorieTrack(calorieTrackDTO);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{cId}")
    public ResponseEntity<Void> deleteCalorieTrackById(@PathVariable int cId){
        boolean wasDeleted = calorieTrackService.deleteCalorieTrack(cId);
        System.out.println(wasDeleted);
        return new ResponseEntity<>(wasDeleted? HttpStatus.NO_CONTENT: HttpStatus.NOT_FOUND);
    }
}
