package com.swiderski.carrental.soap.client;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.crud.client.ClientService;
import com.swiderski.carrental.soap.SoapEndpoint;
import com.swiderski.carrental.soap.SoapService;
import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientData;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientListRequest;
import com.swiderski.rental_service.schema.client.ClientRequest;
import com.swiderski.rental_service.schema.client.ClientSOAP;
import com.swiderski.rental_service.schema.client.ObjectFactory;
import com.swiderski.rental_service.schema.pageable.PageableXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

@Service
@SoapEndpoint(publish = "/client")
@WebService(endpointInterface = "com.swiderski.rental_service.schema.client.ClientSOAP",
        serviceName = "ClientSoapService",
        targetNamespace = "http://swiderski.com/rental-service/schema/client",
        portName = "ClientPort")
@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class ClientSoapService implements ClientSOAP, SoapService {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientWebMapper clientWebMapper;
    private final ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public Client addClient(Client clientToAdd) {
        ClientData clientData = clientToAdd.getClient();

        ClientDto clientDto = clientWebMapper.toDto(clientData);
        ClientDto savedClient = clientService.save(clientDto);

        Client client = objectFactory.createClient();
        ClientData clientDataResponse = clientWebMapper.toWebData(savedClient);
        client.setClient(clientDataResponse);

        return client;
    }

    @Override
    public Client deleteClient(ClientDeleteRequest deleteRequest) {
        long clientId = deleteRequest.getId();

        ClientDto clientDto = clientService.delete(clientId);
        ClientData clientData = clientWebMapper.toWebData(clientDto);

        Client client = objectFactory.createClient();
        client.setClient(clientData);

        return client;
    }


    @Override
    public Client getClient(ClientRequest request) {
        long carId = request.getId();

        ClientDto clientDto = clientService.getById(carId);

        Client clientResponse = objectFactory.createClient();
        ClientData clientData = clientWebMapper.toWebData(clientDto);
        clientResponse.setClient(clientData);

        return clientResponse;
    }

    @Override
    public ClientList getAllClients(ClientListRequest pageableRequest) {
        PageRequest pageable = clientWebMapper.toPageRequest(pageableRequest.getPageRequest());
        ClientParam clientParam = clientWebMapper.toClientParam(pageableRequest.getClientFilter());

        Page<ClientDto> page = clientService.getAll(clientParam, pageable);
        PageableXml clientPageable = clientWebMapper.toWebPageable(page);


        ClientList clientList = objectFactory.createClientList();
        clientList.setPage(clientPageable);

        return clientList;
    }
}
