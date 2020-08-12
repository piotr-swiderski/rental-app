package com.swiderski.carrental.soapClient.client;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.client.ClientDto;
import com.swiderski.carrental.crud.client.ClientParam;
import com.swiderski.carrental.soapClient.abstraction.AbstractSoapClientController;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("v1/soap/clients")
public class ClientSoapController extends AbstractSoapClientController<ClientDto, ClientSoapClient> {

    private final ClientSoapClient carClientProxy;

    public ClientSoapController(ClientSoapClient clientSoapClient) {
        super(clientSoapClient);
        this.carClientProxy = clientSoapClient;
    }

    @GetMapping
    @ApiPageable
    public Page<ClientDto> getAll(@ModelAttribute ClientParam clientParam,
                                  @ApiIgnore @NotNull Pageable pageable) {
        return carClientProxy.getAll(clientParam, pageable);
    }
}
