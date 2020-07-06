package com.swiderski.carrental.controllers;

import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.mapper.ClientMapper;
import com.swiderski.carrental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("car-rental-api/client")
@CrossOrigin
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper = ClientMapper.INSTANCE;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public Set<ClientDto> getAllClients() {
        Set<Client> allClients = clientService.getAllClients();
        return clientMapper.clientSetToClientDtoSet(allClients);
    }

    @GetMapping("/{id}")
    public ClientDto getClient(@PathVariable long id) {
        Client clientById = clientService.getClientById(id);
        return clientMapper.clientToClientDto(clientById);
    }

    @PostMapping("/add")
    public ClientDto addClient(@RequestBody ClientDto clientDto) {
        Client client = clientMapper.clientDtoDtoClient(clientDto);
        clientService.saveClient(client);
        return clientDto;
    }

    @DeleteMapping("/delete/{id}")
    public ClientDto deleteClient(@PathVariable long id) {
        Client client = clientService.deleteClient(id);
        return clientMapper.clientToClientDto(client);
    }

    @PutMapping("/update/{id}")
    public ClientDto updateClient(@PathVariable long id, ClientDto clientDto) {
        Client client = clientMapper.clientDtoDtoClient(clientDto);
        Client clientUpdated = clientService.updateClient(id, client);
        return clientMapper.clientToClientDto(clientUpdated);
    }
}
