package com.crud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String orderReference;
    private String loadingPlace;
    private String deliveryPlace;
    private LocalDate loadingDate;
    private LocalDate deliveryDate;
    private Boolean completed;
}
