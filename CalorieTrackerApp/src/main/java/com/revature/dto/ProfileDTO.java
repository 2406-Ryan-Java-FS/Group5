package com.revature.dto;

import com.revature.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {
    private Integer pId;
    private String gender;
    private double height;
    private double weight;
    private int activity;
    private int calorieGoal;
    // foreign key
    private Integer uId;
}
