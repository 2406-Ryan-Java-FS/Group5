package com.revature.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private String message;

//    public AuthResponse(String token, String message) {
//        this.token = token;
//        this.message = message;
//    }

    // Getters and setters
}
