package com.swiderski.carrental.services;

import com.swiderski.carrental.entity.Client;

public interface ClientService {

    Client saveClient(Client client);

    Client updateClient(long id, Client client);

    Client getClientById(long id);

    Client deleteClient(long id);
}
