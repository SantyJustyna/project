package com.crud.project.service;

import com.crud.project.controller.VehicleNotFoundException;
import com.crud.project.domain.Vehicle;
import com.crud.project.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(final Long vehicleId) throws VehicleNotFoundException {
        return vehicleRepository.findById(vehicleId).orElseThrow(VehicleNotFoundException::new);
    }

    public Vehicle saveVehicle(final Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(final Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }
}