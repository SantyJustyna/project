package com.crud.project.controller;

import com.crud.project.domain.DriverDto;
import com.crud.project.domain.VehicleDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class DriverController {
    @GetMapping
    public List<DriverDto> getDrivers() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{driverId}")
    public DriverDto getDriver(Long driverId) {
        return new DriverDto(1L, "name", "surname");
    }

    @DeleteMapping(value = "{driverId}")
    public void deleteVehicle(Long vehicleId) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DriverDto updateDriver(DriverDto driverDto) {
        return new DriverDto(1L, "name2", "surname2");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDriver(DriverDto driverDto) {

    }
}
