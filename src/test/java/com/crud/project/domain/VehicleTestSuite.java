package com.crud.project.domain;

import com.crud.project.repository.DriverRepository;
import com.crud.project.repository.OrderRepository;
import com.crud.project.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class VehicleTestSuite {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldCreateAndFindVehicle() {
        //Given
        Vehicle vehicle = new Vehicle();

        //When
        vehicleRepository.save(vehicle);
        Long vehicleId = vehicle.getId();
        Optional<Vehicle> createdVehicle = vehicleRepository.findById(vehicleId);

        //Then
        assertTrue(createdVehicle.isPresent());
    }

    @Test
    public void shouldUpdateVehicle() {
        //Given
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateNumber("GDA0000");
        vehicleRepository.save(vehicle);

        //When
        String updatedPlateNumber = "GDA11111";
        vehicle.setPlateNumber(updatedPlateNumber);
        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        //Then
        assertEquals(updatedPlateNumber, updatedVehicle.getPlateNumber());
    }

    @Test
    public void shouldDeleteVehicle() {
        //Given
        Order order = new Order();
        orderRepository.save(order);
        Long orderId = order.getId();

        Driver driver = new Driver();
        driverRepository.save(driver);
        Long driverId = driver.getId();

        Vehicle vehicle = new Vehicle();
        vehicle.setOrders(List.of(order));
        vehicle.setDrivers(List.of(driver));
        vehicleRepository.save(vehicle);
        Long vehicleId = vehicle.getId();

        //When
        vehicleRepository.deleteById(vehicleId);
        Optional<Vehicle> deletedVehicle = vehicleRepository.findById(vehicleId);
        Optional<Driver> createdDriver = driverRepository.findById(driverId);
        Optional<Order> createdOrder = orderRepository.findById(orderId);

        //Then
        assertFalse(deletedVehicle.isPresent());
        assertTrue(createdDriver.isPresent());
        assertTrue(createdOrder.isPresent());
    }
}
