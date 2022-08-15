package com.example.remindbot.model;

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
    private Long remindId;
    private String remindText;
    private Long chatId;
    private String createdAt;
    private String createdTo;

    public Remind(String remindText, Long chatId) {
        this.remindText = remindText;
        this.chatId = chatId;
    }
}
