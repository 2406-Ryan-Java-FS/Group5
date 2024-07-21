package com.revature.services;

import com.revature.dto.ProfileDTO;

import java.util.List;

public interface ProfileService {
    public ProfileDTO createProfile(Integer uId, ProfileDTO profileDTO);

    public List<ProfileDTO> getAllProfiles(Integer uId);
    public ProfileDTO getProfile(Integer pId);
    public ProfileDTO getProfileByUserId(Integer uId);
    public ProfileDTO updateProfile(Integer uId, Integer pId, ProfileDTO profileDTO);
    //public ProfileDTO deleteProfile(int pId);
    public void deleteProfile(Integer uId, Integer pId);


}
