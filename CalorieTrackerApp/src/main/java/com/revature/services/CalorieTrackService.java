package com.revature.services;

import com.revature.dto.CalorieTrackDTO;

import java.time.LocalDate;
import java.util.List;

public interface CalorieTrackService {
    public CalorieTrackDTO getCalories(int cId);

    public List<CalorieTrackDTO> getCaloriesByUser(int uId);

    public List<CalorieTrackDTO> getCaloriesByDate(LocalDate logDate);
    public List<CalorieTrackDTO> getCaloriesByDateBetween(LocalDate logDateStart, LocalDate logDateEnd);

    public CalorieTrackDTO getCaloriesByFoodName(String foodName);

    public CalorieTrackDTO createCalorieTrack(CalorieTrackDTO calorieTrackDTO);

    public CalorieTrackDTO updateCalorieTrack(int cId, CalorieTrackDTO calorieTrackDTO);

    public CalorieTrackDTO deleteCalorieTrack(int cId);


//    PUT https://localhost:8080:api/caloriestrack/{cId}





}
