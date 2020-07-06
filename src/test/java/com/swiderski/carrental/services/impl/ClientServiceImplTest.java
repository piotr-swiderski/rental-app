package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.repository.ClientRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.swiderski.carrental.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveClient_savedClient() {
        //given
        Client client = getClient();
        when(clientRepository.save(client)).thenReturn(client);
        //when
        Client savedClient = clientService.saveClient(client);
        //then
        assertEquals(client, savedClient);
    }

    @Test
    public void getAllClients() {
        //given
        List<Client> clients = getClients();
        when(clientRepository.findAll()).thenReturn(clients);
        //when
        Set<Client> returnedClients = clientService.getAllClients();
        //then
        assertEquals(2, returnedClients.size());
    }

    @Test
    public void updateClient_shouldChangeClientName() {
        //given
        Client client = getClient();
        Client modifiedClient = getClient();
        modifiedClient.setName("XXX");
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        Client updateClient = clientService.updateClient(clientId, modifiedClient);
        //then
        assertEquals(modifiedClient, updateClient);
    }

    @Test
    public void getClientById_shouldReturnedClientById() {
        //given
        Client client = getClient();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        Client clientById = clientService.getClientById(clientId);
        //then
        assertEquals(client, clientById);
    }

    @Test
    public void getClientById_shouldThrowNotFoundException() {
        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());
        String expectedMessages = "Could not find " + Client.class.getSimpleName() + " with id = " + clientId;

        Throwable exception = assertThrows(NotFoundException.class, () -> clientService.getClientById(clientId));
        assertEquals(expectedMessages, exception.getMessage());
    }

    @Test
    public void deleteClient() {
        //given
        Client client = getClient();
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        //when
        Client deletedClient = clientService.deleteClient(clientId);
        //then
        assertEquals(client, deletedClient);
    }
}