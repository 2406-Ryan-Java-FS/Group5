package com.revature.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", updatable = false)
    private Integer uId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;


    /* It indicates that each User is associated with exactly one Profile and vice versa.
    * - mappedBy = "user" points to the user field in the ProfileEntity class, indicating that the Profile class contains the foreign key for this relationship.
    * - cascade = CascadeType.ALL means that if if you save or delete a User, the associated Profile will also be saved or deleted automatically.
    * - orphanRemoval = true means that if a User is deleted or its association with the Profile is removed, the Profile will also be deleted from the database.
    *  */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

//    @OneToMany(mappedBy = "user")
//    @JsonManagedReference(value = "user")
//    private List<CalorieTrack> calorieTrackLists;

}

