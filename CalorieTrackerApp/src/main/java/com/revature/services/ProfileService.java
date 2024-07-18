package com.revature.services;

import java.util.List;

import com.revature.dto.ProfileDTO;
import com.revature.dto.UserDTO;

public interface ProfileService {
    public ProfileDTO createProfile(Integer uId, ProfileDTO profileDTO);
    public ProfileDTO getProfile(Integer pId);
    public ProfileDTO updateProfile(Integer uId, Integer pId, ProfileDTO profileDTO);
    //public ProfileDTO deleteProfile(int pId);
    public void deleteProfile(Integer uId, Integer pId);

}
