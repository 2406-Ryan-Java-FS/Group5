package com.revature.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "food")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id", updatable = false)
    private int fId;

    @Column(name = "food_name", unique = true, nullable = false)
    private String foodName;

    @Column(name = "calories", nullable = false)
    private int calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id", nullable = false)
    private User user;

//    @OneToMany(mappedBy="food")
//    @JsonManagedReference(value = "food")
//    private List<CalorieTrack> calorieTrackList;

}
