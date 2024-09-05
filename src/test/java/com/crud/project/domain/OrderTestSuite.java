package com.crud.project.domain;

import com.crud.project.repository.ClientRepository;
import com.crud.project.repository.DriverRepository;
import com.crud.project.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class OrderTestSuite {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void shouldCreateAndFindOrder() {
        //Given
        Order order = new Order();

        //When
        orderRepository.save(order);
        Long orderId = order.getId();
        Optional<Order> createdOrder = orderRepository.findById(orderId);

        //Then
        assertTrue(createdOrder.isPresent());
    }

    @Test
    public void shouldUpdateOrder() {
        //Given
        Order order = new Order();
        order.setCompleted(true);
        orderRepository.save(order);

        //When
        Boolean updatedStatus = false;
        order.setCompleted(updatedStatus);
        Order updatedOrder = orderRepository.save(order);

        //Then
        assertEquals(updatedStatus, updatedOrder.getCompleted());
    }

    @Test
    public void shouldDeleteOrder() {
        //Given
        Driver driver = new Driver();
        driverRepository.save(driver);
        Long driverId = driver.getId();

        Client client = new Client();
        clientRepository.save(client);
        Long clientId = client.getId();

        Order order = new Order();
        order.setClient(client);
        order.setDriver(driver);
        orderRepository.save(order);
        Long orderId = order.getId();

        //When
        orderRepository.deleteById(orderId);
        Optional<Order> deletedOrder = orderRepository.findById(orderId);
        Optional<Driver> createdDriver = driverRepository.findById(driverId);
        Optional<Client> createdClient = clientRepository.findById(clientId);

        //Then
        assertFalse(deletedOrder.isPresent());
        assertTrue(createdDriver.isPresent());
        assertTrue(createdClient.isPresent());
    }
}
