package com.swiderski.carrental.dto;

import com.swiderski.carrental.crud.client.Client;
import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientMapper;
import org.junit.jupiter.api.Test;

import static com.swiderski.carrental.utils.Utils.getClient;
import static com.swiderski.carrental.utils.Utils.getClientDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class ClientMapperTest {

    ClientMapper clientMapper = ClientMapper.INSTANCE;

    @Test
    public void shouldMapClientToClientDto() {
        //given
        Client client = getClient();
        ClientDto clientDto = getClientDto();
        //when
        ClientDto clientDtoMapper = clientMapper.toDto(client);
        //then
        assertEquals(clientDto, clientDtoMapper);
    }

    @Test
    public void shouldMapClientDtoToClient() {
        //given
        ClientDto clientDto = getClientDto();
        Client client = getClient();
        //when
        Client clientMap = clientMapper.fromDto(clientDto);
        //then
        assertEquals(client, clientMap);
    }

    @Test
    public void shouldAssertFalseWhenMapClientDtoToClient() {
        //given
        ClientDto clientDto = getClientDto();
        Client client = getClient();
        client.setName("john");
        //when
        Client clientMap = clientMapper.fromDto(clientDto);
        //then
        assertNotEquals(client, clientMap);
    }

    @Test
    public void shouldAssertFalseWhenMapClientToClientDto() {
        //given
        ClientDto clientDto = getClientDto();
        Client client = getClient();
        client.setName("john");
        //when
        ClientDto clientDtoMap = clientMapper.toDto(client);
        //then
        assertNotEquals(clientDto, clientDtoMap);
    }
}