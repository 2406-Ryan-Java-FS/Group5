package com.revature.services.impl;

import com.revature.dto.CalorieTrackDTO;
import com.revature.dto.FoodDTO;
import com.revature.exceptions.CalorieTrackExceptions.CalorieTrackNotFoundException;
import com.revature.exceptions.FoodExceptions.FoodNotFoundException;
import com.revature.models.CalorieTrack;
import com.revature.models.Food;
import com.revature.models.User;
import com.revature.repositories.CalorieTrackRepo;
import com.revature.repositories.FoodRepo;
import com.revature.repositories.UserRepo;
import com.revature.services.CalorieTrackService;
import com.revature.services.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional
public class CalorieTrackServiceImpl implements CalorieTrackService {

    @Autowired
    private CalorieTrackRepo calorieTrackRepo;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private FoodRepo foodRepository;


    @Override
    public CalorieTrackDTO getCalories(int cId) {
        CalorieTrack calorieTrack = calorieTrackRepo.findById(cId).orElseThrow(() -> new CalorieTrackNotFoundException("Calorie Track not found"));
//        CalorieTrack calorieTrack = calorieTrackRepo.findById(cId).orElse(null);
        return convertCalorieTrackToCalorieTrackDTO(calorieTrack);
    }

    @Override
    public List<CalorieTrackDTO> getCaloriesByUser(int uId) {
        List<CalorieTrack> calorieTrackList = calorieTrackRepo.findAllByUser_uId(uId);
        List<CalorieTrackDTO> calorieTrackDTOS = new ArrayList<>();
        if(calorieTrackList != null && !calorieTrackList.isEmpty()){
            for(CalorieTrack ct: calorieTrackList){
                calorieTrackDTOS.add(convertCalorieTrackToCalorieTrackDTO(ct));
            }
        }
        return calorieTrackDTOS;
    }

    @Override
    public List<CalorieTrackDTO> getCaloriesByUserAndDate(int uId, LocalDate logDate) {
        List<CalorieTrack> calorieTrackList = calorieTrackRepo.findAllByUser_uIdAndLogDate(uId, logDate);
        List<CalorieTrackDTO> calorieTrackDTOS = new ArrayList<>();
        if(calorieTrackList != null && !calorieTrackList.isEmpty()){
            for(CalorieTrack ct: calorieTrackList){
                calorieTrackDTOS.add(convertCalorieTrackToCalorieTrackDTO(ct));
            }
        }
        return calorieTrackDTOS;
    }

    @Override
    public List<CalorieTrackDTO> getCaloriesByUserAndDateBetween(int uId, LocalDate logDateStart, LocalDate logDateEnd) {
        List<CalorieTrack> calorieTrackList = calorieTrackRepo.findAllByUser_uIdAndLogDateBetween(uId, logDateStart, logDateEnd);
        List<CalorieTrackDTO> calorieTrackDTOS = new ArrayList<>();
        if(calorieTrackList != null && !calorieTrackList.isEmpty()){
            for(CalorieTrack ct: calorieTrackList){
                calorieTrackDTOS.add(convertCalorieTrackToCalorieTrackDTO(ct));
            }
        }
        return calorieTrackDTOS;
    }


    @Override
    public Result<CalorieTrackDTO> createCalorieTrack(CalorieTrackDTO calorieTrackDTO) {
        System.out.println("in service layer");
        Result<CalorieTrackDTO> result = validateCalorieTrack(calorieTrackDTO);
        if(!result.isSuccess()){
            return result;
        }
        if(calorieTrackDTO.getCId() != 0){
            result.addErrorMessage("cId must be set for `add` operation");
            return result;
        }
        CalorieTrack calorieTrack = calorieTrackRepo.save(convertCalorieTrackDTOTOCalorieTrack(calorieTrackDTO));
        CalorieTrackDTO returnedCalorieTrackDTO = convertCalorieTrackToCalorieTrackDTO(calorieTrack);
        result.setPayload(returnedCalorieTrackDTO);
        return result;
    }

