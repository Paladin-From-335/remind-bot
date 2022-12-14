package com.example.remindbot.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "remind_table")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "remind_id")
    private Long remindId;

    @Column(columnDefinition = "remind_text")
    private String remindText;

    @JoinColumn(name = "user_id", table = "user_table")
    @Column(columnDefinition = "chat_id")
    private Long chatId;

    @Column(columnDefinition = "created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "created_to")
    private LocalDateTime createdTo;

    public Reminder(String remindText, Long chatId,
                    LocalDateTime createdAt, LocalDateTime createdTo) {
        this.remindText = remindText;
        this.chatId = chatId;
        this.createdAt = createdAt;
        this.createdTo = createdTo;
    }
}
