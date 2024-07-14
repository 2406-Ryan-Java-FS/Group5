package com.revature.services;

import com.revature.dto.ProfileDTO;

public interface ProfileService {
    public ProfileDTO createProfile(ProfileDTO profileDTO);
    public ProfileDTO getProfile(int pId);
    public ProfileDTO updateProfile(int pId, ProfileDTO profileDTO);
    //public ProfileDTO deleteProfile(int pId);
    public void deleteProfile(int pId);


}
