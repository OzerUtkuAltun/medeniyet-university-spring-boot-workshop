package com.ozerutkualtun.medeniyetdemo.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String name;
    private BigDecimal price;
    private LocalDate expiryDate;
    private boolean isDeleted;
}
