package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;
    @NotNull(message = "sender id should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer senderId;
    @NotNull(message = "receiverId should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer receiverId;
    @NotEmpty(message = "message should not be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String message;
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime dateTime;

}
