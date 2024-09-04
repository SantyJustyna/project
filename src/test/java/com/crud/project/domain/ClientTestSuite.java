package com.crud.project.domain;

import com.crud.project.repository.ClientRepository;
import com.crud.project.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
public class ClientTestSuite {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldCreateAndFindClient() {
        //Given
        Client client = new Client();

        //When
        clientRepository.save(client);
        Long id = client.getId();
        Optional<Client> createdClient = clientRepository.findById(id);

        //Then
        assertTrue(createdClient.isPresent());
    }

    @Test
    public void shouldUpdateClient() {
        //Given
        Client client = new Client();
        client.setName("Bosch");
        client.setAddress("Łódź");
        client.setVatNumber("PL88888888888");
        clientRepository.save(client);

        //When
        String updatedVat = "PL000";
        client.setVatNumber(updatedVat);
        Client updatedClient = clientRepository.save(client);

        //Then
        assertEquals(updatedVat, updatedClient.getVatNumber());
    }

    @Test
    public void shouldDeleteClient() {
        //Given
        Client client = new Client();
        clientRepository.save(client);
        Long clientId = client.getId();

        Order order = new Order();
        order.setClient(client);
        orderRepository.save(order);
        Long orderId = order.getId();

        //When
        clientRepository.deleteById(clientId);
        Optional<Client> deletedClient = clientRepository.findById(clientId);
        Optional<Order> createdOrder = orderRepository.findById(orderId);

        //Then
        assertFalse(deletedClient.isPresent());
        assertTrue(createdOrder.isPresent());
    }
}
