package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "p_id", updatable = false)
        private Integer pId;

        @Column(name = "gender", nullable = false)
        private String gender;

        @Column(name = "height", nullable = false)
        private double height;

        @Column(name = "weight", nullable = false)
        private double weight;

        @Column(name = "activity", nullable = false)
        private int activity;

        @Column(name = "calorie_goal", nullable = false)
        private int calorieGoal;


        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "u_id", nullable = false)
        private User user;

}
