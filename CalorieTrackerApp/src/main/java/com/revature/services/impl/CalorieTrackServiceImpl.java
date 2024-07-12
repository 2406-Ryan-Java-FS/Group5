package com.revature.services.impl;

import com.revature.dto.CalorieTrackDTO;
import com.revature.exceptions.CalorieTrackExceptions.CalorieTrackNotFoundException;
import com.revature.models.CalorieTrack;
import com.revature.repositories.CalorieTrackRepo;
import com.revature.repositories.FoodRepo;
import com.revature.repositories.UserRepo;
import com.revature.services.CalorieTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalorieTrackServiceImpl implements CalorieTrackService {

    @Autowired
    CalorieTrackRepo calorieTrackRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    FoodRepo foodReo;

    @Override
    public CalorieTrackDTO getCalories(int cId) {
        CalorieTrack calorieTrack = calorieTrackRepo.findById(cId).orElseThrow(() -> new CalorieTrackNotFoundException("Calorie Track not found"));


    }

    @Override
    public List<CalorieTrackDTO> getCaloriesByUser(int uId) {
        return null;
    }

    @Override
    public List<CalorieTrackDTO> getCaloriesByUserAndDateBetween(int uId, LocalDate logDateStart, LocalDate logDateEnd) {
        return null;
    }

    @Override
    public CalorieTrackDTO createCalorieTrack(CalorieTrackDTO calorieTrackDTO) {
        return null;
    }

    @Override
    public CalorieTrackDTO updateCalorieTrack(int cId, CalorieTrackDTO calorieTrackDTO) {
        return null;
    }

    @Override
    public List<CalorieTrackDTO> getCaloriesByUserAndDate(int uId, LocalDate logDate) {
        return null;
    }

    @Override
    public void deleteCalorieTrack(int cId) {

    }

    private CalorieTrack convertCalorieTrackDTOTOCalorieTrack(CalorieTrackDTO calorieTrackDTO){
        return CalorieTrack.builder()
                .serving(calorieTrackDTO.getServing())
                .logDate(calorieTrackDTO.getLogDate())
                .build();
    }

    private CalorieTrackDTO convertCalorieTrackToCalorieTrackDTO(CalorieTrack calorieTrack){
        return CalorieTrackDTO.builder()
                .cId(calorieTrack.getCId())
                .serving(calorieTrack.getServing())
                .logDate(calorieTrack.getLogDate())
                .uId(calorieTrack.getUser().getUId())
                .fId(calorieTrack.getFood().getFId())
                .build();
    }
}
