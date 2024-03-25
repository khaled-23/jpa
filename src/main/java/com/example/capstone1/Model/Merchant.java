package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "merchant name should not be empty")
    @Size(min = 4, max = 40, message = "merchant name should be at least 4 characters")
    @Pattern(regexp = "^[a-zA-Z]+", message = "merchant name should only contain letters")
    @Column(columnDefinition = "varchar(40) not null")
    private String name;
}
