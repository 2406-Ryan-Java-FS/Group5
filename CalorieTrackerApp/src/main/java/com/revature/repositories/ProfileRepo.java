package com.revature.repositories;

import com.revature.models.Profile;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
    Optional<Profile> findById(int pId);

    //Optional<Profile> findByUserId(int uId);

//    List<Profile> findAll();
}
