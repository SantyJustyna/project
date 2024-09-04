package com.crud.project.controller;

import com.crud.project.domain.ClientDto;
import com.crud.project.domain.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {
    @GetMapping
    public List<ClientDto> getClients() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{clientId}")
    public ClientDto getClient(Long clientId) {
        return new ClientDto(1L, "Bosch", "ul.Słoneczna 3, 99-100 Łódź", "PL8291641817");
    }

    @DeleteMapping(value = "{clientId}")
    public void deleteClient(Long clientId) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto updateClient(ClientDto clientDto) {
        return new ClientDto(1L, "Indesit", "ul.Słoneczna 3, 99-100 Łódź", "PL8291641817");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createClient(ClientDto clientDto) {

    }
}
