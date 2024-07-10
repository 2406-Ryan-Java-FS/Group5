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
    private int pId;
    private boolean gender;
    private double height;
    private double weight;
    private int activity;
    private int calorie_goal;
    private int u_id;
}
