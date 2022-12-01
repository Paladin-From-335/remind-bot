package com.example.remindbot.repo;

import com.example.remindbot.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.user_timezone FROM user_table u WHERE u.user_id = :id", nativeQuery = true)
    String getUserTimezone(Long id);

    @Query(value = "UPDATE user_table SET user_timezone =:userTimezone WHERE user_id =:id", nativeQuery = true)
    User setTimezone(String userTimezone, Long id);
}
