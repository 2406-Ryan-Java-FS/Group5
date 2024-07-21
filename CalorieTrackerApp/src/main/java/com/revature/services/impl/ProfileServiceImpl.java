package com.revature.services.impl;

import com.revature.dto.ProfileDTO;
import com.revature.exceptions.profileexceptions.InvalidProfileDetailsException;
import com.revature.exceptions.profileexceptions.UserProfileAlreadyExistsException;
import com.revature.exceptions.profileexceptions.UserProfileDoesNotExistException;
import com.revature.exceptions.userexceptions.UserNotFoundException;
import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.repositories.ProfileRepo;
import com.revature.repositories.UserRepo;
import com.revature.services.ProfileService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional // ensures that all database operations within the annotated method are executed in a single transaction.
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepo profileRepo;
    private final UserRepo userRepo;

    // I'm creating a dummy user with an ID of 1. The dummy user will be linked to this profile.
    //private static final int uId = 1;

    @Autowired
    public ProfileServiceImpl(final UserRepo userRepo, final ProfileRepo profileRepo){
        this.profileRepo = profileRepo;
        this.userRepo = userRepo;
    }

    @Override
    public ProfileDTO createProfile(Integer uId, ProfileDTO profileDTO){

        // Check if a registered user from the DB exists and if they do not exist, throw an exception.
        User userEntity = userRepo.findById(uId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // First we validate the profile details entered by the user.
        // if details are wrong, then throw an InvalidProfileDetailsException
        validateProfileDetails(profileDTO);

        // Check if a profile by this user already exists in the DB.
       if (profileRepo.findByUser_uId(uId).isPresent()){
           throw new UserProfileAlreadyExistsException("A profile for this user already exists.");
       }
//        if (profileRepo.findById(pId).isPresent()){
//            throw new UserProfileAlreadyExistsException("A profile for this user already exists.");
//        }
        // Takes in the request body(profile details) and converts it to profileEntity to be stored in DB.
        Profile profileEntity = convertProfileDTOProfileEntity(profileDTO);

        // Setting the user found in the DB as the one responsible for creating this profile
        profileEntity.setUser(userEntity);

        // Save the profileEntity into the DB using the profileRepo.save() method
        Profile savedProfileEntity = profileRepo.save(profileEntity);

        // convert the savedProfileEntity object to a DTO object and return it.
        return convertProfileEntityToProfileDTO(savedProfileEntity);
    }

    @Override
    public ProfileDTO getProfile(Integer pId) {

        // check if the profile is present in the DB, otherwise throw a UserProfileDoesNotExistException
        Profile profileEntity = profileRepo.findById(pId)
                .orElseThrow(() -> new UserProfileDoesNotExistException("User profile not found."));

        return convertProfileEntityToProfileDTO(profileEntity);

    }

    @Override
    public ProfileDTO updateProfile(Integer uId, Integer pId, ProfileDTO profileDTO) {

        // Check if the profile to be updated exists in the DB.
//        Profile existingProfileEntity = profileRepo.findByPIdAndUser_UId(pId, uId)
//                .orElseThrow(() -> new UserProfileDoesNotExistException("User profile not found."));
        Profile existingProfileEntity = profileRepo.findById(pId)
                .orElseThrow(() -> new UserProfileDoesNotExistException("User profile not found."));

        // validate the updated profile details.
        validateProfileDetails(profileDTO);

        // Update the existingProfileEntity with new details.
        // The updateProfileEntityFromProfileDTO takes in two parameters, the existingProfileEntity object to be updated, and the profileDTO object with the new details.
        updateProfileEntityFromProfileDTO(existingProfileEntity, profileDTO);

        // save the newly updated profileEntity in the DB.
        Profile updatedProfileEntity = profileRepo.save(existingProfileEntity);

        return convertProfileEntityToProfileDTO(updatedProfileEntity);
    }

    @Override
    public void deleteProfile(Integer pId, Integer uId) {

        // check if the profile exists in the DB using its ID. If it doesn't exist then throw the UserProfileDoesNotExistException.
        if (!profileRepo.existsById(pId)) {
            throw new UserProfileDoesNotExistException("User profile not found");
        }
//       Profile profileEntity = profileRepo.findByPIdAndUser_UId(pId, uId)
//               .orElseThrow(() -> new UserProfileDoesNotExistException("User profile not found"));

        //otherwise delete the profile from the DB using profileRepo.deleteById() method.
         profileRepo.deleteById(pId);
//        profileRepo.delete(profileEntity);
    }

    // ACCESSED BY THE ADMIN ONLY
    @Override
    public ProfileDTO getProfileByUserId(Integer uId) {
        // Check if profile exists in the DB using the user's id, otherwise return an exception.
        Profile profileEntity = profileRepo.findByUser_uId(uId)
                .orElseThrow(() -> new UserProfileDoesNotExistException("Profile not found for user with id: " + uId));

        return convertProfileEntityToProfileDTO(profileEntity);
    }

    @Override
    public List<ProfileDTO> getAllProfiles(@PathVariable Integer uId) {
        List<Profile> allProfilesEntity = profileRepo.findAll();
        List<ProfileDTO> allProfilesDTO = new ArrayList<>();

        for(Profile profileEntity: allProfilesEntity){
            ProfileDTO profileDTO = convertProfileEntityToProfileDTO(profileEntity);
            allProfilesDTO.add(profileDTO);
        }

        return allProfilesDTO;
    }

    // Utility methods for our implementation class
    private Profile convertProfileDTOProfileEntity(ProfileDTO profileDTO){
        return Profile.builder()
                .gender(profileDTO.getGender())
                .height(profileDTO.getHeight())
                .weight(profileDTO.getWeight())
                .activity(profileDTO.getActivity())
                .calorieGoal(profileDTO.getCalorieGoal())
                .build();
    }

    private ProfileDTO convertProfileEntityToProfileDTO(Profile profileEntity){
        return ProfileDTO.builder()
                .pId(profileEntity.getPId())
                .gender(profileEntity.getGender())
                .height(profileEntity.getHeight())
                .weight(profileEntity.getWeight())
                .activity(profileEntity.getActivity())
                .calorieGoal(profileEntity.getCalorieGoal())
//                // include the dummy user's id when converting the profileEntity object back to a DTO object.
//                .uId(uId)
                .uId(profileEntity.getUser().getUId())
                .build();
    }

    // Checks and validates the profile details entered by the user.
    // If the profile details, such as height, weight, activity and CalorieGoal details are less than or equal to 0, throw an InvalidProfileDetailsException.
    private void validateProfileDetails(ProfileDTO profileDTO) {

//        if (profileDTO.getHeight() == null || profileDTO.getWeight() == null ||
//                profileDTO.getActivity() == null || profileDTO.getCalorieGoal() == null) {
//            throw new IncompleteProfileDetailsException("Profile details are incomplete.");
//        }

        if (profileDTO.getHeight() <= 0 || profileDTO.getWeight() <= 0 ||
                profileDTO.getActivity() < 1 || profileDTO.getActivity() > 5 ||
                profileDTO.getCalorieGoal() <= 0) {
            throw new InvalidProfileDetailsException("Invalid profile details provided");
        }
    }

    // Updates the profileEntity object with details from the profileDTO.
    // Takes two arguments, the profileEntity object to be updated and the profileDTO object with the new details.
    private void updateProfileEntityFromProfileDTO(Profile profileEntity, ProfileDTO profileDTO){
        profileEntity.setGender(profileDTO.getGender());
        profileEntity.setHeight(profileDTO.getHeight());
        profileEntity.setWeight(profileDTO.getWeight());
        profileEntity.setActivity(profileDTO.getActivity());
        profileEntity.setCalorieGoal(profileDTO.getCalorieGoal());
    }
}
