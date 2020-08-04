package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.exception.NotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

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
        System.out.println(savedClient);
        System.out.println(clientDto);
        assertEquals(clientDto, savedClient);
    }

    @Test
    public void getAllClients() {
        //given
        List<ClientDto> clientsDto = getClientsDto();
        List<Client> clients = getClients();
        when(clientRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(new PageImpl<>(clients));
        //when
        Page<ClientDto> returnedClients = clientService.getAll(new ClientParam(), pageRequest);
        //then
        assertEquals(2, returnedClients.getTotalElements());
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
        String expectedMessages = "Could not find " + "entity" + " with id = " + clientId;

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