package com.crud.project.mapper;

import com.crud.project.domain.Client;
import com.crud.project.domain.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
public class ClientMapperTestSuite {
    @Autowired
    private ClientMapper mapper;

    @Test
    public void shouldMapToClient() {
        //Given
        ClientDto clientDto = new ClientDto(12L, "test name", "test address", "wp@wp.pl", "PL0");

        //When
        Client client = mapper.mapToClient(clientDto);

        //Then
        assertEquals("test name", client.getName());
    }

    @Test
    public void shouldMapToClientDto() {
        //Given
        Client client = new Client(12L, "test name", "test address", "wp@wp.pl","PL0000");

        //When
        ClientDto clientDto = mapper.mapToClientDto(client);

        //Then
        assertEquals("test address", clientDto.getAddress());
    }

    @Test
    public void shouldMapToClientDtoList() {
        //Given
        List<Client> clientList = List.of(new Client(13L, "name1", "address1", "wp@wp.pl", "VAT1"),
                                          new Client(14L, "name2", "address2", "wp@wp.pl", "VAT2"));

        //When
        List<ClientDto> clientDtoList = mapper.mapToClientDtoList(clientList);

        //Then
        assertEquals(2, clientDtoList.size());
        assertEquals("name2", clientDtoList.get(1).getName());
    }
}
