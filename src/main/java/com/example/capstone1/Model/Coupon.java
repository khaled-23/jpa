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
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;
    @NotNull
    private Integer merchantStockId;
    @NotEmpty(message = "coupon key should not be empty")
    @Size(min = 4,max = 10, message = "key should be 4 at minimum and 10 at maximum")
    @Column(columnDefinition = "varchar(10) not null")
    private String couponKey;
    @NotNull(message = "percent should not be empty")
    @Min(value = 10,message = "minimum discount percent is 10")
    @Max(value = 100,message = "maximum discount percent is 100")
    @Positive(message = "discount percent should be positive")
    @Column(columnDefinition = "int not null check(percent>=10 and percent<=100) ")
    private Integer percent;
    @NotNull(message = "coupon uses should not be empty")
    @Positive(message = "coupon uses should be positive")
    @Min(value = 1,message = "minimum uses should be 1")
    @Max(value = 10,message = "maximum uses should be 10")
    @Column(columnDefinition = "int not null check(uses>=1 and uses<=10)")
    private Integer uses;
}
