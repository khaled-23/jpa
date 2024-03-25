package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min=4, message = "name should be more than 3 characters")
    @Pattern(regexp = "^[a-zA-Z_ ]+$", message = "name should only contain letters")
    @Column(columnDefinition = "varchar(40) not null")
    private String name;
    @NotNull(message = "price should not be empty")
    @Positive(message = "price should be a positive number")
    @Column(columnDefinition = "int not null check(price>0)")
    private Double price;
    @NotNull(message = "category id should not be empty")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
}
