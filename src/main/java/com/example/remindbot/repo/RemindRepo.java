package com.example.remindbot.repo;

import com.example.remindbot.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RemindRepo extends JpaRepository<Reminder, Long> {
}
