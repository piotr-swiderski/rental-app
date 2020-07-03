package com.swiderski.carrental.dto;

import com.swiderski.carrental.entity.Car;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.entity.EngineType;
import com.swiderski.carrental.mapper.ClientMapper;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;

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

    public Client getClient() {
        Car car = new Car(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        HashSet<Car> cars = new HashSet<>(Collections.singletonList(car));
        return new Client(1, "jan", "kowalski", "jan@gmail.com", "dasds", "123");
    }

    public ClientDto getClientDto() {
        CarDto carDto = new CarDto(1, "toyota", "red", EngineType.diesel, 2020, 2000);
        HashSet<CarDto> carDtos = new HashSet<>(Collections.singletonList(carDto));
        return new ClientDto(1, "jan", "kowalski", "jan@gmail.com", 200, carDtos);
    }
}