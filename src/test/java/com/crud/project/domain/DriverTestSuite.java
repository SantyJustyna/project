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
public class DriverTestSuite {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldCreateAndFindDriver() {
        //Given
        Driver driver = new Driver();

        //When
        driverRepository.save(driver);
        Long driverId = driver.getId();
        Optional<Driver> createdDriver = driverRepository.findById(driverId);

        //Then
        assertTrue(createdDriver.isPresent());
    }

    @Test
    public void shouldUpdateDriver() {
        //Given
        Driver driver = new Driver();
        driver.setSurname("Kowalski");
        driverRepository.save(driver);

        //When
        String updatedSurname = "Nowak";
        driver.setSurname(updatedSurname);
        Driver updatedDriver = driverRepository.save(driver);

        //Then
        assertEquals(updatedSurname, updatedDriver.getSurname());
    }

    @Test
    public void shouldDeleteDriver() {
        //Given
        Vehicle vehicle = new Vehicle();
        vehicleRepository.save(vehicle);
        Long vehicleId = vehicle.getId();

        Order order = new Order();
        orderRepository.save(order);
        Long orderId = order.getId();

        Driver driver = new Driver();
        driver.setOrders(List.of(order));
        driver.setVehicles(List.of(vehicle));
        driverRepository.save(driver);
        Long driverId = driver.getId();

        //When
        driverRepository.deleteById(driverId);
        Optional<Driver> deletedDriver = driverRepository.findById(driverId);
        Optional<Vehicle> createdVehicle = vehicleRepository.findById(vehicleId);
        Optional<Order> createdOrder = orderRepository.findById(orderId);

        //Then
        assertFalse(deletedDriver.isPresent());
        assertTrue(createdVehicle.isPresent());
        assertTrue(createdOrder.isPresent());
    }
}