    @Override
    public Result<CalorieTrackDTO> updateCalorieTrack(CalorieTrackDTO calorieTrackDTO) {
        //When there are multiple errors in the process of validation, display list of error messages.
        Result<CalorieTrackDTO> result = validateCalorieTrack(calorieTrackDTO);

        //.isSuccess() checks if there are error messages in the list.
        if(!result.isSuccess()){
            return result;
        }

        //If Id for update doesn't exist or eaulas or less than zero, the calorie track cannot be updated.
        if(calorieTrackDTO.getCId() <= 0){
            result.addErrorMessage("cId must be set for `update` operation");
            return result;
        }

        // update calorieTrack
        CalorieTrack calorieTrack = calorieTrackRepo.save(convertCalorieTrackDTOTOCalorieTrack(calorieTrackDTO));

        //if it returns null meaning update operation went wrong, return result with error messages.
//        if(calorieTrack == null){
//            String msg = String.format("cId: %s, not found", calorieTrack.getCId());
//            result.addErrorMessage(msg);
//        }

        //if update is successful, convert the entity into DTO and set Payload.
        CalorieTrackDTO returnedCalorieTrackDTO = convertCalorieTrackToCalorieTrackDTO(calorieTrack);
        result.setPayload(returnedCalorieTrackDTO);
        return result;
    }


    @Override
    public boolean deleteCalorieTrack(int cId) {
        //when Id doesn't exist in the database.
        try{
            CalorieTrackDTO calorieTrackDTO = getCalories(cId);
            if(calorieTrackDTO.getCId() == cId){

                calorieTrackRepo.deleteById(cId);
                return true;
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return false;
    }

    //helper method for validating calorieTrackDTO
    private Result<CalorieTrackDTO> validateCalorieTrack(CalorieTrackDTO calorieTrackDTO){
        Result<CalorieTrackDTO> result = new Result<>();
        System.out.println("validating...");
        if (calorieTrackDTO == null){
            //throw new CalorieTrackNullException("calorieTrack cannot be null");
            result.addErrorMessage("CalorieTrack cannot be null");
            return result;
        }
        if(calorieTrackDTO.getUId() == 0){
            // throw userNotFoundException("UserId is required")
            result.addErrorMessage("UserId is required");
        }
        if(calorieTrackDTO.getFood().getFId() == 0){
            //throw foodNotFound("Food cannot be null")
            result.addErrorMessage("Food cannot be null");
        }
        if(calorieTrackDTO.getServing() <=0){
            result.addErrorMessage("serving has to be equal to or greater than 0");
        }
        return result;
    }

    private CalorieTrack convertCalorieTrackDTOTOCalorieTrack(CalorieTrackDTO calorieTrackDTO){
        // change exception.
        User user = userRepository.findById(calorieTrackDTO.getUId()).orElseThrow(() -> new CalorieTrackNotFoundException("user is not found"));
        Food food = foodRepository.findById(calorieTrackDTO.getFood().getFId()).orElseThrow(() -> new FoodNotFoundException("food cannot be found"));
        CalorieTrack calorieTrack = new CalorieTrack();
        calorieTrack.setCId(calorieTrackDTO.getCId()); // Assuming setCId method exists
        calorieTrack.setServing(calorieTrackDTO.getServing());
        calorieTrack.setLogDate(calorieTrackDTO.getLogDate());
        calorieTrack.setUser(user);
        calorieTrack.setFood(food);
        return calorieTrack;
    }

    private CalorieTrackDTO convertCalorieTrackToCalorieTrackDTO(CalorieTrack calorieTrack){
        return new CalorieTrackDTO(
                calorieTrack.getCId(),
                calorieTrack.getServing(),
                calorieTrack.getLogDate(),
                convertFoodToFoodDTO(calorieTrack.getFood()),
                calorieTrack.getUser().getUId()
        );
    }

    private FoodDTO convertFoodToFoodDTO(Food food){
        return FoodDTO.builder()
                .fId(food.getFId())
                .foodName(food.getFoodName())
                .calorie(food.getCalorie())
                .uId(food.getUser().getUId())
                .build();
    }
}