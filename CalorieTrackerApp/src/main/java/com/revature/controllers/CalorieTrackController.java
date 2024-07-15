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
    private CalorieTrackService calorieTrackService;

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

    @GetMapping("/user/{uId}")
    public List<CalorieTrackDTO> getCalorieTrackByUser(@PathVariable int uId){
        return calorieTrackService.getCaloriesByUser(uId);
    }

    @GetMapping("/user/{uId}/date")
    public List<CalorieTrackDTO> getCaloriesByUserAndDate(@PathVariable int uId,
                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDate){
        return calorieTrackService.getCaloriesByUserAndDate(uId, logDate);
    }

    @GetMapping("/user/{uId}/date-between")
    public List<CalorieTrackDTO> getCaloriesByUserAndDateBetween(@PathVariable int uId,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDateStart,
                                                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate logDateEnd){
        return calorieTrackService.getCaloriesByUserAndDateBetween(uId, logDateStart, logDateEnd);
    }

    @PostMapping
    public ResponseEntity<?> createCalorieTrack(@RequestBody CalorieTrackDTO calorieTrackDTO){
        // Get the Result of CalorieTrack
        Result<CalorieTrackDTO> result = calorieTrackService.createCalorieTrack(calorieTrackDTO);

        //If creation went successful, it will return payload of result which has a CalorieTrackDTO object.
        if(result.isSuccess()){
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
        return new ResponseEntity<>(wasDeleted? HttpStatus.NO_CONTENT: HttpStatus.NOT_FOUND);
    }
}
