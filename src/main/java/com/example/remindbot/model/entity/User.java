package com.example.remindbot.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
    @Id
    @Column(columnDefinition = "user_id")
    private Long userId;

    @Column(columnDefinition = "user_timezone")
    private String userTimezone;

    @Column(columnDefinition = "is_turn_on")
    private Integer isTurnOn = 1;

    @Column(columnDefinition = "repeats")
    private Integer repeats = 3;

    public User(Long userId) {
        this.userId = userId;
    }
}
