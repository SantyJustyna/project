package com.crud.project.controller;

import com.crud.project.domain.Vehicle;
import com.crud.project.domain.VehicleDto;
import com.crud.project.mapper.VehicleMapper;
import com.crud.project.service.VehicleService;
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
@RequestMapping("/v1/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService service;

    @Autowired
    private VehicleMapper mapper;

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getVehicles() {
        List<Vehicle> vehicles = service.getAllVehicles();
        return ResponseEntity.ok(mapper.mapToVehicleDtoList(vehicles));
    }

    @GetMapping(value = "{vehicleId}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable Long vehicleId) throws VehicleNotFoundException {
        return ResponseEntity.ok(mapper.mapToVehicleDto(service.getVehicleById(vehicleId)));
    }

    @DeleteMapping(value = "{vehicleId}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long vehicleId) {
        service.deleteVehicle(vehicleId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = mapper.mapToVehicle(vehicleDto);
        Vehicle savedVehicle = service.saveVehicle(vehicle);
        return ResponseEntity.ok(mapper.mapToVehicleDto(savedVehicle));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createVehicle(@RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = mapper.mapToVehicle(vehicleDto);
        service.saveVehicle(vehicle);
        return ResponseEntity.ok().build();
    }
}
