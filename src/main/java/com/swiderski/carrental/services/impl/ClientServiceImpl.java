package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.repository.ClientRepository;
import com.swiderski.carrental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Set<Client> getAllClients() {
        return new HashSet<>(clientRepository.findAll());
    }

    @Override
    public Client updateClient(long id, Client client) {
        getOptionalClient(id).ifPresent(c -> {
            client.setId(id);
            clientRepository.save(client);
        });
        return client;
    }

    @Override
    public Client getClientById(long id) {
        return getOptionalClient(id).orElseThrow(EntityNotFoundException::new);
    } //todo

    @Override
    public Client deleteClient(long id) {
        Client clientById = getClientById(id);
        clientRepository.delete(clientById);
        return clientById;
    }

    private Optional<Client> getOptionalClient(long id) {
        return clientRepository.findById(id);
    }
}
