package com.example.remindbot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "remind_table")
public class Remind {
    @Id
    @Column(columnDefinition = "remind_id")
    private Long remindId;

    @Column(columnDefinition = "remind_text")
    private String remindText;

    private Long chatId;

    @Column(columnDefinition = "created_at")
    private String createdTo;

    @Column(columnDefinition = "created_to")
    private String createdAt;

    public Remind(String remindText, Long chatId) {
        this.remindText = remindText;
        this.chatId = chatId;
    }

}
