package com.revature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "calorie_track")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalorieTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", updatable = false)
    private int cId;

    @Column(name = "serving", nullable = false)
    private double serving;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime dateTime;

    // foreign keys
    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    /*This annotation specifies the foreign key column in the database that links a shoe to its user.
     The nullable = false part means that a shoe must always be associated with a user.*/
    @JoinColumn(name = "u_id", nullable = false)
    private User user;

    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    /*This annotation specifies the foreign key column in the database that links a shoe to its user.
     The nullable = false part means that a shoe must always be associated with a user.*/
    @JoinColumn(name = "f_id", nullable = false)
    private Food food;



}
