package com.crud.project.mapper;

import com.crud.project.domain.Client;
import com.crud.project.domain.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientMapper {
    public Client mapToClient(final ClientDto clientDto) {
        return new Client(
                clientDto.getId(),
                clientDto.getName(),
                clientDto.getAddress(),
                clientDto.getMail(),
                clientDto.getVatNumber()
        );
    }

    public ClientDto mapToClientDto(final Client client) {
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getAddress(),
                client.getMail(),
                client.getVatNumber()
        );
    }

    public List<ClientDto> mapToClientDtoList(final List<Client> clientList) {
        return clientList.stream()
                .map(this::mapToClientDto)
                .toList();
    }
}
