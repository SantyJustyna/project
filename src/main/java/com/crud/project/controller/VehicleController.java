package com.crud.project.controller;

import com.crud.project.domain.VehicleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/vehicles")
public class VehicleController {
    @GetMapping
    public List<VehicleDto> getVehicles() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{vehicleId}")
    public VehicleDto getVehicle(Long vehicleId) {
        return new VehicleDto(1L, "GDA41322", "frigo", LocalDate.now());
    }

    @DeleteMapping(value = "{vehicleId}")
    public void deleteVehicle(Long vehicleId) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public VehicleDto updateVehicle(VehicleDto vehicleDto) {
        return new VehicleDto(1L, "ZZZ726563", "frigo", LocalDate.now());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createVehicle(VehicleDto vehicleDto) {

    }
}
