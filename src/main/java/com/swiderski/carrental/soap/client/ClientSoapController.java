package com.swiderski.carrental.soap.client;

import com.swiderski.carrental.soap.SoapEndpoint;
import com.swiderski.carrental.soap.SoapService;
import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientClient;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientListRequest;
import com.swiderski.rental_service.schema.client.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.jws.WebService;

@Controller
@SoapEndpoint(publish = "/client")
@WebService(endpointInterface = "com.swiderski.rental_service.schema.client.ClientClient")
public class ClientSoapController implements ClientClient, SoapService {

    @Autowired
    private ClientSoapService clientSoapService;

    @Override
    public Client addClient(Client clientToAdd) {
        return clientSoapService.add(clientToAdd);
    }

    @Override
    public Client deleteClient(ClientDeleteRequest deleteRequest) {
        return clientSoapService.delete(deleteRequest);
    }

    @Override
    public Client getClient(ClientRequest request) {
        return clientSoapService.get(request);
    }

    @Override
    public ClientList getAllClients(ClientListRequest pageableRequest) {
        return clientSoapService.getAll(pageableRequest);
    }
}
