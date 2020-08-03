package com.swiderski.carrental.soap;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientRequest;
import com.swiderski.rental_service.schema.client.ListPageRequest;
import com.swiderski.rental_service.schema.client.ObjectFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ClientEndpoint {

    private final ClientService clientService;
    private final ClientWebMapper clientWebMapper;

    private static final String NAMESPACE_URI = "http://swiderski.com/rental-service/schema/client";

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
        ClientData clientData = clientWebMapper.toClientData(clientDto);
        clientResponse.setClient(clientData);

        return clientResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ClientDeleteRequest")
    @ResponsePayload
    public Client deleteClient(@RequestPayload ClientDeleteRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        long clientId = request.getId();

        ClientDto clientDto = clientService.delete(clientId);
        ClientData clientData = clientWebMapper.toClientData(clientDto);

        Client client = objectFactory.createClient();
        client.setClient(clientData);

        return client;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "Client")
    @ResponsePayload
    public Client addClient(@RequestPayload Client clientRequest) {
        ObjectFactory objectFactory = new ObjectFactory();
        ClientData clientData = clientRequest.getClient();

        ClientDto clientDto = clientWebMapper.toClientDto(clientData);
        ClientDto savedClient = clientService.save(clientDto);

        Client client = objectFactory.createClient();
        ClientData clientDataResponse = clientWebMapper.toClientData(savedClient);
        client.setClient(clientDataResponse);

        return client;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PageRequest")
    @ResponsePayload
    public ClientList getClientList(@RequestPayload ListPageRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();

        int pageNo = request.getPageNo();
        int pageSize = request.getPageSize();
        String sortBy = request.getSortBy();

        List<ClientDto> allWithoutSpec = clientService
                .getAll(new ClientParam(),
                        PageRequest.of(pageNo, pageSize, Sort.by(sortBy))).getContent();
        List<ClientData> clientDataList = clientWebMapper.toClientDataList(allWithoutSpec);

        ClientList clientList = objectFactory.createClientList();
        clientList.getClient().addAll(clientDataList);

        return clientList;
    }

}
