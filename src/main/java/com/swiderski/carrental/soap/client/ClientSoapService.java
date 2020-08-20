package com.swiderski.carrental.soap.client;

import com.swiderski.rental_service.schema.client.Client;
import com.swiderski.rental_service.schema.client.ClientDeleteRequest;
import com.swiderski.rental_service.schema.client.ClientList;
import com.swiderski.rental_service.schema.client.ClientListRequest;
import com.swiderski.rental_service.schema.client.ClientRequest;

public interface ClientSoapService {

    Client add(Client clientToAdd);

    Client delete(ClientDeleteRequest deleteRequest);

    Client get(ClientRequest request);

    ClientList getAll(ClientListRequest pageableRequest);
}
