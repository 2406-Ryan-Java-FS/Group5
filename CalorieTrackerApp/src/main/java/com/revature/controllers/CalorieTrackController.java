package com.revature.controllers;

import com.revature.dto.CalorieTrackDTO;
import com.revature.services.CalorieTrackService;
import com.revature.services.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/calorietracks")
public class CalorieTrackController {
    private final CalorieTrackService calorieTrackService;

    @Autowired
    public CalorieTrackController(CalorieTrackService calorieTrackService){
        this.calorieTrackService = calorieTrackService;
    }

    @GetMapping("/{cId}")
    public ResponseEntity<CalorieTrackDTO> getCalorieTrack(@PathVariable int cId){
        CalorieTrackDTO calorieTrackDTO = calorieTrackService.getCalories(cId);
        if(calorieTrackDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(calorieTrackDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<CalorieTrackDTO> getCalorieTrackByUser(@RequestParam int uId){
        return calorieTrackService.getCaloriesByUser(uId);
    }

    @GetMapping("/search")
    public List<CalorieTrackDTO> getCaloriesByUserAndDate(@RequestParam int uId,
                                                          @RequestParam LocalDate logDate){
        return calorieTrackService.getCaloriesByUserAndDate(uId, logDate);
    }

    @GetMapping("/search")
    public List<CalorieTrackDTO> getCaloriesByUserAndDateBetween(@RequestParam int uId,
                                                                 @RequestParam LocalDate logDateStart,
                                                                 @RequestParam LocalDate logDateEnd){
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
