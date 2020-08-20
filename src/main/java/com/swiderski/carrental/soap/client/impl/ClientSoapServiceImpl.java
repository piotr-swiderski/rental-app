package com.swiderski.carrental.soap.client.impl;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.carrental.soap.client.ClientSoapService;
import com.swiderski.carrental.soap.client.ClientWebMapper;
import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientListRequest;
import com.swiderski.rental_service.schema.client.ClientRequest;
import com.swiderski.rental_service.schema.client.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ClientSoapServiceImpl implements ClientSoapService {

    private final ClientService clientService;
    private final ClientWebMapper clientWebMapper;
    private final ObjectFactory objectFactory = new ObjectFactory();

    public ClientSoapServiceImpl(ClientService clientService, ClientWebMapper clientWebMapper) {
        this.clientService = clientService;
        this.clientWebMapper = clientWebMapper;
    }

    public Client add(Client clientToAdd) {
        ClientData clientData = clientToAdd.getClient();

        ClientDto clientDto = clientWebMapper.toDto(clientData);
        ClientDto savedClient = clientService.save(clientDto);

        Client client = objectFactory.createClient();
        ClientData clientDataResponse = clientWebMapper.toWebData(savedClient);
        client.setClient(clientDataResponse);

        return client;
    }

    public Client delete(ClientDeleteRequest deleteRequest) {
        long clientId = deleteRequest.getId();

        ClientDto clientDto = clientService.delete(clientId);
        ClientData clientData = clientWebMapper.toWebData(clientDto);

        Client client = objectFactory.createClient();
        client.setClient(clientData);

        return client;
    }


    public Client get(ClientRequest request) {
        long carId = request.getId();

        ClientDto clientDto = clientService.getById(carId);

        Client clientResponse = objectFactory.createClient();
        ClientData clientData = clientWebMapper.toWebData(clientDto);
        clientResponse.setClient(clientData);

        return clientResponse;
    }

    public ClientList getAll(ClientListRequest pageableRequest) {
        PageRequest pageable = clientWebMapper.toPageRequest(pageableRequest.getPageable());
        ClientParam clientParam = clientWebMapper.toClientParam(pageableRequest.getClientFilter());

        Page<ClientDto> page = clientService.getAll(clientParam, pageable);
        PageableXml clientPageable = clientWebMapper.toWebPageable(page);


        ClientList clientList = objectFactory.createClientList();
        clientList.setPage(clientPageable);

        return clientList;
    }
}
