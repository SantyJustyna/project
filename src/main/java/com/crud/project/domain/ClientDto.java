package com.crud.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String name;
    private String address;
    private String vatNumber;
}
