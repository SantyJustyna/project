package com.crud.project.controller;

import com.crud.project.domain.Driver;
import com.crud.project.domain.DriverDto;
import com.crud.project.domain.VehicleDto;
import com.crud.project.mapper.DriverMapper;
import com.crud.project.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/drivers")
public class DriverController {
    @Autowired
    private DriverMapper mapper;

    @Autowired
    private DriverService service;

    @GetMapping
    public ResponseEntity<List<DriverDto>> getDrivers() {
        List<Driver> drivers = service.getAllDrivers();
        return ResponseEntity.ok(mapper.mapToDriverDtoList(drivers));
    }

    @GetMapping(value = "{driverId}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Long driverId) throws DriverNotFoundException {
        return ResponseEntity.ok(mapper.mapToDriverDto(service.getDriverById(driverId)));
    }

    @DeleteMapping(value = "{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long driverId) {
        service.deleteDriver(driverId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverDto> updateDriver(@RequestBody DriverDto driverDto) {
        Driver driver = mapper.mapToDriver(driverDto);
        Driver savedDriver = service.saveDriver(driver);
        return ResponseEntity.ok(mapper.mapToDriverDto(savedDriver));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createDriver(@RequestBody DriverDto driverDto) {
        Driver driver = mapper.mapToDriver(driverDto);
        service.saveDriver(driver);
        return ResponseEntity.ok().build();
    }
}
