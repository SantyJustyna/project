package com.crud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String plateNumber;
    private String type;
    private LocalDate nextInspectionDate;
}
