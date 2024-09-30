package com.crud.project.service;

import com.crud.project.controller.VehicleNotFoundException;
import com.crud.project.domain.Vehicle;
import com.crud.project.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTestSuite {
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Test
    void testGetAllVehicles() {
        // Given
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.findAll()).thenReturn(Collections.singletonList(vehicle));

        // When
        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        // Then
        assertNotNull(vehicles);
        assertEquals(1, vehicles.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void testGetVehicleById() throws VehicleNotFoundException {
        // Given
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        // When
        Vehicle result = vehicleService.getVehicleById(vehicleId);

        // Then
        assertNotNull(result);
        assertEquals(vehicle, result);
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    void testGetVehicleByIdThrowsException() {
        // Given
        Long vehicleId = 1L;
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.getVehicleById(vehicleId));
        verify(vehicleRepository, times(1)).findById(vehicleId);
    }

    @Test
    void testSaveVehicle() {
        // Given
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        // When
        Vehicle result = vehicleService.saveVehicle(vehicle);

        // Then
        assertNotNull(result);
        assertEquals(vehicle, result);
        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    void testDeleteVehicle() {
        // Given
        Long vehicleId = 1L;
        doNothing().when(vehicleRepository).deleteById(vehicleId);

        // When
        vehicleService.deleteVehicle(vehicleId);

        // Then
        verify(vehicleRepository, times(1)).deleteById(vehicleId);
    }
}
