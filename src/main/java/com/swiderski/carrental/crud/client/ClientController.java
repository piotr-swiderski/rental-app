package com.swiderski.carrental.crud.client;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.crud.abstraction.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

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
    @ApiPageable
    public Page<ClientDto> getAll(@Valid @ModelAttribute ClientParam clientParam,
                                  @ApiIgnore @NonNull Pageable pageable) {
        return clientService.getAll(clientParam, pageable);
    }

}
