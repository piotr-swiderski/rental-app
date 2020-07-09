package com.swiderski.carrental.services.impl;

import com.swiderski.carrental.dto.ClientDto;
import com.swiderski.carrental.entity.Client;
import com.swiderski.carrental.exception.NotFoundException;
import com.swiderski.carrental.mapper.ClientMapper;
import com.swiderski.carrental.repository.ClientRepository;
import com.swiderski.carrental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientDto save(ClientDto client) {
        Client clientMapped = clientMapper.clientDtoDtoClient(client);
        Client pagedResult = clientRepository.save(clientMapped);
        return clientMapper.clientToClientDto(pagedResult);
    }

    @Override
    public List<ClientDto> getAll(int pageNo, int pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        List<Client> all = clientRepository.findAll(paging).getContent();
        return clientMapper.clientListToClientDtoList(all);
    }

    @Override
    public ClientDto update(long id, ClientDto client) {
        client.setId(id);
        return save(client);
    }

    @Override
    public ClientDto getById(long id) {
        Client client = getClient(id);
        return clientMapper.clientToClientDto(client);
    }

    @Override
    public ClientDto delete(long id) {
        Client client = getClient(id);
        clientRepository.delete(client);
        return clientMapper.clientToClientDto(client);
    }

    private Client getClient(long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, Client.class.getSimpleName()));
    }
}
