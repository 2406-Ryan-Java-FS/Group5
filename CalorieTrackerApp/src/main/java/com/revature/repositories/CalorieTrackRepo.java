package com.revature.repositories;

import com.revature.models.CalorieTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalorieTrackRepo extends JpaRepository<CalorieTrack, Integer> {
}
