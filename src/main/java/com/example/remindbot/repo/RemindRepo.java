package com.example.remindbot.repo;

import com.example.remindbot.model.entity.Reminder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RemindRepo extends JpaRepository<Reminder, Long> {

    @Query(value = "DELETE FROM remind_table WHERE created_to <:currentDate", nativeQuery = true)
    void deleteOld(LocalDate currentDate);

    @Query(value = "SELECT * FROM remind_table WHERE created_to =:today", nativeQuery = true)
    List<Reminder> actualReminders(LocalDate today);
}
