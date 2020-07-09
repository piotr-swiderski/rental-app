package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.mapper.ClientMapper;
import com.swiderski.carrental.repository.ClientRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.swiderski.carrental.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    @Spy
    ClientMapper clientMapper = ClientMapper.INSTANCE;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveClient_savedClient() {
        //given
        ClientDto clientDto = getClientDto();
        Client client = getClient();
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        //when
        ClientDto savedClient = clientService.save(clientDto);
        //then
        assertEquals(clientDto, savedClient);
    }

    @Test
    public void getAllClients() {
        //given
        List<ClientDto> clientsDto = getClientsDto();
        List<Client> clients = getClients();
        when(clientRepository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(clients));
        //when
        Collection<ClientDto> returnedClients = clientService.getAll(pageNo, pageSize, sortBy);
        //then
        assertEquals(2, returnedClients.size());
    }

    @Test
    public void updateClient_shouldChangeClientName() {
        //given
        Client client = getClient();
        Client modifiedClient = getClient();
        modifiedClient.setName("XXX");
        ClientDto modifiedClientDto = getClientDto();
        modifiedClientDto.setName("XXX");
        when(clientRepository.save(any(Client.class))).thenReturn(modifiedClient);
        //when
        ClientDto updateClient = clientService.update(clientId, modifiedClientDto);
        //then
        assertEquals(modifiedClientDto, updateClient);
    }

    @Test
    public void getClientById_shouldReturnedClientById() {
        //given
        Client client = getClient();
        ClientDto clientDto = getClientDto();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        ClientDto clientById = clientService.getById(clientId);
        //then
        assertEquals(clientDto, clientById);
    }

    @Test
    public void getClientById_shouldThrowNotFoundException() {
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + Client.class.getSimpleName() + " with id = " + clientId;

        Throwable exception = assertThrows(NotFoundException.class, () -> clientService.getById(clientId));
        assertEquals(expectedMessages, exception.getMessage());
    }

    @Test
    public void deleteClient() {
        //given
        Client client = getClient();
        ClientDto clientDto = getClientDto();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        ClientDto deletedClient = clientService.delete(clientId);
        //then
        assertEquals(clientDto, deletedClient);
    }
}