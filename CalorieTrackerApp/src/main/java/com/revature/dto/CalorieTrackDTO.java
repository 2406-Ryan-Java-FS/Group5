package com.revature.dto;

import com.revature.models.Food;
import com.revature.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalorieTrackDTO {
    private int cId;
    private double serving;
    private LocalDate logDate;
    //    private UserDTO user;
    private FoodDTO food;
    private int uId;

}
