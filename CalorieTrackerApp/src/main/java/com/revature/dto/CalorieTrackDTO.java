package com.revature.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CalorieTrackDTO {
    private int cId;
    private double serving;
    private LocalDate logDate;

    // foreign keys
    private int uId;
    private int fId;
}
