package com.crud.project.controller;

import com.crud.project.domain.Client;
import com.crud.project.domain.ClientDto;
import com.crud.project.domain.OrderDto;
import com.crud.project.mapper.ClientMapper;
import com.crud.project.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/clients")
public class ClientController {
    @Autowired
    private ClientMapper mapper;

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientDto>> getClients() {
        List<Client> clients = service.getAllClients();
        return ResponseEntity.ok(mapper.mapToClientDtoList(clients));
    }

    @GetMapping(value = "{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long clientId) throws ClientNotFoundException {
        return ResponseEntity.ok(mapper.mapToClientDto(service.getClientById(clientId)));
    }

    @DeleteMapping(value = "{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        service.deleteClient(clientId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto) {
        Client client = mapper.mapToClient(clientDto);
        Client savedClient = service.saveClient(client);
        return ResponseEntity.ok(mapper.mapToClientDto(savedClient));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createClient(@RequestBody ClientDto clientDto) {
        Client client = mapper.mapToClient(clientDto);
        service.saveClient(client);
        return ResponseEntity.ok().build();
    }
}
