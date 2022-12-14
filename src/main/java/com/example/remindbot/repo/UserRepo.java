package com.example.remindbot.repo;

import com.example.remindbot.model.entity.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Transactional
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.user_timezone FROM user_table u WHERE u.user_id = :id", nativeQuery = true)
    String getUserTimezone(Long id);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user_table SET user_timezone =:userTimezone WHERE user_id =:id", nativeQuery = true)
    void setTimezone(String userTimezone, Long id);
}
