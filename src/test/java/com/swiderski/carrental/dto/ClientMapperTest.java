package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.mapper.ClientMapper;
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
        ClientDto clientDtoMapper = clientMapper.clientToClientDto(client);
        //then
        assertEquals(clientDto, clientDtoMapper);
    }

    @Test
    public void shouldMapClientDtoToClient() {
        //given
        ClientDto clientDto = getClientDto();
        Client client = getClient();
        //when
        Client clientMap = clientMapper.clientDtoDtoClient(clientDto);
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
        Client clientMap = clientMapper.clientDtoDtoClient(clientDto);
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
        ClientDto clientDtoMap = clientMapper.clientToClientDto(client);
        //then
        assertNotEquals(clientDto, clientDtoMap);
    }
}