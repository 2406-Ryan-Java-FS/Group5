package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalorieTrackerAppApplication {

	public static void main(String[] args) {
		System.out.println("app is running");
		SpringApplication.run(CalorieTrackerAppApplication.class, args);
	}

}
