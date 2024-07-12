package com.revature.services;

import com.revature.dto.ProfileDTO;

public interface ProfileService {
    public ProfileDTO createProfile(ProfileDTO profileDTO);
    public ProfileDTO getProfile(ProfileDTO profileDTO);
    public ProfileDTO updateProfile(ProfileDTO profileDTO);
    public ProfileDTO deleteProfile(ProfileDTO profileDTO);


}
