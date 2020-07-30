package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:/application.properties")
@RequestMapping(value = {"${rest.api.version}/clients"})
public class ClientController extends AbstractController<ClientService, ClientDto> {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService service, ClientService clientService) {
        super(service);
        this.clientService = clientService;
    }

    @GetMapping()
    public Page<ClientDto> getAll(@ModelAttribute ClientParam clientParam,
                                  @PageableDefault Pageable pageable) {
        return clientService.getAll(clientParam, pageable);
    }

}
