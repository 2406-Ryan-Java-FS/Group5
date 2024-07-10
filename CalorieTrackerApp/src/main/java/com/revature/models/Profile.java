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
        private int p_id;

        @Column(name = "gender", nullable = false)
        private boolean gender;

        @Column(name = "height", nullable = false)
        private double height;

        @Column(name = "weight", nullable = false)
        private double weight;

        @Column(name = "activity", nullable = false)
        private int activity;

        @Column(name = "calorie_goal", nullable = false)
        private int calorie_goal;

        @Column
        @OneToOne(fetch = FetchType.LAZY)
    /*This annotation specifies the foreign key column in the database that links a shoe to its user.
     The nullable = false part means that a shoe must always be associated with a user.*/
        @JoinColumn(name = "u_id", nullable = false)
        private User user;

}
