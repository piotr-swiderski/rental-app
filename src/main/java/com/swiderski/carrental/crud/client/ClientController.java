package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/clients"})
public class ClientController extends AbstractController<ClientService, ClientDto, ClientParam> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService service, ClientService clientService) {
        super(service);
        this.clientService = clientService;
    }
}
