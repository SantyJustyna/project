package com.crud.project.service;

import com.crud.project.controller.ClientNotFoundException;
import com.crud.project.domain.Client;
import com.crud.project.repository.ClientRepository;
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

@ExtendWith(MockitoExtension.class)
public class ClientServiceTestSuite {
    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void testGetAllClients() {
        // Given
        Client client = new Client();
        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));

        // When
        List<Client> clients = clientService.getAllClients();

        // Then
        assertNotNull(clients);
        assertEquals(1, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClientById() throws ClientNotFoundException {
        // Given
        Long clientId = 1L;
        Client client = new Client();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // When
        Client result = clientService.getClientById(clientId);

        // Then
        assertNotNull(result);
        assertEquals(client, result);
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void testGetClientByIdThrowsException() {
        // Given
        Long clientId = 1L;
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ClientNotFoundException.class, () -> clientService.getClientById(clientId));
        verify(clientRepository, times(1)).findById(clientId);
    }

    @Test
    void testSaveClient() {
        // Given
        Client client = new Client();
        when(clientRepository.save(client)).thenReturn(client);

        // When
        Client result = clientService.saveClient(client);

        // Then
        assertNotNull(result);
        assertEquals(client, result);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testDeleteClient() {
        // Given
        Long clientId = 1L;
        doNothing().when(clientRepository).deleteById(clientId);

        // When
        clientService.deleteClient(clientId);

        // Then
        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
