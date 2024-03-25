package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer merchantId;
    @NotNull(message = "stock should not be empty")
    @Min(value = 10,message = "minimum stock at start should be 10")
    @Column(columnDefinition = "int not null check(stock>=10)")
    private Integer stock;
}
