package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CalorieTrackerAppApplication {

	public static void main(String[] args) {
		System.out.println("app is running");
		SpringApplication.run(CalorieTrackerAppApplication.class, args);
	}

}
