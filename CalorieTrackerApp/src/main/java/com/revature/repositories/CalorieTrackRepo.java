package com.revature.repositories;

import com.revature.models.CalorieTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalorieTrackRepo extends JpaRepository<CalorieTrack, Integer> {
    Optional<CalorieTrack> findById(int cId);

    List<CalorieTrack> findAllByUserId(int uId);

    List<CalorieTrack> findAllByUserIdAndLogDate(int uId, LocalDate logDate);

    List<CalorieTrack> findAllByUserAndDateTimeBetween(
            int uId,
            LocalDate logDateStart,
            LocalDate logDateEnd);
}
