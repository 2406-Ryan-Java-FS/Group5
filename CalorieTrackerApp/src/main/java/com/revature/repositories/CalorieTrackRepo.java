package com.revature.repositories;

import com.revature.models.CalorieTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalorieTrackRepo extends JpaRepository<CalorieTrack, Integer> {
    Optional<CalorieTrack> findById(int cId);

    @Query("""
            select ct from CalorieTrack ct
            inner join fetch ct.user u
            inner join fetch ct.food f
            where u.uId = :uId
            """)
    List<CalorieTrack> findAllByUser_uId(@Param("uId") int uId);

    @Query("""
            select ct from CalorieTrack ct
            inner join fetch ct.user u
            inner join fetch ct.food f
            where u.uId = :uId and ct.logDate = :logDate
            """)
    List<CalorieTrack> findAllByUser_uIdAndLogDate(@Param("uId") int uId, @Param("logDate") LocalDate logDate);

    @Query("""
            select ct from CalorieTrack ct
            inner join fetch ct.user u
            inner join fetch ct.food f
            where u.uId = :uId and ct.logDate between :logDateStart and :logDateEnd
            """)
    List<CalorieTrack> findAllByUser_uIdAndLogDateBetween(
            @Param("uId") int uId,
            @Param("logDateStart") LocalDate logDateStart,
            @Param("logDateEnd") LocalDate logDateEnd);
//    Optional<CalorieTrack> findById(int cId);
//
//    @Query("""
//            select ct.cId, ct.serving, ct.logDate,
//            u.uId, u.username, u.password,
//            f.fId, f.foodName, f.calories
//            from calorieTrack ct
//            inner join user u on ct.uId = u.uId
//            inner join food f on ct.fId = f.fId
//            where ct.uId=?1
//            """)
//    List<CalorieTrack> findAllByUser_uId(int uId);
//
//
//    @Query("""
//            select ct.cId, ct.serving, ct.logDate,
//            u.uId, u.username, u.password,
//            f.fId, f.foodName, f.calories
//            from calorieTrack ct
//            inner join user u on ct.uId = u.uId
//            inner join food f on ct.fId = f.fId
//            where ct.uId=?1 and logDate=?2
//            """)
//    List<CalorieTrack> findAllByUser_uIdAndLogDate(int uId, LocalDate logDate);
//
//    @Query("""
//            select ct.cId, ct.serving, ct.logDate,
//            u.uId, u.username, u.password,
//            f.fId, f.foodName, f.calories
//            from calorieTrack ct
//            inner join user u on ct.uId = u.uId
//            inner join food f on ct.fId = f.fId
//            where ct.uId=?1 and logDate between ?2 and ?3
//            """)
//    List<CalorieTrack> findAllByUser_uIdAndLogDateBetween(
//           int uId,
//           LocalDate logDateStart,
//           LocalDate logDateEnd);
}
