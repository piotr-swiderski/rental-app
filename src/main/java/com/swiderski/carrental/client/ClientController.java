package com.swiderski.carrental.client;

import com.swiderski.carrental.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car-rental-api/client")
public class ClientController extends AbstractController<ClientService, ClientDto> {

    @Autowired
    public ClientController(ClientService service) {
        super(service);
    }
}
