package com.crud.project.service;

import com.crud.project.controller.DriverNotFoundException;
import com.crud.project.domain.Driver;
import com.crud.project.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(final Long driverId) throws DriverNotFoundException {
        return driverRepository.findById(driverId).orElseThrow(DriverNotFoundException::new);
    }

    public Driver saveDriver(final Driver driver) {
        return driverRepository.save(driver);
    }

    public void deleteDriver(final Long driverId) {
        driverRepository.deleteById(driverId);
    }
}