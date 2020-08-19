package com.swiderski.carrental.soapClient.client;

import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/soap/clients")
public class ClientSoapController extends AbstractSoapClientController<ClientDto, ClientSoapClient, ClientParam> {


    public ClientSoapController(ClientSoapClient clientSoapClient) {
        super(clientSoapClient);
    }

}
