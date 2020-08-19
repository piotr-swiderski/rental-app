package com.swiderski.carrental.soapClient.client;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.soap.client.ClientWebMapper;
import com.swiderski.carrental.soapClient.abstraction.CommonSoapClient;
import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientFilter;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientListRequest;
import com.swiderski.rental_service.schema.client.ClientRequest;
import com.swiderski.rental_service.schema.client.ClientSOAP;
import com.swiderski.rental_service.schema.client.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageRequestXml;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientSoapClient implements CommonSoapClient<ClientDto, ClientParam> {

    private final ClientSOAP clientSoapProxy;
    private final ObjectFactory clientObjectFactory;
    private final ClientWebMapper clientWebMapper;

    public ClientSoapClient(@Qualifier("clientProxy") ClientSOAP carSoapProxy, ObjectFactory clientObjectFactory, ClientWebMapper clientWebMapper) {
        this.clientSoapProxy = carSoapProxy;
        this.clientObjectFactory = clientObjectFactory;
        this.clientWebMapper = clientWebMapper;
    }

    public Page<ClientDto> getAll(ClientParam clientParam, Pageable pageable) {
        ClientListRequest clientListRequest = clientObjectFactory.createClientListRequest();

        ClientFilter clientFilter = clientWebMapper.toClientFiler(clientParam);
        PageRequestXml pageableXml = clientWebMapper.toPageRequestXml(pageable);
        clientListRequest.setClientFilter(clientFilter);
        clientListRequest.setPageRequest(pageableXml);

        ClientList clients = clientSoapProxy.getAllClients(clientListRequest);

        List<ClientData> clientData = clientWebMapper.toDataList(clients.getPage().getContent());
        List<ClientDto> pageList = clientWebMapper.toDtoList(clientData);
        return new PageImpl<>(pageList, pageable, clients.getPage().getNumberOfElements());
    }

    @Override
    public ClientDto delete(long id) {
        ClientDeleteRequest deleteRequest = clientObjectFactory.createClientDeleteRequest();
        deleteRequest.setId(id);

        Client client = clientSoapProxy.deleteClient(deleteRequest);
        return clientWebMapper.toDto(client.getClient());
    }

    @Override
    public ClientDto getById(long id) {
        ClientRequest clientRequest = clientObjectFactory.createClientRequest();
        clientRequest.setId(id);

        Client client = clientSoapProxy.getClient(clientRequest);
        return clientWebMapper.toDto(client.getClient());
    }

    @Override
    public ClientDto add(ClientDto dto) {
        Client clientToAdd = clientObjectFactory.createClient();

        ClientData clientData = clientWebMapper.toWebData(dto);
        clientToAdd.setClient(clientData);

        Client client = clientSoapProxy.addClient(clientToAdd);
        return clientWebMapper.toDto(client.getClient());
    }

    @Override
    public ClientDto update(ClientDto dto, long id) {
        dto.setId(id);
        return add(dto);
    }
}
