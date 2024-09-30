package com.crud.project.service;

import com.crud.project.controller.DriverNotFoundException;
import com.crud.project.domain.Driver;
import com.crud.project.repository.DriverRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTestSuite {
    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    @Test
    void testGetAllDrivers() {
        // Given
        Driver driver = new Driver();
        when(driverRepository.findAll()).thenReturn(Collections.singletonList(driver));

        // When
        List<Driver> drivers = driverService.getAllDrivers();

        // Then
        Assertions.assertNotNull(drivers);
        assertEquals(1, drivers.size());
        verify(driverRepository, times(1)).findAll();
    }

    @Test
    void testGetDriverById() throws DriverNotFoundException {
        // Given
        Long driverId = 1L;
        Driver driver = new Driver();
        when(driverRepository.findById(driverId)).thenReturn(Optional.of(driver));

        // When
        Driver result = driverService.getDriverById(driverId);

        // Then
        Assertions.assertNotNull(result);
        assertEquals(driver, result);
        verify(driverRepository, times(1)).findById(driverId);
    }

    @Test
    void testGetDriverByIdThrowsException() {
        // Given
        Long driverId = 1L;
        when(driverRepository.findById(driverId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(DriverNotFoundException.class, () -> driverService.getDriverById(driverId));
        verify(driverRepository, times(1)).findById(driverId);
    }

    @Test
    void testSaveDriver() {
        // Given
        Driver driver = new Driver();
        when(driverRepository.save(driver)).thenReturn(driver);

        // When
        Driver result = driverService.saveDriver(driver);

        // Then
        Assertions.assertNotNull(result);
        assertEquals(driver, result);
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testDeleteDriver() {
        // Given
        Long driverId = 1L;
        doNothing().when(driverRepository).deleteById(driverId);

        // When
        driverService.deleteDriver(driverId);

        // Then
        verify(driverRepository, times(1)).deleteById(driverId);
    }
}
