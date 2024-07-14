package com.revature.services;

import com.revature.dto.CalorieTrackDTO;

import java.time.LocalDate;
import java.util.List;

public interface CalorieTrackService {
    public CalorieTrackDTO getCalories(int cId);

    public List<CalorieTrackDTO> getCaloriesByUser(int uId);

    public List<CalorieTrackDTO> getCaloriesByUserAndDateBetween(int uId, LocalDate logDateStart, LocalDate logDateEnd);

    public List<CalorieTrackDTO> getCaloriesByUserAndDate(int uId, LocalDate logDate);

    public Result<CalorieTrackDTO> createCalorieTrack(CalorieTrackDTO calorieTrackDTO);

    public Result<CalorieTrackDTO> updateCalorieTrack(CalorieTrackDTO calorieTrackDTO);

    public boolean deleteCalorieTrack(int cId);




//    PUT https://localhost:8080:api/caloriestrack/{cId}







}
