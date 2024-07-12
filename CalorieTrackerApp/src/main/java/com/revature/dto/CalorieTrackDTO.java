package com.revature.dto;

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

    // foreign keys
    private int uId;
    private int fId;
}
