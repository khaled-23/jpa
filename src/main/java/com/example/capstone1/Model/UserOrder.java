package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @NotNull(message = "user id should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime dateTime;
    @NotNull(message = "product id should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer productId;
}
