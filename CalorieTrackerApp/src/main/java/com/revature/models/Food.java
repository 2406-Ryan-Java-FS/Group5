package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id", updatable = false)
    private int f_id;

    @Column(name = "food_name", unique = true, nullable = false)
    private String foodName;

    @Column(name = "calorie", nullable = false)
    private int calorie;


}
