package com.revature.services.impl;

import com.revature.dto.CalorieTrackDTO;
import com.revature.services.CalorieTrackService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalorieTrackServiceImpl implements CalorieTrackService {
    @Override
    public CalorieTrackDTO getCalories(int cId) {
        return null;
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
}
