package com.crud.project.service;

import com.crud.project.controller.ClientNotFoundException;
import com.crud.project.domain.Client;
import com.crud.project.notification.ClientObserver;
import com.crud.project.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(final Long clientId) throws ClientNotFoundException {
        return clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);
    }

    public Client saveClient(final Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(final Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
