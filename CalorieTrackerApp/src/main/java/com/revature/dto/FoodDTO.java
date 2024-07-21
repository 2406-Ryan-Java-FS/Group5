package com.revature.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodDTO {
    private int fId;
    private String foodName;
    private int calorie;

    // foreign key
    private Integer uId;
}
