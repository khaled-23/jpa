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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username should not be empty")
    @Size(min = 6,max = 40,message = "username should be at least 6 characters")
    @Column(columnDefinition = "varchar(40) not null")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 7,max = 20,message = "password should be at least 7 characters and 20 maximum")
    @Pattern(regexp = "^((?=\\S*?[a-zA-Z])(?=\\S*?[0-9]).{7,})\\S$",message = "password must contain digit and characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "enter a valid email")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;
    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "^(Admin|Customer)$",message = "role should be either Admin or Customer")
    @Column(columnDefinition = "varchar(8) not null check(role='Admin' or role='Customer')")
    private String role;
    @NotNull(message = "balance should not be empty")
    @PositiveOrZero(message = "balance can not be a negative number")
    @Column(columnDefinition = "int not null default 0 check(balance>=0)")
    private Double balance;
}
