package com.revature.controllers;

import com.revature.dto.ProfileDTO;
import com.revature.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;
    
    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint working";
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO){

        // Get the new createdProfile from the profileService.createProfile() method which returns a profileDTO object.
        ProfileDTO createdProfileDTO = profileService.createProfile(profileDTO);
        return new ResponseEntity<>(createdProfileDTO, HttpStatus.CREATED);

    }

    @GetMapping("/{pId}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable int pId){

        // Call the profileService.getProfile() method to return the individual profile.
        ProfileDTO profileDTO = profileService.getProfile(pId);

        return ResponseEntity.ok(profileDTO);
    }

    @PutMapping("/{pId}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable int pId, @RequestBody ProfileDTO profileDTO){
        ProfileDTO updatedProfile = profileService.updateProfile(pId, profileDTO);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{pId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int pId){
        profileService.deleteProfile(pId);
        return ResponseEntity.noContent().build();
    }
}
