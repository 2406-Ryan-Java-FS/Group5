package com.revature.controllers;

import com.revature.dto.ProfileDTO;
import com.revature.dto.UserDTO;
import com.revature.models.User;
import com.revature.services.ProfileService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users/{uId}/profiles")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;
    
    @Autowired
    public ProfileController(ProfileService profileService, UserService userService){
        this.profileService = profileService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@PathVariable int uId, @RequestBody ProfileDTO profileDTO){

        UserDTO userDTO = userService.getUserById(uId);

        // Check if the user's role is equal to "USER", if not, return a HTTPStatus Forbidden.
        if (!userDTO.getRole().equals("USER")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        // Get the new createdProfile from the profileService.createProfile() method which returns a profileDTO object.
        ProfileDTO createdProfileDTO = profileService.createProfile(uId, profileDTO);

        return new ResponseEntity<>(createdProfileDTO, HttpStatus.CREATED);

    }

    // THIS WILL ONLY BE ACCESSED BY THE ADMIN
    @GetMapping("/{pId}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable int pId){

        // Call the profileService.getProfile() method to return the individual profile.
        ProfileDTO profileDTO = profileService.getProfile(pId);

        return ResponseEntity.ok(profileDTO);
    }


    @PutMapping("/{pId}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable int uId, @PathVariable int pId, @RequestBody ProfileDTO profileDTO){

        UserDTO userDTO = userService.getUserById(uId);

        // Check if the user's role is equal to "USER", if not, return a HTTPStatus Forbidden.
        if (!userDTO.getRole().equals("USER")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        ProfileDTO updatedProfile = profileService.updateProfile(uId, pId, profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{pId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int uId, @PathVariable int pId){
        profileService.deleteProfile(uId, pId);
        return ResponseEntity.noContent().build();
    }
}
