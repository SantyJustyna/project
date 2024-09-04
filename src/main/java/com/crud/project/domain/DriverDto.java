package com.crud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DriverDto {
    private Long id;
    private String name;
    private String surname;
}
