package com.swiderski.carrental.soap.client.simple;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.carrental.soap.client.ClientWebMapper;
import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientListRequest;
import com.swiderski.rental_service.schema.client.ClientRequest;
import com.swiderski.rental_service.schema.client.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEndpoint {

    private final ClientService clientService;
    private final ClientWebMapper clientWebMapper;

    private static final String NAMESPACE_URI = "http://swiderski.com/rental-service/schema/client";

    @Autowired
    public ClientEndpoint(ClientService clientService, ClientWebMapper clientWebMapper) {
        this.clientService = clientService;
        this.clientWebMapper = clientWebMapper;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ClientRequest")
    @ResponsePayload
    public Client getClient(@RequestPayload ClientRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        long carId = request.getId();

        ClientDto clientDto = clientService.getById(carId);

        Client clientResponse = objectFactory.createClient();
        ClientData clientData = clientWebMapper.toWebData(clientDto);
        clientResponse.setClient(clientData);

        return clientResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ClientDeleteRequest")
    @ResponsePayload
    public Client deleteClient(@RequestPayload ClientDeleteRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        long clientId = request.getId();

        ClientDto clientDto = clientService.delete(clientId);
        ClientData clientData = clientWebMapper.toWebData(clientDto);

        Client client = objectFactory.createClient();
        client.setClient(clientData);

        return client;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Client")
    @ResponsePayload
    public Client addClient(@RequestPayload Client clientRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        ClientData clientData = clientRequest.getClient();

        ClientDto clientDto = clientWebMapper.toDto(clientData);
        ClientDto savedClient = clientService.save(clientDto);

        Client client = objectFactory.createClient();
        ClientData clientDataResponse = clientWebMapper.toWebData(savedClient);
        client.setClient(clientDataResponse);

        return client;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ClientListRequest")
    @ResponsePayload
    public ClientList getClientList(@RequestPayload ClientListRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();

        PageRequest pageable = clientWebMapper.toPageRequest(request.getPageable());
        ClientParam clientParam = clientWebMapper.toClientParam(request.getClientFilter());

        Page<ClientDto> page = clientService.getAll(clientParam, pageable);
        PageableXml clientPageable = clientWebMapper.toWebPageable(page);


        ClientList clientList = objectFactory.createClientList();
        clientList.setPage(clientPageable);

        return clientList;
    }

}
